package com.example.ls;

import android.nfc.cardemulation.OffHostApduService;


public class SecretaryGeneralRssItem {

	private String name;
	private String educationqual;
	private String permanentAddress;
	private String permanentPhone;
	private String officeAddress1;
	private String oficephone1;
	private String officeAddress2;
	private String officephone2;
	private String emailID;
	private String pictureUrl;
	private String biodataUrl;
	
	public void setName(String string) {	
		this.name = string;
	}
	
	public String getName() {	
		return name;
	}

	public void setEducationQual(String string) {
		this.educationqual = string;	
	}
	public String getEducationQual() {
		return educationqual;	
	}

	public void setPermanentAddress(String string) {
		permanentAddress = string;
		
	}
	public String getPermanentAddress() {
		return permanentAddress;
		
	}

	public void setPermanentphone(String string) {
		permanentPhone = string;
		
	}
	public String getPermanentphone() {
		return permanentPhone;
		
	}

	public void setOfficeAddress1(String string) {
		this.officeAddress1 =string;			
		
	}
	
	public String getOfficeAddress1() {
		return officeAddress1;			
		
	}

	public void setOfficephone1(String string) {
		this.oficephone1 = string;
		
	}
	public String getOfficephone1() {
		return oficephone1;
		
	}

	public void setOfficeAddress2(String string) {
		this.officeAddress2 = string;
		
	}
	public String getOfficeAddress2() {
		return officeAddress2;
		
	}
	public void setOfficephone2(String string) {
		this.officephone2 = string;
		
	}
	public String getOfficephone2() {
		return officephone2;
		
	}

	public void setEmailID(String string) {
		this.emailID  = string;
		
	}
	public String getEmailID() {
		return emailID;
		
	}

	public void setictureUrl(String string) {
		this.pictureUrl = string;
		
	}
	
	public String getPictureUrl() {
		return pictureUrl;
		
	}

	@Override
	public String toString() {
		return "Name: " + name + "\n" +
				"Education Qualification: " + educationqual	+ "\n" +
				"Permanent Address: " + permanentAddress + "\n" +
				"Permanent Phone: " + permanentPhone+ "\n" +
				"Office Address 1: " + officeAddress1 + "\n" +
				"Office Phone 1: "+ oficephone1 + "\n" +
				"Office Address 1: " + officeAddress2	+ "\n" +
				"Office Phone 1 : " + officephone2+ "\n" +
				"Email-ID: "+ emailID + "\n" +
				"Biodata URL: "+ biodataUrl + "\n" +
				"Picture URL: " + pictureUrl + "\n" + "" ;
	}

	public void setBiodataUrl(String string) {
		this.biodataUrl = string;
		
	}	

}
