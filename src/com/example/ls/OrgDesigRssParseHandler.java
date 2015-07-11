package com.example.ls;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class OrgDesigRssParseHandler extends DefaultHandler {

	private List<OrgDesigRssItem> rssItems;

	private OrgDesigRssItem currentItem;

	private boolean parsingName;
	private boolean parsingFatherName;
	private boolean parsingMotherName;
	private boolean parsingDOB;
	private boolean parsingPlaceOfBirth;
	private boolean parsingMaritalStatus;
	private boolean parsingSpouseName;
	private boolean parsingEducatQualification;
	private boolean parsingPermanentAddress;
	private boolean parsingPermanentphone;
	private boolean parsingLocalAddress;
	private boolean parsingLocalphone;
	private boolean parsingEmailID;
	private boolean parsingPositionHeld;
	private boolean parsingFax;
	private boolean parsingLanguagesKnown;
	private boolean parsingSpecialInterests;
	private boolean parsingFavouritePasstime;
	private boolean parsingOtherInfo;
	private boolean parsingCountryVisite;
	private boolean parsingPictureUrl;
	private boolean parsingProfession;
	private boolean parsingSocialActivity;
	private boolean parsingPartyName;
	private boolean parsingStateName;
	private boolean parsingNoOfChildren;
	private boolean parsingBooks;
	private boolean parsingFavouritePassTime;

	public OrgDesigRssParseHandler() {
		rssItems = new ArrayList<OrgDesigRssItem>();
	}

	public List<OrgDesigRssItem> getItems() {
		return rssItems;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("Members".equalsIgnoreCase(qName)) {
			currentItem = new OrgDesigRssItem();
		} else if ("Name".equalsIgnoreCase(qName)) {
			parsingName = true;
		} else if ("PartyName".equalsIgnoreCase(qName)) {
			parsingPartyName = true;
		} else if ("FatherName".equalsIgnoreCase(qName)) {
			parsingFatherName = true;
		} else if ("MotherName".equalsIgnoreCase(qName)) {
			parsingMotherName = true;
		} else if ("DOB".equalsIgnoreCase(qName)) {
			parsingDOB = true;
		} else if ("PlaceOfBirth".equalsIgnoreCase(qName)) {
			parsingPlaceOfBirth = true;
		} else if ("MaritalStatus".equalsIgnoreCase(qName)) {
			parsingMaritalStatus = true;
		} else if ("SpouseName".equalsIgnoreCase(qName)) {
			parsingSpouseName = true;
		} else if ("EducatQualification".equalsIgnoreCase(qName)) {
			parsingEducatQualification = true;
		} else if ("PermanentAddress".equalsIgnoreCase(qName)) {
			parsingPermanentAddress = true;
		} else if ("Permanentphone".equalsIgnoreCase(qName)) {
			parsingPermanentphone = true;
		} else if ("LocalAddress".equalsIgnoreCase(qName)) {
			parsingLocalAddress = true;
		} else if ("Localphone".equalsIgnoreCase(qName)) {
			parsingLocalphone = true;
		} else if ("EmailID".equalsIgnoreCase(qName)) {
			parsingEmailID = true;
		} else if ("PositionHeld".equalsIgnoreCase(qName)) {
			parsingPositionHeld = true;
		} else if ("Profession".equalsIgnoreCase(qName)) {
			parsingProfession = true;
		} else if ("SocialActivity".equalsIgnoreCase(qName)) {
			parsingSocialActivity = true;
		} else if ("Fax".equalsIgnoreCase(qName)) {
			parsingFax = true;
		} else if ("LanguagesKnown".equalsIgnoreCase(qName)) {
			parsingLanguagesKnown = true;
		} else if ("SpecialInterests".equalsIgnoreCase(qName)) {
			parsingSpecialInterests = true;
		} else if ("FavouritePasstime".equalsIgnoreCase(qName)) {
			parsingFavouritePasstime = true;
		} else if ("CountryVisite".equalsIgnoreCase(qName)) {
			parsingCountryVisite = true;
		} else if ("OtherInfo".equalsIgnoreCase(qName)) {
			parsingOtherInfo = true;
		} else if ("PictureUrl".equalsIgnoreCase(qName)) {
			parsingPictureUrl = true;
		} else if ("StateName".equalsIgnoreCase(qName)) {
			parsingStateName = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("Members".equalsIgnoreCase(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if ("Name".equalsIgnoreCase(qName)) {
			parsingName = false;
		} else if ("PartyName".equalsIgnoreCase(qName)) {
			parsingPartyName = false;
		} else if ("FatherName".equalsIgnoreCase(qName)) {
			parsingFatherName = false;
		} else if ("MotherName".equalsIgnoreCase(qName)) {
			parsingMotherName = false;
		} else if ("DOB".equalsIgnoreCase(qName)) {
			parsingDOB = false;
		} else if ("PlaceOfBirth".equalsIgnoreCase(qName)) {
			parsingPlaceOfBirth = false;
		} else if ("MaritalStatus".equalsIgnoreCase(qName)) {
			parsingMaritalStatus = false;
		} else if ("SpouseName".equalsIgnoreCase(qName)) {
			parsingSpouseName = false;
		} else if ("EducatQualification".equalsIgnoreCase(qName)) {
			parsingEducatQualification = false;
		} else if ("PermanentAddress".equalsIgnoreCase(qName)) {
			parsingPermanentAddress = false;
		} else if ("Permanentphone".equalsIgnoreCase(qName)) {
			parsingPermanentphone = false;
		} else if ("LocalAddress".equalsIgnoreCase(qName)) {
			parsingLocalAddress = false;
		} else if ("Localphone".equalsIgnoreCase(qName)) {
			parsingLocalphone = false;
		} else if ("EmailID".equalsIgnoreCase(qName)) {
			parsingEmailID = false;
		} else if ("PositionHeld".equalsIgnoreCase(qName)) {
			parsingPositionHeld = false;
		} else if ("Profession".equalsIgnoreCase(qName)) {
			parsingProfession = false;
		} else if ("SocialActivity".equalsIgnoreCase(qName)) {
			parsingSocialActivity = false;
		} else if ("Fax".equalsIgnoreCase(qName)) {
			parsingFax = false;
		} else if ("LanguagesKnown".equalsIgnoreCase(qName)) {
			parsingLanguagesKnown = false;
		} else if ("SpecialInterests".equalsIgnoreCase(qName)) {
			parsingSpecialInterests = false;
		} else if ("FavouritePasstime".equalsIgnoreCase(qName)) {
			parsingFavouritePasstime = false;
		} else if ("CountryVisite".equalsIgnoreCase(qName)) {
			parsingCountryVisite = false;
		} else if ("OtherInfo".equalsIgnoreCase(qName)) {
			parsingOtherInfo = false;
		} else if ("PictureUrl".equalsIgnoreCase(qName)) {
			parsingPictureUrl = false;
		} else if ("stateName".equalsIgnoreCase(qName)) {
			parsingStateName = false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (parsingName) {
			if (currentItem != null)
				currentItem.setName(new String(ch, start, length));
			parsingName = false;
		} else if (parsingFatherName) {
			if (currentItem != null)
				currentItem.setFatherName(new String(ch, start, length));
			parsingFatherName = false;
		} else if (parsingMotherName) {
			if (currentItem != null)
				currentItem.setMotherName(new String(ch, start, length));
			parsingMotherName = false;
		} else if (parsingDOB) {
			if (currentItem != null)
				currentItem.setDOB(new String(ch, start, length));
			parsingDOB = false;
		} else if (parsingPlaceOfBirth) {
			if (currentItem != null)
				currentItem.setPlaceOfBirth(new String(ch, start, length));
			parsingPlaceOfBirth = false;
		} else if (parsingMaritalStatus) {
			if (currentItem != null)
				currentItem.setMaritalStatus(new String(ch, start, length));
			parsingMaritalStatus = false;
		} else if (parsingSpouseName) {
			if (currentItem != null)
				currentItem.setSpouseName(new String(ch, start, length));
			parsingSpouseName = false;
		} else if (parsingEducatQualification) {
			if (currentItem != null)
				currentItem
						.setEducatQualification(new String(ch, start, length));
			parsingEducatQualification = false;
		} else if (parsingPermanentAddress) {
			if (currentItem != null)
				currentItem.setPermanentAddress(new String(ch, start, length));
			parsingPermanentAddress = false;
		} else if (parsingPermanentphone) {
			if (currentItem != null)
				currentItem.setPermanentphone(new String(ch, start, length));
			parsingPermanentphone = false;
		} else if (parsingLocalAddress) {
			if (currentItem != null)
				currentItem.setLocalAddress(new String(ch, start, length));
			parsingLocalAddress = false;
		} else if (parsingLocalphone) {
			if (currentItem != null)
				currentItem.setLocalphone(new String(ch, start, length));
			parsingLocalphone = false;
		} else if (parsingEmailID) {
			if (currentItem != null)
				currentItem.setEmailID(new String(ch, start, length));
			parsingEmailID = false;
		} else if (parsingPositionHeld) {
			if (currentItem != null)
				currentItem.setPositionHeld(new String(ch, start, length));
			parsingPositionHeld = false;
		} else if (parsingFax) {
			if (currentItem != null)
				currentItem.setFax(new String(ch, start, length));
			parsingFax = false;
		} else if (parsingLanguagesKnown) {
			if (currentItem != null)
				currentItem.setLanguagesKnown(new String(ch, start, length));
			parsingLanguagesKnown = false;
		} else if (parsingSpecialInterests) {
			if (currentItem != null)
				currentItem.setSpecialInterests(new String(ch, start, length));
			parsingSpecialInterests = false;
		} else if (parsingFavouritePasstime) {
			if (currentItem != null)
				currentItem.setFavouritePasstime(new String(ch, start, length));
			parsingFavouritePasstime = false;
		} else if (parsingOtherInfo) {
			if (currentItem != null)
				currentItem.setOtherInfo(new String(ch, start, length));
			parsingOtherInfo = false;
		} else if (parsingCountryVisite) {
			if (currentItem != null)
				currentItem.setCountryVisite(new String(ch, start, length));
			parsingCountryVisite = false;
		} else if (parsingStateName) {
			if (currentItem != null)
				currentItem.setStateName(new String(ch, start, length));
			parsingStateName = false;
		} else if (parsingPartyName) {
			if (currentItem != null)
				currentItem.setPartyName(new String(ch, start, length));
			parsingPartyName = false;
		} else if (parsingProfession) {
			if (currentItem != null)
				currentItem.setProfession(new String(ch, start, length));
			parsingProfession = false;
		} else if (parsingSocialActivity) {
			if (currentItem != null)
				currentItem.setSocialActivity(new String(ch, start, length));
			parsingSocialActivity = false;
		} else if (parsingPictureUrl) {
			if (currentItem != null) {
				currentItem.setPictureUrl(new String(ch, start, length));
				parsingPictureUrl = false;
			}
		}
	}
}
