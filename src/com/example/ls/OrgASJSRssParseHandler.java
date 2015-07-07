package com.example.ls;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class OrgASJSRssParseHandler extends DefaultHandler {
	private List<OrgASJSRssItem> rssItems;
	private OrgASJSRssItem currentItem;
	

	private boolean parsingename;
	private boolean parsingdesignation;
	private boolean parsingworkresponsibility;
	private boolean parsingcategorycode;
	private boolean parsingeaddress;
	private boolean parsingtelephone1;
	private boolean parsingtelephone2;
	private boolean parsingtelephone3;
	private boolean parsingfax;
	private boolean parsingemailid;
	
	public OrgASJSRssParseHandler() {
		rssItems = new ArrayList<OrgASJSRssItem>();
	}
	
	public List<OrgASJSRssItem> getItems() {
		return rssItems;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("Employee".equals(qName)) {
			currentItem = new OrgASJSRssItem();
		}
		else if ("Ename".equals(qName)) {
			parsingename = true;
		}
		else if ("designation".equals(qName)) {
			parsingdesignation = true;
		}
		else if ("WorkResponsiblity".equals(qName)) {
			parsingworkresponsibility = true;
		}
		else if ("categorycode".equals(qName)) {
			parsingcategorycode = true;
		}
		else if ("Eaddress".equals(qName)) {
			parsingeaddress = true;
		}
		else if ("Telephone1".equals(qName)) {
			parsingtelephone1 = true;
		}
		else if ("Telephone2".equals(qName)) {
			parsingtelephone2 = true;
		}
		else if ("Telephone3".equals(qName)) {
			parsingtelephone3 = true;
		}
		else if ("fax".equals(qName)) {
			parsingfax = true;
		}
		else if ("EmailID".equals(qName)) {
			parsingemailid = true;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("Employee".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} 
		else if ("Ename".equals(qName)) {
			parsingename = false;
		}
		else if ("designation".equals(qName)) {
			parsingdesignation = false;
		}
		else if ("WorkResponsiblity".equals(qName)) {
			parsingworkresponsibility = false;
		}
		else if ("categorycode".equals(qName)) {
			parsingcategorycode = false;
		}
		else if ("Eaddress".equals(qName)) {
			parsingeaddress = false;
		}
		else if ("Telephone1".equals(qName)) {
			parsingtelephone1 = false;
		}else if ("Telephone2".equals(qName)) {
			parsingtelephone2 = false;
		}
		else if ("Telephone3".equals(qName)) {
			parsingtelephone3 = false;
		}
		else if ("fax".equals(qName)) {
			parsingfax = false;
		}
		else if ("EmailID".equals(qName)) {
			parsingemailid = false;
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (parsingename) {
			if (currentItem != null) 
			currentItem.setename(new String(ch, start, length));
		}
		else if (parsingdesignation) 
		{
			if (currentItem != null)
			{
				currentItem.setdesignation(new String(ch, start, length));
				parsingdesignation = false;
			}
	    }
		else if (parsingworkresponsibility) {
			if (currentItem != null) {
				currentItem.setworkresponsibility(new String(ch, start, length));
				parsingworkresponsibility = false;
			}
		}
		else if (parsingcategorycode) 
		{
			if (currentItem != null) {
				currentItem.setcategorycode(new String(ch, start, length));
				parsingcategorycode = false;
			}
		}
		else if (parsingeaddress) 
		{
			if (currentItem != null) {
				currentItem.seteaddress(new String(ch, start, length));
				parsingeaddress = false;
			}
		}
		else if (parsingtelephone1) 
		{
			if (currentItem != null) {
				currentItem.settelephone1(new String(ch, start, length));
				parsingtelephone1 = false;
			}
		}
		else if (parsingtelephone2) 
		{
			if (currentItem != null) {
				currentItem.settelephone2(new String(ch, start, length));
				parsingtelephone2 = false;
			}
		}
		else if (parsingtelephone3) 
		{
			if (currentItem != null) {
				currentItem.settelephone3(new String(ch, start, length));
				parsingtelephone3 = false;
			}
		}
		else if (parsingfax) 
		{
			if (currentItem != null) {
				currentItem.setfax(new String(ch, start, length));
				parsingfax = false;
			}
		}
		else if (parsingemailid) 
		{
			if (currentItem != null) {
				currentItem.setemailid(new String(ch, start, length));
				parsingemailid = false;
			}
		}
}

}
