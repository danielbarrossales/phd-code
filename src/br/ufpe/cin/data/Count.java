package br.ufpe.cin.data;

import org.joda.time.LocalDate;

public class Count implements Comparable<Count>{
	
	private LocalDate creationDate;
	private Integer count;
	
	public Count(LocalDate creationDate, Integer count) {
		super();
		this.creationDate = creationDate;
		this.count = count;
	}

	public int compareTo(Count c) {
		return creationDate.compareTo(c.creationDate);
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	
}
