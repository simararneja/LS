package com.example.ls;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;




public class SQLRssReader {
	
	private String rssUrl;

	
	public SQLRssReader(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	
	public List<SQLRssItem> getItems() throws Exception {
		// SAX parse RSS data
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		SQLRssParseHandler handler = new SQLRssParseHandler();
		
		saxParser.parse(rssUrl, handler);

		return handler.getItems();
		
	}

	

}
