package br.ufpe.cin.data;

import org.joda.time.LocalDate;

public class SumDayData extends SumData {
	
	public SumDayData(IndividualsCounter counter, String directory, InfectionUnitReader reader) {
		super("day", counter, directory, reader);
	}
	
	public LocalDate getNextInterval(LocalDate date) {
		return date.plusDays(1);
	}

	@Override
	public LocalDate getFirstDayInInterval(LocalDate date) {
		return date;
	}
	

}
