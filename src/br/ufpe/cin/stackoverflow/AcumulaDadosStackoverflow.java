package br.ufpe.cin.stackoverflow;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.joda.time.LocalDate;

import br.ufpe.cin.data.ContabilizadorIndividuos;
import br.ufpe.cin.data.Contagem;
import br.ufpe.cin.data.LedorUnidadeInfeccao;
import br.ufpe.cin.data.LedorUnidadeInfeccaoSinonimos;
import br.ufpe.cin.data.UnidadeInfeccao;
import br.ufpe.cin.data.Util;

public abstract class AcumulaDadosStackoverflow {
	
	protected String intervaloAcumulo;

	protected ContabilizadorIndividuos contabilizador;

	protected String diretorioArquivos;

	public abstract LocalDate obtemPrimeiroDiaIntervalo(LocalDate data);

	public abstract LocalDate obtemProximoIntervalo(LocalDate data);
	
	public LedorUnidadeInfeccaoSinonimos ledor;

	public AcumulaDadosStackoverflow(String intervaloAcumulo, ContabilizadorIndividuos contabilizador, String diretorio, LedorUnidadeInfeccaoSinonimos ledor) {
		this.intervaloAcumulo = intervaloAcumulo;
		this.contabilizador = contabilizador;
		this.diretorioArquivos = diretorio;
		this.ledor = ledor;
	}

	public Map<LocalDate, Integer> contagem(Map<Integer, UnidadeInfeccao> posts) {
		UnidadeInfeccao projeto;
		Map<Integer, Integer> individuosContabilizados = new HashMap<Integer, Integer>();
		Map<LocalDate, Integer> contagemUsuarios = new HashMap<LocalDate, Integer>();
		Iterator<UnidadeInfeccao> it = posts.values().iterator();
		Integer contagem = null;
		int quantidade = 0;
		while (it.hasNext()) {
			quantidade = 0;
			projeto = it.next();

			quantidade = contabilizador.contabilizarIndividuos(projeto, individuosContabilizados);

			LocalDate primeiroDiaIntervalo = obtemPrimeiroDiaIntervalo(projeto.dataCriacao);
			contagem = contagemUsuarios.get(primeiroDiaIntervalo);
			if (contagem != null) {
				contagem += quantidade;
				contagemUsuarios.put(primeiroDiaIntervalo, contagem);
			} else if (quantidade > 0) {
				contagemUsuarios.put(primeiroDiaIntervalo, quantidade);
			}
		}
		return contagemUsuarios;
	}

	public void analisa(String[] sinonimos, boolean imprimeArquivoData, boolean imprimeArquivoDados) throws IOException {

		Util.imprimir("************************************************************");
		Util.imprimir("* ininiciando processamento de " + getIntervaloAcumulo() + " para a linguagem " + sinonimos[0]);
		Util.imprimir("************************************************************");

		if (!imprimeArquivoDados && !imprimeArquivoData) {
			Util.imprimir("vc nao quer salvar nada, entao nao vou processar");
		}

		Map<Integer, UnidadeInfeccao> projetos = new HashMap<Integer, UnidadeInfeccao>();

		ledor.leUnidadeInfeccao(sinonimos, projetos);

		Util.imprimir(projetos.size() + " registros de projetos lidos");

		Util.imprimir("Iniciando leitura do arquivo de membros...");

		ledor.leMembros(projetos);

		System.gc();

		Util.imprimir("iniciando contagem de usuarios");

		Map<LocalDate, Integer> contagemUsuarios = contagem(projetos);

		Util.imprimir("contagem de usuarios finalizada");
		Util.imprimir("iniciando gravacao do arquivo de saida");

		escreveResultado(sinonimos, imprimeArquivoData, imprimeArquivoDados, contagemUsuarios, intervaloAcumulo);

		Util.imprimir("************************************************************");
		Util.imprimir("* processamento de " + getIntervaloAcumulo() + "para a linguagem " + sinonimos[0] + " finalizado");
		Util.imprimir("************************************************************");
	}

	public void escreveResultado(String[] sinonimos, boolean imprimeArquivoData, boolean imprimeArquivoDados,
			Map<LocalDate, Integer> contagemUsuarios, String intervaloAcumulo) throws IOException {
		ArrayList<Contagem> pq = new ArrayList<Contagem>();
		Set<Entry<LocalDate, Integer>> entradas = contagemUsuarios.entrySet();
		Iterator<Entry<LocalDate, Integer>> it2 = entradas.iterator();
		Entry<LocalDate, Integer> entrada = null;
		while (it2.hasNext()) {
			entrada = it2.next();
			pq.add(new Contagem(entrada.getKey(), entrada.getValue()));
		}

		FileWriter fw = new FileWriter(diretorioArquivos + "saida_data_" + sinonimos[0] + "_" + intervaloAcumulo + ".txt");
		BufferedWriter bw = new BufferedWriter(fw);

		FileWriter fw2 = new FileWriter(diretorioArquivos + "dados_" + sinonimos[0] + "_" + intervaloAcumulo + ".dat");
		BufferedWriter bw2 = new BufferedWriter(fw2);

		Collections.sort(pq);
		int acumulado = 0;
		int indiceArquivo = 1;
		LocalDate anterior = null;
		for (int k = 0; k < pq.size(); k++) {
			while (anterior != null && !obtemProximoIntervalo(anterior).isEqual(pq.get(k).getDataCriacao())) {
				anterior = obtemProximoIntervalo(anterior);

				if (imprimeArquivoData) {
					bw.write(anterior + "," + indiceArquivo + "," + acumulado);
					bw.newLine();
				}

				if (imprimeArquivoDados) {
					bw2.write(indiceArquivo + "	" + acumulado);
					bw2.newLine();
				}

				indiceArquivo++;
			}

			anterior = pq.get(k).getDataCriacao();
			acumulado += pq.get(k).getContagem();

			if (imprimeArquivoData) {
				bw.write(pq.get(k).getDataCriacao() + "," + indiceArquivo + "," + acumulado);
				bw.newLine();
			}

			if (imprimeArquivoDados) {
				bw2.write(indiceArquivo + "	" + acumulado);
				bw2.newLine();
			}

			indiceArquivo++;
		}

		bw.close();
		fw.close();

		bw2.close();
		fw2.close();
	}

	public String getIntervaloAcumulo() {
		return intervaloAcumulo;
	}

	public void setIntervaloAcumulo(String intervaloAcumulo) {
		this.intervaloAcumulo = intervaloAcumulo;
	}

}
