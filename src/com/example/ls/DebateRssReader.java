package com.example.ls;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class DebateRssReader {
	
	private String rssUrl;


	public DebateRssReader(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	public List<DebateRssItem> getItems() throws Exception {
		// SAX parse RSS data
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		DebateRssParseHandler handler = new DebateRssParseHandler();
		
		saxParser.parse(rssUrl, handler);

		return handler.getItems();
		
	}

	

}
