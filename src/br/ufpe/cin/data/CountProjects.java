package br.ufpe.cin.data;

import java.util.Map;

public class CountProjects implements IndividualsCounter {

	@Override
	public int countIndividuals(InfectionUnit unit, Map<Integer, Integer> countedIndividuals) {
		if(countedIndividuals.get(unit.id) == null){
			countedIndividuals.put(unit.id, 0);
			return 1;
		} else {
			return 0;
		}
	}

}
