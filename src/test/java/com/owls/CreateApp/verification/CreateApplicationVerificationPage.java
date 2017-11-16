package com.owls.CreateApp.verification;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.owls.CreateApp.indexpage.CreateApplicationIndexPage;
import com.owls.init.AbstractPage;
import com.owls.init.Common;
import com.owls.utility.ContactAddressDetails;
import com.owls.utility.PostalAddressDetails;
import com.owls.utility.WildlifePossession;

public class CreateApplicationVerificationPage extends AbstractPage{
	
	public static String applicationURL="";

	public CreateApplicationVerificationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	WebElement btnLogout;

	public boolean VerifyAdminLogin() {

		boolean bool = false;
		Common.waitForElement(driver, btnLogout);
		if(Common.isElementDisplayed(btnLogout))
		{
		
			bool = true;
		}

		return bool;
	}
	
	/**
	 *  
	 * @param fileName
	 * @param application
	 * @return
	 */
	public boolean VerifyCreatedApplicationStatus(String fileName, String application) {
	
		 String start=".//a[text()='In Progress']/../..//a[text()='";
		 String end="']";
		 String end2="']/../../td[2]/a";
		 WebElement applicationStatus = driver.findElement(By.xpath(start+application+end));
		 System.err.println(start+application+end);
		 WebElement applicationName=driver.findElement(By.xpath(start+application+end2));
		 System.err.println(start+application+end2);
		Common.writeDataProperties(fileName,"ApplicationName", applicationName.getText(), "Store Application Subject ");
	
		return Common.isElementDisplayed(applicationStatus);
		
	}
	
	
	/*
	 * Method to verify Specified Permises and Postal address pasted correctly upon selecting "SAME AS ABOVE option
	 * */
	
	@FindBy (xpath=".//input[@id='edit-operating-address-street']")
	WebElement operatingAddressStreet;
	
	@FindBy (xpath=".//input[@id='edit-operating-address-city']")
	WebElement operatingCity;

	@FindBy (xpath=".//input[@id='edit-operating-address-state']")
	WebElement operatingState;
	
	@FindBy (xpath=".//input[@id='edit-operating-address-postalcode']")
	WebElement operatingPostcode;

	public boolean verifyOperatingAddress(String dataFileName) {
		// TODO Auto-generated method stub
		if (Common.readDataProperties(dataFileName, "Primary_Address").equalsIgnoreCase(operatingAddressStreet.getAttribute("value"))
				&& Common.readDataProperties(dataFileName, "Primary_City").equalsIgnoreCase(operatingCity.getAttribute("value"))
				&& Common.readDataProperties(dataFileName, "Primary_State").equalsIgnoreCase(operatingState.getAttribute("value"))
				&& Common.readDataProperties(dataFileName, "Primary_Postcode").equalsIgnoreCase(operatingPostcode.getAttribute("value")))
		{
			return true;
		}
		else
			return false;
	}
	
	/*
	 * Method to verify 'Same as Above' checkboxe  functionality.
	 * 
	 * */
	
	public boolean verifyPostalAddress() {
		// TODO Auto-generated method stub
		
		if(PostalAddressDetails.getPostalAddress().equalsIgnoreCase(operatingAddressStreet.getAttribute("value"))
				&& PostalAddressDetails.getPostalCity().equalsIgnoreCase(operatingCity.getAttribute("value"))
				&& PostalAddressDetails.getPostalState().equalsIgnoreCase(operatingState.getAttribute("value"))
				&& PostalAddressDetails.getPostalPostcode().equalsIgnoreCase(operatingPostcode.getAttribute("value")))
		{
			return true;
		}else
			return false;
	}
	
	/*
	 * Method will verify species added at the time of application are persisted correctly in Species table. 
	 * 
	 * */			
	
	
	public boolean verifySpeciesTable() {
		// TODO Auto-generated method stub
		
		WebElement speciesLineItem=driver.findElement(By.xpath("//*[@id='edit-owls-number-of-species-table']//span[text()=\""+WildlifePossession.getSpecies()+"\"]"));
		WebElement speciesNumber=driver.findElement(By.xpath("//*[@id='edit-owls-number-of-species-table']//span[text()='"+WildlifePossession.getSpecies()+"']//..//..//td[3]//span"));
		WebElement speciesAlive_Dead=driver.findElement(By.xpath("//*[@id='edit-owls-number-of-species-table']//span[text()='"+WildlifePossession.getSpecies()+"']//..//..//td[4]//span"));
		
		if(speciesLineItem.isDisplayed() &&speciesNumber.getText().equalsIgnoreCase(WildlifePossession.getNumber())
				&& speciesAlive_Dead.getText().equalsIgnoreCase(WildlifePossession.getAlive_dead()))
		{
			return true;
		}
		else
			return false;
				
	}
	/**
	 * 
	 * verifyUploadedDocument will verify uploaded document table that document are uploaded correctly. 
	 * @return whether Document uploaded are persisted in Uploaded Document Table. 
	 */
	
