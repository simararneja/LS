package com.example.ls;

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
	public String[] organisationDesignations;
	public String[] orgDesignationsValue;
	public String[] comtypes;
	public String[] comLinks;
	public String[] comShortNames;
	public String[] comLongNames;
	public String[] postMonthLink;

	LoadItems() {

		urlStart = RSSFeedIP;

		// List items of homscreen sliding menu
		items = new String[] { "Today's Birthday", "Provisional Calender",
				"List Of Business", "Bulletin Part - I", "Bulletin Part - II",
				"Question List", "Daily Synopsis",
				"Committee Meeting", "Organisation"};
		links = new String[] { "todaybdaylist.aspx",
				"provisionalCalendar.aspx", "businessList.aspx",
				"bulletinpart_I.aspx", "bulletinpart_II.aspx",
				"member_questions.aspx", "DailySynopsis.aspx", "committee" ,"organisation"
				};
		type = new String[] { "4", "0", "0", "0", "0", "2", "0", "5", "7" };
		websitesbottommenu = new String[] { "Lok Sabha(English)",
				"Lok Sabha(Hindi)", "Rajya Sabha(English)",
				"Rajya Sabha(Hindi)", "Rajya Sabha Debates", "Parliament Digital Library" };
		websitesbottommenulinks = new String[] { "http://loksabha.nic.in",
				"http://loksabhahindi.nic.in", "http://rajyasabha.nic.in",
				"http://rajyasabhahindi.nic.in", "http://rsdebate.nic.in","http://eparlib.india.nic.in/" };
		livebottommenu = new String[] { "Rajya Sabha TV", "Lok Sabha TV",
				"Doordarshan" };
		livebottommenulinks = new String[] { "http://rstv.nic.in",
				"http://loksabhatv.nic.in", "http://webcast.gov.in" };
		membertypes = new String[] { "Alphabetical", "State wise",
				"Party wise", "Birthday wise", "Women Members",
				"Nominated Members", "Vacant Consituencies" };
		membertypevalues = new String[] { "a", "s", "p", "b", "w", "n", "v" };
		months = new String[] { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		postMonthLink = new String[] { "janmonth.aspx?monthname=january",
				"febmonth.aspx?monthname=february",
				"marchmonth.aspx?monthname=march",
				"aprilmonth.aspx?monthname=april",
				"maymonth.aspx?monthname=may", "junemonth.aspx?monthname=june",
				"julymonth.aspx?monthname=july",
				"augustmonth.aspx?monthname=august",
				"septmonth.aspx?monthname=september",
				"octmonth.aspx?monthname=october",
				"novmonth.aspx?monthname=november",
				"decmonth.aspx?monthname=december" };
		organisationDesignations = new String[] { "Speaker", "Deputy Speaker",
				"Secretary General", "Secretariat" };
		orgDesignationsValue = new String[] { "s", "d", "g", "o" };
		comtypes = new String[] { "Financial Committees",
				"Standing Committees of Lok Sabha",
				"Department Related Standing Committees (RS)",
				"Department Related Standing Committees (LS)",
				"Joint Committees", "Adhoc Committees", "Select Committees" };
		comLinks = new String[] { "CommitteeMembershipall.aspx?comm=RS",
				"CommitteeMembershipall.aspx?comm=DRSCRS",
				"CommitteeMembershipall.aspx?comm=DRSCRS",
				"CommitteeMembershipall.aspx?comm=DRSCLS",
				"CommitteeMembershipall.aspx?comm=JC",
				"CommitteeMembershipall.aspx?comm=ADHOC",
				"CommitteeMembershipall.aspx?comm=ADHOC" };
		comShortNames = new String[] { "RS", "RS", "DRSCRS", "DRSCLS", "JC",
				"ADHOC", "SELECT" };

	}

	public String[] GetItemArray() {
		return items;
	}

	public String GetMemBerDetailURL(String ID) {
		return urlStart + "member_biography.aspx?member_id=" + ID;
	}

	public String getMemQuestionsURL(String MP_Code) {
		return urlStart + "starred_UnstarredQuestionList.aspx?member_id="
				+ MP_Code;
	}

	public String getDebateUrl(String MP_Code) {
		return urlStart + "member_debates.aspx?member_id=" + MP_Code;
	}

	public String GetMemBioDataURL(String MP_Code) {
		return urlStart + "member_biography.aspx?member_iD=" + MP_Code;
	}

	public String GetStarredQuestionsURL() {
		return urlStart + "SttaredQuestionList.aspx";
	}

	public String GetUnStarredQuestionsURL() {
		return urlStart + "unStarredQuestionList.aspx";
	}

	public String getSpeakerUrl() {
		return urlStart + "Speaker.aspx";
	}

	public String getDeputySpeakerURL() {
		return urlStart + "DySpeaker.aspx";
	}

	public String getSecretaryGeneralURL() {
		return "http://164.100.47.132/ipad_rssfeed/SecretaryGeneral.aspx";
	}

	public String getASJSUrl() {
		return urlStart + "ASJS.aspx";
	}

	public String getDirectorsUrl() {
		return urlStart + "Directors.aspx";
	}

	public static String getPDFReaderLink() {
		return "http://docs.google.com/viewer?url=";
	}

	public String getMemberPictureURL() {
		return "http://164.100.47.132/mpimage/photo/";
	}

	public String getQuestionListUrl() {

		return urlStart + "member_questions.aspx";
	}

	public String getDateWiseUrl() {

		return urlStart + "datewiseMeeting.aspx";
	}
	
	public String getCommitteeWiseUrl() {

		return urlStart + "CommitteeWiseMeetings.aspx";
	}

	public String getMembersLoginUrl() {
		return "http://mpls.nic.in";
	}

	public String getGovtBillsUrl(String mp_code) {
		return urlStart + "member_govtBills.aspx?member_iD=" + mp_code;
	}

	

	
}
