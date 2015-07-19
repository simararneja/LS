package com.example.ls;

public class GovtBillRssItem {
	private String date;
	private String link;
	private String memberid;
	private String debateid;
	private String debateType;
	private String keywords;
	private String participants;
	private String title;
		
	
	public String getlink() {
		return link;
	}
	public void setlink(String link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		if(debateid !=null && memberid!= null){
			
			return "Debate Type: " + debateType + "\n\n"
					+ "Title: " + title + "\n\n"
					+ "Date: " + date +" \n\n"
					+ "Participants: " + participants +"\n\n";
		}
		return "No Govt Bills";
	}
	public void setMemberid(String string) {
		this.memberid = string;
		
	}
	public String getMemberid() {
		return link;
	}
	public void setDebateType(String string) {
		this.debateType = string;
		
	}
	public String getDebateType() {
		return link;
	}
	public void setTitle(String string) {
		this.title = string;
		
	}
	public String getTitle() {
		return link;
	}
	public void setDate(String string) {
		this.date = string;
		
	}
	public String getDate() {
		return link;
	}
	public void setDebateid(String string) {
		this.debateid = string;
		
	}
	public String getDebateid() {
		return link;
	}
	
	public void setKeywords(String string) {
		this.keywords = string;
		
	}
	public String getKeywords() {
		return link;
	}
	public void setParticipants(String string) {
		this.participants = string;
		
	}
	public String getParticipants() {
		return link;
	}
}
