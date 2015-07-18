package com.example.ls;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class MemberBioDataRssReader {
	
	private String rssUrl;

	public MemberBioDataRssReader(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	
	public List<MBRssItem> getItems() throws Exception {
		// SAX parse RSS data
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		MemberBioDataRssParseHandler handler = new MemberBioDataRssParseHandler();
		
		saxParser.parse(rssUrl, handler);

		return handler.getItems();
		
	}

}
