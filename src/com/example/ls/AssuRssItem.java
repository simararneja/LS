package com.example.ls;

/**
 * This code encapsulates RSS item data.
 * Our application needs title and link data.
 * 
 * @author ITCuties
 *
 */
public class AssuRssItem {
	
	private String Dslot;
	private String link;
	private String MonthsName;
	private String ses_no;
	private String qtype;
	private String PubDate;
	private String ministryName;
	private String subject;
	private String qid;
	
	public void setses_no(String ses_no) {
		this.ses_no= ses_no;
	}
	public String getses_no() {
		return ses_no;
	}
	public void setqtype(String qtype) {
		this.qtype= qtype;
	}
	public String getqtype() {
		return qtype;
	}
	public void setPubDate(String PubDate) {
		this.PubDate= PubDate;
	}
	public String getPubDate() {
		return PubDate;
	}
	public void setministryName(String ministryName) {
		this.ministryName= ministryName;
	}
	public String getministryName() {
		return ministryName;
	}
	public void setqid(String qid) {
		this.qid= qid;
	}
	public String getqid() {
		return qid;
	}
	public void setsubject(String subject) {
		this.subject= subject;
	}
	public String getsubject() {
		return subject;
	}
	public String getDslot() {
		return Dslot;
	}
	public void setDslot(String Dslot) {
		this.Dslot= Dslot;
	}
	public String getlink() {
		return link;
	}
	public void setlink(String link) {
		this.link = link;
	}
	public String getMonthsName() {
		return MonthsName;
	}
	public void setMonthsName(String monthsName) {
		MonthsName = monthsName;
	}
	@Override
	public String toString() {
		return "Assurance No.: " + qid + "\n" +
				"Assurance Type: " + qtype +"\n"+
				"Subject: " + subject + "\n" +
				"Ministry: " + ministryName;
	}
	

	

}
