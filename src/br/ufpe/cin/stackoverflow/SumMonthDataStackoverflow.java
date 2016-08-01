package br.ufpe.cin.stackoverflow;

import org.joda.time.LocalDate;

import br.ufpe.cin.data.IndividualsCounter;
import br.ufpe.cin.data.InfectionUnitSynonymReader;

public class SumMonthDataStackoverflow extends SumDataStackOverflow {
	
	public SumMonthDataStackoverflow(IndividualsCounter counter, String directory, InfectionUnitSynonymReader reader) {
		super("month", counter, directory, reader);
	}
	
	public LocalDate getNextInterval(LocalDate date) {
		return date.plusMonths(1);
	}
	
	@Override
	public LocalDate getFirstDayInInterval(LocalDate date) {
		return date.minusDays(date.getDayOfMonth() - 1);
	}

}
