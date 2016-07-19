package br.ufpe.cin.dados;

import org.joda.time.LocalDate;

public class AcumulaDadosDia extends AcumulaDados {
	
	public AcumulaDadosDia(ContabilizadorIndividuos contabilizador, String diretorio, LedorUnidadeInfeccao ledor) {
		super("dia", contabilizador, diretorio, ledor);
	}
	
	public LocalDate obtemProximoIntervalo(LocalDate data) {
		return data.plusDays(1);
	}

	@Override
	public LocalDate obtemPrimeiroDiaIntervalo(LocalDate data) {
		return data;
	}
	

}
