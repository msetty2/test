package com.owls.CreateApp.index;

//////
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.owls.init.Common;
import com.owls.init.ITestStatus;
import com.owls.init.SeleniumInit;

public class CreateApplicationIndex extends SeleniumInit {

	/**
	 * TestNG's test method contains (annotated with @Test)
	 * 1. Test steps log.
	 * 2. TestNG log [Common.logstep()] which will print in testNG report.
	 * 3. Calling method to indexpage class  and verification class.
	 */
	@Test // pass
	public void SubmitApplication_Wildlife_Basic_Licence() {

		int numOfFailure = 0;
		int logStep = 1;

		String dataFileName="basicApplicationData";
		String configFileName="basicApplicationConfig";
		
		Common.logcase("Testcase Id: TC_001 ");
		Common.logcase("Testcase Name: To verify that user is able to submite application to OWLS portal.");

		Common.logstep("Step " + (logStep++) + " : Open url:<a>" + testUrl + "</a>");
		
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
		
		Common.logstep("Step " + (logStep++)+" Click on Application Menu.");
		applicationIndexPage.clickonApplication();
		
		Common.logstep("Step " + (logStep++)+" Click on Create Application button.");
		applicationIndexPage.clickonCreateApplication();
		
		Common.logstep("Step " + (logStep++)+" Select Application type from Drop down.");
		applicationIndexPage.SelectApplicationType("Wildlife Basic Licence");
		
		Common.logstep("Step " + (logStep++)+" Click on Next button");
		applicationIndexPage.clickonNext();
		
		Common.logstep("Step " + (logStep++)+" Select 'Same as above' checkbox'.");
		applicationIndexPage.checkboxSameasAbove();
		
		Common.logstep("Step " + (logStep++)+" Upload file.");
		applicationIndexPage.UploadFile();
		
		Common.logstep("Step " + (logStep++)+" Click on submit.");
		applicationIndexPage.clickonSubmit();
		
		Common.logstep("Step " + (logStep++)+" Navigate to My Application page.");
		applicationIndexPage.clickonApplication();
	
		Common.logMandetoryAssert("Verify that created application is displayed on Apllication list of 'My Application' page.");
		
		if (applicationVerificatioPage.VerifyCreatedApplicationStatus(dataFileName,"Wildlife Basic Licence")) {
			logStatus(ITestStatus.PASSED);
		} else {
			logStatus(ITestStatus.FAILED);
			numOfFailure++;
		}

		if (numOfFailure > 0) {
			Assert.assertTrue(false);
		}

	}

	
	@Test // pass
	public void SubmitApplication_Wildlife_Specimen_Licence() {

		int numOfFailure = 0;
		int logStep = 1;

		String dataFileName="basicApplicationData";
		String configFileName="basicApplicationConfig";
		
		Common.logcase("Testcase Id: TC_002 ");
		Common.logcase("Testcase Name: To verify that user is able to submite application for 'Wildlife Specimen Licence'.");

		Common.logstep("Step " + (logStep++) + " : Open url:<a>" + testUrl + "</a>");
		
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
		
		Common.logstep("Step " + (logStep++)+" Click on Application Menu.");
		applicationIndexPage.clickonApplication();
		
		Common.logstep("Step " + (logStep++)+" Click on Create Application button.");
		applicationIndexPage.clickonCreateApplication();
		
		Common.logstep("Step " + (logStep++)+" Select Application type from Drop down.");
		applicationIndexPage.SelectApplicationType("Wildlife Specimen Licence");
	
		Common.logstep("Step " + (logStep++)+" Click on Next button");
		applicationIndexPage.clickonNext();
		
		Common.logstep("Step " + (logStep++)+" Select 'Same as above' checkbox'.");
		applicationIndexPage.checkboxSameasAbove();
		
		Common.logstep("Step " + (logStep++)+" Select 'Do you have any wildlife in your possession at the time of this application?' to YES.");
		applicationIndexPage.HaveanyWildInPossession();
		
		Common.logstep("Step " + (logStep++)+" Enter 'Licence Number'.");
		applicationIndexPage.enterLicenceNumber();
		
		Common.logstep("Step " + (logStep++)+" Select 'Species'.");
		applicationIndexPage.selectSpecies();
		
		Common.logstep("Step " + (logStep++)+" Enter number of species.");
		applicationIndexPage.enterNumberofSpecies();
		
		Common.logstep("Step " + (logStep++)+"Select Species description (Alive/Dead)");
		applicationIndexPage.selectspeciesDiscription();
		
		Common.logstep("Step " + (logStep++)+" Click on 'Add Species' button");
		applicationIndexPage.clickonAddSpecies();
		
		Common.logstep("Step " + (logStep++)+" Upload file.");
		applicationIndexPage.UploadFile();
		
		Common.logstep("Step " + (logStep++)+" Click on submit.");
		applicationIndexPage.clickonSubmit();
		
		Common.logstep("Step " + (logStep++)+" Navigate to My Application page.");
		applicationIndexPage.clickonApplication();
	
		Common.logMandetoryAssert("Verify that created application is displayed on Apllication list of 'My Application' page.");
		
		if (applicationVerificatioPage.VerifyCreatedApplicationStatus(dataFileName,"Wildlife Specimen Licence")) {
			logStatus(ITestStatus.PASSED);
		} else {
			logStatus(ITestStatus.FAILED);
			numOfFailure++;
		}

		if (numOfFailure > 0) {
			Assert.assertTrue(false);
		}
	}
	
