package com.example.rs;

/**
 * This code encapsulates RSS item data.
 * Our application needs title and link data.
 * 
 * @author ITCuties
 *
 */
public class TBRssItem {
	
	private String MemberName;
	private String ConstituencyAndState;
	private String PartyName;
	private String PictureURL;
	private String DOB;
	private String emailID;
		
	public String getMemberName() {
		return MemberName;
	}
	public void setMemberName(String MemberName) {
		this.MemberName= MemberName;
	}
	public String getConstituencyAndState() {
		return ConstituencyAndState;
	}
	public void setConstituencyAndState(String ConstituencyAndState) {
		this.ConstituencyAndState = ConstituencyAndState;
	}
	public String getPartyName() {
		return PartyName;
	}
	public void setPartyName(String PartyName) {
		this.PartyName = PartyName;
	}
	public String getPictureURL() {
		return PictureURL;
	}
	public void setPictureURL(String PictureURL) {
		this.PictureURL = PictureURL;
	}
	
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String DOB) {
		this.DOB = DOB;
	}
	
	public String getEmailID(){
		return emailID;
	}
	public void setEmailID(String emailID){
		this.emailID = emailID;
	}
	
	
	@Override
	public String toString() {
		return MemberName;
	}
	

	

}
