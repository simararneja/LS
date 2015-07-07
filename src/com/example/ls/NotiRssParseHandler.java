package com.example.ls;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class NotiRssParseHandler extends DefaultHandler {
	private List<NotiRssItem> rssItems;
	private NotiRssItem currentItem;
	

	private boolean parsingNData;
	
	public NotiRssParseHandler() {
		rssItems = new ArrayList<NotiRssItem>();
	}
	
	public List<NotiRssItem> getItems() {
		return rssItems;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("NoticeData".equalsIgnoreCase(qName)) {
			currentItem = new NotiRssItem();
			parsingNData = true;
		}
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("NoticeData".equalsIgnoreCase(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} 
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (parsingNData) {
			if (currentItem != null) 
			currentItem.setNData(new String(ch, start, length));
			parsingNData = false;
		}
}

}
