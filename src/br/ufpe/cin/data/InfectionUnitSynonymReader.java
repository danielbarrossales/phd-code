package br.ufpe.cin.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface InfectionUnitSynonymReader {
	
	public void readInfectionUnit(String[] synonyms, Map<Integer, InfectionUnit> projects) throws FileNotFoundException, IOException;
	
	public void readMembers(Map<Integer, InfectionUnit> posts) throws FileNotFoundException, IOException;

}
