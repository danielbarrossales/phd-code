package br.ufpe.cin.stackoverflow;

import org.joda.time.LocalDate;

import br.ufpe.cin.data.IndividualsCounter;
import br.ufpe.cin.data.InfectionUnitSynonymReader;

public class SumWeekDataStackoverflow extends SumDataStackOverflow {
	
	public SumWeekDataStackoverflow(IndividualsCounter counter, String directory, InfectionUnitSynonymReader reader) {
		super("week", counter, directory, reader);
	}
	
	public LocalDate getNextInterval(LocalDate date) {
		return date.plusWeeks(1);
	}
	
	public LocalDate getFirstDayInInterval(LocalDate date) {
		return date.minusDays(date.getDayOfWeek());
	}

}
