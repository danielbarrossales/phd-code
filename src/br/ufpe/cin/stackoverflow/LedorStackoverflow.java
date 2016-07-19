package br.ufpe.cin.stackoverflow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.joda.time.LocalDate;

import br.ufpe.cin.dados.LedorUnidadeInfeccaoSinonimos;
import br.ufpe.cin.dados.Linguagem;
import br.ufpe.cin.dados.UnidadeInfeccao;
import br.ufpe.cin.dados.Util;

public class LedorStackoverflow implements LedorUnidadeInfeccaoSinonimos {
	
	@Override
	public void leUnidadeInfeccao(String[] sinonimos, Map<Integer, UnidadeInfeccao> posts)
			throws FileNotFoundException, IOException {
		BufferedReader br = null;
		FileReader fr = null;
		File f = null;
		String linha = null;
		String[] linhaQuebrada = null;

		Util.imprimir("Iniciando leitura do arquivo de posts...");
		fr = new FileReader(
				"/Users/emanoel/Google Drive/workspace-doutorado/Doutorado/src/br/ufpe/cin/stackoverflow/posts.csv");
		br = new BufferedReader(fr);

		// ler arquivo linha a linha
		br.readLine(); // pular a primeira linha
		int i = 0;
		Integer id = 0;
		boolean ehNumero = false;
		while ((linha = br.readLine()) != null) {
			linhaQuebrada = linha.trim().split(",");
			ehNumero = linhaQuebrada[3].chars().allMatch( Character::isDigit );
			if (existe(linhaQuebrada[1], sinonimos) && ehNumero) {//no caso do stackoverflow um post tem varias tags, entao basta um dos sinonimos da linguagem que estamos procurando
				id = Integer.parseInt(linhaQuebrada[0]);
				posts.put(id, new UnidadeInfeccao(id, Linguagem.obterCodigoLinguagem(sinonimos[0]),//a linguagem alvo sempre sera a primeira do array de sinonimos
						obtemData(linhaQuebrada[2]), Integer.parseInt(linhaQuebrada[3])));
			}
			i++;
			/*
			 * if (i % 100000 == 0) { imprimir (i +
			 * " registros (projetos) processados"); }
			 */
		}
		Util.imprimir(i + " registros (posts) processados");

		fr.close();
		br.close();
	}

	@Override
	public void leMembros(Map<Integer, UnidadeInfeccao> projetos) throws FileNotFoundException, IOException {
		// como um post so tem um usuario fazendo a pergunta, nao faz sentido pensar em outros membros da pergunta.
		// este metodo so vai ser importante para fontes de dados de projetos, onde o projeto tem mais de um membro.
	}
	
	public boolean existe(String tagsPost, String[] sinonimosLinguagem) {
		for(String sinonimo : sinonimosLinguagem) {
			if(tagsPost.contains("<" + sinonimo + ">")) {
				return true;
			}
		}
		
		return false;
	}
	
	public LocalDate obtemData(String dataHora) {
		String data = dataHora.replaceAll("\"", "");
		return new LocalDate(data.split(" ")[0]);
	}

}
