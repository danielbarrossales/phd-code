package br.ufpe.cin.dados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

public abstract class AcumulaDados {

	protected String intervaloAcumulo;

	protected ContabilizadorIndividuos contabilizador;

	protected String diretorioArquivos;

	public abstract LocalDate obtemPrimeiroDiaIntervalo(LocalDate data);

	public abstract LocalDate obtemProximoIntervalo(LocalDate data);
	
	public LedorUnidadeInfeccao ledor;

	public AcumulaDados(String intervaloAcumulo, ContabilizadorIndividuos contabilizador, String diretorio, LedorUnidadeInfeccao ledor) {
		this.intervaloAcumulo = intervaloAcumulo;
		this.contabilizador = contabilizador;
		this.diretorioArquivos = diretorio;
		this.ledor = ledor;
	}

	public Map<LocalDate, Integer> contagem(Map<Integer, UnidadeInfeccao> projetos) {
		UnidadeInfeccao projeto;
		Map<Integer, Integer> individuosContabilizados = new HashMap<Integer, Integer>();
		Map<LocalDate, Integer> contagemUsuarios = new HashMap<LocalDate, Integer>();
		Iterator<UnidadeInfeccao> it = projetos.values().iterator();
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

	public void analisa(String linguagem, boolean imprimeArquivoData, boolean imprimeArquivoDados) throws IOException {

		Util.imprimir("************************************************************");
		Util.imprimir("* ininiciando processamento de " + getIntervaloAcumulo() + " para a linguagem " + linguagem);
		Util.imprimir("************************************************************");

		if (!imprimeArquivoDados && !imprimeArquivoData) {
			Util.imprimir("vc nao quer salvar nada, entao nao vou processar");
		}

		Map<Integer, UnidadeInfeccao> projetos = new HashMap<Integer, UnidadeInfeccao>();

		ledor.leUnidadeInfeccao(linguagem, projetos);

		Util.imprimir(projetos.size() + " registros de projetos lidos");

		Util.imprimir("Iniciando leitura do arquivo de membros...");

		ledor.leMembros(projetos);

		System.gc();

		Util.imprimir("iniciando contagem de usuarios");

		Map<LocalDate, Integer> contagemUsuarios = contagem(projetos);

		Util.imprimir("contagem de usuarios finalizada");
		Util.imprimir("iniciando gravacao do arquivo de saida");

		escreveResultado(linguagem, imprimeArquivoData, imprimeArquivoDados, contagemUsuarios, intervaloAcumulo);

		Util.imprimir("************************************************************");
		Util.imprimir("* processamento de " + getIntervaloAcumulo() + "para a linguagem " + linguagem + " finalizado");
		Util.imprimir("************************************************************");
	}

	public void escreveResultado(String linguagem, boolean imprimeArquivoData, boolean imprimeArquivoDados,
			Map<LocalDate, Integer> contagemUsuarios, String intervaloAcumulo) throws IOException {
		ArrayList<Contagem> pq = new ArrayList<Contagem>();
		Set<Entry<LocalDate, Integer>> entradas = contagemUsuarios.entrySet();
		Iterator<Entry<LocalDate, Integer>> it2 = entradas.iterator();
		Entry<LocalDate, Integer> entrada = null;
		while (it2.hasNext()) {
			entrada = it2.next();
			pq.add(new Contagem(entrada.getKey(), entrada.getValue()));
		}

		FileWriter fw = new FileWriter(diretorioArquivos + "saida_data_" + linguagem + "_" + intervaloAcumulo + ".txt");
		BufferedWriter bw = new BufferedWriter(fw);

		FileWriter fw2 = new FileWriter(diretorioArquivos + "dados_" + linguagem + "_" + intervaloAcumulo + ".dat");
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
