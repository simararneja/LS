package com.example.ls;

public class VacantConsituenciesRSSItem {
	
	public String constituencyAndState;

	public void setConstituencyAndState(String string) {
		this.constituencyAndState = string;
	}
	
	public String getConstituencyAndState(){
		return constituencyAndState;
	}
	@Override
	public String toString() {
		return constituencyAndState + "";
	}
	
}
