package br.ufpe.cin.data;

import org.joda.time.LocalDate;

public class SumWeekData extends SumData {
	
	public SumWeekData(IndividualsCounter counter, String directory, InfectionUnitReader reader) {
		super("week", counter, directory, reader);
	}
	
	public LocalDate getNextInterval(LocalDate date) {
		return date.plusWeeks(1);
	}
	
	public LocalDate getFirstDayInInterval(LocalDate date) {
		return date.minusDays(date.getDayOfWeek());
	}

}
