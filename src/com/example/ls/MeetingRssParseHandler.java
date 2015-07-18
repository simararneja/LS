package com.example.ls;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MeetingRssParseHandler extends DefaultHandler {

	private List<MeetingRssItem> rssItems;

	private MeetingRssItem currentItem;
	private boolean parsingComName;
	private boolean parsingDate;
	private boolean parsingTime;
	private boolean parsingVenuePlace;
	private boolean parsingAgenda;

	public MeetingRssParseHandler() {
		rssItems = new ArrayList<MeetingRssItem>();
	}

	public List<MeetingRssItem> getItems() {
		return rssItems;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("CommitteeMeetings".equals(qName) || "MeetingsDate".equals(qName) ) {
			currentItem = new MeetingRssItem();
		} 
		else if ("CommitteeName".equals(qName)){
			
			parsingComName = true;
		}
		else if ("date".equals(qName)){
			
			parsingDate = true;
		}
		else if ("Time".equals(qName)){
			
			parsingTime = true;
		}
		else if ("Vanueplace".equals(qName))
			parsingVenuePlace = true;
		else if ("Agenda".equals(qName))
			parsingAgenda = true;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("CommitteeMeetings".equals(qName)|| "MeetingsDate".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if ("CommitteeName".equals(qName))
			parsingComName = false;
		else if ("date".equals(qName))
			parsingDate = false;
		else if ("Time".equals(qName))
			parsingTime = false;
		else if ("Vanueplace".equals(qName))
			parsingVenuePlace = false;
		else if ("Agenda".equals(qName))
			parsingAgenda = false;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (parsingComName) {
			if (currentItem != null){				
				currentItem.setComName(new String(ch, start, length));
				parsingComName = false;
			}
		} else if (parsingDate) {
			if (currentItem != null){				
				currentItem.setDate(new String(ch, start, length));
				parsingDate = false;
			}
		}
		else if (parsingTime) {
			if (currentItem != null){				
				currentItem.setTime(new String(ch, start, length));
				parsingTime = false;
			}
		}
		else if (parsingVenuePlace) {
			if (currentItem != null){				
				currentItem.setVenuePlace(new String(ch, start, length));
				parsingVenuePlace = false;
			}
		}
		else if (parsingAgenda) {
			if (currentItem != null){				
				currentItem.setAgenda(new String(ch, start, length));
				parsingAgenda = false;
			}
		}
	}

}
