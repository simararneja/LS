package com.example.rs;

/**
 * This code encapsulates RSS item data.
 * Our application needs title and link data.
 * 
 * @author ITCuties
 *
 */
public class CommMemDetailRssItem {
	
	private String comName;
	private String chairMan;
	private String memName;
	
	public CommMemDetailRssItem()
	{
		this.memName = "";
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
		return comName;
	}


	public String getComName() {
		return comName;
	}


	public void setComName(String comName) {
		this.comName = comName;
	}


	public String getChairMan() {
		return chairMan;
	}


	public void setChairMan(String chairMan) {
		this.chairMan = chairMan;
	}


	public String getMemName() {
		return memName;
	}


	public void setMemName(String memName) {
		this.memName = memName;
	}
	
	
	
	
}
