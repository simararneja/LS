package com.example.ls;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MeetingRssReader {
	private String rssUrl;


	public MeetingRssReader(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	public List<MeetingRssItem> getItems() throws Exception {
		// SAX parse RSS data
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		MeetingRssParseHandler handler = new MeetingRssParseHandler();
		
		saxParser.parse(rssUrl, handler);

		return handler.getItems();
		
	}
}
