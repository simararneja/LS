package com.example.rs;
/**
 * This code encapsulates RSS item data.
 * Our application needs title and link data.
 * 
 * @author ITCuties
 *
 */
public class Bull2RssItem {
	
	private String description;
	private String Link;
	private String MonthsName;
	private String BusinessList;
	private String sessno;
	private String Dslot;
	
	public String getMonthsName() {
		return MonthsName;
	}
	public void setMonthsName(String value) {
		this.MonthsName= value;
	}
	public String getDslot() {
		return Dslot;
	}
	public void setDslot(String value) {
		this.Dslot= value;
	}
	public String getSession() {
		return sessno;
	}
	public void setSession(String value) {
		this.sessno= value;
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
	@Override
	public String toString() {
			return MonthsName + " " + Dslot + " - Session: "+sessno;
	}
	
	

}
