package com.example.ls;


public class SQLRssItem {
	
	private String Dslot;
	private String link;
	private String MonthsName;
		
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
		return Dslot;
	}
	

	

}