	@Test // pass
	public void verifyCreateApplication() {

		int numOfFailure = 0;
		int logStep = 1;

		/*
		String dataFileName="basicApplicationData";
		String configFileName="basicApplicationConfig";
		
		Common.logcase("Testcase Id: TC_Portal_001");
		Common.logcase("Testcase Name:	 To verify Online user submits a basic application with conditions to trigger the 'Review Application'  task.");
		*/
		Common.logstep("Step " + (logStep++) + " : Open url:<a>" + testUrl + "</a>");
		
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
		
		Common.logstep("Step " + (logStep++)+" Click on Details Menu.");
		applicationVerificatioPage=applicationIndexPage.clickonDetails();		
		
		Common.logstep("Step " + (logStep++)+" Click on Contact Details Option.");
		applicationVerificatioPage=applicationIndexPage.clickonContactDetail(dataFileName);		
	
		Common.logstep("Step " + (logStep++)+" Click on Application Menu.");
		applicationIndexPage.clickonApplication();
		
		Common.logstep("Step " + (logStep++)+" Click on Create Application button.");
		applicationIndexPage.clickonCreateApplication();
		
		Common.logstep("Step " + (logStep++)+" Select Application type from Drop down.");
		applicationIndexPage.SelectApplicationType(applicationType);
	
		Common.logstep("Step " + (logStep++)+" Click on Next button");
		applicationIndexPage.clickonNext();
		
		Common.logOptionalAssert("Verify Operating Address retrived correctly from User's Contact Details->Primary Address.");
		if(applicationVerificatioPage.verifyOperatingAddress(dataFileName)) {
			Common.optionalAssertPassed();;
		}else {
			Common.optionalAssertFailed();
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Select 'Same as above' checkbox'.");
		applicationIndexPage.checkboxSameasAbove();
		
		Common.logOptionalAssert("Verify Postal Address pasted correctly as same as Specified Premises.");
		if(applicationVerificatioPage.verifyPostalAddress()) {
			Common.optionalAssertPassed();
		}else {
			
			Common.optionalAssertFailed();
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Select 'I have prior convictions' radio button and enter offence details as below.");
		applicationIndexPage.ClickonIHavepriorConviction();
		  
		  String offence = Common.generateRandomChars(10);
		  appdata.setOffenceName(offence);
		  String offenceYear = String.valueOf(Common.randBetween(2000, 2016));
		  appdata.setOffenceYear(offenceYear);
		  String offenceState="VIC";
		  appdata.setOffencestate(offenceState);
		  
		  Common.log("Offence: "+offence);
		  Common.log("Offence Year: "+offenceYear);
		  Common.log("Offence State: "+offenceState);
		  
		  applicationIndexPage.EnterOffenceDetails(offence,offenceYear,offenceState);
		  
		  applicationIndexPage.clickonAddpriorConvictionsButton();
		  
		  Common.logOptionalAssert("Verify that Conviction data should be displayed below conviction form.");
		  if (applicationVerificatioPage.VerifyAddedConvictiondata(offence,offenceYear,offenceState)) {
		   Common.optionalAssertPassed();
		  } else {
			  Common.optionalAssertFailed();
		   numOfFailure++;
		  }
		
			Common.logstep("Step " + (logStep++)+" Choose Prefered Licence Expiry.");
			applicationIndexPage.licenceExpiry(applicationType);
		
		  
		Common.log("Click on 'No Conviction' Option");
		applicationIndexPage.selectNoConviction();
		  
		Common.logstep("Step " + (logStep++)+" Select 'Do you have any wildlife in your possession at the time of this application?' to YES.");
		applicationIndexPage.HaveanyWildInPossession();
		
		Common.logstep("Step " + (logStep++)+" Enter 'Licence Number'.");
		applicationIndexPage.enterLicenceNumber();
		
		Common.logstep("Step " + (logStep++)+" Select 'Species'.");
		applicationIndexPage.selectSpecies();
		
		Common.logstep("Step " + (logStep++)+" Enter number of species.");
		applicationIndexPage.enterNumberofSpecies();
		
		Common.logstep("Step " + (logStep++)+"Select Species description (Alive/Dead)");
		applicationIndexPage.selectspeciesDiscription();
		
		Common.logstep("Step " + (logStep++)+" Click on 'Add Species' button");
		applicationIndexPage.clickonAddSpecies();
		
		Common.logOptionalAssert("Verify that if User has selected any wildlife possession at the time of application persisted in the Species Table");
		
		if(applicationVerificatioPage.verifySpeciesTable()) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "incorrectSpeciesTable");
			numOfFailure++;
		}
		Common.log(" Select 'No wildlife possession'..!");
		applicationIndexPage.noWildLifePossession();

		Common.logstep((logStep++)+" Upload file.");
		if(applicationType.equalsIgnoreCase("Dingo Licence" ))
		{
			applicationIndexPage.UploadFiles();
		}
		else
			applicationIndexPage.UploadFile();

		Common.logOptionalAssert("Verify that Uploaded file persisted in 'Uploaded Documents Table.'");
		
		if(applicationVerificatioPage.verifyUploadedDocument()) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "incorrectDocumentTable");
			numOfFailure++;
		}
		
		Common.logstep("Step " + (logStep++)+" Choose I Agree from Declaration By Applicant Options.");
		applicationIndexPage.chooseIAgree();
	
		Common.logstep("Step " + (logStep++)+" Click on submit.");
		applicationIndexPage.clickonSubmit();
		
		Common.logstep("Step " + (logStep++)+" Navigate to My Application page.");
		applicationIndexPage.clickonApplication();
	
		Common.logMandetoryAssert("Verify that created application is displayed on Apllication list of 'My Application' page.");
		
		if (applicationVerificatioPage.VerifyCreatedApplicationStatus(configFileName,applicationType)) {
			Common.writeProperties(configFileName,"Application_Created_Sucessfully","True","");
			
			Common.optionalAssertPassed();
			Common.logStatus("Pass");
		
		} else {
			Common.writeProperties(configFileName,"Application_Created_Sucessfully","False"," ");
			numOfFailure++;
		}

		if (numOfFailure > 0) {
			Common.logStatus("Fail");			
			Assert.assertTrue(false);
		}
	}
	
	
	@Test // pass
	public void verifyPaymentNoticeAndAwaitingTask() throws IOException {

		int numOfFailure = 0;
		int logStep = 1;

	/*	String dataFileName="basicApplicationData";
		String configFileName="basicApplicationConfig";
		
		Common.logcase("Testcase Id: TC_Portal_002 ");
		Common.logcase("Testcase Name: The 'Payment Notice' and 'Pay Fee' links should be visible to Online Portal User. The online Portal user should be able to download The 'Payment Notice'.");
	*/
		Common.logcase(testName);
		try {
			String interview_inspection = Common.readProperties(configFileName,"Delegate_Approval_Task_Complete");
			if (interview_inspection.contains("False")) {
				throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
			}
		} catch (Exception e) {
			throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
		}
	
		appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));

		Common.logstep("Step " + (logStep++) + " : Open url:<a>" + testUrl + "</a>");
		
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
		
		Common.logstep("Step " + (logStep++)+" Click on Application Menu.");
		applicationIndexPage.clickonApplication();
		
		Common.logMandetoryAssert("Verify that 'Payment Notice' and 'Pay Fee' Links are available for Payment Pending Application.");
		
		if(applicationVerificatioPage.verifyPaymentPendingApplication()) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "missingPaymentNotice.png");
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Click on Payment Notice link to download Payment Notice PDF.");
		applicationIndexPage.clickonPaymentNotice(configFileName);
		Common.pause(5);
		
		try
	     {
	        //final URL scalaByExampleUrl = new URL(address);
			File paymentNotice=new File("Resource/Downloads/OWLS Payment Notice (Application)Template.pdf");
	        final PDDocument documentToBeParsed = PDDocument.load(paymentNotice);
	        final PDFTextStripper stripper = new PDFTextStripper();
	     
	        final String pdfText = stripper.getText(documentToBeParsed);
	        System.out.println("Parsed text size is " + pdfText.length() + " characters:");
	        System.out.println(pdfText);
	        
	        Common.logMandetoryAssert("Verify that User's Postal address is available in Payment Notice PDF.");
			if(applicationVerificatioPage.verifyAddressFromPDF(dataFileName,pdfText)) {
				Common.optionalAssertPassed();
				Common.writeDataProperties(configFileName, "Payment_Notice_Verified","True","");
				Common.logStatus("Pass");
				paymentNotice.delete();
			}else {
				Common.optionalAssertFailed();
				numOfFailure++;
				paymentNotice.delete();
			}
	     }
	     catch (IOException ioEx)
	     {
	      System.err.println("Exception while trying to parse text from PDF at "+ ioEx.getMessage());
	     }
		
		if(numOfFailure > 0)
		{
			Common.logStatus("Fail");
			Assert.assertTrue(false);
		}			
	}
	
