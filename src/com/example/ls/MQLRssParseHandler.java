package com.example.ls;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MQLRssParseHandler extends DefaultHandler {

	private List<MQLRssItem> rssItems;

	private MQLRssItem currentItem;

	private boolean parsinglink;
	private boolean parsingqid;
	private boolean parsingministryName;
	private boolean parsingsubject;
	private boolean parsingqtype;
	private boolean parsingqses_no;
	private boolean parsingErrorMessage;

	public MQLRssParseHandler() {
		rssItems = new ArrayList<MQLRssItem>();
	}

	public List<MQLRssItem> getItems() {
		return rssItems;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("ErrorMessage".equals(qName) || "errorMessage".equals(qName)
				|| "ErrorMsg".equals(qName)) {
			currentItem = new MQLRssItem();
			parsingErrorMessage = true;
		} else {

			if ("question".equalsIgnoreCase(qName) || "Member".equals(qName)) {
				currentItem = new MQLRssItem();
			} else if ("qid".equalsIgnoreCase(qName)) {
				parsingqid = true;
			} else if ("Link".equalsIgnoreCase(qName)) {
				parsinglink = true;
			} else if ("subject".equalsIgnoreCase(qName)) {
				parsingsubject = true;
			} else if ("ministryname".equalsIgnoreCase(qName)) {
				parsingministryName = true;
			} else if ("qtype".equalsIgnoreCase(qName)) {
				parsingqtype = true;
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("ErrorMessage".equals(qName) || "errorMessage".equals(qName)
				|| "ErrorMsg".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
			parsingErrorMessage = false;
		} else {

			if ("question".equalsIgnoreCase(qName) || "Member".equals(qName)) {
				rssItems.add(currentItem);
				currentItem = null;
			} else if ("qid".equalsIgnoreCase(qName)) {
				parsingqid = false;
			} else if ("Link".equalsIgnoreCase(qName)) {
				parsinglink = false;
			} else if ("subject".equalsIgnoreCase(qName)) {
				parsingsubject = false;
			} else if ("ministryname".equalsIgnoreCase(qName)) {
				parsingministryName = false;
			} else if ("qtype".equalsIgnoreCase(qName)) {
				parsingqtype = false;
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(parsingErrorMessage){
			if(currentItem!=null){
				currentItem.setErrorMessage(new String(ch, start, length));
			}
		}
		if (parsingqid) {
			if (currentItem != null)
				currentItem.setqid(new String(ch, start, length));
		} else if (parsinglink) {
			if (currentItem != null) {
				currentItem.setlink(new String(ch, start, length));
				parsinglink = false;
			}
		} else if (parsingministryName) {
			if (currentItem != null) {
				currentItem.setministryName(new String(ch, start, length));
				parsingministryName = false;
			}
		} else if (parsingsubject) {
			if (currentItem != null) {
				currentItem.setsubject(new String(ch, start, length));
				parsingsubject = false;
			}
		} else if (parsingqtype) {
			if (currentItem != null) {
				currentItem.setqtype(new String(ch, start, length));
				parsingqtype = false;
			}
		}
	}
}
