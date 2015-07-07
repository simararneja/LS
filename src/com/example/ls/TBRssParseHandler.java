package com.example.ls;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TBRssParseHandler extends DefaultHandler {

	private List<TBRssItem> rssItemsList;

	private TBRssItem currentItem;

	private boolean parsingName;
	private boolean parsingPartyName;
	private boolean parsingCAS;
	private boolean parsingPicture;
	private boolean parsingEmail;
	private boolean parsingDOB;

	public TBRssParseHandler() {
		rssItemsList = new ArrayList<TBRssItem>();
	}

	public List<TBRssItem> getItems() {
		return rssItemsList;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("MemberName".equalsIgnoreCase(qName)) {
			parsingName = true;
		} else if ("Member".equals(qName)) {
			currentItem = new TBRssItem();
		} else if ("ConstituencyAndState".equalsIgnoreCase(qName)) {
			parsingCAS = true;
		} else if ("Partyname".equalsIgnoreCase(qName)) {
			parsingPartyName = true;
		} else if ("Link".equalsIgnoreCase(qName)) {
			parsingPicture = true;
		} else if ("EmailID".equals(qName)) {
			parsingEmail = true;
		} else if ("dob".equals(qName)) {
			parsingDOB = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("MemberName".equalsIgnoreCase(qName)) {
			parsingName = false;
		} else if ("Member".equals(qName)) {
			rssItemsList.add(currentItem);
			currentItem = null;
		} else if ("ConstituencyAndState".equalsIgnoreCase(qName)) {
			parsingCAS = false;
		} else if ("Partyname".equalsIgnoreCase(qName)) {
			parsingPartyName = false;
		} else if ("Link".equalsIgnoreCase(qName)) {
			parsingPicture = false;
		} else if ("EmailID".equalsIgnoreCase(qName)) {
			parsingPicture = false;
		} else if ("dob".equalsIgnoreCase(qName)) {
			parsingPicture = false;
		}

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (parsingName) {
			if (currentItem != null) {
				currentItem.setMemberName(new String(ch, start, length));
			}
			parsingName = false;
		} else if (parsingPartyName) {
			if (currentItem != null) {
				currentItem.setPartyName(new String(ch, start, length));
				parsingPartyName = false;
			}
		} else if (parsingCAS) {
			if (currentItem != null)
				currentItem.setConstituencyAndState(new String(ch, start,
						length));
			parsingCAS = false;
		} else if (parsingPicture) {
			if (currentItem != null)
				currentItem.setPictureURL(new String(ch, start, length));
			parsingPicture = false;
		} else if (parsingEmail) {
			if (currentItem != null)
				currentItem.setEmailID(new String(ch, start, length));
			parsingEmail = false;
		} else if (parsingDOB) {
			if (currentItem != null)
				currentItem.setDOB(new String(ch, start, length));
			parsingEmail = false;
		}
	}
}
