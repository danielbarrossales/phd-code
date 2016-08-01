package br.ufpe.cin.data;

import org.joda.time.LocalDate;

public class InfectionUnit {
	
	public int id;
	public int language;
	public LocalDate creationDate;
	public int owner;
	private int[] members;
	
	public InfectionUnit(int id, int language, LocalDate creationDate, int owner) {
		super();
		this.id = id;
		this.language = language;
		this.creationDate = creationDate;
		this.owner = owner;
	}

	public int[] getMembers() {
		return members;
	}

	public void setMembers(int[] membros) {
		this.members = membros;
	}
	
	public int getMember(int ord) {
		return members[ord];
	}

}
