package com.example.ls;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class AssuRssReader {
	
	private String rssUrl;


	public AssuRssReader(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	public List<AssuRssItem> getItems() throws Exception {
		// SAX parse RSS data
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		AssuRssParseHandler handler = new AssuRssParseHandler();
		
		saxParser.parse(rssUrl, handler);

		return handler.getItems();
		
	}

	

}
