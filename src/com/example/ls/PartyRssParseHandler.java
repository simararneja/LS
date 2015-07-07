package com.example.ls;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class PartyRssParseHandler extends DefaultHandler {

	private List<PartyRssItem> rssItems;
	
	private PartyRssItem currentItem;
	

	private boolean parsingnoofmembers;
	private boolean parsingParty;
	private boolean parsingpartyname;
	
	public PartyRssParseHandler() {
		rssItems = new ArrayList<PartyRssItem>();
	}
	
	public List<PartyRssItem> getItems() {
		return rssItems;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("Member".equalsIgnoreCase(qName)) {
			currentItem = new PartyRssItem();
		}else if ("MemberCount".equalsIgnoreCase(qName)) {
			parsingnoofmembers = true;
		}
		else if ("Partycode".equalsIgnoreCase(qName)) {
			parsingParty = true;
		}
		else if ("Partyname".equalsIgnoreCase(qName)) {
			parsingpartyname = true;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("Member".equalsIgnoreCase(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		}else if ("MemberCount".equalsIgnoreCase(qName)) {
			parsingnoofmembers = false;
		}
		else if ("Partycode".equalsIgnoreCase(qName)) {
			parsingParty = false;
		}
		else if ("Partyname".equalsIgnoreCase(qName)) {
			parsingpartyname = false;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		 if (parsingnoofmembers) {
			if (currentItem != null) 
			{currentItem.setnoofmembers(new String(ch, start, length));
			parsingnoofmembers = false;
			}
		} else if (parsingParty) {
			if (currentItem != null) 
			{currentItem.setParty(new String(ch, start, length));
			parsingParty = false;
			}
		}
		else if (parsingpartyname) {
			if (currentItem != null) 
			{currentItem.setpartyname(new String(ch, start, length));
			parsingpartyname = false;
			}
		}
	}
}