/*	@Test // pass
	public void verifyCreateSpecimenApplication() {

		int numOfFailure = 0;
		int logStep = 1;

		String dataFileName="specimenApplicationData";
		String configFileName="specimenApplicationConfig";
		
		Common.logcase("Testcase Id: TC_Portal_003");
		Common.logcase("Testcase Name: To verify Online user submits a specimen application with conditions to trigger the 'Review Application'  task.");

		Common.logstep("Step " + (logStep++) + " : Open url:<a>" + testUrl + "</a>");
		
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
		
		Common.logstep("Step " + (logStep++)+" Click on Details Menu.");
		applicationVerificatioPage=applicationIndexPage.clickonDetails();		
		
		Common.logstep("Step " + (logStep++)+" Click on Contact Details Option.");
		applicationVerificatioPage=applicationIndexPage.clickonContactDetail(dataFileName);		
	
		Common.logstep("Step " + (logStep++)+" Click on Application Menu.");
		applicationIndexPage.clickonApplication();
		
		Common.logstep("Step " + (logStep++)+" Click on Create Application button.");
		applicationIndexPage.clickonCreateApplication();
		
		Common.logstep("Step " + (logStep++)+" Select Application type from Drop down.");
		applicationIndexPage.SelectApplicationType("Wildlife Specimen Licence");
	
		Common.logstep("Step " + (logStep++)+" Click on Next button");
		applicationIndexPage.clickonNext();
		
		Common.logOptionalAssert("Verify Operating Address retrived correctly from User's Contact Details->Primary Address.");
		if(applicationVerificatioPage.verifyOperatingAddress(dataFileName)) {
			Common.optionalAssertPassed();;
		}else {
			Common.optionalAssertFailed();
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Select 'Same as above' checkbox'.");
		applicationIndexPage.checkboxSameasAbove();
		
		Common.logOptionalAssert("Verify Postal Address pasted correctly as same as Specified Premises.");
		if(applicationVerificatioPage.verifyPostalAddress()) {
			Common.optionalAssertPassed();
		}else {
			
			Common.optionalAssertFailed();
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Select 'I have prior convictions' radio button and enter offence details as below.");
		applicationIndexPage.ClickonIHavepriorConviction();
		  
		  String offence = Common.generateRandomChars(10);
		  appdata.setOffenceName(offence);
		  String offenceYear = String.valueOf(Common.randBetween(2000, 2016));
		  appdata.setOffenceYear(offenceYear);
		  String offenceState="VIC";
		  appdata.setOffencestate(offenceState);
		  
		  Common.log("Offence: "+offence);
		  Common.log("Offence Year: "+offenceYear);
		  Common.log("Offence State: "+offenceState);
		  
		  applicationIndexPage.EnterOffenceDetails(offence,offenceYear,offenceState);
		  
		  applicationIndexPage.clickonAddpriorConvictionsButton();
		  
		  Common.logOptionalAssert("Verify that Conviction data should be displayed below conviction form.");
		  if (applicationVerificatioPage.VerifyAddedConvictiondata(offence,offenceYear,offenceState)) {
		   Common.optionalAssertPassed();
		  } else {
			  Common.optionalAssertFailed();
		   numOfFailure++;
		  }
		
		Common.logstep("Step " + (logStep++)+" Select 'Do you have any wildlife in your possession at the time of this application?' to YES.");
		applicationIndexPage.HaveanyWildInPossession();
		
		Common.logstep("Step " + (logStep++)+" Enter 'Licence Number'.");
		applicationIndexPage.enterLicenceNumber();
		
		Common.logstep("Step " + (logStep++)+" Select 'Species'.");
		applicationIndexPage.selectSpecies();
		
		Common.logstep("Step " + (logStep++)+" Enter number of species.");
		applicationIndexPage.enterNumberofSpecies();
		
		Common.logstep("Step " + (logStep++)+"Select Species description (Alive/Dead)");
		applicationIndexPage.selectspeciesDiscription();
		
		Common.logstep("Step " + (logStep++)+" Click on 'Add Species' button");
		applicationIndexPage.clickonAddSpecies();
		
		Common.logOptionalAssert("Verify that if User has selected any wildlife possession at the time of application persisted in the Species Table");
		
		if(applicationVerificatioPage.verifySpeciesTable()) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "incorrectSpeciesTable");
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Upload file.");
		applicationIndexPage.UploadFile();
		
		Common.logOptionalAssert("Verify that Uploaded file persisted in 'Uploaded Documents Table.'");
		
		if(applicationVerificatioPage.verifyUploadedDocument()) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "incorrectDocumentTable");
			numOfFailure++;
		}
		
		Common.logstep("Step " + (logStep++)+" Click on submit.");
		applicationIndexPage.clickonSubmit();
		
		Common.logstep("Step " + (logStep++)+" Navigate to My Application page.");
		applicationIndexPage.clickonApplication();
	
		Common.logMandetoryAssert("Verify that created application is displayed on Apllication list of 'My Application' page.");
		
		if (applicationVerificatioPage.VerifyCreatedApplicationStatus(dataFileName,"Wildlife Specimen Licence")) {
			Common.writeProperties(configFileName,"Application_Created_Sucessfully","True","");
			Common.optionalAssertPassed();
			Common.logStatus("Pass");
		
		} else {
			Common.writeProperties(configFileName,"Application_Created_Sucessfully","False"," ");
			numOfFailure++;
		}

		if (numOfFailure > 0) {
			Assert.assertTrue(false);
			Common.logStatus("Fail");			
		}
	}
	
	@Test // pass
	public void verifySpecimenAppPaymentNoticeAndAwaitingTask() throws IOException {

		int numOfFailure = 0;
		int logStep = 1;

		String dataFileName="specimenApplicationData";
		String configFileName="specimenApplicationConfig";
	
		Common.logcase("Testcase Id: TC_Portal_004");
		Common.logcase("Testcase Name: The 'Payment Notice' and 'Pay Fee' links should be visible to Online Portal User. The online Portal user should be able to download The 'Payment Notice'.");

		try {
			String interview_inspection = Common.readProperties(configFileName,"Delegate_Approval_Task_Complete");
			if (interview_inspection.contains("False")) {
				throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
			}
		} catch (Exception e) {
			throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
		}
		appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));

		Common.logstep("Step " + (logStep++) + " : Open url:<a>" + testUrl + "</a>");
		
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
		
		Common.logstep("Step " + (logStep++)+" Click on Application Menu.");
		applicationIndexPage.clickonApplication();
		
		Common.logMandetoryAssert("Verify that 'Payment Notice' and 'Pay Fee' Links are available for Payment Pending Application.");
		
		if(applicationVerificatioPage.verifyPaymentPendingApplication()) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "missingPaymentNotice.png");
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Click on Payment Notice link to download Payment Notice PDF.");
		applicationIndexPage.clickonPaymentNotice(dataFileName);
		Common.pause(5);
		
		try
	     {
	        //final URL scalaByExampleUrl = new URL(address);
			File paymentNotice=new File("Resource/Downloads/OWLS Payment Notice (Application)Template.pdf");
	        final PDDocument documentToBeParsed = PDDocument.load(paymentNotice);
	        final PDFTextStripper stripper = new PDFTextStripper();
	     
	        final String pdfText = stripper.getText(documentToBeParsed);
	        System.out.println("Parsed text size is " + pdfText.length() + " characters:");
	        System.out.println(pdfText);
	        
	        Common.logMandetoryAssert("Verify that User's Postal address is available in Payment Notice PDF.");
			if(applicationVerificatioPage.verifyAddressFromPDF(dataFileName,pdfText)) {
				Common.optionalAssertPassed();
				Common.logStatus("Pass");
				paymentNotice.delete();
			}else {
				Common.optionalAssertFailed();
				numOfFailure++;
				paymentNotice.delete();
			}
	     }
	     catch (IOException ioEx)
	     {
	      System.err.println("Exception while trying to parse text from PDF at "+ ioEx.getMessage());
	     }
		
		if(numOfFailure >0)
		{
			Common.logStatus("Fail");
			Assert.assertTrue(false);
		}
	}
	*/
	
	@Test // pass
	public void createApplicationWithoutError() {

		int numOfFailure = 0;
		int logStep = 1;

	/*	String dataFileName="basicApplicationNoValidationData";
		String configFileName="basicApplicationConfig";
		
		Common.logcase("Testcase Id: TC_Portal_005");
		Common.logcase("Testcase Name: To verify Online user submits a basic application with no conditions to trigger the 'Await payment confirmation'  task.");
*/
		Common.logcase(testName);
		Common.logstep("Step " + (logStep++) + " : Open url:<a>" + testUrl + "</a>");
		
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
		
		Common.logstep("Step " + (logStep++)+" Click on Details Menu.");
		applicationVerificatioPage=applicationIndexPage.clickonDetails();		
		
		Common.logstep("Step " + (logStep++)+" Click on Contact Details Option.");
		applicationVerificatioPage=applicationIndexPage.clickonContactDetail(configFileName);		
	
		Common.logstep("Step " + (logStep++)+" Click on Application Menu.");
		applicationIndexPage.clickonApplication();
		
		Common.logstep("Step " + (logStep++)+" Click on Create Application button.");
		applicationIndexPage.clickonCreateApplication();
		
		Common.logstep("Step " + (logStep++)+" Select Application type from Drop down.");
		applicationIndexPage.SelectApplicationType(applicationType);
	
		Common.logstep("Step " + (logStep++)+" Click on Next button");
		applicationIndexPage.clickonNext();
		
		Common.logOptionalAssert("Verify Operating Address retrived correctly from User's Contact Details->Primary Address.");
		if(applicationVerificatioPage.verifyOperatingAddress(configFileName)) {
			Common.optionalAssertPassed();;
		}else {
			Common.optionalAssertFailed();
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Select 'Same as above' checkbox'.");
		applicationIndexPage.checkboxSameasAbove();
		
		Common.logOptionalAssert("Verify Postal Address pasted correctly as same as Specified Premises.");
		if(applicationVerificatioPage.verifyPostalAddress()) {
			Common.optionalAssertPassed();
		}else {
			
			Common.optionalAssertFailed();
			numOfFailure++;
		}
		
		Common.logstep("Step " + (logStep++)+" Choose Prefered Licence Expiry.");
		applicationIndexPage.licenceExpiry(applicationType);
	
		Common.log("Click on 'No Conviction' Option");
		applicationIndexPage.selectNoConviction();
		
		Common.log(" Select 'No wildlife possession'..!");
		applicationIndexPage.noWildLifePossession();

		Common.log("Click on 'No Conviction' Option");
		applicationIndexPage.selectNoConviction();
		
		Common.logstep("Step " + (logStep++)+" Choose I Agree from Declaration By Applicant Options.");
		applicationIndexPage.chooseIAgree();
		
		Common.logstep("Step " + (logStep++)+" Click on submit.");
		applicationIndexPage.clickonSubmit();

		Common.logstep("Step " + (logStep++)+" Navigate to My Application page.");
		applicationIndexPage.clickonApplication();
		
		Common.logMandetoryAssert("Verify Application created without any error condition in 'Payment Pending' status.");
		
		if(applicationVerificatioPage.verifyCreatedApplicationStatus(configFileName,applicationType)) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "applicationNotCreated.png");
			numOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that 'Payment Notice' and 'Pay Fee' Links are available for Payment Pending Application.");
		
		if(applicationVerificatioPage.verifyPaymentPendingApplication()) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "missingPaymentNotice.png");
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Click on Payment Notice link to download Payment Notice PDF.");
		applicationIndexPage.clickonPaymentNotice(configFileName);
		Common.pause(5);
		
		try
	     {
	        //final URL scalaByExampleUrl = new URL(address);
			File paymentNotice=new File("Resource/Downloads/OWLS Payment Notice (Application)Template.pdf");
	        final PDDocument documentToBeParsed = PDDocument.load(paymentNotice);
	        final PDFTextStripper stripper = new PDFTextStripper();
	     
	        final String pdfText = stripper.getText(documentToBeParsed);
	        System.out.println("Parsed text size is " + pdfText.length() + " characters:");
	        System.out.println(pdfText);
	        
	        Common.logMandetoryAssert("Verify that User's Postal address is available in Payment Notice PDF.");
			if(applicationVerificatioPage.verifyAddressFromPDF(configFileName,pdfText)) {
				Common.optionalAssertPassed();
				Common.writeDataProperties(configFileName, "Payment_Notice_Verified","True","");
				Common.logStatus("Pass");
				paymentNotice.delete();
			}else {
				Common.optionalAssertFailed();
				numOfFailure++;
				paymentNotice.delete();
				Runtime.getRuntime().exec("del G:\\KiwiQA Automation Projects\\OWLS_Automation\\Resource\\Downloads\\*");
			}
	     }
	     catch (IOException ioEx)
	     {
	      System.err.println("Exception while trying to parse text from PDF at "+ ioEx.getMessage());
	     }
		
		if(numOfFailure > 0)
		{
			Common.logStatus("Fail");
			Assert.assertTrue(false);
		}

	}
	/*
	@Test // pass
	public void createSpecimenApplicationWithoutError() {

		int numOfFailure = 0;
		int logStep = 1;

		String dataFileName="specimenApplicationNoValidationData";
		
		Common.logcase("Testcase Id: TC_Portal_006");
		Common.logcase("Testcase Name: To verify Online user submits a specimen application with no conditions to trigger the 'Await Payment Confirmation'  task.");

		Common.logstep("Step " + (logStep++) + " : Open url:<a>" + testUrl + "</a>");
		
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
		
		Common.logstep("Step " + (logStep++)+" Click on Details Menu.");
		applicationVerificatioPage=applicationIndexPage.clickonDetails();		
		
		Common.logstep("Step " + (logStep++)+" Click on Contact Details Option.");
		applicationVerificatioPage=applicationIndexPage.clickonContactDetail(dataFileName);		
	
		Common.logstep("Step " + (logStep++)+" Click on Application Menu.");
		applicationIndexPage.clickonApplication();
		
		Common.logstep("Step " + (logStep++)+" Click on Create Application button.");
		applicationIndexPage.clickonCreateApplication();
		
		Common.logstep("Step " + (logStep++)+" Select Application type from Drop down.");
		applicationIndexPage.SelectApplicationType("Wildlife Specimen Licence");
	
		Common.logstep("Step " + (logStep++)+" Click on Next button");
		applicationIndexPage.clickonNext();
		
		Common.logOptionalAssert("Verify Operating Address retrived correctly from User's Contact Details->Primary Address.");
		if(applicationVerificatioPage.verifyOperatingAddress(dataFileName)) {
			Common.optionalAssertPassed();;
		}else {
			Common.optionalAssertFailed();
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Select 'Same as above' checkbox'.");
		applicationIndexPage.checkboxSameasAbove();
		
		Common.logOptionalAssert("Verify Postal Address pasted correctly as same as Specified Premises.");
		if(applicationVerificatioPage.verifyPostalAddress()) {
			Common.optionalAssertPassed();
		}else {
			
			Common.optionalAssertFailed();
			numOfFailure++;
		}
		
		Common.logstep("Step " + (logStep++)+" Click on submit.");
		applicationIndexPage.clickonSubmit();

		Common.logstep("Step " + (logStep++)+" Navigate to My Application page.");
		applicationIndexPage.clickonApplication();
		
		Common.logMandetoryAssert("Verify Application created without any error condition in 'Payment Pending' status.");
		
		if(applicationVerificatioPage.verifyCreatedApplicationStatus(dataFileName,"Wildlife Specimen Licence")) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "applicationNotCreated.png");
			numOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that 'Payment Notice' and 'Pay Fee' Links are available for Payment Pending Application.");
		
		if(applicationVerificatioPage.verifyPaymentPendingApplication()) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "missingPaymentNotice.png");
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Click on Payment Notice link to download Payment Notice PDF.");
		applicationIndexPage.clickonPaymentNotice(dataFileName);
		Common.pause(5);
		
		try
	     {
	        //final URL scalaByExampleUrl = new URL(address);
			File paymentNotice=new File("Resource/Downloads/OWLS Payment Notice (Application)Template.pdf");
	        final PDDocument documentToBeParsed = PDDocument.load(paymentNotice);
	        final PDFTextStripper stripper = new PDFTextStripper();
	     
	        final String pdfText = stripper.getText(documentToBeParsed);
	        System.out.println("Parsed text size is " + pdfText.length() + " characters:");
	        System.out.println(pdfText);
	        
	        Common.logMandetoryAssert("Verify that User's Postal address is available in Payment Notice PDF.");
			if(applicationVerificatioPage.verifyAddressFromPDF(dataFileName,pdfText)) {
				Common.optionalAssertPassed();
				Common.writeDataProperties(dataFileName, "Payment_Notice_Verified","True","");
				Common.logStatus("Pass");
				paymentNotice.delete();
			}
			else
			{
				Common.optionalAssertFailed();
				numOfFailure++;
				paymentNotice.delete();
			}
	     }
	     catch (IOException ioEx)
	     {
	      System.err.println("Exception while trying to parse text from PDF at "+ ioEx.getMessage());
	     }
		
		if(numOfFailure > 0)
		{
			Common.logStatus("Fail");
			Assert.assertTrue(false);
		}

	}
	
	@Test // pass
	public void createAdvancedApplicationWithoutError() {

		int numOfFailure = 0;
		int logStep = 1;

		String dataFileName="advancedApplicationNoValidationData";
		
		Common.logcase("Testcase Id: TC_Portal_007");
		Common.logcase("Testcase Name: To verify Online user submits a advance application with no conditions to trigger the 'Await Payment Confirmation'  task.");

		Common.logstep("Step " + (logStep++) + " : Open url:<a>" + testUrl + "</a>");
		
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
		
		Common.logstep("Step " + (logStep++)+" Click on Details Menu.");
		applicationVerificatioPage=applicationIndexPage.clickonDetails();		
		
		Common.logstep("Step " + (logStep++)+" Click on Contact Details Option.");
		applicationVerificatioPage=applicationIndexPage.clickonContactDetail(dataFileName);		
	
		Common.logstep("Step " + (logStep++)+" Click on Application Menu.");
		applicationIndexPage.clickonApplication();
		
		Common.logstep("Step " + (logStep++)+" Click on Create Application button.");
		applicationIndexPage.clickonCreateApplication();
		
		Common.logstep("Step " + (logStep++)+" Select Application type from Drop down.");
		applicationIndexPage.SelectApplicationType("Wildlife Advanced Licence");
	
		Common.logstep("Step " + (logStep++)+" Click on Next button");
		applicationIndexPage.clickonNext();
		
		Common.logOptionalAssert("Verify Operating Address retrived correctly from User's Contact Details->Primary Address.");
		if(applicationVerificatioPage.verifyOperatingAddress(dataFileName)) {
			Common.optionalAssertPassed();;
		}else {
			Common.optionalAssertFailed();
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Select 'Same as above' checkbox'.");
		applicationIndexPage.checkboxSameasAbove();
		
		Common.logOptionalAssert("Verify Postal Address pasted correctly as same as Specified Premises.");
		if(applicationVerificatioPage.verifyPostalAddress()) {
			Common.optionalAssertPassed();
		}else {
			
			Common.optionalAssertFailed();
			numOfFailure++;
		}
		
		Common.logstep("Step " + (logStep++)+" Click on submit.");
		applicationIndexPage.clickonSubmit();

		Common.logstep("Step " + (logStep++)+" Navigate to My Application page.");
		applicationIndexPage.clickonApplication();
		
		Common.logMandetoryAssert("Verify Application created without any error condition in 'Payment Pending' status.");
		
		if(applicationVerificatioPage.verifyCreatedApplicationStatus(dataFileName,"Wildlife Advanced Licence")) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "applicationNotCreated.png");
			numOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that 'Payment Notice' and 'Pay Fee' Links are available for Payment Pending Application.");
		
		if(applicationVerificatioPage.verifyPaymentPendingApplication()) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "missingPaymentNotice.png");
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Click on Payment Notice link to download Payment Notice PDF.");
		applicationIndexPage.clickonPaymentNotice(dataFileName);
		Common.pause(5);
		
		try
	     {
	        //final URL scalaByExampleUrl = new URL(address);
			File paymentNotice=new File("Resource/Downloads/OWLS Payment Notice (Application)Template.pdf");
	        final PDDocument documentToBeParsed = PDDocument.load(paymentNotice);
	        final PDFTextStripper stripper = new PDFTextStripper();
	     
	        final String pdfText = stripper.getText(documentToBeParsed);
	        System.out.println("Parsed text size is " + pdfText.length() + " characters:");
	        System.out.println(pdfText);
	        
	        Common.logMandetoryAssert("Verify that User's Postal address is available in Payment Notice PDF.");
			if(applicationVerificatioPage.verifyAddressFromPDF(dataFileName,pdfText)) {
				Common.optionalAssertPassed();
				Common.writeDataProperties(dataFileName, "Payment_Notice_Verified","True","");
				Common.logStatus("Pass");
				paymentNotice.delete();
			}else {
				Common.optionalAssertFailed();
				numOfFailure++;
				paymentNotice.delete();
			}
	     }
	     catch (IOException ioEx)
	     {
	      System.err.println("Exception while trying to parse text from PDF at "+ ioEx.getMessage());
	     }
		
		if(numOfFailure > 0)
		{
			Common.logStatus("Fail");
			Assert.assertTrue(false);
		}

	}

	@Test // pass
	public void verifyCreateAdvancedApplication() {

		int numOfFailure = 0;
		int logStep = 1;

		String dataFileName="advancedApplicationData";
		String configFileName="basicApplicationConfig";
		
		Common.logcase("Testcase Id: TC_Portal_008");
		Common.logcase("Testcase Name: To verify Online user submits an advanced application with conditions to trigger the 'Review Application'  task.");

		Common.logstep("Step " + (logStep++) + " : Open url:<a>" + testUrl + "</a>");
		
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
		
		Common.logstep("Step " + (logStep++)+" Click on Details Menu.");
		applicationVerificatioPage=applicationIndexPage.clickonDetails();		
		
		Common.logstep("Step " + (logStep++)+" Click on Contact Details Option.");
		applicationVerificatioPage=applicationIndexPage.clickonContactDetail(dataFileName);		
	
		Common.logstep("Step " + (logStep++)+" Click on Application Menu.");
		applicationIndexPage.clickonApplication();
		
		Common.logstep("Step " + (logStep++)+" Click on Create Application button.");
		applicationIndexPage.clickonCreateApplication();
		
		Common.logstep("Step " + (logStep++)+" Select Application type from Drop down.");
		applicationIndexPage.SelectApplicationType("Wildlife Advanced Licence");
	
		Common.logstep("Step " + (logStep++)+" Click on Next button");
		applicationIndexPage.clickonNext();
		
		Common.logOptionalAssert("Verify Operating Address retrived correctly from User's Contact Details->Primary Address.");
		if(applicationVerificatioPage.verifyOperatingAddress(dataFileName)) {
			Common.optionalAssertPassed();;
		}else {
			Common.optionalAssertFailed();
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Select 'Same as above' checkbox'.");
		applicationIndexPage.checkboxSameasAbove();
		
		Common.logOptionalAssert("Verify Postal Address pasted correctly as same as Specified Premises.");
		if(applicationVerificatioPage.verifyPostalAddress()) {
			Common.optionalAssertPassed();
		}else {
			
			Common.optionalAssertFailed();
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Select 'I have prior convictions' radio button and enter offence details as below.");
		applicationIndexPage.ClickonIHavepriorConviction();
		  
		  String offence = Common.generateRandomChars(10);
		  appdata.setOffenceName(offence);
		  String offenceYear = String.valueOf(Common.randBetween(2000, 2016));
		  appdata.setOffenceYear(offenceYear);
		  String offenceState="VIC";
		  appdata.setOffencestate(offenceState);
		  
		  Common.log("Offence: "+offence);
		  Common.log("Offence Year: "+offenceYear);
		  Common.log("Offence State: "+offenceState);
		  
		  applicationIndexPage.EnterOffenceDetails(offence,offenceYear,offenceState);
		  
		  applicationIndexPage.clickonAddpriorConvictionsButton();
		  
		  Common.logOptionalAssert("Verify that Conviction data should be displayed below conviction form.");
		  if (applicationVerificatioPage.VerifyAddedConvictiondata(offence,offenceYear,offenceState)) {
		   Common.optionalAssertPassed();
		  } else {
			  Common.optionalAssertFailed();
		   numOfFailure++;
		  }
		
		  Common.log("Click on 'No Conviction' Option");
		  applicationIndexPage.selectNoConviction();
		  
		Common.logstep("Step " + (logStep++)+" Select 'Do you have any wildlife in your possession at the time of this application?' to YES.");
		applicationIndexPage.HaveanyWildInPossession();
		
		Common.logstep("Step " + (logStep++)+" Enter 'Licence Number'.");
		applicationIndexPage.enterLicenceNumber();
		
		Common.logstep("Step " + (logStep++)+" Select 'Species'.");
		applicationIndexPage.selectSpecies();
		
		Common.logstep("Step " + (logStep++)+" Enter number of species.");
		applicationIndexPage.enterNumberofSpecies();
		
		Common.logstep("Step " + (logStep++)+"Select Species description (Alive/Dead)");
		applicationIndexPage.selectspeciesDiscription();
		
		Common.logstep("Step " + (logStep++)+" Click on 'Add Species' button");
		applicationIndexPage.clickonAddSpecies();
		
		Common.logOptionalAssert("Verify that if User has selected any wildlife possession at the time of application persisted in the Species Table");
		
		if(applicationVerificatioPage.verifySpeciesTable()) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "incorrectSpeciesTable");
			numOfFailure++;
		}
		
		Common.log(" Select 'No wildlife possession'..!");
		applicationIndexPage.noWildLifePossession();
		
		Common.logstep((logStep++)+" Upload file.");
		applicationIndexPage.UploadFile();
		
		Common.logOptionalAssert("Verify that Uploaded file persisted in 'Uploaded Documents Table.'");
		
		if(applicationVerificatioPage.verifyUploadedDocument()) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "incorrectDocumentTable");
			numOfFailure++;
		}
		
		Common.logstep("Step " + (logStep++)+" Click on submit.");
		applicationIndexPage.clickonSubmit();
		
		Common.logstep("Step " + (logStep++)+" Navigate to My Application page.");
		applicationIndexPage.clickonApplication();
	
		Common.logMandetoryAssert("Verify that created application is displayed on Apllication list of 'My Application' page.");
		
		if (applicationVerificatioPage.VerifyCreatedApplicationStatus(dataFileName,"Wildlife Advanced Licence")) {
			Common.writeProperties(configFileName,"Application_Created_Sucessfully","True","");
			Common.optionalAssertPassed();
			Common.logStatus("Pass");
		
		} else {
			Common.writeProperties(configFileName,"Application_Created_Sucessfully","False"," ");
			numOfFailure++;
		}

		if (numOfFailure > 0) {
			Assert.assertTrue(false);
			Common.logStatus("Fail");			
		}
	}

	@Test // pass
	public void verifyAdvancedAppPaymentNoticeAndAwaitingTask() throws IOException {

		int numOfFailure = 0;
		int logStep = 1;

		String dataFileName="advancedApplicationData";
		String configFileName="advancedApplicationConfig";
	
		Common.logcase("Testcase Id: TC_Portal_008");
		Common.logcase("Testcase Name: The 'Payment Notice' and 'Pay Fee' links should be visible to Online Portal User. The online Portal user should be able to download The 'Payment Notice'.");

		try {
			String interview_inspection = Common.readProperties(configFileName,"Delegate_Approval_Task_Complete");
			if (interview_inspection.contains("False")) {
				throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
			}
		} catch (Exception e) {
			throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
		}
		appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));

		Common.logstep("Step " + (logStep++) + " : Open url:<a>" + testUrl + "</a>");
		
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
		
		Common.logstep("Step " + (logStep++)+" Click on Application Menu.");
		applicationIndexPage.clickonApplication();
		
		Common.logMandetoryAssert("Verify that 'Payment Notice' and 'Pay Fee' Links are available for Payment Pending Application.");
		
		if(applicationVerificatioPage.verifyPaymentPendingApplication()) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "missingPaymentNotice.png");
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Click on Payment Notice link to download Payment Notice PDF.");
		applicationIndexPage.clickonPaymentNotice(dataFileName);
		Common.pause(5);
		
		try
	     {
	        //final URL scalaByExampleUrl = new URL(address);
			File paymentNotice=new File("Resource/Downloads/OWLS Payment Notice (Application)Template.pdf");
	        final PDDocument documentToBeParsed = PDDocument.load(paymentNotice);
	        final PDFTextStripper stripper = new PDFTextStripper();
	     
	        final String pdfText = stripper.getText(documentToBeParsed);
	        System.out.println("Parsed text size is " + pdfText.length() + " characters:");
	        System.out.println(pdfText);
	        
	        Common.logMandetoryAssert("Verify that User's Postal address is available in Payment Notice PDF.");
			if(applicationVerificatioPage.verifyAddressFromPDF(dataFileName,pdfText)) {
				Common.optionalAssertPassed();
				Common.logStatus("Pass");
				paymentNotice.delete();
			}else {
				Common.optionalAssertFailed();
				numOfFailure++;
				paymentNotice.delete();
			}
	     }
	     catch (IOException ioEx)
	     {
	      System.err.println("Exception while trying to parse text from PDF at "+ ioEx.getMessage());
	     }
		
		if(numOfFailure >0)
		{
			Common.logStatus("Fail");
			Assert.assertTrue(false);
		}
	}
	
	@Test // pass
	public void verifyWarningMessage() {

		int numOfFailure = 0;
		int logStep = 1;

		String dataFileName="basicApplicationData";
		String configFileName="basicApplicationConfig";
		
		Common.logcase("Testcase Id: TC_Portal_010 ");
		Common.logcase("Testcase Name: To verify Online user submits a basic application with conditions to trigger the 'Review Application'  task.");

		Common.logstep("Step " + (logStep++) + " : Open url:<a>" + testUrl + "</a>");
		
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
		
		Common.logstep("Step " + (logStep++)+" Click on Application Menu.");
		applicationIndexPage.clickonApplication();
		
		Common.logstep("Step " + (logStep++)+" Click on Create Application button.");
		applicationIndexPage.clickonCreateApplication();
		
		Common.logstep("Step " + (logStep++)+" Select Application type from Drop down.");
		applicationIndexPage.SelectApplicationType("Wildlife Advanced Licence");
	
		Common.logstep("Step " + (logStep++)+" Click on Next button");
		applicationIndexPage.clickonNext();
		
		Common.logMandetoryAssert(" Verify that 'Warning' pop-up is displayed.");
		if(applicationVerificatioPage.verifyWarningPopup()) {
			Common.optionalAssertPassed();;
		}else {
			Common.optionalAssertFailed();
			numOfFailure++;
		}
		
		Common.logMandetoryAssert(" Verify Warning Message content .. !");
		if(applicationVerificatioPage.verifyWarningMessage()) {
			Common.optionalAssertPassed();;
		}else {
			Common.optionalAssertFailed();
			numOfFailure++;
		} 
		
		Common.logMandetoryAssert(" Verify 'OK' and 'Cancel' buttons available in Warning pop-up!");
		if(applicationVerificatioPage.verifyButtons()) {
			Common.optionalAssertPassed();
			Common.logStatus("Pass");

		}else {
			Common.optionalAssertFailed();
			numOfFailure++;
		} 
		
		if (numOfFailure > 0) {
			Common.logStatus("Fail");			
			Assert.assertTrue(false);
		}
	}
	
	@Test // pass
	public void verifyAdvancedApplicationWarning() {

		int numOfFailure = 0;
		int logStep = 1;

		String dataFileName="basicApplicationData";
		String configFileName="basicApplicationConfig";
		
		Common.logcase("Testcase Id: TC_Portal_011 ");
		Common.logcase("Testcase Name: To verify Online User won't allow to apply for Advaced License when Basic License Permit Already exist.");

		Common.logstep("Step " + (logStep++) + " : Open url:<a>" + testUrl + "</a>");
		
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
		
		Common.logstep("Step " + (logStep++)+" Click on Application Menu.");
		applicationIndexPage.clickonApplication();
		
		Common.logstep("Step " + (logStep++)+" Click on Create Application button.");
		applicationIndexPage.clickonCreateApplication();
		
		Common.logstep("Step " + (logStep++)+" Select Application type from Drop down.");
		applicationIndexPage.SelectApplicationType("Wildlife Advanced Licence");
	
		Common.logstep("Step " + (logStep++)+" Click on Next button");
		applicationIndexPage.clickonNext();
		
		Common.logMandetoryAssert(" Verify that validation error message.");
		if(applicationVerificatioPage.verifyValidationError()) {
			Common.optionalAssertPassed();
			Common.logStatus("Pass");
		}else {
			Common.optionalAssertFailed();
			numOfFailure++;
		}
		
		if (numOfFailure > 0) {
			Common.logStatus("Fail");			
			Assert.assertTrue(false);
		}
	}
	
	
	@Test // pass
	public void verifyBasicApplicationWarning() {

		int numOfFailure = 0;
		int logStep = 1;

		String dataFileName="basicApplicationData";
		String configFileName="basicApplicationConfig";
		
		Common.logcase("Testcase Id: TC_Portal_011 ");
		Common.logcase("Testcase Name: To verify Online User won't allow to apply for Advaced License when Basic License Permit Already exist.");

		Common.logstep("Step " + (logStep++) + " : Open url:<a>" + testUrl + "</a>");
		
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
		
		Common.logstep("Step " + (logStep++)+" Click on Application Menu.");
		applicationIndexPage.clickonApplication();
		
		Common.logstep("Step " + (logStep++)+" Click on Create Application button.");
		applicationIndexPage.clickonCreateApplication();
		
		Common.logstep("Step " + (logStep++)+" Select Application type from Drop down.");
		applicationIndexPage.SelectApplicationType("Wildlife Basic Licence");
	
		Common.logstep("Step " + (logStep++)+" Click on Next button");
		applicationIndexPage.clickonNext();
		
		Common.logMandetoryAssert(" Verify that validation error message.");
		if(applicationVerificatioPage.verifyValidationError()) {
			Common.optionalAssertPassed();
			Common.logStatus("Pass");
		}else {
			Common.optionalAssertFailed();
			numOfFailure++;
		}
		
		if (numOfFailure > 0) {
			Common.logStatus("Fail");			
			Assert.assertTrue(false);
		}
	}

	@Test // pass
	public void verifyCreateDingoApplication (){

		int numOfFailure = 0;
		int logStep = 1;

		String dataFileName="dingoApplicationData";
		String configFileName="dingoApplicationConfig";
		
		Common.logcase("Testcase Id: TC_Portal_013 ");
		Common.logcase("Testcase Name: To verify that user is able to submit Dingo License application to OWLS portal.");

		Common.logstep("Step " + (logStep++) + " : Open url:<a>" + testUrl + "</a>");
		
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
		
		Common.logstep("Step " + (logStep++)+" Click on Details Menu.");
		applicationVerificatioPage=applicationIndexPage.clickonDetails();		
		
		Common.logstep("Step " + (logStep++)+" Click on Contact Details Option.");
		applicationVerificatioPage=applicationIndexPage.clickonContactDetail(dataFileName);		
	
		Common.logstep("Step " + (logStep++)+" Click on Application Menu.");
		applicationIndexPage.clickonApplication();
		
		Common.logstep("Step " + (logStep++)+" Click on Create Application button.");
		applicationIndexPage.clickonCreateApplication();
		
		Common.logstep("Step " + (logStep++)+" Select Application type from Drop down.");
		applicationIndexPage.SelectApplicationType("Dingo Licence");
	
		Common.logstep("Step " + (logStep++)+" Click on Next button");
		applicationIndexPage.clickonNext();
		
		Common.logOptionalAssert("Verify Operating Address retrived correctly from User's Contact Details->Primary Address.");
		if(applicationVerificatioPage.verifyOperatingAddress(dataFileName)) {
			Common.optionalAssertPassed();;
		}else {
			Common.optionalAssertFailed();
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Select 'Same as above' checkbox'.");
		applicationIndexPage.checkboxSameasAbove();
		
		Common.logOptionalAssert("Verify Postal Address pasted correctly as same as Specified Premises.");
		if(applicationVerificatioPage.verifyPostalAddress()) {
			Common.optionalAssertPassed();
		}else {
			
			Common.optionalAssertFailed();
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Select 'I have prior convictions' radio button and enter offence details as below.");
		applicationIndexPage.ClickonIHavepriorConviction();
		  
		  String offence = Common.generateRandomChars(10);
		  appdata.setOffenceName(offence);
		  String offenceYear = String.valueOf(Common.randBetween(2000, 2016));
		  appdata.setOffenceYear(offenceYear);
		  String offenceState="VIC";
		  appdata.setOffencestate(offenceState);
		  
		  Common.log("Offence: "+offence);
		  Common.log("Offence Year: "+offenceYear);
		  Common.log("Offence State: "+offenceState);
		  
		  applicationIndexPage.EnterOffenceDetails(offence,offenceYear,offenceState);
		  
		  applicationIndexPage.clickonAddpriorConvictionsButton();
		  
		  Common.logOptionalAssert("Verify that Conviction data should be displayed below conviction form.");
		  if (applicationVerificatioPage.VerifyAddedConvictiondata(offence,offenceYear,offenceState)) {
		   Common.optionalAssertPassed();
		  } else {
			  Common.optionalAssertFailed();
		   numOfFailure++;
		  }
		
		Common.log("Click on 'No Conviction' Option");
		applicationIndexPage.selectNoConviction();
		  
		Common.logstep("Step " + (logStep++)+" Select 'Do you have any wildlife in your possession at the time of this application?' to YES.");
		applicationIndexPage.HaveanyWildInPossession();
		
		Common.logstep("Step " + (logStep++)+" Enter 'Licence Number'.");
		applicationIndexPage.enterLicenceNumber();
		
		Common.logstep("Step " + (logStep++)+" Select 'Species'.");
		applicationIndexPage.selectDingo();
		
		Common.logstep("Step " + (logStep++)+" Enter number of species.");
		applicationIndexPage.enterNumberofSpecies();
		
		Common.logstep("Step " + (logStep++)+"Select Species description (Alive/Dead)");
		applicationIndexPage.selectspeciesDiscription();
		
		Common.logstep("Step " + (logStep++)+" Click on 'Add Species' button");
		applicationIndexPage.clickonAddSpecies();
		
		Common.logOptionalAssert("Verify that if User has selected any wildlife possession at the time of application persisted in the Species Table");
		
		if(applicationVerificatioPage.verifySpeciesTable()) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "incorrectSpeciesTable");
			numOfFailure++;
		}
		
		Common.log(" Select 'No wildlife possession'..!");
		applicationIndexPage.noWildLifePossession();
		
		Common.logstep((logStep++)+" Upload file.");
		applicationIndexPage.UploadFiles();
		
		Common.logOptionalAssert("Verify that Uploaded file persisted in 'Uploaded Documents Table.'");
		
		if(applicationVerificatioPage.verifyUploadedDocument()) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "incorrectDocumentTable");
			numOfFailure++;
		}
		
		Common.logstep("Step " + (logStep++)+" Click on submit.");
		applicationIndexPage.clickonSubmit();
		
		Common.logstep("Step " + (logStep++)+" Navigate to My Application page.");
		applicationIndexPage.clickonApplication();
	
		Common.logMandetoryAssert("Verify that created application is displayed on Apllication list of 'My Application' page.");
		
		if (applicationVerificatioPage.VerifyCreatedApplicationStatus(dataFileName,"Dingo Licence")) {
			Common.writeProperties(configFileName,"Application_Created_Sucessfully","True","");
			Common.optionalAssertPassed();
			Common.logStatus("Pass");
		
		} else {
			Common.writeProperties(configFileName,"Application_Created_Sucessfully","False"," ");
			numOfFailure++;
		}

		if (numOfFailure > 0) {
			Common.logStatus("Fail");			
			Assert.assertTrue(false);
		}
	}
	
	@Test // pass
	public void verifyDingoAppPaymentNoticeAndAwaitingTask() throws IOException {

		int numOfFailure = 0;
		int logStep = 1;

		String dataFileName="dingoApplicationData";
		String configFileName="dingoApplicationConfig";
	
		Common.logcase("Testcase Id: TC_Portal_014");
		Common.logcase("Testcase Name: The 'Payment Notice' and 'Pay Fee' links should be visible to Online Portal User. The online Portal user should be able to download The 'Payment Notice'.");

		try {
			String interview_inspection = Common.readProperties(configFileName,"Delegate_Approval_Task_Complete");
			if (interview_inspection.contains("False")) {
				throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
			}
		} catch (Exception e) {
			throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
		}
		appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));

		Common.logstep("Step " + (logStep++) + " : Open url:<a>" + testUrl + "</a>");
		
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
		
		Common.logstep("Step " + (logStep++)+" Click on Application Menu.");
		applicationIndexPage.clickonApplication();
		
		Common.logMandetoryAssert("Verify that 'Payment Notice' and 'Pay Fee' Links are available for Payment Pending Application.");
		
		if(applicationVerificatioPage.verifyPaymentPendingApplication()) {
			Common.optionalAssertPassed();
		}else {
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "missingPaymentNotice.png");
			numOfFailure++;
		}
		
		Common.logstep((logStep++)+" Click on Payment Notice link to download Payment Notice PDF.");
		applicationIndexPage.clickonPaymentNotice(dataFileName);
		Common.pause(5);
		
		try
	     {
	        //final URL scalaByExampleUrl = new URL(address);
			File paymentNotice=new File("Resource/Downloads/OWLS Payment Notice (Application)Template.pdf");
	        final PDDocument documentToBeParsed = PDDocument.load(paymentNotice);
	        final PDFTextStripper stripper = new PDFTextStripper();
	     
	        final String pdfText = stripper.getText(documentToBeParsed);
	        System.out.println("Parsed text size is " + pdfText.length() + " characters:");
	        System.out.println(pdfText);
	        
	        Common.logMandetoryAssert("Verify that User's Postal address is available in Payment Notice PDF.");
			if(applicationVerificatioPage.verifyAddressFromPDF(dataFileName,pdfText)) {
				Common.optionalAssertPassed();
				Common.logStatus("Pass");
				paymentNotice.delete();
			}else {
				Common.optionalAssertFailed();
				numOfFailure++;
				paymentNotice.delete();
			}
	     }
	     catch (IOException ioEx)
	     {
	      System.err.println("Exception while trying to parse text from PDF at "+ ioEx.getMessage());
	     }
		
		if(numOfFailure >0)
		{
			
			Common.logStatus("Fail");
			Assert.assertTrue(false);
		}
	}
	*/
}
	
