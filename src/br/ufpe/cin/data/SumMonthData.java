package br.ufpe.cin.data;

import org.joda.time.LocalDate;

public class SumMonthData extends SumData {
	
	public SumMonthData(IndividualsCounter counter, String directory, InfectionUnitReader reader) {
		super("month", counter, directory, reader);
	}
	
	public LocalDate getNextInterval(LocalDate data) {
		return data.plusMonths(1);
	}
	
	@Override
	public LocalDate getFirstDayInInterval(LocalDate data) {
		return data.minusDays(data.getDayOfMonth() - 1);
	}

}
