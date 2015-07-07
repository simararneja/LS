package com.example.ls;

public class OrgASJSRssItem
{
	private String Ename;
	private String Designation;
	private String WorkResponsibility;
	private String categorycode;
	private String Eaddress;
	private String Telephone1;
	private String Telephone2;
	private String Telephone3;
	private String fax;
	private String EmailID;
	private String Link;
	
	
	public String getename() {
		return Ename;
	}
	public void setename(String Ename) 
	{
		this.Ename= Ename;
	}
	
	public String getdesignation() {
		return Designation;
	}
	public void setdesignation(String Designation) 
	{
		this.Designation= Designation;
	}
	
	public String getworkresponsibity() {
		return WorkResponsibility;
	}
	public void setworkresponsibility(String WorkResponsibility) 
	{
		this.WorkResponsibility= WorkResponsibility;
	}
	
	public String getcategorycode() {
		return categorycode;
	}
	public void setcategorycode(String categorycode) 
	{
		this.categorycode= categorycode;
	}
	
	public String geteaddress() {
		return Eaddress;
	}
	public void seteaddress(String Eaddress) 
	{
		this.Eaddress= Eaddress;
	}
	
	public String gettelephone1() {
		return Telephone1;
	}
	public void settelephone1(String Telephone1) 
	{
		this.Telephone1= Telephone1;
	}
	
	public String gettelephone2() {
		return Telephone2;
	}
	public void settelephone2(String Telephone2) 
	{
		this.Telephone2= Telephone2;
	}
	
	public String gettelephone3() {
		return Telephone3;
	}
	public void settelephone3(String Telephone3) 
	{
		this.Telephone3= Telephone3;
	}
	
	public String getfax() {
		return fax;
	}
	public void setfax(String fax) 
	{
		this.fax= fax;
	}
	
	public String getemailid() {
		return EmailID;
	}
	public void setemailid(String EmailID) 
	{
		this.EmailID= EmailID;
	}
	
	
	
	@Override
	public String toString() 
	{
			return Ename +", "+Designation +"\n"+
					WorkResponsibility+"\n"+
					EmailID;
	}
	

}
