package com.owls.CreateApp.indexpage;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.owls.CreateApp.verification.CreateApplicationVerificationPage;
import com.owls.init.AbstractPage;
import com.owls.init.Common;
import com.owls.init.TestData;
import com.owls.utility.ContactAddressDetails;
import com.owls.utility.PostalAddressDetails;
import com.owls.utility.WildlifePossession;
/**
 * Indexpage class contains 
 * 1. Element Locators ( @FindBy(xpath = "//input[@id='edit-name']") WebElement txtUserName;
 * 2. Logic of 
 */

public class CreateApplicationIndexPage extends AbstractPage {

	public CreateApplicationIndexPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(xpath = "//input[@id='edit-name']")
	WebElement txtUserName;

	@FindBy(xpath = "//input[@id='edit-pass']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='edit-submit']")
	WebElement btnLogin;

	/**
	 *  test method contains (annotated with @Test)
	 * 1. Test steps log.
	 * 2. TestNG log [Common.logstep()] which will print in testNG report.
	 * 3. Calling method to indexpage class  and verification class.
	 */
	public CreateApplicationVerificationPage enterAdminUsername() {
		
		Common.waitForElement(driver, txtUserName);
		Common.pause(1);
		
		Common.type(txtUserName, TestData.getPortalUserName());
		Common.log(" Portal UserName: "+TestData.getPortalUserName());
		return new CreateApplicationVerificationPage(driver);
	}

	public CreateApplicationVerificationPage enterAdminPassword() {

		Common.type(txtPassword, TestData.getPortalPassword());
		Common.log(" Password: "+TestData.getPortalPassword());

		return new CreateApplicationVerificationPage(driver);
	}
	
	public CreateApplicationVerificationPage clickonlogin()
	{
		Common.jsClick(driver, btnLogin);
		
		return new CreateApplicationVerificationPage(driver);
	}
	
	@FindBy(xpath="//span[text()='Personal']")
	WebElement btnPersonalMenu;
	
	public CreateApplicationVerificationPage clickPersonalMenu()
	{
		Common.waitForElement(driver, btnPersonalMenu);
		Common.pause(2);
		
		Common.clickOn(driver, btnPersonalMenu);
		
		return new CreateApplicationVerificationPage(driver);
	}
	
	@FindBy(xpath="//span[text()='Application']")
	WebElement btnApplication;
	
	public CreateApplicationVerificationPage clickonApplication()
	{
		Common.clickOn(driver, btnApplication);
		Common.pause(5);
		return new CreateApplicationVerificationPage(driver);
	}
	
	@FindBy(xpath="//a[text()='Create Application']")
	WebElement btnCreateApplication;
	
	public CreateApplicationVerificationPage clickonCreateApplication()
	{
		Common.waitForElement(driver, btnCreateApplication);
		Common.pause(2);
		
		Common.clickOn(driver, btnCreateApplication);
		
		return new CreateApplicationVerificationPage(driver);
	}
	
	@FindBy(xpath="//*[@id='edit-application-type']")
	WebElement combo_selectApplicationType;
	
	public CreateApplicationVerificationPage SelectApplicationType(String ApplicationType)
	{
		Common.waitForElement(driver, combo_selectApplicationType);
		Common.pause(2);
		
		Common.selectFromComboByVisibleText(combo_selectApplicationType, ApplicationType);
		
		return new CreateApplicationVerificationPage(driver);
	}
	

	@FindBy(xpath="//*[@id='action_next']")
	WebElement btn_next;
	
	public CreateApplicationVerificationPage clickonNext()
	{
		Common.pause(2);
		
		Common.clickOn(driver, btn_next);
		
		return new CreateApplicationVerificationPage(driver);
	}
	
	@FindBy(xpath="//label[text()='Same as above']")
	WebElement checkbox_SameAsAbove;
	
	public CreateApplicationVerificationPage checkboxSameasAbove()
	{
		Common.waitForElement(driver, checkbox_SameAsAbove);
		Common.pause(5);
		Common.mouseOver(driver, checkbox_SameAsAbove);
		
		Common.jsClick(driver, checkbox_SameAsAbove);
		
		setPostalAddress();
		
		return new CreateApplicationVerificationPage(driver);
	}
	
	/**
	 * UploadFile method will upload Test Document.
	 * 
	 */
	
	//@FindBy(xpath=".//*[@id='upload-document-block']//input[contains(@id,'edit')]")
	
