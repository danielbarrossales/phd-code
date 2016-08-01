package br.ufpe.cin.stackoverflow;

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

import br.ufpe.cin.data.IndividualsCounter;
import br.ufpe.cin.data.Count;
import br.ufpe.cin.data.InfectionUnitReader;
import br.ufpe.cin.data.InfectionUnitSynonymReader;
import br.ufpe.cin.data.InfectionUnit;
import br.ufpe.cin.data.Util;

public abstract class SumDataStackOverflow {
	
	protected String sumInterval;

	protected IndividualsCounter counter;

	protected String filesDirectory;

	public abstract LocalDate getFirstDayInInterval(LocalDate date);

	public abstract LocalDate getNextInterval(LocalDate date);
	
	public InfectionUnitSynonymReader reader;

	public SumDataStackOverflow(String sumInterval, IndividualsCounter counter, String directory, InfectionUnitSynonymReader reader) {
		this.sumInterval = sumInterval;
		this.counter = counter;
		this.filesDirectory = directory;
		this.reader = reader;
	}

	public Map<LocalDate, Integer> count(Map<Integer, InfectionUnit> posts) {
		InfectionUnit project;
		Map<Integer, Integer> countedIndividuals = new HashMap<Integer, Integer>();
		Map<LocalDate, Integer> individualCount = new HashMap<LocalDate, Integer>();
		Iterator<InfectionUnit> it = posts.values().iterator();
		Integer count = null;
		int sum = 0;
		while (it.hasNext()) {
			sum = 0;
			project = it.next();

			sum = counter.countIndividuals(project, countedIndividuals);

			LocalDate firstDayInInterval = getFirstDayInInterval(project.creationDate);
			count = individualCount.get(firstDayInInterval);
			if (count != null) {
				count += sum;
				individualCount.put(firstDayInInterval, count);
			} else if (sum > 0) {
				individualCount.put(firstDayInInterval, sum);
			}
		}
		return individualCount;
	}

	public void analyze(String[] synonyms, boolean printDateFile, boolean printDataFile) throws IOException {

		Util.print("************************************************************");
		Util.print("* starting processing " + getSumInterval() + " for language " + synonyms[0]);
		Util.print("************************************************************");

		if (!printDataFile && !printDateFile) {
			Util.print("not saving any files, so I am not going to process at all");
		}

		Map<Integer, InfectionUnit> posts = new HashMap<Integer, InfectionUnit>();

		reader.readInfectionUnit(synonyms, posts);

		Util.print(posts.size() + " post records read");

		Util.print("starting to read users file...");

		reader.readMembers(posts);

		System.gc();

		Util.print("starting the user count...");

		Map<LocalDate, Integer> userCount = count(posts);

		Util.print("user count finished");
		Util.print("starting to save output");

		saveResult(synonyms, printDateFile, printDataFile, userCount, sumInterval);

		Util.print("************************************************************");
		Util.print("* processing of " + getSumInterval() + " for language " + synonyms[0] + " finished");
		Util.print("************************************************************");
	}

	public void saveResult(String[] synonyms, boolean printDateFile, boolean printDataFile,
			Map<LocalDate, Integer> contagemUsuarios, String intervaloAcumulo) throws IOException {
		ArrayList<Count> pq = new ArrayList<Count>();
		Set<Entry<LocalDate, Integer>> inputs = contagemUsuarios.entrySet();
		Iterator<Entry<LocalDate, Integer>> it2 = inputs.iterator();
		Entry<LocalDate, Integer> input = null;
		while (it2.hasNext()) {
			input = it2.next();
			pq.add(new Count(input.getKey(), input.getValue()));
		}

		FileWriter fw = new FileWriter(filesDirectory + "output_date_" + synonyms[0] + "_" + intervaloAcumulo + ".txt");
		BufferedWriter bw = new BufferedWriter(fw);

		FileWriter fw2 = new FileWriter(filesDirectory + "data_" + synonyms[0] + "_" + intervaloAcumulo + ".dat");
		BufferedWriter bw2 = new BufferedWriter(fw2);

		Collections.sort(pq);
		int sum = 0;
		int fileIndex = 1;
		LocalDate previous = null;
		for (int k = 0; k < pq.size(); k++) {
			while (previous != null && !getNextInterval(previous).isEqual(pq.get(k).getCreationDate())) {
				previous = getNextInterval(previous);

				if (printDateFile) {
					bw.write(previous + "," + fileIndex + "," + sum);
					bw.newLine();
				}

				if (printDataFile) {
					bw2.write(fileIndex + "	" + sum);
					bw2.newLine();
				}

				fileIndex++;
			}

			previous = pq.get(k).getCreationDate();
			sum += pq.get(k).getCount();

			if (printDateFile) {
				bw.write(pq.get(k).getCreationDate() + "," + fileIndex + "," + sum);
				bw.newLine();
			}

			if (printDataFile) {
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

	public void setIntervaloAcumulo(String sumInterval) {
		this.sumInterval = sumInterval;
	}

}
