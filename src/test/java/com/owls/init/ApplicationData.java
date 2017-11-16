
package com.owls.init;


public class ApplicationData {

	public static String applicationSubject="";
	public static String OffenceName="";
	public static String OffenceYear="";
	public static String offencestate="";
	public static String ApplicationNumber="";
	

	public String getApplicationNumber() {
		return ApplicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		System.out.println("Set Application number: "+applicationNumber);
		ApplicationNumber = applicationNumber;
	}

	public String getOffencestate() {
		return offencestate;
	}

	public void setOffencestate(String offencestate) {
		this.offencestate = offencestate;
	}

	public String getOffenceYear() {
		return OffenceYear;
	}

	public void setOffenceYear(String offenceYear) {
		OffenceYear = offenceYear;
	}

	public String getOffenceName() {
		return OffenceName;
	}

	public void setOffenceName(String offenceName) {
		OffenceName = offenceName;
	}

	public String getApplicationSubject() {
		System.out.println("Get Subject name: "+applicationSubject);
		return applicationSubject;
	}

	public void setApplicationSubject(String appSubject) {
		System.out.println("Subject = "+appSubject);
		this.applicationSubject = appSubject;
	}
}