	@FindBy (xpath=".//div[@id='upload_document']//input")
	WebElement uploadFile;
	public static String fileName="TestUpload.pdf";
	
	public CreateApplicationVerificationPage UploadFile()
	{	
		System.err.println( " Back from Upload Files ... ");
		File uploadDocument = new File("Resource\\"+fileName);
		Common.type(uploadFile, uploadDocument.getAbsolutePath());
		
		return new CreateApplicationVerificationPage(driver);
	}
	
	@FindBy (xpath=".//label[text()='No file selected.']/../..//input")
	WebElement uploadFiles;
	
	public CreateApplicationVerificationPage UploadFiles()
	{		
		File uploadDocument = new File("Resource\\"+fileName);
			WebElement upload = driver.findElement(By.xpath(".//input[@name='document_file[]']"));
			System.err.println(" Dingo file will upload ... ");
			  JavascriptExecutor je = (JavascriptExecutor)driver;
			  String js = "arguments[0].style.visibility='visible';";
			  je.executeScript(js,upload);
			  upload.sendKeys(uploadDocument.getAbsolutePath());
	
		return new CreateApplicationVerificationPage(driver);
	}
	
	
	@FindBy(xpath="//*[@id='edit-form-submit-bottom']")
	WebElement submitApplication;
	
	@FindBy(xpath="//h1[text()='Detail Application']")
	WebElement AppdetailTitle;
	
	
	public CreateApplicationVerificationPage clickonSubmit()
	{
		Common.pause(1);
		
		Common.jsClick(driver, submitApplication);

		Common.waitForElement(driver, AppdetailTitle);
		Common.pause(2);
		
		CreateApplicationVerificationPage.applicationURL=driver.getCurrentUrl();
		
		return new CreateApplicationVerificationPage(driver);
	}
	
	@FindBy(xpath="//label[@for='edit-have-wildlife-in-possession-c-1']")
	WebElement chkbk_yes;
	
	
	public CreateApplicationVerificationPage HaveanyWildInPossession()
	{
		Common.jsClick(driver, chkbk_yes);
		return new CreateApplicationVerificationPage(driver);
	}
	
	@FindBy(xpath="//input[@name='received_from_licence_number_c']")
	WebElement licence_Number;
	
	public CreateApplicationVerificationPage enterLicenceNumber()
	{
		Common.type(licence_Number, "1234");
		return new CreateApplicationVerificationPage(driver);
	}
	
	@FindBy(xpath="//span[@class='select2-chosen']")
	WebElement select_species;
	
	@FindBy(xpath=".//*[@id='select2-chosen-1']")
	WebElement speciesSelected;
	public CreateApplicationVerificationPage selectSpecies()
	{
		Common.clickOn(driver, select_species);
		Common.pause(2);
		List<WebElement> species = driver.findElements(By.xpath("//ul[@class='select2-results']/li/div"));
		int rannumber ;
		try {
			rannumber = Common.randBetween(1, 20);
			Common.pause(2);
			species.get(rannumber).click();
		}
		catch(IndexOutOfBoundsException e)
		{
			System.err.println(" This is selecting dingo specimen .. !! ");
			rannumber = Common.randBetween(1, 1);
			Common.pause(2);	
			species.get(rannumber).click();
		}
			WildlifePossession.setSpecies(speciesSelected.getText());
		System.err.println("Selected Species"+WildlifePossession.getSpecies());
		
		return new CreateApplicationVerificationPage(driver);
	}
	
	public CreateApplicationVerificationPage selectDingo()
	{
		Common.clickOn(driver, select_species);
		Common.pause(2);
		
		List<WebElement> species = driver.findElements(By.xpath("//ul[@class='select2-results']/li/div"));
		int rannumber = Common.randBetween(1, 1);
		Common.pause(2);
		species.get(rannumber).click();
	
		WildlifePossession.setSpecies(speciesSelected.getText());
		System.err.println("Selected Species"+WildlifePossession.getSpecies());
		
		return new CreateApplicationVerificationPage(driver);
	}
	
	@FindBy(xpath="//input[@name='owls_number_of_species_number_of_animals_c']")
	WebElement txt_number;
	
	public CreateApplicationVerificationPage enterNumberofSpecies()
	{
		int randNumber=Common.randBetween(1, 20);
		Common.pause(2);
		Common.type(txt_number, String.valueOf(randNumber));
		
		WildlifePossession.setNumber(String.valueOf(randNumber));
		System.err.println(" Number of Species : "+WildlifePossession.getNumber());
	
		return new CreateApplicationVerificationPage(driver);
	}
	
