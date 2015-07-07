package com.example.ls;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class MDRssParseHandler extends DefaultHandler {

	private List<MDRssItem> rssItems;
	
	private MDRssItem currentItem;
	

	private boolean parsingSno;
	private boolean parsingMPCode;
	private boolean parsingGender;
	private boolean parsingMemberName;
	private boolean parsingParty;
	private boolean parsingPartyname;
	private boolean parsingState;
	private boolean parsingstateName;
	private boolean parsingPermanentAddress;
	private boolean parsingPermanentphone;
	private boolean parsingLocalAddress;
	private boolean parsingLocalphone;
	private boolean parsingEmailID;
	
	public MDRssParseHandler() {
		rssItems = new ArrayList<MDRssItem>();
	}
	
	public String[] getItems() {
		//return rssItems;
		String[] MemDetails = new String[2];
		MDRssItem tempitem = rssItems.get(0); 
		MemDetails[0] = tempitem.getMemberName();
		MemDetails[1] = tempitem.getStateName();
		return MemDetails;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("Member".equals(qName)) {
			currentItem = new MDRssItem();
		} else if ("Sno".equals(qName)) {
			parsingSno = true;
		} else if ("Gender".equals(qName)) {
			parsingGender = true;
		}else if ("MPCode".equals(qName)) {
			parsingMPCode = true;
		}else if ("MemberName".equals(qName)) {
			parsingMemberName = true;
		}else if ("Party".equals(qName)) {
			parsingParty = true;
		}else if ("Partyname".equals(qName)) {
			parsingPartyname = true;
		}else if ("State".equals(qName)) {
			parsingState = true;
		}else if ("stateName".equals(qName)) {
			parsingstateName = true;
		}else if ("PermanentAddress".equals(qName)) {
			parsingPermanentAddress = true;
		}else if ("Permanentphone".equals(qName)) {
			parsingPermanentphone = true;
		}else if ("LocalAddress".equals(qName)) {
			parsingLocalAddress = true;
		}else if ("Localphone".equals(qName)) {
			parsingLocalphone = true;
		}else if ("EmailID".equals(qName)) {
			parsingEmailID = true;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("Member".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if ("Sno".equals(qName)) {
			parsingSno = false;
		} else if ("Gender".equals(qName)) {
			parsingGender = false;
		}else if ("MPCode".equals(qName)) {
			parsingMPCode = false;
		}else if ("MemberName".equals(qName)) {
			parsingMemberName = false;
		}else if ("Party".equals(qName)) {
			parsingParty = false;
		}else if ("Partyname".equals(qName)) {
			parsingPartyname = false;
		}else if ("State".equals(qName)) {
			parsingState = false;
		}else if ("stateName".equals(qName)) {
			parsingstateName = false;
		}else if ("PermanentAddress".equals(qName)) {
			parsingPermanentAddress = false;
		}else if ("Permanentphone".equals(qName)) {
			parsingPermanentphone = false;
		}else if ("LocalAddress".equals(qName)) {
			parsingLocalAddress = false;
		}else if ("Localphone".equals(qName)) {
			parsingLocalphone = false;
		}else if ("EmailID".equals(qName)) {
			parsingEmailID = false;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (parsingSno) {
			if (currentItem != null) 
			currentItem.setSno(new String(ch, start, length));
		}else if (parsingMPCode) {
		if (currentItem != null) 
			currentItem.setMPCode(new String(ch, start, length));
	}else if (parsingGender) {
		if (currentItem != null) 
			currentItem.setGender(new String(ch, start, length));
	}else if (parsingMemberName) {
		if (currentItem != null) 
			currentItem.setMemberName(new String(ch, start, length));
	}else if (parsingParty) {
		if (currentItem != null) 
			currentItem.setParty(new String(ch, start, length));
	}else if (parsingPartyname) {
		if (currentItem != null) 
			currentItem.setPartyname(new String(ch, start, length));
	}else if (parsingState) {
		if (currentItem != null) 
			currentItem.setState(new String(ch, start, length));
	}else if (parsingstateName) {
		if (currentItem != null) 
			currentItem.setStateName(new String(ch, start, length));
	}else if (parsingPermanentAddress) {
		if (currentItem != null) 
			currentItem.setPermanentAddress(new String(ch, start, length));
	}else if (parsingPermanentphone) {
		if (currentItem != null) 
			currentItem.setPermanentphone(new String(ch, start, length));
	}else if (parsingLocalAddress) {
		if (currentItem != null) 
			currentItem.setLocalAddress(new String(ch, start, length));
	}else if (parsingLocalphone) {
		if (currentItem != null) 
			currentItem.setLocalphone(new String(ch, start, length));
	}else if (parsingEmailID) {
		if (currentItem != null) 
			currentItem.setEmailID(new String(ch, start, length));
	}
}
}
