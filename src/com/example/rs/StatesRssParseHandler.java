package com.example.rs;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class StatesRssParseHandler extends DefaultHandler {

	private List<StatesRssItem> rssItems;
	
	private StatesRssItem currentItem;
	

	private boolean parsingnoofmembers;
	private boolean parsingstates;
	private boolean parsingstatename;
	
	public StatesRssParseHandler() {
		rssItems = new ArrayList<StatesRssItem>();
	}
	
	public List<StatesRssItem> getItems() {
		return rssItems;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("Member".equalsIgnoreCase(qName)) {
			currentItem = new StatesRssItem();
		}else if ("noofmembers".equalsIgnoreCase(qName)) {
			parsingnoofmembers = true;
		}
		else if ("state".equalsIgnoreCase(qName)) {
			parsingstates = true;
		}
		else if ("statename".equalsIgnoreCase(qName)) {
			parsingstatename = true;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("Member".equalsIgnoreCase(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		}else if ("noofmembers".equalsIgnoreCase(qName)) {
			parsingnoofmembers = false;
		}
		else if ("state".equalsIgnoreCase(qName)) {
			parsingstates = false;
		}
		else if ("statename".equalsIgnoreCase(qName)) {
			parsingstatename = false;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		 if (parsingnoofmembers) {
			if (currentItem != null) 
			{currentItem.setnoofmembers(new String(ch, start, length));
			parsingnoofmembers = false;
			}
		} else if (parsingstates) {
			if (currentItem != null) 
			{currentItem.setstates(new String(ch, start, length));
			parsingstates = false;
			}
		}
		else if (parsingstatename) {
			if (currentItem != null) 
			{currentItem.setstatename(new String(ch, start, length));
			parsingstatename = false;
			}
		}
	}
}
