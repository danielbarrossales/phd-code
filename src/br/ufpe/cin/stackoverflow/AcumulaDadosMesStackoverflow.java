package br.ufpe.cin.stackoverflow;

import org.joda.time.LocalDate;

import br.ufpe.cin.data.ContabilizadorIndividuos;
import br.ufpe.cin.data.LedorUnidadeInfeccaoSinonimos;

public class AcumulaDadosMesStackoverflow extends AcumulaDadosStackoverflow {
	
	public AcumulaDadosMesStackoverflow(ContabilizadorIndividuos contabilizador, String diretorio, LedorUnidadeInfeccaoSinonimos ledor) {
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
