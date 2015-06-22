package com.example.rs;

import java.util.Collection;
import java.util.List;

import android.app.Activity;

public class LoadItems extends Activity {
	private final String RSSFeedIP = "http://164.100.47.132/ipad_rssfeed/";
	public String[] items;
	public String[] links;
	public String[] type;
	public String urlStart;
	public String[] websitesbottommenu;
	public String[] websitesbottommenulinks;
	public String[] livebottommenulinks;
	public String[] livebottommenu;
	public String[] membertypes;
	public String[] membertypevalues;
	public String[] months;
	public String[] orgDesignations;
	public String[] orgDesignationsValue;
	public String[] comtypes;
	public String[] comLinks;
	public String[] comShortNames;
	public String[] comLongNames;
	
	LoadItems(){
		
		urlStart = RSSFeedIP;
		
		//List items of homscreen sliding menu
		items = new String[]{"Today's Birthday","Provisional Calender","Question Chart","List Of Business","Bulletin Part - I","Bulletin Part - II","Question List","Daily Synopsis","Notifications","Organisation","Committees"};
		links = new String[]{"todaybdaylist.aspx","provisionalCalendar.aspx","member_questions.aspx","businessList.aspx","bulletinpart_I.aspx","bulletinpart_II.aspx","member_questions.aspx","DailySynopsis.aspx","Notifications.aspx","org","com"};
		type = new String[]{"4","0","0","0","0","0","0","2","0","0","3","5"};
		websitesbottommenu = new String[] {"Rajya Sabha(English)","Rajya Sabha(Hindi)","Lok Sabha(English)","Lok Sabha(Hindi)","RS Debates"};
		websitesbottommenulinks = new String[] {"http://loksabhahindi.nic.in","http://loksabha.nic.in","http://rajyasabha.nic.in","http://rajyasabhahindi.nic.in","http://rsdebate.nic.in"};
		livebottommenu = new String[]{"Rajya Sabha TV","Lok Sabha TV","Doordarshan"};
		livebottommenulinks = new String[]{"http://rstv.nic.in","http://loksabhatv.nic.in","http://webcast.gov.in"};
		membertypes = new String[] {"Alphabetical","State wise","Party wise","B'day wise","Women","Nominated","Council of Ministers"};
		membertypevalues = new String[] {"a","s","p","b","w","n","c"};
		months = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
		orgDesignations = new String[]{"Speaker","Deputy Speaker","Secretary General","Secretariat"};
		orgDesignationsValue = new String[]{"c","d","s","o"};
		comtypes = new String[]{"Financial Committees", "Standing Committees of Lok Sabha","Department Related Standing Committees (RS)","Department Related Standing Committees (LS)","Joint Committees","Adhoc Committees","Select Committees"};
		comLinks = new String[] {"CommitteeMembershipall.aspx?comm=RS","CommitteeMembershipall.aspx?comm=DRSCRS","CommitteeMembershipall.aspx?comm=DRSCLS","CommitteeMembershipall.aspx?comm=JC","CommitteeMembershipall.aspx?comm=ADHOC","CommitteeMembershipall.aspx?comm=SELECT"};
		comShortNames = new String[]{"RS","DRSCRS","DRSCLS","JC","ADHOC","SELECT"};
	
	}
	
	public String[] GetItemArray()
	{
		return items;
	}
	public String GetMemBerDetailURL(String ID)
	{
		return urlStart + "member_biography.aspx?member_id="+ID;
	}
	public String GetMemQuestionsURL(String MP_Code)
	{
		return urlStart+"QuestionList.aspx?member_id=" + MP_Code;
	}
	public String GetMemAssurancesURL(String MP_Code)
	{
		return urlStart+"MemberAssurance.aspx?member_id=" + MP_Code;
	}
	public String GetMemBioDataURL(String MP_Code)
	{
		return urlStart+"member_biography.aspx?member_id=" + MP_Code;
	}
	public String GetStarredQuestionsURL()
	{
		return urlStart + "SttaredQuestionList.aspx";
	}
	public String GetUnStarredQuestionsURL()
	{
		return urlStart + "unStarredQuestionList.aspx";
	}
	public String GetChairmanURL()
	{
		return urlStart + "Chairman.aspx";
	}
	public String GetDyChairmanURL()
	{
		return urlStart + "DyChairman.aspx";
	}
	public String GetSGURL()
	{
		return urlStart + "SecretaryGeneral.aspx";
	}
	public String GetASJSUrl()
	{
		return urlStart +"ASJS.aspx";
	}
	public String GetDirectorsUrl()
	{
		return urlStart +"Directors.aspx";
	}
	public static String GetPDFReaderLink()
	{
		return "http://docs.google.com/viewer?url=";
	}
}
