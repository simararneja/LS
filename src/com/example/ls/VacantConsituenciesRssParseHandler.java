package com.example.ls;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class VacantConsituenciesRssParseHandler extends DefaultHandler {
	private List<VacantConsituenciesRSSItem> rssItems;

	private VacantConsituenciesRSSItem currentItem;
	public boolean parsingConstituencyAndState;

	public VacantConsituenciesRssParseHandler() {
		rssItems = new ArrayList<VacantConsituenciesRSSItem>();
	}

	public List<VacantConsituenciesRSSItem> getItems() {
		return rssItems;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("Member".equalsIgnoreCase(qName)) {
			currentItem = new VacantConsituenciesRSSItem();
		} else if ("ConstituencyAndState".equalsIgnoreCase(qName)) {
			parsingConstituencyAndState = true;
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("Member".equalsIgnoreCase(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if ("ConstituencyAndState".equalsIgnoreCase(qName)) {
			parsingConstituencyAndState = false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (parsingConstituencyAndState) {
			if (currentItem != null) {
				currentItem.setConstituencyAndState(new String(ch, start,
						length));
				parsingConstituencyAndState = false;
			}
		}

	}
}
