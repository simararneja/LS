package com.example.ls;
/**
 * This code encapsulates RSS item data.
 * Our application needs title and link data.
 * 
 * @author ITCuties
 *
 */
public class LOBRssItem {
	
	private String description;
	private String Link;
	private String MonthsName;
	private String BusinessList;
	private String errorMessage;
	
	public String getMonthsName() {
		return MonthsName;
	}
	public void setMonthsName(String value) {
		this.MonthsName= value;
	}
	public String getBusinessList() {
		return BusinessList;
	}
	public void setBusinessList(String value) {
		this.BusinessList= value;
	}
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description= description;
	}
	public String getLink() {
		return Link;
	}
	public void setLink(String Link) {
		this.Link = Link;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
			return MonthsName + " " + BusinessList;
	}
	
	

}
