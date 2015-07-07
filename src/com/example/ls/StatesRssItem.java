package com.example.ls;

/**
 * This code encapsulates RSS item data.
 * Our application needs title and link data.
 * 
 * @author ITCuties
 *
 */
public class StatesRssItem {
	
	private String states;
	private String statename;
	private String noofmembers;
	
	public void setnoofmembers(String noofmembers) {
		this.noofmembers= noofmembers;
	}
	public String getnoofmembers() {
		return noofmembers;
	}
	public void setstates(String states) {
		this.states= states;
	}
	public String getstates() {
		return states;
	}
	public void setstatename(String statename) {
		this.statename= statename;
	}
	public String getstatename() {
		return statename;
	}
	@Override
	public String toString() {
		return statename + " ( " + noofmembers + " )";
	}
	

	

}
