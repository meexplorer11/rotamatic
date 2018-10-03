package com.javargon.rotamatic.model;

/**
 * 
 * @author vivek
 *
 */
public class SupportPerson {

	private String name;
	private String mobNo;
	private String officeNo;
	
	public SupportPerson(String name, String mobNo, String officeNo) {
		this.name = name;
		this.mobNo = mobNo;
		this.officeNo = officeNo;
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
		return "TeamMate [name=" + name + ", mobNo=" + mobNo + ", officeNo=" + officeNo + "]";
	}
	
}
