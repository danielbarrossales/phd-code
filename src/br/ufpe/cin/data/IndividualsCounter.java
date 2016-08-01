package br.ufpe.cin.data;

import java.util.Map;

public interface IndividualsCounter {
	
	int countIndividuals(InfectionUnit project, Map<Integer, Integer> countedIndividuals);

}
