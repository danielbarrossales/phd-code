package br.ufpe.cin.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Dado {

	private LocalDate data;
	private int valor;

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public String toString() {
		return data.format(DateTimeFormatter.ISO_LOCAL_DATE) + "," + valor;
	}
	
}
