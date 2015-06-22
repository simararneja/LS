package com.example.rs;

/**
 * This code encapsulates RSS item data.
 * Our application needs title and link data.
 * 
 * @author ITCuties
 *
 */
public class PartyRssItem {
	
	private String Party;
	private String partyname;
	private String noofmembers;
	
	public void setnoofmembers(String noofmembers) {
		this.noofmembers= noofmembers;
	}
	public String getnoofmembers() {
		return noofmembers;
	}
	public void setParty(String Party) {
		this.Party= Party;
	}
	public String getParty() {
		return Party;
	}
	public void setpartyname(String partyname) {
		this.partyname= partyname;
	}
	public String getpartyname() {
		return partyname;
	}
	@Override
	public String toString() {
		return partyname + " ( " + noofmembers + " )";
	}
	

	

}
