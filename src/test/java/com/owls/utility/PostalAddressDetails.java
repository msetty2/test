package com.owls.utility;

public class PostalAddressDetails {
	
	public static String postalAddress="";
	public static String postalCity="";
	public static String postalState="";
	public static String postalPostcode="";
	
	public static String getPostalAddress() {
		return postalAddress;
	}
	public static void setPostalAddress(String postalAddress) {
		PostalAddressDetails.postalAddress = postalAddress;
	}
	public static String getPostalCity() {
		return postalCity;
	}
	public static void setPostalCity(String postalCity) {
		PostalAddressDetails.postalCity = postalCity;
	}
	public static String getPostalState() {
		return postalState;
	}
	public static void setPostalState(String postalState) {
		PostalAddressDetails.postalState = postalState;
	}
	public static String getPostalPostcode() {
		return postalPostcode;
	}
	public static void setPostalPostcode(String postalPostcode) {
		PostalAddressDetails.postalPostcode = postalPostcode;
	}

}
