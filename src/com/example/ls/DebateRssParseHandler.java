package com.example.ls;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DebateRssParseHandler extends DefaultHandler {

	private List<DebateRssItem> rssItems;

	private DebateRssItem currentItem;

	private boolean parsingMemberid;
	private boolean parsingDebateid;
	private boolean parsinglink;
	private boolean parsingDebateType;
	private boolean parsingTitle;
	private boolean parsingDate;
	private boolean parsingParticipants;
	private boolean parsingRefKeywords;

	public DebateRssParseHandler() {
		rssItems = new ArrayList<DebateRssItem>();
	}

	public List<DebateRssItem> getItems() {
		return rssItems;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("Member".equalsIgnoreCase(qName)) {
			currentItem = new DebateRssItem();
		} else if ("memberid".equalsIgnoreCase(qName)) {
			parsingMemberid = true;
		} else if ("debateid".equalsIgnoreCase(qName)) {
			parsingDebateid = true;
		} else if ("debatetype".equalsIgnoreCase(qName)) {
			parsingDebateType = true;
		} else if ("Title".equalsIgnoreCase(qName)) {
			parsingTitle = true;
		} else if ("date".equalsIgnoreCase(qName)) {
			parsingDate = true;
		} else if ("participants".equalsIgnoreCase(qName)) {
			parsingParticipants = true;
		} else if ("refKeywords".equalsIgnoreCase(qName)) {
			parsingRefKeywords = true;
		} else if ("Link".equalsIgnoreCase(qName)) {
			parsinglink = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("Member".equalsIgnoreCase(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if ("memberid".equalsIgnoreCase(qName)) {
			parsingMemberid = false;
		} else if ("debateid".equalsIgnoreCase(qName)) {
			parsingDebateid = false;
		} else if ("debatetype".equalsIgnoreCase(qName)) {
			parsingDebateType = false;
		} else if ("Title".equalsIgnoreCase(qName)) {
			parsingTitle = false;
		} else if ("date".equalsIgnoreCase(qName)) {
			parsingDate = false;
		} else if ("participants".equalsIgnoreCase(qName)) {
			parsingParticipants = false;
		} else if ("refKeywords".equalsIgnoreCase(qName)) {
			parsingRefKeywords = false;
		} else if ("Link".equalsIgnoreCase(qName)) {
			parsinglink = false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (parsingMemberid) {
			if (currentItem != null)
				currentItem.setMemberid(new String(ch, start, length));
		} else if (parsinglink) {
			if (currentItem != null) {
				currentItem.setlink(new String(ch, start, length));
				parsinglink = false;
			}
		} else if (parsingDebateType) {
			if (currentItem != null) {
				currentItem.setDebateType(new String(ch, start, length));
				parsingDebateType = false;
			}
		} else if (parsingTitle) {
			if (currentItem != null) {
				currentItem.setTitle(new String(ch, start, length));
				parsingTitle = false;
			}
		} else if (parsingDate) {
			if (currentItem != null) {
				currentItem.setDate(new String(ch, start, length));
				parsingDate = false;
			}
		} else if (parsingDebateid) {
			if (currentItem != null) {
				currentItem.setDebateid(new String(ch, start, length));
				parsingDebateid = false;
			}
		} else if (parsingParticipants) {
			if (currentItem != null) {
				currentItem.setParticipants(new String(ch, start, length));
				parsingParticipants = false;
			}
		} else if (parsingRefKeywords) {
			if (currentItem != null) {
				currentItem.setKeywords(new String(ch, start, length));
				parsingRefKeywords = false;
			}
		}

	}
}
