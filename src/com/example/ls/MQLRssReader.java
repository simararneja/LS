package com.example.ls;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class MQLRssReader {
	
	private String rssUrl;


	public MQLRssReader(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	public List<MQLRssItem> getItems() throws Exception {
		// SAX parse RSS data
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		MQLRssParseHandler handler = new MQLRssParseHandler();
		
		saxParser.parse(rssUrl, handler);

		return handler.getItems();
		
	}

	

}
