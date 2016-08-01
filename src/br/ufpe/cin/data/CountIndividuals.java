package br.ufpe.cin.data;

import java.util.Map;

public class CountIndividuals implements IndividualsCounter {

	@Override
	public int countIndividuals(InfectionUnit infectionUnit, Map<Integer, Integer> countedIndividuals) {
		int sum = 0;
		if(countedIndividuals.get(infectionUnit.owner) == null){
			++sum;
			countedIndividuals.put(infectionUnit.owner, 0);
		}
		
		if(infectionUnit.getMembers() != null) {
			for(int u = 0; u < infectionUnit.getMembers().length; u++) {
				if(countedIndividuals.get(infectionUnit.getMember(u)) == null){
					sum += 1;
					countedIndividuals.put(infectionUnit.getMember(u), 0);
				}
			}
		}
		return sum;
	}

}
