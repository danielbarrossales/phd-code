package br.ufpe.cin.stackoverflow;

import org.joda.time.LocalDate;

import br.ufpe.cin.data.IndividualsCounter;
import br.ufpe.cin.data.InfectionUnitSynonymReader;

public class SumDayDataStackOverflow extends SumDataStackOverflow {
	
	public SumDayDataStackOverflow(IndividualsCounter counter, String diretorio, InfectionUnitSynonymReader reader) {
		super("day", counter, diretorio, reader);
	}
	
	public LocalDate getNextInterval(LocalDate data) {
		return data.plusDays(1);
	}

	@Override
	public LocalDate getFirstDayInInterval(LocalDate data) {
		return data;
	}

}
