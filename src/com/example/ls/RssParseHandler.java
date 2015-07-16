package com.example.ls;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RssParseHandler extends DefaultHandler {

	private List<RssItem> rssItems;

	private RssItem currentItem;

	private boolean parsingdescription;
	/*
	 * private boolean parsingMonthsName; private boolean parsingBusinessItem;
	 */
	private boolean parsingLink;
	private boolean parsingHead;

	public RssParseHandler() {
		rssItems = new ArrayList<RssItem>();
	}

	public List<RssItem> getItems() {
		return rssItems;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("Item".equalsIgnoreCase(qName)) {
			currentItem = new RssItem();
		} else if ("description".equalsIgnoreCase(qName)
				|| "Description".equalsIgnoreCase(qName)) {
			parsingdescription = true;
		}
		if ("Link".equalsIgnoreCase(qName)) {
			parsingLink = true;
		} else if ("Title".equals(qName) || "Head".equals(qName)) {
			parsingHead = true;
		}
		
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("Item".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if ("description".equals(qName)
				|| "Description".equalsIgnoreCase(qName)) {
			parsingdescription = false;
		} else if ("Link".equals(qName)) {
			parsingLink = false;
		} else if ("Title".equals(qName) || "Head".equals(qName)) {
			parsingHead = false;
		}
		/*
		 * else if ("MonthsName".equals(qName)) { parsingMonthsName = false; }
		 * else if ("BusinessList".equals(qName)) { parsingBusinessItem = false;
		 * }
		 */
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (parsingdescription) {
			if (currentItem != null)
				currentItem.setdescription(new String(ch, start, length));
		} else if (parsingLink) {
			if (currentItem != null) {
				currentItem.setLink(new String(ch, start, length));
				parsingLink = false;
			}
		} else if (parsingHead){
			if(currentItem!=null){
				currentItem.setHead(new String(ch,start,length));
				parsingHead= false;
			}
		}

	}
}
