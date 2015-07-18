package com.example.ls;

public class MeetingRssItem {
	private String comName;
	private String date;
	private String time;
	private String venuPlace;
	private String agenda;

	@Override
	public String toString() {
		if(date==null)
			date= "N/A";
		if(comName==null)
			comName="N/A";
		if(time==null)
			time="N/A";
		if(venuPlace==null)
			venuPlace="N/A";
		if(agenda==null)
			agenda="N/A";
			
		return 	  "Date:" + date + "\n\n" 
				+ "Committee Name:" + comName +"\n\n"
				+ "Time:" + time + "\n\n" 
				+ "Venue:" + venuPlace +"\n\n" 
				+ "Agenda:" +"\n"
				+ agenda + "\n\n";
	}

	public void setComName(String string) {
		this.comName = string;
		
	}
	public String getComName(){
		return comName;
	}

	public void setDate(String string) {
		this.date = string;
		
	}
	public String getDate(){
		return date;
	}
	public void setTime(String string) {
		this.time = string;
		
	}
	public String getTime(){
		return time;
	}

	public void setVenuePlace(String string) {
		this.venuPlace = string;
		
	}
	public String getVenuePlace(){
		return venuPlace;
	}

	public void setAgenda(String string) {
		this.agenda = string;
		
	}
	public String getAgenda(){
		return agenda;
	}

}
