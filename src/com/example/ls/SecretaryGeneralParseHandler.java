package com.example.ls;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SecretaryGeneralParseHandler extends DefaultHandler {

	private List<SecretaryGeneralRssItem> rssItems;

	private SecretaryGeneralRssItem currentItem;
	private boolean parsingName;
	private boolean parsingEducationQual;
	private boolean parsingPermanentAddress;
	private boolean parsingPermanentphone;
	private boolean parsingOfficeAddress1;
	private boolean parsingOfficephone1;
	private boolean parsingOfficeAddress2;
	private boolean parsingOfficephone2;
	private boolean parsingEmailID;
	private boolean parsingPictureUrl;
	private boolean parsingBiodataUrl;

	public SecretaryGeneralParseHandler() {
		rssItems = new ArrayList<SecretaryGeneralRssItem>();
	}

	public List<SecretaryGeneralRssItem> getItems() {
		return rssItems;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("LokSabha".equals(qName)) {
			currentItem = new SecretaryGeneralRssItem();
		} else if ("Name".equals(qName)) {
			parsingName = true;
		} else if ("EducatQualification".equals(qName)) {
			parsingEducationQual = true;
		} else if ("PermanentAddress".equals(qName)) {
			parsingPermanentAddress = true;
		} else if ("Permanentphone".equals(qName)) {
			parsingPermanentphone = true;
		} else if ("OfficeAddress1".equals(qName)) {
			parsingOfficeAddress1 = true;
		} else if ("Oficephone1".equals(qName)) {
			parsingOfficephone1 = true;
		} else if ("OfficeAddress2".equals(qName)) {
			parsingOfficeAddress2 = true;
		} else if ("Oficephone2".equals(qName)) {
			parsingOfficephone2 = true;
		} else if ("EmailID".equals(qName)) {
			parsingEmailID = true;
		} else if ("PictureUrl".equals(qName)) {
			parsingPictureUrl = true;
		}
		else if ("BiodataUrl".equals(qName)) {
			parsingBiodataUrl = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("LokSabha".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if ("Name".equals(qName)) {
			parsingName = false;
		} else if ("EducatQualification".equals(qName)) {
			parsingEducationQual = false;
		} else if ("PermanentAddress".equals(qName)) {
			parsingPermanentAddress = false;
		} else if ("Permanentphone".equals(qName)) {
			parsingPermanentphone = false;
		} else if ("OfficeAddress1".equals(qName)) {
			parsingOfficeAddress1 = false;
		} else if ("Oficephone1".equals(qName)) {
			parsingOfficephone1 = false;
		} else if ("OfficeAddress2".equals(qName)) {
			parsingOfficeAddress2 = false;
		} else if ("Oficephone2".equals(qName)) {
			parsingOfficephone2 = false;
		} else if ("EmailID".equals(qName)) {
			parsingEmailID = false;
		} else if ("PictureUrl".equals(qName)) {
			parsingPictureUrl = false;
		}
		else if ("BiodataUrl".equals(qName)) {
			parsingBiodataUrl = false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (parsingName) {
			if (currentItem != null)
				currentItem.setName(new String(ch, start, length));
			parsingName = false;
		} else if (parsingEducationQual) {
			if (currentItem != null) {
				currentItem.setEducationQual(new String(ch, start, length));
				parsingEducationQual = false;
			}
		} else if (parsingPermanentAddress) {
			if (currentItem != null) {
				currentItem.setPermanentAddress(new String(ch, start, length));
				parsingPermanentAddress = false;
			}
		} else if (parsingPermanentphone) {
			if (currentItem != null) {
				currentItem.setPermanentphone(new String(ch, start, length));
				parsingPermanentphone = false;
			}
		} else if (parsingOfficeAddress1) {
			if (currentItem != null) {
				currentItem.setOfficeAddress1(new String(ch, start, length));
				parsingOfficeAddress1 = false;
			}
		} else if (parsingOfficephone1) {
			if (currentItem != null) {
				currentItem.setOfficephone1(new String(ch, start, length));
				parsingOfficephone1 = false;
			}
		}
		else if (parsingOfficeAddress2) {
			if (currentItem != null) {
				currentItem.setOfficeAddress2(new String(ch, start, length));
				parsingOfficeAddress2 = false;
			}
		}
		else if (parsingOfficephone2) {
			if (currentItem != null) {
				currentItem.setOfficephone2(new String(ch, start, length));
				parsingOfficephone2 = false;
			}
		}
		else if (parsingEmailID) {
			if (currentItem != null) {
				currentItem.setEmailID(new String(ch, start, length));
				parsingEmailID = false;
			}
		}
		else if (parsingPictureUrl) {
			if (currentItem != null) {
				currentItem.setictureUrl(new String(ch, start, length));
				parsingPictureUrl = false;
			}
		}
		else if (parsingBiodataUrl) {
			if (currentItem != null) {
				currentItem.setBiodataUrl(new String(ch, start, length));
				parsingBiodataUrl = false;
			}
		}
	}
}