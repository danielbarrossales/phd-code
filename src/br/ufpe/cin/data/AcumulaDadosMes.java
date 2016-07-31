package br.ufpe.cin.data;

import org.joda.time.LocalDate;

public class AcumulaDadosMes extends AcumulaDados {
	
	public AcumulaDadosMes(ContabilizadorIndividuos contabilizador, String diretorio, LedorUnidadeInfeccao ledor) {
		super("mes", contabilizador, diretorio, ledor);
	}
	
	public LocalDate obtemProximoIntervalo(LocalDate data) {
		return data.plusMonths(1);
	}
	
	@Override
	public LocalDate obtemPrimeiroDiaIntervalo(LocalDate data) {
		return data.minusDays(data.getDayOfMonth() - 1);
	}

}
