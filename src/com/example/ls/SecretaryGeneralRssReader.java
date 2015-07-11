package com.example.ls;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SecretaryGeneralRssReader {
	private String rssUrl;

	
	public SecretaryGeneralRssReader(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	public List<SecretaryGeneralRssItem> getItems() throws Exception {
		// SAX parse RSS data
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		SecretaryGeneralParseHandler handler = new SecretaryGeneralParseHandler();
		
		saxParser.parse(rssUrl, handler);

		return handler.getItems();
		
	}

}
