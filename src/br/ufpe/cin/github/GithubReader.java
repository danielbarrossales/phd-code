package br.ufpe.cin.github;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.joda.time.LocalDate;

import br.ufpe.cin.data.InfectionUnitReader;
import br.ufpe.cin.data.Language;
import br.ufpe.cin.data.InfectionUnit;
import br.ufpe.cin.data.Util;

public class GithubReader implements InfectionUnitReader {

	@Override
	public void readInfectionUnit(String language, Map<Integer, InfectionUnit> projects)
			throws FileNotFoundException, IOException {
		BufferedReader br = null;
		FileReader fr = null;
		File f = null;
		String line = null;
		String[] splitLine = null;

		Util.print("Starting to read projects file...");
		fr = new FileReader(
				"/Users/emanoel/Google Drive/workspace-doutorado/Doutorado/src/br/ufpe/cin/github/projects.csv");
		br = new BufferedReader(fr);

		// read file line by line
		br.readLine(); // skip the first line
		int i = 0;
		Integer id = 0;
		while ((line = br.readLine()) != null) {
			splitLine = line.trim().split(",");
			if (splitLine[1].equals(language)) {
				id = Integer.parseInt(splitLine[0]);
				projects.put(id, new InfectionUnit(id, Language.getLanguageCode(splitLine[1]),
						new LocalDate(splitLine[2]), Integer.parseInt(splitLine[3])));
			}
			i++;
		}
		Util.print(i + " records (projects) processed");

		fr.close();
		br.close();
	}

	@Override
	public void readMembers(Map<Integer, InfectionUnit> projetos) throws FileNotFoundException, IOException {
		BufferedReader br;
		FileReader fr;
		String line;
		String[] splitLine;
		int i;
		Integer id;
		fr = new FileReader(
				"/Users/emanoel/Google Drive/workspace-doutorado/Doutorado/src/br/ufpe/cin/github/project_members.csv");
		br = new BufferedReader(fr);
		InfectionUnit project = null;
		i = 0;
		br.readLine(); // skip the first line
		while ((line = br.readLine()) != null) {
			splitLine = line.trim().split(",");
			id = Integer.parseInt(splitLine[0]);
			project = projetos.get(id);
			if (project != null) {
				project.setMembers(Util.toIntArray(splitLine));
			}
			i++;
		}
		Util.print(i + " records (members) processed");

		fr.close();
		br.close();
	}

}
