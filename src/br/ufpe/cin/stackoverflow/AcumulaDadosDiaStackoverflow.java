package br.ufpe.cin.stackoverflow;

import org.joda.time.LocalDate;

import br.ufpe.cin.dados.ContabilizadorIndividuos;
import br.ufpe.cin.dados.LedorUnidadeInfeccaoSinonimos;

public class AcumulaDadosDiaStackoverflow extends AcumulaDadosStackoverflow {
	
	public AcumulaDadosDiaStackoverflow(ContabilizadorIndividuos contabilizador, String diretorio, LedorUnidadeInfeccaoSinonimos ledor) {
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