	public boolean verifyUploadedDocument() {
		// TODO Auto-generated method stub
		WebElement docName=driver.findElement(By.xpath(".//td//span[contains(text(),'"+CreateApplicationIndexPage.fileName+"')]"));
		if(docName.isDisplayed())
			return true;
		else
				return false;
	}
	
	@FindBy(xpath="//table[@id='edit-owls-convictions-table']//tbody/tr[2]//span[contains(@data-get-to,'convictions')]")
	 List<WebElement> addedConvictiondata;
	 
	 public boolean VerifyAddedConvictiondata(String offence, String offenceYear, String offenceState) {
	  
	  if(addedConvictiondata.get(0).getText().toLowerCase().contains(offence.toLowerCase()) && 
	    addedConvictiondata.get(1).getText().toLowerCase().contains(offenceYear.toLowerCase()) &&
	    addedConvictiondata.get(2).getText().toLowerCase().contains(offenceState.toLowerCase()))
		  return true;
	  else
		  return false;
	 }

	 /**
	  * Method will verify that 'Pay Fee' and 'Payment Notice' links are available for pending payment application.
	  * @return
	  */
	 
	 @FindBy (xpath="//a[text()='Payment Pending']//..//..//a[text()='Pay Fee']")
	 WebElement payFee;
	 
	 @FindBy (xpath="//a[text()='Payment Pending']//..//..//a[text()='Payment Notice']")
	 WebElement paymentNotice;
	 
	public boolean verifyPaymentPendingApplication() {
		// TODO Auto-generated method stub
		return Common.isElementDisplayed(payFee) && Common.isElementDisplayed(paymentNotice);
	}

	/**
	 * Method will verify User's Primary address from pdf content.
	 * @param pdfText contains extracted pdf 
	 * @return boolean
	 */
	public boolean verifyAddressFromPDF(String fileName,String pdfText) {
		// TODO Auto-generated method stub
		try
		{
			return pdfText.contains(Common.readDataProperties(fileName,"Primary_Address"));
		}
		catch(Exception e)
		{
			Common.logstep(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Method will get the application name which is in 'Payment Pending' status and get the application name and write it to property file.
	 * @param fileName
	 * @param application
	 * @return boolean
	 */
	public boolean verifyCreatedApplicationStatus(String fileName, String application) {
		
		 String start=".//a[text()='";
		 String end="']/../..//a[text()='Payment Pending']";
		 String end2="/../../td[2]/a";
		 WebElement applicationstatus=driver.findElement(By.xpath(start+application+end));
		 WebElement applicationName=driver.findElement(By.xpath(start+application+end+end2));

		Common.writeDataProperties(fileName,"ApplicationName", applicationName.getText(), "Store Application Subject ");
	
		return Common.isElementDisplayed(applicationstatus);
		
	}
	
	@FindBy (xpath=".//h4[contains(text(),'WARNING')]")
	WebElement warningPopup;

	public boolean verifyWarningPopup() {
		// TODO Auto-generated method stub
		return Common.isElementDisplayed(warningPopup);
	}
	@FindBy (xpath=".//p[contains(text(),'You can not apply the Advanced Application because the Basic Application is already exists.')]")
	WebElement warningMessage;

	@FindBy (xpath=".//div[@class='modal-body']//p")
	WebElement warningMessageContent;
	
	public boolean verifyWarningMessage() {
		// TODO Auto-generated method stub
		
		Common.log(" Warning Message : "+warningMessageContent.getText());
		return Common.isElementDisplayed(warningMessage);
	}

	@FindBy (xpath=".//button[text()='OK']")
	WebElement OkButton;
	
	@FindBy (xpath=".//button[text()='Cancel']")
	WebElement CancelButton;
	
	public boolean verifyButtons() {
		// TODO Auto-generated method stub
		return Common.isElementDisplayed(OkButton) && Common.isElementDisplayed(CancelButton);
	}
	
	@FindBy (xpath=".//h2[@class='visually-hidden']/../../div")
	WebElement errorMessage;

	public boolean verifyValidationError() {
		// TODO Auto-generated method stub
		Common.log(errorMessage.getText());
		
		return Common.isElementDisplayed(errorMessage);
	}

	
	 
}
