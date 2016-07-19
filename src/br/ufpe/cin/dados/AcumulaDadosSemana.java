package br.ufpe.cin.dados;

import org.joda.time.LocalDate;

public class AcumulaDadosSemana extends AcumulaDados {
	
	public AcumulaDadosSemana(ContabilizadorIndividuos contabilizador, String diretorio, LedorUnidadeInfeccao ledor) {
		super("semana", contabilizador, diretorio, ledor);
	}
	
	public LocalDate obtemProximoIntervalo(LocalDate data) {
		return data.plusWeeks(1);
	}
	
	public LocalDate obtemPrimeiroDiaIntervalo(LocalDate data) {
		return data.minusDays(data.getDayOfWeek());
	}

}
