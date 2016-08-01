package br.ufpe.cin.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.joda.time.LocalDate;

public abstract class SumData {

	protected String sumInterval;

	protected IndividualsCounter counter;

	protected String filesPath;

	public abstract LocalDate getFirstDayInInterval(LocalDate data);

	public abstract LocalDate getNextInterval(LocalDate data);
	
	public InfectionUnitReader reader;

	public SumData(String sumInterval, IndividualsCounter counter, String path, InfectionUnitReader reader) {
		this.sumInterval = sumInterval;
		this.counter = counter;
		this.filesPath = path;
		this.reader = reader;
	}

	public Map<LocalDate, Integer> count(Map<Integer, InfectionUnit> projects) {
		InfectionUnit project;
		Map<Integer, Integer> accountedIndividuals = new HashMap<Integer, Integer>();
		Map<LocalDate, Integer> userCount = new HashMap<LocalDate, Integer>();
		Iterator<InfectionUnit> it = projects.values().iterator();
		Integer count = null;
		int quantity = 0;
		while (it.hasNext()) {
			quantity = 0;
			project = it.next();

			quantity = counter.countIndividuals(project, accountedIndividuals);

			LocalDate firstDay = getFirstDayInInterval(project.creationDate);
			count = userCount.get(firstDay);
			if (count != null) {
				count += quantity;
				userCount.put(firstDay, count);
			} else if (quantity > 0) {
				userCount.put(firstDay, quantity);
			}
		}
		return userCount;
	}

	public void analyze(String language, boolean printDateFile, boolean printDataFile) throws IOException {

		Util.print("************************************************************");
		Util.print("* starting processing of " + getSumInterval() + "for language " + language);
		Util.print("************************************************************");

		if (!printDataFile && !printDateFile) {
			Util.print("not saving any files, so I am not going to process at all");
		}

		Map<Integer, InfectionUnit> projects = new HashMap<Integer, InfectionUnit>();

		reader.readInfectionUnit(language, projects);

		Util.print(projects.size() + " project records read");

		Util.print("started reading members file...");

		reader.readMembers(projects);

		System.gc();

		Util.print("started counting members...");

		Map<LocalDate, Integer> memberCount = count(projects);

		Util.print("member count finished");
		Util.print("starting to write to the output file");

		saveResult(language, printDateFile, printDataFile, memberCount, sumInterval);

		Util.print("************************************************************");
		Util.print("* processing of " + getSumInterval() + " for language " + language + " finished");
		Util.print("************************************************************");
	}

	public void saveResult(String language, boolean writeDateFile, boolean writeDataFile,
			Map<LocalDate, Integer> memberCount, String sumInterval) throws IOException {
		ArrayList<Count> pq = new ArrayList<Count>();
		Set<Entry<LocalDate, Integer>> inputs = memberCount.entrySet();
		Iterator<Entry<LocalDate, Integer>> it2 = inputs.iterator();
		Entry<LocalDate, Integer> input = null;
		while (it2.hasNext()) {
			input = it2.next();
			pq.add(new Count(input.getKey(), input.getValue()));
		}

		FileWriter fw = new FileWriter(filesPath + "output_date_" + language + "_" + sumInterval + ".txt");
		BufferedWriter bw = new BufferedWriter(fw);

		FileWriter fw2 = new FileWriter(filesPath + "data_" + language + "_" + sumInterval + ".dat");
		BufferedWriter bw2 = new BufferedWriter(fw2);

		Collections.sort(pq);
		int sum = 0;
		int fileIndex = 1;
		LocalDate previous = null;
		for (int k = 0; k < pq.size(); k++) {
			while (previous != null && !getNextInterval(previous).isEqual(pq.get(k).getCreationDate())) {
				previous = getNextInterval(previous);

				if (writeDateFile) {
					bw.write(previous + "," + fileIndex + "," + sum);
					bw.newLine();
				}

				if (writeDataFile) {
					bw2.write(fileIndex + "	" + sum);
					bw2.newLine();
				}

				fileIndex++;
			}

			previous = pq.get(k).getCreationDate();
			sum += pq.get(k).getCount();

			if (writeDateFile) {
				bw.write(pq.get(k).getCreationDate() + "," + fileIndex + "," + sum);
				bw.newLine();
			}

			if (writeDataFile) {
				bw2.write(fileIndex + "	" + sum);
				bw2.newLine();
			}

			fileIndex++;
		}

		bw.close();
		fw.close();

		bw2.close();
		fw2.close();
	}

	public String getSumInterval() {
		return sumInterval;
	}

	public void setSumInterval(String sumInterval) {
		this.sumInterval = sumInterval;
	}

}
