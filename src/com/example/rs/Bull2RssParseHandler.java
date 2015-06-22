package com.example.rs;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Bull2RssParseHandler extends DefaultHandler {

	private List<Bull2RssItem> rssItems;
	
	private Bull2RssItem currentItem;
	private String month;
	private String session;
	

	private boolean parsingdescription;
	private boolean parsingMonthsName;
	private boolean parsingBusinessItem;
	private boolean parsingLink;
	private boolean parsingSession;
	private boolean parsingDslot;
	private boolean parsingdate_slot;
	
	public Bull2RssParseHandler() {
		rssItems = new ArrayList<Bull2RssItem>();
	}
	
	public List<Bull2RssItem> getItems() {
		return rssItems;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("Session".equals(qName))
		{
			parsingSession = true;
		}
		else if ("Link".equals(qName)) {
			parsingLink = true;
		}
		else if ("MonthsName".equals(qName)) {
			parsingMonthsName = true;
		}
		else if ("Dslot".equals(qName)) {
			parsingDslot = true;
		}
		else if ("Link".equals(qName)) {
			parsingLink = true;
		}
		else if ("date_slot".equals(qName)) {
			parsingdate_slot = true;
			currentItem = new Bull2RssItem();
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
	
		if ("Session".equals(qName))
		{
			parsingSession = false;
		}
		else if ("Link".equals(qName)) {
			parsingLink = false;
		}
		else if ("MonthsName".equals(qName)) {
			parsingMonthsName = false;
		}
		else if ("Dslot".equals(qName)) {
			parsingDslot = false;
		}
		else if ("Link".equals(qName)) {
			parsingLink = false;
		}
		else if ("date_slot".equals(qName)) {
			parsingdate_slot = false;
			rssItems.add(currentItem);
			currentItem = null;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (parsingMonthsName) {
			this.month = new String(ch, start, length);
			parsingMonthsName=false;
		}
		else if (parsingSession) {
				this.session = new String(ch, start, length);
				parsingSession = false;
		}
		else if (parsingDslot) {
			if (currentItem != null) {
				currentItem.setDslot(new String(ch, start, length));
				currentItem.setMonthsName(this.month);
				currentItem.setSession(this.session);
				parsingBusinessItem = false;
			}
		}
		else if (parsingLink) {
			if (currentItem != null) {
				currentItem.setLink(new String(ch, start, length));
				parsingLink = false;
			}
		}
}
}
