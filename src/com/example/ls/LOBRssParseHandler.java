package com.example.ls;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LOBRssParseHandler extends DefaultHandler {

	private List<LOBRssItem> rssItems;
	
	private LOBRssItem currentItem;
	private String month;
	

	private boolean parsingdescription;
	private boolean parsingMonthsName;
	private boolean parsingBusinessItem;
	private boolean parsingLink;
	private boolean parsingErrorMessage;
	
	public LOBRssParseHandler() {
		rssItems = new ArrayList<LOBRssItem>();
	}
	
	public List<LOBRssItem> getItems() {
		
		return rssItems;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if("Members".equals(qName)){
			currentItem = new LOBRssItem();
		}
		else if("ErrorMsg".equalsIgnoreCase(qName)){
			parsingErrorMessage = true;		
		}
		if ("monthslot".equals(qName))
		{
			parsingMonthsName = true;
		}
		else if ("Item".equals(qName)) {
			currentItem = new LOBRssItem();
		} else if ("description".equals(qName)) {
			parsingdescription = true;
		}else if ("Link".equals(qName)) {
			parsingLink = true;
		}
		else if ("MonthsName".equals(qName)) {
			parsingMonthsName = true;
		}
		else if ("BusinessList".equals(qName)) {
			parsingBusinessItem = true;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if("Members".equals(qName)){
			rssItems.add(currentItem);
			currentItem = null;
		}
		if("ErrorMsg".equalsIgnoreCase(qName)){
			
			parsingErrorMessage = false;
		
		}
		if ("Item".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if ("description".equals(qName)) {
			parsingdescription = false;
		}else if ("Link".equals(qName)) {
			parsingLink = false;
		}
		else if ("MonthsName".equals(qName)) {
			parsingMonthsName = false;
		}
		else if ("BusinessList".equals(qName)) {
			parsingBusinessItem = false;
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (parsingdescription) {
			if (currentItem != null) 
			currentItem.setdescription(new String(ch, start, length));
		}else if (parsingLink) {
		if (currentItem != null) {
			currentItem.setLink(new String(ch, start, length));
			parsingLink = false;
			}
		}
		else if (parsingMonthsName) {
			this.month = new String(ch, start, length);
			if (currentItem != null) {
				currentItem.setMonthsName(new String(ch, start, length));
				parsingMonthsName = false;
			}
		}
		else if (parsingBusinessItem) {
			if (currentItem != null) {
				currentItem.setBusinessList(new String(ch, start, length));
				currentItem.setMonthsName(this.month);
				parsingBusinessItem = false;
			}
		}
		
		 if (parsingErrorMessage) {
			if (currentItem != null) {
				currentItem.setErrorMessage("Lok Sabha not in session");
				//currentItem.setMonthsName(this.month);
				parsingErrorMessage = false;
			}
		}
}
}
