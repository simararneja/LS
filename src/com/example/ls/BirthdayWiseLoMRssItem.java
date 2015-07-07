package com.example.ls;

/**
 * This code encapsulates RSS item data.
 * Our application needs title and link data.
 * 
 * @author ITCuties
 *
 */
public class BirthdayWiseLoMRssItem {
	
	private String Sno;
	private String MPCode;
	private String Gender;
	private String MemberName;
	private String Party;
	private String Partyname;
	private String State;
	private String stateName;
	private String PermanentAddress;
	private String Permanentphone;
	private String LocalAddress;
	private String Localphone;
	private String EmailID;
	
	
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getSno() {
		return Sno;
	}
	public void setSno(String sno) {
		Sno = sno;
	}
	public String getMPCode() {
		return MPCode;
	}
	public void setMPCode(String mPCode) {
		MPCode = mPCode;
	}
	public String getMemberName() {
		return MemberName;
	}
	public void setMemberName(String memberName) {
		MemberName = memberName;
	}
	public String getParty() {
		return Party;
	}
	public void setParty(String party) {
		Party = party;
	}
	public String getPartyname() {
		return Partyname;
	}
	public void setPartyname(String partyname) {
		Partyname = partyname;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getPermanentAddress() {
		return PermanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		PermanentAddress = permanentAddress;
	}
	public String getPermanentphone() {
		return Permanentphone;
	}
	public void setPermanentphone(String permanentphone) {
		Permanentphone = permanentphone;
	}
	public String getLocalAddress() {
		return LocalAddress;
	}
	public void setLocalAddress(String localAddress) {
		LocalAddress = localAddress;
	}
	public String getLocalphone() {
		return Localphone;
	}
	public void setLocalphone(String localphone) {
		Localphone = localphone;
	}
	public String getEmailID() {
		return EmailID;
	}
	public void setEmailID(String emailID) {
		EmailID = emailID;
	}
	@Override
	public String toString() {
		/*return "S.No: " + Sno + "\n" +
				"M.P. Code: "+MPCode + "\n" +
				"Gender: "+Gender + "\n" +
				"Member Name: " +MemberName + "\n" +
				"Party: "+Party+ "\n" +
				"Party Name: "+ Partyname + "\n" +
				"State: "+ State+ "\n" +
				"State Name: "+stateName + "\n" +
				"Permanent Address: "+ PermanentAddress + "\n" +
				"Permanent Phone: "+ Permanentphone+ "\n" +
				"Local Address: " + LocalAddress + "\n" +
				"Local Phone: "+ Localphone + "\n" +
				"Email-ID: " + EmailID + "\n";*/
		return MemberName+"\n"+
		Partyname + "\n" +
		stateName;
	}
	
	
	
	
}
