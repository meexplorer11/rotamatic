package com.javargon.rotamatic.model;

import java.time.LocalDate;

/**
 * 
 * @author vivek
 *
 */
public class Row {
	private LocalDate localDate;
	private String name;
	private String mobNo;
	private String officeNo;
	
	public Row(LocalDate localDate, String name, String mobNo, String officeNo) {
		this.localDate = localDate;
		this.name = name;
		this.mobNo = mobNo;
		this.officeNo = officeNo;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public String getName() {
		return name;
	}

	public String getMobNo() {
		return mobNo;
	}

	public String getOfficeNo() {
		return officeNo;
	}

	@Override
	public String toString() {
		return localDate + "\t" + name + "\t" + mobNo + "\t" + officeNo;
	}

	
}