	/*
	 * Method will select random value from Alive/Dead Species  dropdown.
	 * 
	 * */
	
	@FindBy(xpath="//select[@name='owls_number_of_species_description']")
	WebElement combospeciesDiscription;
	
	public CreateApplicationVerificationPage selectspeciesDiscription()
	{
		String speciesDropdown;
		Common.pause(2);
		speciesDropdown=Common.selectFromComboByIndex(combospeciesDiscription, Common.randBetween(1, 2));

		WildlifePossession.setAlive_dead(speciesDropdown);
		System.err.println(" Selected Specice Alive /Dead : "+WildlifePossession.getAlive_dead());
	
		return new CreateApplicationVerificationPage(driver);
	}
	
	
	
	/*
	 * Method will click on 'Add Species' button.
	 * */
	
	@FindBy(xpath="//input[@value='Add Species']")
	WebElement btn_AddSpecies;
	
	public CreateApplicationVerificationPage clickonAddSpecies()
	{
		Common.pause(2);
		Common.clickOn(driver, btn_AddSpecies);
		
		return new CreateApplicationVerificationPage(driver);
	}
	
	
	
	/*
	 * Method to click on Details Menu item.
	 * */
	
	@FindBy(xpath=".//span[text()='Details']")
	WebElement btn_Details;

	public CreateApplicationVerificationPage clickonDetails() {
		// TODO Auto-generated method stub
		
		Common.pause(2);
		Common.clickOn(driver, btn_Details);
		
		return new CreateApplicationVerificationPage(driver);
		
	}

	/*
	 * Method to click on Contact Detail option.
	 *
	 * */
	
	@FindBy(xpath=".//span[text()='Contact Details']")
	WebElement btn_Contact_detail;
	
	public CreateApplicationVerificationPage clickonContactDetail(String fileName) {
		// TODO Auto-generated method stub
		Common.pause(2);
		Common.clickOn(driver, btn_Contact_detail);
		setContactAddress(fileName);
		return new CreateApplicationVerificationPage(driver);	
	}
	
	/*
	 * Method will retrieve Address data from Contact Details and call the appropriate setter.
	 * */


	@FindBy (xpath=".//*[@id='edit-primary-address-street']")
	WebElement primaryAddress;
	
	@FindBy (xpath=".//*[@id='edit-primary-address-city']")
	WebElement primaryCity;
	
	@FindBy(xpath=".//*[@id='edit-primary-address-state']")
	public static WebElement primaryState;
	
	@FindBy(xpath=".//*[@id='edit-primary-address-postalcode']")
	WebElement primaryPostcode;
	
	
	private void setContactAddress(String fileName) {
		// TODO Auto-generated method stub
		
		Common.writeDataProperties(fileName,"Primary_Address", primaryAddress.getAttribute("value"), "");
		ContactAddressDetails.setPrimaryAddress(primaryAddress.getAttribute("value"));;
		System.err.println(ContactAddressDetails.getPrimaryAddress());
		Common.writeDataProperties(fileName,"Primary_City", primaryCity.getAttribute("value"), "");
		ContactAddressDetails.setPrimaryCity(primaryCity.getAttribute("value"));
		System.err.println(ContactAddressDetails.getPrimaryCity());
		Common.writeDataProperties(fileName,"Primary_State", primaryState.getAttribute("value"), "");
		ContactAddressDetails.setPrimaryState(primaryState.getAttribute("value"));
		System.err.println(ContactAddressDetails.getPrimaryState());
		Common.writeDataProperties(fileName,"Primary_Postcode", primaryPostcode.getAttribute("value"), "");
		ContactAddressDetails.setPrimaryPostcode(primaryPostcode.getAttribute("value"));
		System.err.println(ContactAddressDetails.getPrimaryPostcode());
	}

	
	/*
	 * Method will retrieve data from Postal Address detail and call appropriate setter method.
	 * */

	@FindBy (xpath=".//input[@id='edit-postal-address-street']")
	WebElement postalAddress;
	
	@FindBy (xpath=".//input[@id='edit-postal-address-city']")
	WebElement postalCity;
	
	@FindBy (xpath=".//input[@id='edit-postal-address-state']")
	WebElement postalState;
	
