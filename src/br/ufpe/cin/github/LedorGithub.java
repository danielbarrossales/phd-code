package br.ufpe.cin.github;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.joda.time.LocalDate;

import br.ufpe.cin.dados.LedorUnidadeInfeccao;
import br.ufpe.cin.dados.Linguagem;
import br.ufpe.cin.dados.UnidadeInfeccao;
import br.ufpe.cin.dados.Util;

public class LedorGithub implements LedorUnidadeInfeccao {

	@Override
	public void leUnidadeInfeccao(String linguagem, Map<Integer, UnidadeInfeccao> projetos)
			throws FileNotFoundException, IOException {
		BufferedReader br = null;
		FileReader fr = null;
		File f = null;
		String linha = null;
		String[] linhaQuebrada = null;

		Util.imprimir("Iniciando leitura do arquivo de projetos...");
		fr = new FileReader(
				"/Users/emanoel/Google Drive/workspace-doutorado/Doutorado/src/br/ufpe/cin/github/projects.csv");
		br = new BufferedReader(fr);

		// ler arquivo linha a linha
		br.readLine(); // pular a primeira linha
		int i = 0;
		Integer id = 0;
		while ((linha = br.readLine()) != null) {
			linhaQuebrada = linha.trim().split(",");
			if (linhaQuebrada[1].equals(linguagem)) {
				id = Integer.parseInt(linhaQuebrada[0]);
				projetos.put(id, new UnidadeInfeccao(id, Linguagem.obterCodigoLinguagem(linhaQuebrada[1]),
						new LocalDate(linhaQuebrada[2]), Integer.parseInt(linhaQuebrada[3])));
			}
			i++;
			/*
			 * if (i % 100000 == 0) { imprimir (i +
			 * " registros (projetos) processados"); }
			 */
		}
		Util.imprimir(i + " registros (projetos) processados");

		fr.close();
		br.close();
	}

	@Override
	public void leMembros(Map<Integer, UnidadeInfeccao> projetos) throws FileNotFoundException, IOException {
		BufferedReader br;
		FileReader fr;
		String linha;
		String[] linhaQuebrada;
		int i;
		Integer id;
		fr = new FileReader(
				"/Users/emanoel/Google Drive/workspace-doutorado/Doutorado/src/br/ufpe/cin/github/project_members.csv");
		br = new BufferedReader(fr);
		UnidadeInfeccao projeto = null;
		i = 0;
		br.readLine(); // pular a primeira linha
		while ((linha = br.readLine()) != null) {
			linhaQuebrada = linha.trim().split(",");
			id = Integer.parseInt(linhaQuebrada[0]);
			projeto = projetos.get(id);
			if (projeto != null) {
				projeto.setMembros(Util.converterArrayString(linhaQuebrada));
			}
			i++;
		}
		Util.imprimir(i + " registros (membros) processados");

		fr.close();
		br.close();
	}

}
