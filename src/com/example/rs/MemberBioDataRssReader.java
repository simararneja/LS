package com.example.rs;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Class reads RSS data.
 * 
 * @author ITCuties
 *
 */
public class MemberBioDataRssReader {
	
	private String rssUrl;

	/**
	 * Constructor
	 * 
	 * @param rssUrl
	 */
	public MemberBioDataRssReader(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	/**
	 * Get RSS items.
	 * 
	 * @return
	 */
	public List<MBRssItem> getItems() throws Exception {
		// SAX parse RSS data
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		MemberBioDataRssParseHandler handler = new MemberBioDataRssParseHandler();
		
		saxParser.parse(rssUrl, handler);

		return handler.getItems();
		
	}

}
