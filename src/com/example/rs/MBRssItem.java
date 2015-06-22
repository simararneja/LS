package com.example.rs;

/**
 * This code encapsulates RSS item data.
 * Our application needs title and link data.
 * 
 * @author ITCuties
 *
 */
public class MBRssItem {
	
	private String Name;
	private String FatherName;
	private String MotherName;
	private String DOB;
	private String PlaceOfBirth;
	private String MaritalStatus;
	private String SpouseName;
	private String EducatQualification;
	private String PermanentAddress;
	private String Permanentphone;
	private String LocalAddress;
	private String Localphone;
	private String EmailID;
	private String PositionHeld;
	private String Fax;
	private String LanguagesKnown;
	private String SpecialInterests;
	private String FavouritePasstime;
	private String OtherInfo;
	private String CountryVisite;
	private String PictureUrl;
	private String Profession;
	private String SocialActivity;
	private String PartyName;
	private String StateName;
	private String NoOfChildren;
	private String Books;
	private String FavouritePassTime;
	
	

	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public String getFatherName() {
		return FatherName;
	}


	public void setFatherName(String fatherName) {
		FatherName = fatherName;
	}


	public String getMotherName() {
		return MotherName;
	}


	public void setMotherName(String motherName) {
		MotherName = motherName;
	}


	public String getDOB() {
		return DOB;
	}


	public void setDOB(String dOB) {
		DOB = dOB;
	}


	public String getPlaceOfBirth() {
		return PlaceOfBirth;
	}


	public void setPlaceOfBirth(String placeOfBirth) {
		PlaceOfBirth = placeOfBirth;
	}


	public String getMaritalStatus() {
		return MaritalStatus;
	}


	public void setMaritalStatus(String maritalStatus) {
		MaritalStatus = maritalStatus;
	}

	public String getPartyName() {
		return PartyName;
	}


	public void setPartyName(String value) {
		PartyName = value;
	}


	public String getSpouseName() {
		return SpouseName;
	}


	public void setSpouseName(String spouseName) {
		SpouseName = spouseName;
	}


	public String getEducatQualification() {
		return EducatQualification;
	}


	public void setEducatQualification(String educatQualification) {
		EducatQualification = educatQualification;
	}


	public String getPermanentAddress() {
		return PermanentAddress;
	}


	public void setPermanentAddress(String permanentAddress) {
		PermanentAddress = permanentAddress;
	}


	public String getPermanentphone() {
		return Permanentphone;
	}


	public void setPermanentphone(String permanentphone) {
		Permanentphone = permanentphone;
	}


	public String getLocalAddress() {
		return LocalAddress;
	}


	public void setLocalAddress(String localAddress) {
		LocalAddress = localAddress;
	}


	public String getLocalphone() {
		return Localphone;
	}


	public void setLocalphone(String localphone) {
		Localphone = localphone;
	}


	public String getEmailID() {
		return EmailID;
	}


	public void setEmailID(String emailID) {
		EmailID = emailID;
	}


	public String getPositionHeld() {
		return PositionHeld;
	}


	public void setPositionHeld(String positionHeld) {
		PositionHeld = positionHeld;
	}


	public String getFax() {
		return Fax;
	}


	public void setFax(String fax) {
		Fax = fax;
	}


	public String getLanguagesKnown() {
		return LanguagesKnown;
	}


	public void setLanguagesKnown(String languagesKnown) {
		LanguagesKnown = languagesKnown;
	}


	public String getSpecialInterests() {
		return SpecialInterests;
	}


	public void setSpecialInterests(String specialInterests) {
		SpecialInterests = specialInterests;
	}
	
	public String getStateName() {
		return StateName;
	}


	public void setStateName(String value) {
		StateName = value;
	}
	
	public String getProfession() {
		return Profession;
	}


	public void setProfession(String value) {
		Profession = value;
	}
	
	public String getSocialActivity() {
		return SocialActivity;
	}


	public void setSocialActivity(String value) {
		SocialActivity = value;
	}


	public String getFavouritePasstime() {
		return FavouritePasstime;
	}


	public void setFavouritePasstime(String favouritePasstime) {
		FavouritePasstime = favouritePasstime;
	}


	public String getOtherInfo() {
		return OtherInfo;
	}


	public void setOtherInfo(String otherInfo) {
		OtherInfo = otherInfo;
	}


	public String getCountryVisite() {
		return CountryVisite;
	}


	public void setCountryVisite(String countryVisite) {
		CountryVisite = countryVisite;
	}


	public String getPictureUrl() {
		return PictureUrl;
	}


	public void setPictureUrl(String pictureUrl) {
		PictureUrl = pictureUrl;
	}


	@Override
	public String toString() {
		return "Name: " + Name + "\n" +
				"Fathers Name: " + FatherName	+ "\n" +
				"Mothers Name: " + MotherName + "\n" +
				"Date of Birth: " + DOB+ "\n" +
				"Place of Birth: " + PlaceOfBirth + "\n" +
				"Marital Status: "+ MaritalStatus + "\n" +
				"Spouse Name: " + SpouseName	+ "\n" +
				"Educational Qualification: " + EducatQualification+ "\n" +
				"Permanent Address: " + PermanentAddress+ "\n" +
				"Permanent Phone: " + Permanentphone + "\n" +
				"Local Address: "+ LocalAddress + "\n" +
				"Local Phone: " + Localphone + "\n" +
				"Email-ID: "+ EmailID + "\n" +
				"Position Held: " + PositionHeld + "\n" +
				"Fax: " + Fax+ "\n" +
				"Languages Known: " + LanguagesKnown + "\n" +
				"Special Interests: "+ SpecialInterests + "\n" +
				"Favourite Passtime: " + FavouritePasstime+ "\n" +
				"Other Info:" + OtherInfo + "\n" +
				"Country Visits: "+ CountryVisite + "\nClick here to view the picture\n";
	}	
	

}
