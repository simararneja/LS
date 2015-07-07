package com.example.ls;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class CommMemRssParseHandler extends DefaultHandler {

	private List<CommMemRssItem> rssItems;
	
	private CommMemRssItem currentItem;
	

	private boolean parsingCommName;
	private boolean parsingChairMan;
	private boolean parsingMemName;
	
	public CommMemRssParseHandler() {
		rssItems = new ArrayList<CommMemRssItem>();
	}
	
	public List<CommMemRssItem> getItems() {
		return rssItems;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("Committees".equals(qName)) {
			currentItem = new CommMemRssItem();
		} else if ("comname".equals(qName)) {
			parsingCommName = true;
		} else if ("chairman".equals(qName)) {
			parsingChairMan = true;
		}else if ("memname".equals(qName)) {
			parsingMemName = true;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("Committees".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		}  else if ("comname".equals(qName)) {
			parsingCommName = false;
		} else if ("chairman".equals(qName)) {
			parsingChairMan = false;
		}else if ("memname".equals(qName)) {
			parsingMemName = false;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (parsingCommName) {
			if (currentItem != null) 
			{currentItem.setComName(new String(ch, start, length));
			parsingCommName = false;}
		}else if (parsingChairMan) {
		if (currentItem != null) 
		{
			parsingChairMan = false;
			currentItem.setChairMan(new String(ch, start, length));
		}
	}else if (parsingMemName) {
		if (currentItem != null) 
		{
			parsingMemName = false;
			currentItem.setMemName(currentItem.getMemName()+","+ new String(ch, start, length));
		}
	}
}
}
