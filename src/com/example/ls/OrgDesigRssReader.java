package com.example.ls;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class OrgDesigRssReader {
	
	private String rssUrl;

	
	public OrgDesigRssReader(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	public List<OrgDesigRssItem> getItems() throws Exception {
		// SAX parse RSS data
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		OrgDesigRssParseHandler handler = new OrgDesigRssParseHandler();
		
		saxParser.parse(rssUrl, handler);

		return handler.getItems();
		
	}

}
