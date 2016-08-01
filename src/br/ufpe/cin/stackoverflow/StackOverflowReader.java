package br.ufpe.cin.stackoverflow;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.joda.time.LocalDate;

import br.ufpe.cin.data.InfectionUnit;
import br.ufpe.cin.data.InfectionUnitSynonymReader;
import br.ufpe.cin.data.Language;
import br.ufpe.cin.data.Util;

public class StackOverflowReader implements InfectionUnitSynonymReader {
	
	@Override
	public void readInfectionUnit(String[] synonyms, Map<Integer, InfectionUnit> posts)
			throws FileNotFoundException, IOException {
		BufferedReader br = null;
		FileReader fr = null;
		String line = null;
		String[] splitLine = null;

		Util.print("started reading posts file...");
		fr = new FileReader(
				"/Users/emanoel/Google Drive/workspace-doutorado/Doutorado/src/br/ufpe/cin/stackoverflow/posts.csv");
		br = new BufferedReader(fr);

		// read file line by line
		br.readLine(); // skip the first line
		int i = 0;
		Integer id = 0;
		boolean isNumber = false;
		while ((line = br.readLine()) != null) {
			splitLine = line.trim().split(",");
			isNumber = splitLine[3].chars().allMatch( Character::isDigit );
			if (exists(splitLine[1], synonyms) && isNumber) {//on stackoverflow a post has several tags, so we need only one of the synonyms to be tagged
				id = Integer.parseInt(splitLine[0]);
				posts.put(id, new InfectionUnit(id, Language.getLanguageCode(synonyms[0]),//the target language is the first one on the synonyms array
						getDate(splitLine[2]), Integer.parseInt(splitLine[3])));
			}
			i++;
		}
		Util.print(i + " records (posts) processed");

		fr.close();
		br.close();
	}

	@Override
	public void readMembers(Map<Integer, InfectionUnit> projetos) throws FileNotFoundException, IOException {
		// since a question only has one user, it does not make sense to think of other member for that question
		// this is important when the infection unit has more than one member
	}
	
	public boolean exists(String tagsPost, String[] languageSynonyms) {
		for(String synonym : languageSynonyms) {
			if(tagsPost.contains("<" + synonym + ">")) {
				return true;
			}
		}
		
		return false;
	}
	
	public LocalDate getDate(String dateHour) {
		String data = dateHour.replaceAll("\"", "");
		return new LocalDate(data.split(" ")[0]);
	}

}