	@FindBy (xpath=".//input[@id='edit-postal-address-postalcode']")
	WebElement postalPostcode;
	
	private void setPostalAddress() {
		// TODO Auto-generated method stub
		
		PostalAddressDetails.setPostalAddress(postalAddress.getAttribute("value"));
		PostalAddressDetails.setPostalCity(postalCity.getAttribute("value"));
		PostalAddressDetails.setPostalState(postalState.getAttribute("value"));
		PostalAddressDetails.setPostalPostcode(postalPostcode.getAttribute("value"));
	}

	/*
	 * Method will click on Permit menu.
	 * 
	 * */
	
	@FindBy (xpath=".//span[text()='Permit']")
	WebElement menuPermit;
	
	public CreateApplicationVerificationPage clickonPermit() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, menuPermit);
		return new CreateApplicationVerificationPage(driver);
	}
	
 //// ======================== Ankit 
	
	@FindBy(xpath="//label[text()='I have prior convictions']")
	 WebElement radiobutton_IHaveConvictions;
	
	public CreateApplicationVerificationPage ClickonIHavepriorConviction()
	 {
	  Common.jsClick(driver, radiobutton_IHaveConvictions);
	  Common.pause(1);
	  return new CreateApplicationVerificationPage(driver);
	 }
	 
	 @FindBy(xpath="//input[@id='edit-owls-convictions-offence-c']")
	 WebElement textbox_offenceName;

	 @FindBy(xpath=".//input[@id='edit-owls-convictions-year-c']")
	 WebElement textbox_Conviction_Year;
	 
	 @FindBy(xpath="//select[@id='edit-owls-convictions-state-c']")
	 WebElement dropdown_state;
	 
	 public CreateApplicationVerificationPage EnterOffenceDetails(String offence,String offenceYear,String offenceState)
	 {
	  
	  textbox_offenceName.sendKeys(offence);
	  
	  textbox_Conviction_Year.sendKeys(offenceYear);
	  
	  Common.selectFromComboByVisibleText(dropdown_state, offenceState);
	  
	  return new CreateApplicationVerificationPage(driver);
	 }
	 
	 @FindBy(xpath="//input[@id='edit-update-prior-convictions']")
	 WebElement button_addPriorconvictions;
	 
	 public void clickonAddpriorConvictionsButton()
	 {
	  Common.clickOn(driver, button_addPriorconvictions);
	  
	  Common.pause(2);
	 }

	 /**
	  * Method will click on Payment Notice link and download Payment notice.
	  */
	 
	
	public void clickonPaymentNotice(String fileName) {
		// TODO Auto-generated method stub
		
		
		 WebElement paymentNotice;
		 String start="//a[text()='";
		 String end="']//..//..//a[text()='Payment Notice']";
		 String applicationSubject=Common.readProperties(fileName, "ApplicationName");
		 paymentNotice=driver.findElement(By.xpath(start+applicationSubject+end));
		 
		Common.clickOn(driver, paymentNotice);
		Common.pause(5);
	}

	@FindBy(xpath="//label[text()='No convictions']")
	 WebElement radiobutton_NoConvictions;

	public void selectNoConviction() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, radiobutton_NoConvictions);
		Common.pause(1);
	}

	@FindBy (xpath="//label[@for='edit-have-wildlife-in-possession-c-0']")
	WebElement noWildlifePossession;
	
	public void noWildLifePossession() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, noWildlifePossession);
		Common.pause(1);
	}

	@FindBy (xpath=".//label[text()='I agree ']")
	WebElement iAgreeOption;
	
	public void chooseIAgree() {
		// TODO Auto-generated method stub
		Common.jsClick(driver, iAgreeOption);
		Common.pause(2);
	}
	
	@FindBy (xpath=".//label[text()='30-Sep-2020']")
	WebElement licenceExpiry;

	@FindBy (xpath=".//label[text()='30-Jun-2020']")
	WebElement licenceExpiryDingo;

	public void licenceExpiry(String applicationType) {
		// TODO Auto-generated method stub
		System.err.println(" Dingo Licence : "+applicationType);
		if(applicationType.equalsIgnoreCase("Dingo Licence"))
		{
			System.err.println(" Digo Licence Expiry .. ");
			Common.jsClick(driver, licenceExpiryDingo);
		}
		else
		{
			System.err.println(" Normla Licence Expriry ..!! ");
			Common.jsClick(driver, licenceExpiry);
		}
	}
	
}


