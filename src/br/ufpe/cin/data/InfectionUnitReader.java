package br.ufpe.cin.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface InfectionUnitReader {
	
	public void readInfectionUnit(String language, Map<Integer, InfectionUnit> projects) throws FileNotFoundException, IOException;
	
	public void readMembers(Map<Integer, InfectionUnit> projects) throws FileNotFoundException, IOException;

}
