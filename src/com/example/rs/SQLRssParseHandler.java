package com.example.rs;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SQLRssParseHandler extends DefaultHandler {

	private List<SQLRssItem> rssItems;
	
	private SQLRssItem currentItem;
	private String monthName;
	private String Session;

	private boolean parsingDslot;
	private boolean parsingMonthsName;
	private boolean parsinglink;
	private boolean parsingSession;
	
	public SQLRssParseHandler() {
		rssItems = new ArrayList<SQLRssItem>();
	}
	
	public List<SQLRssItem> getItems() {
		return rssItems;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if("Session".equalsIgnoreCase(qName))
		{
			parsingSession = true;
		}
		else if ("date_slot".equals(qName)) {
			currentItem = new SQLRssItem();
		}else if ("Dslot".equals(qName)) {
			parsingDslot = true;
		}else if ("link".equals(qName)) {
			parsinglink = true;
		}
		else if ("monthsname".equalsIgnoreCase(qName)) {
			parsingMonthsName = true;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if("Session".equalsIgnoreCase(qName))
		{
			parsingSession = false;
		}
		else if ("date_slot".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if ("Dslot".equals(qName)) {
			parsingDslot = false;
		} else if ("link".equals(qName)) {
			parsinglink = false;
		}
		else if ("monthsname".equalsIgnoreCase(qName)) {
			parsingMonthsName = false;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		 if (parsingDslot) {
			if (currentItem != null) 
			currentItem.setDslot(monthName + " " + new String(ch, start, length) +" - Session: "+ Session);
		}else if (parsinglink) {
		if (currentItem != null) {
			currentItem.setlink(new String(ch, start, length));
			parsinglink = false;
		}
		}
		else if (parsingMonthsName) {
			monthName = new String(ch, start, length);
			parsingMonthsName = false;
		}
		else if (parsingSession) {
			Session = new String(ch, start, length);
			parsingSession = false;
		}
}
}
