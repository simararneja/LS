package com.example.ls;
/**
 * This code encapsulates RSS item data.
 * Our application needs title and link data.
 * 
 * @author ITCuties
 *
 */
public class RssItem {
	
	private String description;
	private String Link;
	private String MonthsName;
	private String BusinessList;
	private String head;
	
	
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
			return head;
	}
	public void setHead(String string) {
		this.head= string;
		
	}
	public String getHead(){
		return head;
	}
	
	

}
