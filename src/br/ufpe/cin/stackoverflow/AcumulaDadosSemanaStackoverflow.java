package br.ufpe.cin.stackoverflow;

import org.joda.time.LocalDate;

import br.ufpe.cin.data.ContabilizadorIndividuos;
import br.ufpe.cin.data.LedorUnidadeInfeccaoSinonimos;

public class AcumulaDadosSemanaStackoverflow extends AcumulaDadosStackoverflow {
	
	public AcumulaDadosSemanaStackoverflow(ContabilizadorIndividuos contabilizador, String diretorio, LedorUnidadeInfeccaoSinonimos ledor) {
		super("semana", contabilizador, diretorio, ledor);
	}
	
	public LocalDate obtemProximoIntervalo(LocalDate data) {
		return data.plusWeeks(1);
	}
	
	public LocalDate obtemPrimeiroDiaIntervalo(LocalDate data) {
		return data.minusDays(data.getDayOfWeek());
	}

}
