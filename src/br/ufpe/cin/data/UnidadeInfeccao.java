package br.ufpe.cin.data;

import org.joda.time.LocalDate;

public class UnidadeInfeccao {
	
	public int id;
	public int linguagem;
	public LocalDate dataCriacao;
	public int dono;
	private int[] membros;
	
	public UnidadeInfeccao(int id, int linguagem, LocalDate dataCriacao, int dono) {
		super();
		this.id = id;
		this.linguagem = linguagem;
		this.dataCriacao = dataCriacao;
		this.dono = dono;
	}

	public int[] getMembros() {
		return membros;
	}

	public void setMembros(int[] membros) {
		this.membros = membros;
	}
	
	public int obtemMembro(int ord) {
		return membros[ord];
	}

}
