package br.ufpe.cin.dados;

import org.joda.time.LocalDate;

public class Contagem implements Comparable<Contagem>{
	
	private LocalDate dataCriacao;
	private Integer contagem;
	
	public Contagem(LocalDate dataCriacao, Integer contagem) {
		super();
		this.dataCriacao = dataCriacao;
		this.contagem = contagem;
	}

	public int compareTo(Contagem c) {
		return dataCriacao.compareTo(c.dataCriacao);
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Integer getContagem() {
		return contagem;
	}

	public void setContagem(Integer contagem) {
		this.contagem = contagem;
	}
	
	
	
}
