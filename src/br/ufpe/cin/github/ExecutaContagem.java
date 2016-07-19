package br.ufpe.cin.github;

import java.io.IOException;

import br.ufpe.cin.dados.AcumulaDados;
import br.ufpe.cin.dados.AcumulaDadosDia;
import br.ufpe.cin.dados.AcumulaDadosMes;
import br.ufpe.cin.dados.AcumulaDadosSemana;
import br.ufpe.cin.dados.ContabilizadorIndividuos;
import br.ufpe.cin.dados.ContabilizarIndividuos;
import br.ufpe.cin.dados.ContabilizarProjetos;
import br.ufpe.cin.dados.LedorUnidadeInfeccao;

/**
 * Runs the count
 * @author emanoelbarreiros
 *
 */
public class ExecutaContagem {

	public static void main(String[] args) throws IOException {

		String linguagens[] = {"Java", "JavaScript", "Ruby", "Python", "PHP", "C", 
				"C++","C#", "Shell", "R", "Go", "Swift", "Dart", "Assembly", "Julia", "Rust","Objective-C"};

		ContabilizadorIndividuos contabilizador = new ContabilizarIndividuos();
		String diretorio = "/Users/emanoel/OneDrive/workspace-doutorado/Doutorado/src/br/ufpe/cin/github/individuos/";
		LedorUnidadeInfeccao ledor = new LedorGithub();
		AcumulaDados acumulaDia = new AcumulaDadosDia(contabilizador, diretorio, ledor);
		AcumulaDados acumulaSemana = new AcumulaDadosSemana(contabilizador, diretorio, ledor);
		AcumulaDados acumulaMes = new AcumulaDadosMes(contabilizador, diretorio, ledor);
		
		
		for(String linguagem  : linguagens) {
			acumulaDia.analisa(linguagem, true, true);
		}
		
		for(String linguagem : linguagens) {
			acumulaSemana.analisa(linguagem, true, true);
		}
		
		for(String linguagem : linguagens) {
			acumulaMes.analisa(linguagem, true, true);
		}
		
		contabilizador = new ContabilizarProjetos();
		diretorio = "/Users/emanoel/OneDrive/workspace-doutorado/Doutorado/src/br/ufpe/cin/github/projetos/";
		acumulaDia = new AcumulaDadosDia(contabilizador, diretorio, ledor);
		acumulaSemana = new AcumulaDadosSemana(contabilizador, diretorio, ledor);
		acumulaMes = new AcumulaDadosMes(contabilizador, diretorio, ledor);
		
		
		for(String linguagem  : linguagens) {
			acumulaDia.analisa(linguagem, true, true);
		}
		
		for(String linguagem : linguagens) {
			acumulaSemana.analisa(linguagem, true, true);
		}
		
		for(String linguagem : linguagens) {
			acumulaMes.analisa(linguagem, true, true);
		}
		
	}

}
