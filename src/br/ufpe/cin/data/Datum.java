package br.ufpe.cin.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Datum {

	private LocalDate date;
	private int value;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String toString() {
		return date.format(DateTimeFormatter.ISO_LOCAL_DATE) + "," + value;
	}
	
}
