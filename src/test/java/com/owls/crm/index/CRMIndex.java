package com.owls.crm.index;




import javax.activation.CommandMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.beust.testng.TestNG;
import com.owls.init.Common;
import com.owls.init.ITestStatus;
import com.owls.init.SeleniumInit;
import com.owls.init.TestData;

public class CRMIndex extends SeleniumInit {

	/**
	 * TestNG's test method contains (annotated with @Test)
	 * 1. Test steps log.
	 * 2. TestNG log [Common.logstep()] which will print in testNG report.
	 * 3. Calling method to indexpage class  and verification class.
	 */
	@Test(alwaysRun=true)
	public void validateReviewAppliactionTask() {

		int numOfFailure = 0;
		int logStep = 1;
	
	/*	String dataFileName="basicApplicationData";
		String configFileName="basicApplicationConfig";
	
		Common.logcase("Testcase Id: TC_CRM_001");
		Common.logcase("Testcase Name: To verify OWLS internal user reviews the \"Review Application\" task");
	*/	
		try {
			String Application_Created = Common.readProperties(configFileName,"Application_Created_Sucessfully");
			
			if(Application_Created.contains("False"))
			{
				throw new SkipException("Skipping Test case as Application is not created in portal.");
			}
		}
		catch(Exception e) {
			throw new SkipException("Skipping Test case as Application is not created in portal.");
		}
		

		Common.logstep("Step " + (logStep++) + " : Open portal url:<a>" + testUrl + "</a>");
		appdata.setApplicationSubject(Common.readDataProperties(configFileName,"ApplicationName"));
		
		Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
		Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
		
		Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
		crmpage.chooseLoginMethod();
		Common.pause(5);
		Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
		crmpage.enterCRMUsername(TestData.getCRMUsername());
		Common.pause(1);
		crmpage.enterCRMPassword(TestData.getCRMPassword());
		Common.pause(1);
		crmpage.clickonCRMLoginbutton();
		
		Common.logstep("Step " + (logStep++) + " Advance search filter feeding with Application Name.");
		crmpage.advanceSearchForApplicationTask();
		
		Common.logMandetoryAssert("Verify that Task status should be 'Review Application' ");
		if (crmverify.VerifycurrentTaskAndQueue("Review Application",appdata.getApplicationSubject(),"Wildlife Licensing Queue")) {
			Common.optionalAssertPassed();
			Common.writeProperties(configFileName,"Task_status_Review_Application", "True", "");
			Common.logStatus("Pass");
		} else {
			numOfFailure++;
			Common.optionalAssertFailed();
			Common.writeProperties(configFileName,"Task_status_Review_Application", "False", "");
		}
		
		if (numOfFailure > 0) {
			Common.logstep("Fail");
			Assert.assertTrue(false);
		}
	}
	
	
	@Test(alwaysRun=true)
	public void completeReviewApplicationTask() {

		int numOfFailure = 0;
		int logStep = 1;
		
	/*	String dataFileName="basicApplicationData";
		String configFileName="basicApplicationConfig";
	
		Common.logcase("Testcase Id: TC_CRM_002");
		Common.logcase("Testcase Name: To verify OWLS Internal user to progress the \"Review Application\" task to \"Interview/Site Inspection\" task.");
*/
	
		try
		{
			String Application_Created = Common.readProperties(configFileName,"Task_status_Review_Application");
			if(Application_Created.contains("False"))
			{
				throw new SkipException("Skipping Test case as Application Task is not Review Application Task.");
			}
		}
		catch(Exception e)
		{
			throw new SkipException("Skipping Test case as Application Task is not Review Application Task.");
		}
		
	
		appdata.setApplicationSubject(Common.readDataProperties(configFileName,"ApplicationName"));
		
		Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
		Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
		
		Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
		crmpage.chooseLoginMethod();
		Common.pause(5);
		Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
		crmpage.enterCRMUsername(TestData.getCRMUsername());
		Common.pause(1);
		crmpage.enterCRMPassword(TestData.getCRMPassword());
		Common.pause(1);
		crmpage.clickonCRMLoginbutton();
		
		Common.logstep("Step " + (logStep++) + " Feed the advance search options and filter current application :"+appdata.getApplicationSubject());
		crmpage.advanceSearchForApplicationTask();
		
		Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
		crmpage.clickOnActionlink(appdata.getApplicationSubject());
		
		Common.logstep("Step " + (logStep++) + " Click on Start link to Start Review Application Task.");
		crmverify=crmpage.clickOnStart();
		
		Common.logOptionalAssert("Verify that Validation Error for Review Application task persisted correctly.");
		if(crmverify.verifyValidationError())
		{
			Common.optionalAssertPassed();
		}
		else
		{
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "incorrectValidationError.png");
			numOfFailure++;
		}
				
		Common.logstep("Step " + (logStep++) + " Mousehover on Completion option and choose 'Completed' option to completed Review Application Errors.");
		crmpage.mouseHoverOnCompletion(appdata.getApplicationSubject());
		
		Common.logstep("Step " + (logStep++) + " Click on the Application link to redirect to Application Detail Page.");
		crmpage.clickOnApplication(appdata.getApplicationSubject());
	
		Common.logstep("Step " + (logStep++) + " Click on 'Current Task' sub-panel.");
		crmpage.clickOnCurrentTask();
		
		Common.logMandetoryAssert(" Verify that 'Interview / Site Inspection' task is available in 'Current Task' sub-panel.");
		if (crmverify.verifyCurrentTask("Interview / Site Inspection")) {
			
			Common.optionalAssertPassed();
			Common.writeProperties(configFileName,"Review_Application_Task_Complete", "True", "");
			Common.logStatus("Pass");
		} else {
			Common.optionalAssertFailed();
			Common.writeProperties(configFileName,"Review_Application_Task_Complete", "False", "");
			numOfFailure++;
		}
		
		if (numOfFailure > 0) {
			Common.logStatus("Fail");
			Assert.assertTrue(false);
		}	
	}
	
	@Test(alwaysRun=true)
	 public void completeInterviewInspectionTask() {

		  int numOfFailure = 0;
		  int logStep = 1;
		  
		/*	String dataFileName="basicApplicationData";
			String configFileName="basicApplicationConfig";
		
		  
		  Common.logcase("Testcase Id: TC_CRM_003");
		  Common.logcase("Testcase Name: To verify Interview/Site inspection task to be available under Wildlife licencing queue and inpsecting screen to be available for the role -'Manager wildlife licencing'.");
*/
			try {
				String Application_Created = Common.readProperties(configFileName,"Review_Application_Task_Complete");

				if (Application_Created.contains("False")) {
					throw new SkipException("Skipping Test case as Review Application Task is not completed successfully.");
				}
			} catch (Exception e) {
				throw new SkipException("Skipping Test case as Review Application Task is not completed successfully.");
			}
			
			appdata.setApplicationSubject(Common.readDataProperties(configFileName,"ApplicationName"));

		  Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
		  Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
		  
		  Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
		  crmpage.chooseLoginMethod();
		  Common.pause(2);
		  Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
		  crmpage.enterCRMUsername(TestData.getCRMInspecterUsername());
		  Common.pause(1);
		  crmpage.enterCRMPassword(TestData.getCRMInspectorPassword());
		  Common.pause(1);
		  crmpage.clickonCRMLoginbutton();
		  
		  Common.logstep("Step " + (logStep++) + " Feed the advance search options and filter current application :"+appdata.getApplicationSubject());
		  crmpage.advanceSearchForApplicationTask();
		  
			Common.logMandetoryAssert("Verify that Task status should be 'Interview/Site Inspection' ");
			if (crmverify.VerifycurrentTaskAndQueue("Interview / Site Inspection",appdata.getApplicationSubject(),queueName)) {
				Common.optionalAssertPassed();
			} else {
				Common.optionalAssertFailed();
				throw new SkipException("'Interview/Site Inspection' is not available to the Inspection User.");
			}
		  
			Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
			crmpage.clickOnActionlink(appdata.getApplicationSubject());
			
			Common.logstep("Step " + (logStep++) + " Click on Start link to Start 'Interview/Site Inspection' task.");
			crmverify=crmpage.clickOnStart();
		  
		  Common.pause(2);
		  Common.SwitchtoTab(driver,2);
		  
		  Common.logMandetoryAssert("Verify that the user is redirected to Application Details Page and 'Interview/Site Inspection' panel is expanded to the user.");
			if(crmverify.verifyInspectingOfficerAssessmentTabExpanded())
			{
				Common.optionalAssertPassed();
			}
			else
			{
				Common.optionalAssertFailed();
				Common.makeScreenshot(driver, "incorrectInspectionTask.png");
				numOfFailure++;
				
			}

			if (numOfFailure > 0) {
				Common.logStatus("Fail");
				Assert.assertTrue(false);
			}
		  
		  
		  Common.logstep("Step " + (logStep++) + " Complete 'INSPECTING OFFICER ASSESSMENT' form as below and click on Save button.");
		  crmpage.enteronDate(dataFileName);
		  crmpage.clickonsiteRadiobutton();
		  crmpage.enterPresentcomment(dataFileName);
		  crmpage.enterInspectionComment(dataFileName);
		  crmpage.enterConcernsComment(dataFileName);
		  crmpage.dropdown_selectDelegate();
		  crmpage.clickonApprovedRadiobutton();
		  crmpage.clickonSavebutton();
		  crmpage.clickonSubmiteToDelegate();
		  
		  Common.pause(5);	
		  driver.navigate().refresh();
			Common.logstep("Step " + (logStep++) + " Click on 'Current Task' sub-panel.");
			crmpage.clickOnCurrentTask();
		  
		  Common.logMandetoryAssert(" Verify that 'Approval By Delegate' task is available in 'Current Task' sub-panel.");
			if (crmverify.verifyCurrentTask("Approval By Delegate")) {
				Common.optionalAssertPassed();
				Common.writeProperties(configFileName,"Interview_Inspection_Task_Complete", "True", "");
				Common.logStatus("Pass");
			
			} else {
				Common.optionalAssertPassed();
				Common.writeProperties(configFileName,"Interview_Inspection_Task_Complete", "False", "");
				numOfFailure++;
			}
			
			if (numOfFailure > 0) {
				Common.logStatus("Fail");
				Assert.assertTrue(false);
			}
		 
	 }
	 
	 @Test(alwaysRun=true)
	 public void verifyAndCompleteApproveDelegateTask() {

		  int numOfFailure = 0;
		  int logStep = 1;
		  
	/*	  String dataFileName="basicApplicationData";
			String configFileName="basicApplicationConfig";
		  
		  Common.logcase("Testcase Id: TC_CRM_004");
		  Common.logcase("Testcase Name: To verify Delegate Office take the 'Approval By Delegate' task to 'Await payment confirmation' task.");

	*/	  
			try {
				String interview_inspection = Common.readProperties(configFileName,"Interview_Inspection_Task_Complete");
				if (interview_inspection.contains("False")) {
					throw new SkipException("Skipping Test case as Interview / Site Inspection task is not completed successfully.");
				}
			} catch (Exception e) {
				throw new SkipException("Skipping Test case as Interview / Site Inspection task is not completed successfully.");
			}
		  
		  appdata.setApplicationSubject(Common.readDataProperties(configFileName,"ApplicationName"));

		  Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
		  Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
		  
		  Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
		  crmpage.chooseLoginMethod();
		  Common.pause(5);
		  Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
		  crmpage.enterCRMUsername(TestData.getCRMDelegateUsername());
		  Common.pause(1);
		  crmpage.enterCRMPassword(TestData.getCRMDelegatePassword());
		  Common.pause(1);
		  crmpage.clickonCRMLoginbutton();
		  
		  Common.logstep("Step " + (logStep++) + "  Advance search filter feeding with Application Name("+appdata.getApplicationSubject()+") and short Task in desanding order according to date created.");
		  crmpage.advanceSearchForApplicationTask();
		  
		  Common.logMandetoryAssert("Verify that Task status of application '"+appdata.getApplicationSubject()+"' should be 'Approval By Delegate' ");
		  if (crmverify.VerifycurrentTaskAndQueue("Approval By Delegate",appdata.getApplicationSubject(),"Wildlife Licensing Queue")) {
				Common.optionalAssertPassed();
		   Common.writeProperties(configFileName,"Task_status_Approval_By_Delegate", "True", "");
		  } else {
			Common.optionalAssertFailed();
		   Common.writeProperties(configFileName,"Task_status_Approval_By_Delegate", "False", "");
		   numOfFailure++;
		  }
		  
			Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
			crmpage.clickOnActionlink(appdata.getApplicationSubject());
			
			Common.logstep("Step " + (logStep++) + " Click on Start link to Start 'Approval By Delegate' task.");
			crmverify=crmpage.clickOnStart();
		  
		  Common.pause(2);
		  Common.SwitchtoTab(driver,2);
		  
		  Common.logMandetoryAssert("Verify that the user is redirected to Application Details Page and 'Delegate Decision' panel is expanded to the user.");
			if(crmverify.verifyInspectingOfficerAssessmentTabExpanded())
			{
				Common.optionalAssertPassed();
			}
			else
			{
				Common.optionalAssertFailed();
				Common.makeScreenshot(driver, "incorrectDelegateScreen.png");
				numOfFailure++;
			}
			
			Common.logMandetoryAssert("Verify that Comments provided by Inspecting officer is correctly displayed to the Delegate Officer.");
			if(crmverify.verifyInspectionComment(dataFileName))
			{
				Common.optionalAssertPassed();
			}
			else
			{
				Common.optionalAssertFailed();
				Common.makeScreenshot(driver, "incorrectInspectionComment.png");
				numOfFailure++;
			}

			Common.logstep("Step " + (logStep++) + " Choose 'Approve' from Delegate 'Delegate Determination'.");
			crmpage.approveFromDelegate();
			
			Common.logstep("Step " + (logStep++) + " Click on 'Verify' button to validate Delegate Signed .");
			crmpage.clickOnVerifyButton();
			
			Common.logMandetoryAssert("Verify that Delegate Signature validated and 'Record Decision' button enable to Click.");
			if(crmverify.verifyDelegateSign())
			{
				Common.optionalAssertPassed();
			}
			else
			{
				Common.optionalAssertFailed();
				Common.makeScreenshot(driver, "incorrectInspectionComment.png");
				numOfFailure++;
			}
			
			Common.logstep("Step " + (logStep++) + " Click on 'Deleate Decision' button to complete Delegate Task .");
			crmpage.clickDelegateDecision();
			
			Common.pause(20);
			driver.navigate().refresh();
			
			Common.logstep("Step " + (logStep++) + " Click on 'Current Task' sub-panel.");
			crmpage.clickOnCurrentTask();
			
			Common.logMandetoryAssert("Verify that Approval By Delagate Task Completed successfully and 'Await payment confirmation' task is available in Current Task Panel.");
			if(crmverify.verifyCurrentTask("Await payment confirmation"))
			{
				Common.optionalAssertPassed();
				Common.writeProperties(configFileName,"Delegate_Approval_Task_Complete", "True", "");
				Common.logStatus("Pass");
			} else {
				Common.optionalAssertFailed();
				Common.writeProperties(configFileName,"Delegate_Approval_Task_Complete", "False", "");
				numOfFailure++;
			}
			
			if (numOfFailure > 0) {
				Common.logStatus("Fail");
				Assert.assertTrue(false);
			}
	}
	 
	 
	 @Test(alwaysRun=true)
	 public void completeAwaitingPaymentConfirmationTask() {

		  int numOfFailure = 0;
		  int logStep = 1;
		  
			
		/*	String dataFileName="basicApplicationData";
			String configFileName="basicApplicationConfig";
		  
		  Common.logcase("Testcase Id: TC_CRM_005");
		  Common.logcase("Testcase Name: To verify OWLS internal user to complete 'Awaiting Payment Confirmation' task and to verify the Permit document is available for the online portal user to download.");
*/
		  
		  System.err.println(" File name : "+configFileName);
			
		  try {
				String interview_inspection = Common.readProperties(configFileName,"Payment_Notice_Verified");
				if (interview_inspection.contains("False")) {
					throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
				}
			} catch (Exception e) {
				throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
			}
		appdata.setApplicationSubject(Common.readDataProperties(configFileName,"ApplicationName"));

		  Common.logstep("Step " + (logStep++) + " : Open portal url:<a>" + testUrl + "</a>");
		  
			Common.logstep("Step " + (logStep++)+" Enter Username.");
			applicationIndexPage.enterAdminUsername();
			
			Common.logstep("Step " + (logStep++)+" Enter Password.");
			applicationIndexPage.enterAdminPassword();
			
			Common.logstep("Step " + (logStep++)+" Click on Login icon.");
			applicationIndexPage.clickonlogin();
			
			Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
			applicationIndexPage.clickPersonalMenu();
		
			Common.logstep("Step " + (logStep++)+" Click on Permit Menu.");
			applicationIndexPage.clickonPermit();
		
			Common.logstep("Step " + (logStep++)+" Get current number of Permit records.");
			crmpage.currentPermitRecords(configFileName);
			
			Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
		  Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
		  
		  Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
		  crmpage.chooseLoginMethod();
		  Common.pause(5);
		  Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
		  crmpage.enterCRMUsername(TestData.getCRMUsername());
		  Common.pause(1);
		  crmpage.enterCRMPassword(TestData.getCRMPassword());
		  Common.pause(1);
		  crmpage.clickonCRMLoginbutton();
		  
		  Common.logstep("Step " + (logStep++) + "Advance search filter feeding with Application Name("+appdata.getApplicationSubject()+") and short Task in desanding order according to date created.");
		  crmpage.advanceSearchForApplicationTask();
		  
		  Common.logMandetoryAssert("Verify that Task status of application '"+appdata.getApplicationSubject()+"' should be 'Await payment confirmation' ");
		  if (crmverify.VerifycurrentTaskAndQueue("Await payment confirmation",appdata.getApplicationSubject(),"Wildlife Licensing Queue")) {
				Common.optionalAssertPassed();
		   Common.writeProperties(configFileName,"Task_status_Await_Payment_Confirmation", "True", "");
		  } else {
				Common.optionalAssertFailed();
		   Common.writeProperties(configFileName,"Task_status_Await_Payment_Confirmation", "False", "");
		   numOfFailure++;
			throw new SkipException("Skipping Test case as 'Await Payment Confirmation' task.");
		  }
		  
			Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
			crmpage.clickOnActionlink(appdata.getApplicationSubject());
			
			Common.logstep("Step " + (logStep++) + " Click on Start link to Start 'Approval By Delegate' task.");
			crmverify=crmpage.clickOnStart();
		  
		  Common.pause(2);
		  Common.SwitchtoTab(driver,2);
		  driver.navigate().refresh();
		  
		  Common.logstep("Step " + (logStep++) + " Click on Business Process icon available on the top right corner.");
		  crmverify=crmpage.clickOnBusinessProcess();
		  
		  Common.logstep("Step " + (logStep++) + " Choose 'Approved' Option to complete 'Await payment confirmation' task.");
		  crmpage.chooseApproveOption();

		  Common.pause(10);
		  Common.SwitchtoTab(driver,0);
		  driver.navigate().refresh();
		  
		  Common.logMandetoryAssert("Verify that the number of permit records are increased to 1.");
			if(!crmverify.verifyNumberOfPermitRecordss(configFileName))
			{
				Common.optionalAssertPassed();
				Common.writeProperties(configFileName,"Await_Payment_Confirmation_Complete", "True", "");
				Common.logStatus("Pass");

			} else {
				Common.optionalAssertFailed();
				Common.writeProperties(configFileName,"Await_Payment_Confirmation_Complete", "False", "");
				numOfFailure++;
			}

			if (numOfFailure > 0) {
				Common.logStatus("Fail");
				Assert.assertTrue(false);
			}
	}
	 @Test(priority=1)
	 public void runCleanUp()
	 {	
		 
		 Assert.assertTrue(true);
		Common.logstep(" Open CRM Application ");
		 driver.get(TestData.getCRMURL());
		 
		  Common.logstep("Choose 'Standard Authentication' login method from Drop down.");
		  crmpage.chooseLoginMethod();
		  Common.pause(5);
		  Common.logstep(" Login to CRM as admin");
		  crmpage.enterCRMUsername(TestData.getCRMUsername());
		  Common.pause(1);
		  crmpage.enterCRMPassword(TestData.getCRMPassword());
		  Common.pause(1);
		  crmpage.clickonCRMLoginbutton();
		
		 Common.logstep(" Click on Owls Administrator ");
		 crmpage.clickOnAdministrator();
		 
		 Common.logstep( " Select Admin option .");
		 crmpage.clickOnAdmin();
		 
		 Common.logstep(" Click on Test Data CleanUP Option");
		 crmpage.clickOnTestDataCleanUP();
		 
		 Common.logstep(" Select current user ");
		 crmpage.selectUser();
		 
		 Common.logstep(" Click on Run Cleanup ");
		 crmpage.clickRunCleanUp();
		 
	 }
	 
	 
		@Test(alwaysRun=true)
		 public void completeInterviewInspectionWithConditionTask() {

			  int numOfFailure = 0;
			  int logStep = 1;
			  
			/*	String dataFileName="basicApplicationData";
				String configFileName="basicApplicationConfig";
			
			  
			  Common.logcase("Testcase Id: TC_CRM_003");
			  Common.logcase("Testcase Name: To verify Interview/Site inspection task to be available under Wildlife licencing queue and inpsecting screen to be available for the role -'Manager wildlife licencing'.");
	*/
				try {
					String Application_Created = Common.readProperties(configFileName,"Review_Application_Task_Complete");

					if (Application_Created.contains("False")) {
						throw new SkipException("Skipping Test case as Review Application Task is not completed successfully.");
					}
				} catch (Exception e) {
					throw new SkipException("Skipping Test case as Review Application Task is not completed successfully.");
				}
				
				appdata.setApplicationSubject(Common.readDataProperties(configFileName,"ApplicationName"));

			  Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
			  Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
			  
			  Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
			  crmpage.chooseLoginMethod();
			  Common.pause(2);
			  Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
			  crmpage.enterCRMUsername(TestData.getCRMInspecterUsername());
			  Common.pause(1);
			  crmpage.enterCRMPassword(TestData.getCRMInspectorPassword());
			  Common.pause(1);
			  crmpage.clickonCRMLoginbutton();
			  
			  Common.logstep("Step " + (logStep++) + " Feed the advance search options and filter current application :"+appdata.getApplicationSubject());
			  crmpage.advanceSearchForApplicationTask();
			  
				Common.logMandetoryAssert("Verify that Task status should be 'Interview/Site Inspection' ");
				if (crmverify.VerifycurrentTaskAndQueue("Interview / Site Inspection",appdata.getApplicationSubject(),queueName)) {
					Common.optionalAssertPassed();
				} else {
					Common.optionalAssertFailed();
					throw new SkipException("'Interview/Site Inspection' is not available to the Inspection User.");
				}
			  
				Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
				crmpage.clickOnActionlink(appdata.getApplicationSubject());
				
				Common.logstep("Step " + (logStep++) + " Click on Start link to Start 'Interview/Site Inspection' task.");
				crmverify=crmpage.clickOnStart();
			  
			  Common.pause(2);
			  Common.SwitchtoTab(driver,2);
			  
			  Common.logMandetoryAssert("Verify that the user is redirected to Application Details Page and 'Interview/Site Inspection' panel is expanded to the user.");
				if(crmverify.verifyInspectingOfficerAssessmentTabExpanded())
				{
					Common.optionalAssertPassed();
				}
				else
				{
					Common.optionalAssertFailed();
					Common.makeScreenshot(driver, "incorrectInspectionTask.png");
					numOfFailure++;
					
				}

				if (numOfFailure > 0) {
					Common.logStatus("Fail");
					Assert.assertTrue(false);
				}
			  
			  
			  Common.logstep("Step " + (logStep++) + " Complete 'INSPECTING OFFICER ASSESSMENT' form as below and click on Save button.");
			  crmpage.enteronDate(dataFileName);
			  crmpage.clickonsiteRadiobutton();
			  crmpage.enterPresentcomment(dataFileName);
			  crmpage.enterInspectionComment(dataFileName);
			  crmpage.enterConcernsComment(dataFileName);
			  crmpage.dropdown_selectDelegate();
			  crmpage.clickonApprovedRadiobutton();
			  crmpage.clickonSavebutton();
			  
			  Common.logstep("Step " + (logStep++) + " Click 'Go to Condition' link to add any additional conditions.");
			  crmpage.clickGotoCondition();
			
			  Common.logstep("Step " + (logStep++) + " Select Free-text option to add any Free Text Condition.");
			  crmpage.chooseFreeText();
			
			  
			  crmpage.clickonSubmiteToDelegate();
			  
			  Common.pause(5);	
			  driver.navigate().refresh();
				Common.logstep("Step " + (logStep++) + " Click on 'Current Task' sub-panel.");
				crmpage.clickOnCurrentTask();
			  
			  Common.logMandetoryAssert(" Verify that 'Approval By Delegate' task is available in 'Current Task' sub-panel.");
				if (crmverify.verifyCurrentTask("Approval By Delegate")) {
					Common.optionalAssertPassed();
					Common.writeProperties(configFileName,"Interview_Inspection_Task_Complete", "True", "");
					Common.logStatus("Pass");
				
				} else {
					Common.optionalAssertPassed();
					Common.writeProperties(configFileName,"Interview_Inspection_Task_Complete", "False", "");
					numOfFailure++;
				}
				
				if (numOfFailure > 0) {
					Common.logStatus("Fail");
					Assert.assertTrue(false);
				}
			 
		 }
	 
	 
	 /*
	 @Test(alwaysRun=true)
		public void validateSpecimenReviewAppliactionTask() {

			int numOfFailure = 0;
			int logStep = 1;
		
			String dataFileName="specimenApplicationData";
			String configFileName="specimenApplicationConfig";
		
			Common.logcase("Testcase Id: TC_CRM_006");
			Common.logcase("Testcase Name: To verify OWLS internal user reviews the \"Review Application\" task");
			
			try {
				String Application_Created = Common.readProperties(configFileName,"Application_Created_Sucessfully");
				
				if(Application_Created.contains("False"))
				{
					throw new SkipException("Skipping Test case as Application is not created in portal.");
				}
			}
			catch(Exception e) {
				throw new SkipException("Skipping Test case as Application is not created in portal.");
			}

			appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));
			
			Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
			Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
			
			Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
			crmpage.chooseLoginMethod();
			Common.pause(5);
			Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
			crmpage.enterCRMUsername(TestData.getCRMUsername());
			Common.pause(1);
			crmpage.enterCRMPassword(TestData.getCRMPassword());
			Common.pause(1);
			crmpage.clickonCRMLoginbutton();
			
			Common.logstep("Step " + (logStep++) + " Advance search filter feeding with Application Name.");
			crmpage.advanceSearchForApplicationTask();
			
			Common.logMandetoryAssert("Verify that Task status should be 'Review Application' ");
			if (crmverify.VerifycurrentTaskAndQueue("Review Application",appdata.getApplicationSubject(),"Wildlife Licensing Queue")) {
				Common.optionalAssertPassed();
				Common.writeProperties(configFileName,"Task_status_Review_Application", "True", "");
				Common.logStatus("Pass");
			} else {
				numOfFailure++;
				Common.optionalAssertFailed();
				Common.writeProperties(configFileName,"Task_status_Review_Application", "False", "");
			}
			
			if (numOfFailure > 0) {
				Common.logStatus("Fail");
				Assert.assertTrue(false);
			}
		}
	 
	 @Test(alwaysRun=true)
		public void completeSpecimenReviewApplicationTask() {

			int numOfFailure = 0;
			int logStep = 1;
			
			String dataFileName="specimenApplicationData";
			String configFileName="specimenApplicationConfig";
		
			Common.logcase("Testcase Id: TC_CRM_007");
			Common.logcase("Testcase Name: To verify OWLS Internal user to progress the \"Review Application\" task to \"Interview/Site Inspection\" task.");

		
			try
			{
				String Application_Created = Common.readProperties(configFileName,"Task_status_Review_Application");
				if(Application_Created.contains("False"))
				{
					throw new SkipException("Skipping Test case as Application Task is not Review Application Task.");
				}
			}
			catch(Exception e)
			{
				throw new SkipException("Skipping Test case as Application Task is not Review Application Task.");
			}
		
			appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));
			
			Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
			Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
			
			Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
			crmpage.chooseLoginMethod();
			Common.pause(5);
			Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
			crmpage.enterCRMUsername(TestData.getCRMUsername());
			Common.pause(1);
			crmpage.enterCRMPassword(TestData.getCRMPassword());
			Common.pause(1);
			crmpage.clickonCRMLoginbutton();
			
			Common.logstep("Step " + (logStep++) + " Feed the advance search options and filter current application :"+appdata.getApplicationSubject());
			crmpage.advanceSearchForApplicationTask();
			
			Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
			crmpage.clickOnActionlink(appdata.getApplicationSubject());
			
			Common.logstep("Step " + (logStep++) + " Click on Start link to Start Review Application Task.");
			crmverify=crmpage.clickOnStart();
			
			Common.logOptionalAssert("Verify that Validation Error for Review Application task persisted correctly.");
			if(crmverify.verifyValidationError())
			{
				Common.optionalAssertPassed();
			}
			else
			{
				Common.optionalAssertFailed();
				Common.makeScreenshot(driver, "incorrectValidationError.png");
				numOfFailure++;
			}
					
			Common.logstep("Step " + (logStep++) + " Mousehover on Completion option and choose 'Completed' option to completed Review Application Errors.");
			crmpage.mouseHoverOnCompletion(appdata.getApplicationSubject());
			
			Common.logstep("Step " + (logStep++) + " Click on the Application link to redirect to Application Detail Page.");
			crmpage.clickOnApplication(appdata.getApplicationSubject());
		
			Common.logstep("Step " + (logStep++) + " Click on 'Current Task' sub-panel.");
			crmpage.clickOnCurrentTask();
			
			Common.logMandetoryAssert(" Verify that 'Interview / Site Inspection' task is available in 'Current Task' sub-panel.");
			if (crmverify.verifyCurrentTask("Interview / Site Inspection")) {
				
				Common.optionalAssertPassed();
				Common.writeProperties(configFileName,"Review_Application_Task_Complete", "True", "");
				Common.logStatus("Pass");
			} else {
				Common.optionalAssertFailed();
				Common.writeProperties(configFileName,"Review_Application_Task_Complete", "False", "");
				numOfFailure++;
			}
			
			if (numOfFailure > 0) {
				Common.logStatus("Fail");
				Assert.assertTrue(false);
			}	
	}
 
	 @Test(alwaysRun=true)
	 public void completeSpecimenInterviewInspectionTask() {

	  int numOfFailure = 0;
	  int logStep = 1;
	  
		String dataFileName="specimenApplicationData";
		String configFileName="specimenApplicationConfig";
	
	  
		  Common.logcase("Testcase Id: TC_CRM_008");
		  Common.logcase("Testcase Name: To verify Interview/Site inspection task to be available under Wildlife licencing queue and inpsecting screen to be available for the role -'Manager wildlife licencing'.");

			try {
				String Application_Created = Common.readProperties(configFileName,"Review_Application_Task_Complete");

				if (Application_Created.contains("False")) {
					throw new SkipException("Skipping Test case as Review Application Task is not completed successfully.");
				}
			} catch (Exception e) {
				throw new SkipException("Skipping Test case as Review Application Task is not completed successfully.");
			}
	   
			appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));

	  Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
	  Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
	  
	  Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
	  crmpage.chooseLoginMethod();
	  Common.pause(2);
	  Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
	  crmpage.enterCRMUsername(TestData.getCRMInspecterUsername());
	  Common.pause(1);
	  crmpage.enterCRMPassword(TestData.getCRMInspectorPassword());
	  Common.pause(1);
	  crmpage.clickonCRMLoginbutton();
	  
	  Common.logstep("Step " + (logStep++) + " Feed the advance search options and filter current application :"+appdata.getApplicationSubject());
	  crmpage.advanceSearchForApplicationTask();
	  
		Common.logMandetoryAssert("Verify that Task status should be 'Interview/Site Inspection' ");
		if (crmverify.VerifycurrentTaskAndQueue("Interview / Site Inspection",appdata.getApplicationSubject(),"Wildlife Licensing Queue")) {
			Common.optionalAssertPassed();
		} else {
			Common.optionalAssertFailed();
			throw new SkipException("'Interview/Site Inspection' is not available to the Inspection User.");
		}
	  
		Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
		crmpage.clickOnActionlink(appdata.getApplicationSubject());
		
		Common.logstep("Step " + (logStep++) + " Click on Start link to Start 'Interview/Site Inspection' task.");
		crmverify=crmpage.clickOnStart();
	  
	  Common.pause(2);
	  Common.SwitchtoTab(driver,2);
	  
	  Common.logMandetoryAssert("Verify that the user is redirected to Application Details Page and 'Interview/Site Inspection' panel is expanded to the user.");
		if(crmverify.verifyInspectingOfficerAssessmentTabExpanded())
		{
			Common.optionalAssertPassed();
		}
		else
		{
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "incorrectInspectionTask.png");
			numOfFailure++;
		}

		if (numOfFailure > 0) {
			Common.logStatus("Fail");
			Assert.assertTrue(false);
		}
	  
	  
	  Common.logstep("Step " + (logStep++) + " Complete 'INSPECTING OFFICER ASSESSMENT' form as below and click on Save button.");
	  crmpage.enteronDate(dataFileName);
	  crmpage.clickonsiteRadiobutton();
	  crmpage.enterPresentcomment(dataFileName);
	  crmpage.enterInspectionComment(dataFileName);
	  crmpage.enterConcernsComment(dataFileName);
	  crmpage.dropdown_selectDelegate();
	  crmpage.clickonApprovedRadiobutton();
	  crmpage.clickonSavebutton();
	  crmpage.clickonSubmiteToDelegate();
	  
	  Common.pause(5);	
	  driver.navigate().refresh();
		Common.logstep("Step " + (logStep++) + " Click on 'Current Task' sub-panel.");
		crmpage.clickOnCurrentTask();
	  
	  Common.logMandetoryAssert(" Verify that 'Approval By Delegate' task is available in 'Current Task' sub-panel.");
		if (crmverify.verifyCurrentTask("Approval By Delegate")) {
			Common.optionalAssertPassed();
			Common.writeProperties(configFileName,"Interview_Inspection_Task_Complete", "True", "");
			Common.logStatus("Pass");
		
		} else {
			Common.optionalAssertPassed();
			Common.writeProperties(configFileName,"Interview_Inspection_Task_Complete", "False", "");
			numOfFailure++;
		}
		
		if (numOfFailure > 0) {
			Common.logStatus("Fail");
			Assert.assertTrue(false);
		}
	 
	 }
	 
	 
	 @Test(alwaysRun=true)
	 public void verifyAndCompleteSpecimenApproveDelegateTask() {

	  int numOfFailure = 0;
	  int logStep = 1;
	  
	  String dataFileName="specimenApplicationData";
		String configFileName="specimenApplicationConfig";
	  
		  Common.logcase("Testcase Id: TC_CRM_009");
		  Common.logcase("Testcase Name: To verify Delegate Officer take the 'Approval By Delegate' task to 'Await payment confirmation' task.");

		  
			try {
				String interview_inspection = Common.readProperties(configFileName,"Interview_Inspection_Task_Complete");
				if (interview_inspection.contains("False")) {
					throw new SkipException("Skipping Test case as Interview / Site Inspection task is not completed successfully.");
				}
			} catch (Exception e) {
				throw new SkipException("Skipping Test case as Interview / Site Inspection task is not completed successfully.");
			}
		  

	  appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));

	  Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
	  Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
	  
	  Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
	  crmpage.chooseLoginMethod();
	  Common.pause(5);
	  Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
	  crmpage.enterCRMUsername(TestData.getCRMDelegateUsername());
	  Common.pause(1);
	  crmpage.enterCRMPassword(TestData.getCRMDelegatePassword());
	  Common.pause(1);
	  crmpage.clickonCRMLoginbutton();
	  
	  Common.logstep("Step " + (logStep++) + "  Advance search filter feeding with Application Name("+appdata.getApplicationSubject()+") and short Task in desanding order according to date created.");
	  crmpage.advanceSearchForApplicationTask();
	  
	  Common.logMandetoryAssert("Verify that Task status of application '"+appdata.getApplicationSubject()+"' should be 'Approval By Delegate' ");
	  if (crmverify.VerifycurrentTaskAndQueue("Approval By Delegate",appdata.getApplicationSubject(),"Wildlife Licensing Queue")) {
			Common.optionalAssertPassed();
	   Common.writeProperties(configFileName,"Task_status_Approval_By_Delegate", "True", "");
	  } else {
		Common.optionalAssertFailed();
	   Common.writeProperties(configFileName,"Task_status_Approval_By_Delegate", "False", "");
	   numOfFailure++;
	  }
	  
		Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
		crmpage.clickOnActionlink(appdata.getApplicationSubject());
		
		Common.logstep("Step " + (logStep++) + " Click on Start link to Start 'Approval By Delegate' task.");
		crmverify=crmpage.clickOnStart();
	  
	  Common.pause(2);
	  Common.SwitchtoTab(driver,2);
	  
	  Common.logMandetoryAssert("Verify that the user is redirected to Application Details Page and 'Delegate Decision' panel is expanded to the user.");
		if(crmverify.verifyInspectingOfficerAssessmentTabExpanded())
		{
			Common.optionalAssertPassed();
		}
		else
		{
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "incorrectDelegateScreen.png");
			numOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Comments provided by Inspecting officer is correctly displayed to the Delegate Officer.");
		if(crmverify.verifyInspectionComment(dataFileName))
		{
			Common.optionalAssertPassed();
		}
		else
		{
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "incorrectInspectionComment.png");
			numOfFailure++;
		}

		Common.logstep("Step " + (logStep++) + " Choose 'Approve' from Delegate 'Delegate Determination'.");
		crmpage.approveFromDelegate();
		
		Common.logstep("Step " + (logStep++) + " Click on 'Verify' button to validate Delegate Signed .");
		crmpage.clickOnVerifyButton();
		
		Common.logMandetoryAssert("Verify that Delegate Signature validated and 'Record Decision' button enable to Click.");
		if(crmverify.verifyDelegateSign())
		{
			Common.optionalAssertPassed();
		}
		else
		{
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "incorrectInspectionComment.png");
			numOfFailure++;
		}
		
		Common.logstep("Step " + (logStep++) + " Click on 'Deleate Decision' button to complete Delegate Task .");
		crmpage.clickDelegateDecision();
		
		Common.pause(10);
		driver.navigate().refresh();
		
		Common.logstep("Step " + (logStep++) + " Click on 'Current Task' sub-panel.");
		crmpage.clickOnCurrentTask();
		
		Common.logMandetoryAssert("Verify that Approval By Delagate Task Completed successfully and 'Await payment confirmation' task is available in Current Task Panel.");
		if(crmverify.verifyCurrentTask("Await payment confirmation"))
		{
			Common.optionalAssertPassed();
			Common.writeProperties(configFileName,"Delegate_Approval_Task_Complete", "True", "");
			Common.logStatus("Pass");
		} else {
			Common.optionalAssertFailed();
			Common.writeProperties(configFileName,"Delegate_Approval_Task_Complete", "False", "");
			numOfFailure++;
		}
		
		if (numOfFailure > 0) {
			Common.logStatus("Fail");
			Assert.assertTrue(false);
		}
	}
	 
	 @Test(alwaysRun=true)
	 public void completeSpecimenPaymentNoticeAndAwaitingTask() {

	  int numOfFailure = 0;
	  int logStep = 1;
	  
		
		String dataFileName="specimenApplicationData";
		String configFileName="specimenApplicationConfig";
	  
		  Common.logcase("Testcase Id: TC_CRM_010");
		  Common.logcase("Testcase Name: To verify OWLS internal user to complete 'Awaiting Payment Confirmation' task and to verify the Permit document is available for the online portal user to download.");
		  
			try {
				String interview_inspection = Common.readProperties(configFileName,"Delegate_Approval_Task_Complete");

				if (interview_inspection.contains("False")) {
					throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
				}
			} catch (Exception e) {
				throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
			}
			appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));

	  Common.logstep("Step " + (logStep++) + " : Open portal url:<a>" + testUrl + "</a>");
	  
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
	
		Common.logstep("Step " + (logStep++)+" Click on Permit Menu.");
		applicationIndexPage.clickonPermit();
	
		Common.logstep("Step " + (logStep++)+" Get current number of Permit records.");
		crmpage.currentPermitRecords(dataFileName);
		
		Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
	  Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
	  
	  Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
	  crmpage.chooseLoginMethod();
	  Common.pause(5);
	  Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
	  crmpage.enterCRMUsername(TestData.getCRMUsername());
	  Common.pause(1);
	  crmpage.enterCRMPassword(TestData.getCRMPassword());
	  Common.pause(1);
	  crmpage.clickonCRMLoginbutton();
	  
	  Common.logstep("Step " + (logStep++) + "Advance search filter feeding with Application Name("+appdata.getApplicationSubject()+") and short Task in desanding order according to date created.");
	  crmpage.advanceSearchForApplicationTask();
	  
	  Common.logMandetoryAssert("Verify that Task status of application '"+appdata.getApplicationSubject()+"' should be 'Await payment confirmation' ");
	  if (crmverify.VerifycurrentTaskAndQueue("Await payment confirmation",appdata.getApplicationSubject(),"Wildlife Licensing Queue")) {
			Common.optionalAssertPassed();
	   Common.writeProperties(configFileName,"Task_status_Await_Payment_Confirmation", "True", "");
	  } else {
			Common.optionalAssertFailed();
	   Common.writeProperties(configFileName,"Task_status_Await_Payment_Confirmation", "False", "");
	   numOfFailure++;
		throw new SkipException("Skipping Test case as 'Await Payment Confirmation' task.");
	  }
	  
		Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
		crmpage.clickOnActionlink(appdata.getApplicationSubject());
		
		Common.logstep("Step " + (logStep++) + " Click on Start link to Start 'Approval By Delegate' task.");
		crmverify=crmpage.clickOnStart();
	  
	  Common.pause(2);
	  Common.SwitchtoTab(driver,2);
	  driver.navigate().refresh();
	  
	  Common.logstep("Step " + (logStep++) + " Click on Business Process icon available on the top right corner.");
	  crmverify=crmpage.clickOnBusinessProcess();
	  
	  Common.logstep("Step " + (logStep++) + " Choose 'Approved' Option to complete 'Await payment confirmation' task.");
	  crmpage.chooseApproveOption();

	  Common.pause(10);
	  Common.SwitchtoTab(driver,0);
	  driver.navigate().refresh();
	  
	  Common.logMandetoryAssert("Verify that the number of permit records are increased to 1.");
		if(!crmverify.verifyNumberOfPermitRecordss(dataFileName))
		{
			Common.logStatus("Pass");
			Common.optionalAssertPassed();
			Common.writeProperties(configFileName,"Await_Payment_Confirmation_Complete", "True", "");
		} else {
			Common.optionalAssertFailed();
			Common.writeProperties(configFileName,"Await_Payment_Confirmation_Complete", "False", "");
			numOfFailure++;
		}

		if (numOfFailure > 0) {
			Common.logStatus("Fail");
			Assert.assertTrue(false);
		}
	}
	 
	 @Test(alwaysRun=true)
	 public void completeBasicApplicationAwaitPaymentConfirmation() {

	  int numOfFailure = 0;
	  int logStep = 1;
	  
		
		String dataFileName="basicApplicationNoValidationData";
	  
		  Common.logcase("Testcase Id: TC_CRM_011");
		  Common.logcase("Testcase Name: To verify OWLS internal user to complete 'Awaiting Payment Confirmation' task and to verify the Permit document is available for the online portal user to download.");
		  
			try {
				String interview_inspection = Common.readProperties(dataFileName,"Payment_Notice_Verified");

				if (interview_inspection.contains("False")) {
					throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
				}
			} catch (Exception e) {
				throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
			}
			appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));

	  Common.logstep("Step " + (logStep++) + " : Open portal url:<a>" + testUrl + "</a>");
	  
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
	
		Common.logstep("Step " + (logStep++)+" Click on Permit Menu.");
		applicationIndexPage.clickonPermit();
	
		Common.logstep("Step " + (logStep++)+" Get current number of Permit records.");
		crmpage.currentPermitRecords(dataFileName);
		
		Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
	  Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
	  
	  Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
	  crmpage.chooseLoginMethod();
	  Common.pause(5);
	  Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
	  crmpage.enterCRMUsername(TestData.getCRMUsername());
	  Common.pause(1);
	  crmpage.enterCRMPassword(TestData.getCRMPassword());
	  Common.pause(1);
	  crmpage.clickonCRMLoginbutton();
	  
	  Common.logstep("Step " + (logStep++) + "Advance search filter feeding with Application Name("+appdata.getApplicationSubject()+") and short Task in desanding order according to date created.");
	  crmpage.advanceSearchForApplicationTask();
	  
	  Common.logMandetoryAssert("Verify that Task status of application '"+appdata.getApplicationSubject()+"' should be 'Await payment confirmation' ");
	  if (crmverify.VerifycurrentTaskAndQueue("Await payment confirmation",appdata.getApplicationSubject(),"Wildlife Licensing Queue")) {
			Common.optionalAssertPassed();
	   Common.writeProperties(dataFileName,"Await_Payment_Confirmation", "True", "");
	  } else {
			Common.optionalAssertFailed();
	   Common.writeProperties(dataFileName,"Await_Payment_Confirmation", "False", "");
	   numOfFailure++;
		throw new SkipException("Skipping Test case as 'Await Payment Confirmation' task is not available or it is not available in Wildlife Licensing Queue.");
	  }
	  
		Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
		crmpage.clickOnActionlink(appdata.getApplicationSubject());
		
		Common.logstep("Step " + (logStep++) + " Click on Start link to Start 'Approval By Delegate' task.");
		crmverify=crmpage.clickOnStart();
	  
	  Common.pause(2);
	  Common.SwitchtoTab(driver,2);
	  driver.navigate().refresh();
	  
	  Common.logstep("Step " + (logStep++) + " Click on Business Process icon available on the top right corner.");
	  crmverify=crmpage.clickOnBusinessProcess();
	  
	  Common.logstep("Step " + (logStep++) + " Choose 'Approved' Option to complete 'Await payment confirmation' task.");
	  crmpage.chooseApproveOption();

	  Common.pause(10);
	  Common.SwitchtoTab(driver,0);
	  driver.navigate().refresh();
	  
	  Common.logMandetoryAssert("Verify that the number of permit records are increased to 1.");
		if(!crmverify.verifyNumberOfPermitRecordss(dataFileName))
		{
			Common.optionalAssertPassed();
			Common.writeProperties(dataFileName,"Await_Payment_Confirmation_Complete", "True", "");
			Common.logStatus("Pass");

		} else {
			Common.optionalAssertFailed();
			Common.writeProperties(dataFileName,"Await_Payment_Confirmation_Complete", "False", "");
			numOfFailure++;
		}

		if (numOfFailure > 0) {
			Common.logStatus("Fail");
			Assert.assertTrue(false);
		}
	}
	 
	 @Test(alwaysRun=true)
	 public void completeSpecimenApplicationAwaitPaymentConfirmation() {

	  int numOfFailure = 0;
	  int logStep = 1;
	  
		
		String dataFileName="specimenApplicationNoValidationData";
	  
		  Common.logcase("Testcase Id: TC_CRM_012");
		  Common.logcase("Testcase Name: To verify OWLS internal user to complete 'Awaiting Payment Confirmation' task and to verify the Permit document is available for the online portal user to download.");
		  
			try {
				String interview_inspection = Common.readProperties(dataFileName,"Payment_Notice_Verified");

				if (interview_inspection.contains("False")) {
					throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
				}
			} catch (Exception e) {
				throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
			}
			appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));

	  Common.logstep("Step " + (logStep++) + " : Open portal url:<a>" + testUrl + "</a>");
	  
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
	
		Common.logstep("Step " + (logStep++)+" Click on Permit Menu.");
		applicationIndexPage.clickonPermit();
	
		Common.logstep("Step " + (logStep++)+" Get current number of Permit records.");
		crmpage.currentPermitRecords(dataFileName);
		
		Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
	  Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
	  
	  Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
	  crmpage.chooseLoginMethod();
	  Common.pause(5);
	  Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
	  crmpage.enterCRMUsername(TestData.getCRMUsername());
	  Common.pause(1);
	  crmpage.enterCRMPassword(TestData.getCRMPassword());
	  Common.pause(1);
	  crmpage.clickonCRMLoginbutton();
	  
	  Common.logstep("Step " + (logStep++) + "Advance search filter feeding with Application Name("+appdata.getApplicationSubject()+") and short Task in desanding order according to date created.");
	  crmpage.advanceSearchForApplicationTask();
	  
	  Common.logMandetoryAssert("Verify that Task status of application '"+appdata.getApplicationSubject()+"' should be 'Await payment confirmation' ");
	  if (crmverify.VerifycurrentTaskAndQueue("Await payment confirmation",appdata.getApplicationSubject(),"Wildlife Licensing Queue")) {
			Common.optionalAssertPassed();
	   Common.writeProperties(dataFileName,"Await_Payment_Confirmation", "True", "");
	  } else {
			Common.optionalAssertFailed();
	   Common.writeProperties(dataFileName,"Await_Payment_Confirmation", "False", "");
	   numOfFailure++;
		throw new SkipException("Skipping Test case as 'Await Payment Confirmation' task is not available or it is not available in Wildlife Licensing Queue.");
	  }
	  
		Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
		crmpage.clickOnActionlink(appdata.getApplicationSubject());
		
		Common.logstep("Step " + (logStep++) + " Click on Start link to Start 'Approval By Delegate' task.");
		crmverify=crmpage.clickOnStart();
	  
	  Common.pause(2);
	  Common.SwitchtoTab(driver,2);
	  driver.navigate().refresh();
	  
	  Common.logstep("Step " + (logStep++) + " Click on Business Process icon available on the top right corner.");
	  crmverify=crmpage.clickOnBusinessProcess();
	  
	  Common.logstep("Step " + (logStep++) + " Choose 'Approved' Option to complete 'Await payment confirmation' task.");
	  crmpage.chooseApproveOption();

	  Common.pause(10);
	  Common.SwitchtoTab(driver,0);
	  driver.navigate().refresh();
	  
	  Common.logMandetoryAssert("Verify that the number of permit records are increased to 1.");
		if(!crmverify.verifyNumberOfPermitRecordss(dataFileName))
		{
			Common.logStatus("Pass");
			Common.optionalAssertPassed();
			Common.writeProperties(dataFileName,"Await_Payment_Confirmation_Complete", "True", "");
		} else {
			Common.optionalAssertFailed();
			Common.writeProperties(dataFileName,"Await_Payment_Confirmation_Complete", "False", "");
			numOfFailure++;
		}

		if (numOfFailure > 0) {
			Common.logStatus("Fail");
			Assert.assertTrue(false);
		}
	}
	 	
	 
	 @Test(alwaysRun=true)
	 public void completeAdvancedApplicationAwaitPaymentConfirmation() {

	  int numOfFailure = 0;
	  int logStep = 1;
	  
		
		String dataFileName="advancedApplicationNoValidationData";
	  
		  Common.logcase("Testcase Id: TC_CRM_013");
		  Common.logcase("Testcase Name: To verify OWLS internal user to complete 'Awaiting Payment Confirmation' task and to verify the Permit document is available for the online portal user to download.");
		  
			try {
				String interview_inspection = Common.readProperties(dataFileName,"Payment_Notice_Verified");

				if (interview_inspection.contains("False")) {
					throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
				}
			} catch (Exception e) {
				throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
			}
			appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));

	  Common.logstep("Step " + (logStep++) + " : Open portal url:<a>" + testUrl + "</a>");
	  
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
	
		Common.logstep("Step " + (logStep++)+" Click on Permit Menu.");
		applicationIndexPage.clickonPermit();
	
		Common.logstep("Step " + (logStep++)+" Get current number of Permit records.");
		crmpage.currentPermitRecords(dataFileName);
		
		Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
	  Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
	  
	  Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
	  crmpage.chooseLoginMethod();
	  Common.pause(5);
	  Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
	  crmpage.enterCRMUsername(TestData.getCRMUsername());
	  Common.pause(1);
	  crmpage.enterCRMPassword(TestData.getCRMPassword());
	  Common.pause(1);
	  crmpage.clickonCRMLoginbutton();
	  
	  Common.logstep("Step " + (logStep++) + "Advance search filter feeding with Application Name("+appdata.getApplicationSubject()+") and short Task in desanding order according to date created.");
	  crmpage.advanceSearchForApplicationTask();
	  
	  Common.logMandetoryAssert("Verify that Task status of application '"+appdata.getApplicationSubject()+"' should be 'Await payment confirmation' ");
	  if (crmverify.VerifycurrentTaskAndQueue("Await payment confirmation",appdata.getApplicationSubject(),"Wildlife Licensing Queue")) {
			Common.optionalAssertPassed();
	   Common.writeProperties(dataFileName,"Await_Payment_Confirmation", "True", "");
	  } else {
			Common.optionalAssertFailed();
	   Common.writeProperties(dataFileName,"Await_Payment_Confirmation", "False", "");
	   numOfFailure++;
		throw new SkipException("Skipping Test case as 'Await Payment Confirmation' task is not available or it is not available in Wildlife Licensing Queue.");
	  }
	  
		Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
		crmpage.clickOnActionlink(appdata.getApplicationSubject());
		
		Common.logstep("Step " + (logStep++) + " Click on Start link to Start 'Approval By Delegate' task.");
		crmverify=crmpage.clickOnStart();
	  
	  Common.pause(2);
	  Common.SwitchtoTab(driver,2);
	  driver.navigate().refresh();
	  
	  Common.logstep("Step " + (logStep++) + " Click on Business Process icon available on the top right corner.");
	  crmverify=crmpage.clickOnBusinessProcess();
	  
	  Common.logstep("Step " + (logStep++) + " Choose 'Approved' Option to complete 'Await payment confirmation' task.");
	  crmpage.chooseApproveOption();

	  Common.pause(10);
	  Common.SwitchtoTab(driver,0);
	  driver.navigate().refresh();
	  
	  Common.logMandetoryAssert("Verify that the number of permit records are increased to 1.");
		if(!crmverify.verifyNumberOfPermitRecordss(dataFileName))
		{
			Common.logStatus("Pass");
			Common.optionalAssertPassed();
			Common.writeProperties(dataFileName,"Await_Payment_Confirmation_Complete", "True", "");
		} else {
			Common.optionalAssertFailed();
			Common.writeProperties(dataFileName,"Await_Payment_Confirmation_Complete", "False", "");
			numOfFailure++;
		}

		if (numOfFailure > 0) {
			Common.logStatus("Fail");
			Assert.assertTrue(false);
		}
	}
	 
	 @Test(alwaysRun=true)
		public void validateAdvancedApplicationReviewAppliactionTask() {

			int numOfFailure = 0;
			int logStep = 1;
		
			String dataFileName="advancedApplicationData";
			String configFileName="advancedApplicationConfig";
		
			Common.logcase("Testcase Id: TC_CRM_014");
			Common.logcase("Testcase Name: To verify OWLS internal user reviews the \"Review Application\" task");
			
			try {
				String Application_Created = Common.readProperties(configFileName,"Application_Created_Sucessfully");
				
				if(Application_Created.contains("False"))
				{
					throw new SkipException("Skipping Test case as Application is not created in portal.");
				}
			}
			catch(Exception e) {
				throw new SkipException("Skipping Test case as Application is not created in portal.");
			}

			appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));
			
			Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
			Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
			
			Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
			crmpage.chooseLoginMethod();
			Common.pause(5);
			Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
			crmpage.enterCRMUsername(TestData.getCRMUsername());
			Common.pause(1);
			crmpage.enterCRMPassword(TestData.getCRMPassword());
			Common.pause(1);
			crmpage.clickonCRMLoginbutton();
			
			Common.logstep("Step " + (logStep++) + " Advance search filter feeding with Application Name.");
			crmpage.advanceSearchForApplicationTask();
			
			Common.logMandetoryAssert("Verify that Task status should be 'Review Application' ");
			if (crmverify.VerifycurrentTaskAndQueue("Review Application",appdata.getApplicationSubject(),"Wildlife Licensing Queue")) {
				Common.optionalAssertPassed();
				Common.writeProperties(configFileName,"Task_status_Review_Application", "True", "");
				Common.logStatus("Pass");
			} else {
				numOfFailure++;
				Common.optionalAssertFailed();
				Common.writeProperties(configFileName,"Task_status_Review_Application", "False", "");
			}
			
			if (numOfFailure > 0) {
				Common.logStatus("Fail");
				Assert.assertTrue(false);
			}
		}
	 
	 @Test(alwaysRun=true)
		public void completeAdvancedApplicationReviewApplicationTask() {

			int numOfFailure = 0;
			int logStep = 1;
			
			String dataFileName="advancedApplicationData";
			String configFileName="advancedApplicationConfig";
		
			Common.logcase("Testcase Id: TC_CRM_015");
			Common.logcase("Testcase Name: To verify OWLS Internal user to progress the \"Review Application\" task to \"Interview/Site Inspection\" task.");

		
			try
			{
				String Application_Created = Common.readProperties(configFileName,"Task_status_Review_Application");
				if(Application_Created.contains("False"))
				{
					throw new SkipException("Skipping Test case as Application Task is not Review Application Task.");
				}
			}
			catch(Exception e)
			{
				throw new SkipException("Skipping Test case as Application Task is not Review Application Task.");
			}
			
		
			appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));
			
			Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
			Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
			
			Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
			crmpage.chooseLoginMethod();
			Common.pause(5);
			Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
			crmpage.enterCRMUsername(TestData.getCRMUsername());
			Common.pause(1);
			crmpage.enterCRMPassword(TestData.getCRMPassword());
			Common.pause(1);
			crmpage.clickonCRMLoginbutton();
			
			Common.logstep("Step " + (logStep++) + " Feed the advance search options and filter current application :"+appdata.getApplicationSubject());
			crmpage.advanceSearchForApplicationTask();
			
			Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
			crmpage.clickOnActionlink(appdata.getApplicationSubject());
			
			Common.logstep("Step " + (logStep++) + " Click on Start link to Start Review Application Task.");
			crmverify=crmpage.clickOnStart();
			
			Common.logOptionalAssert("Verify that Validation Error for Review Application task persisted correctly.");
			if(crmverify.verifyValidationError())
			{
				Common.optionalAssertPassed();
			}
			else
			{
				Common.optionalAssertFailed();
				Common.makeScreenshot(driver, "incorrectValidationError.png");
				numOfFailure++;
			}
					
			Common.logstep("Step " + (logStep++) + " Mousehover on Completion option and choose 'Completed' option to completed Review Application Errors.");
			crmpage.mouseHoverOnCompletion(appdata.getApplicationSubject());
			
			Common.logstep("Step " + (logStep++) + " Click on the Application link to redirect to Application Detail Page.");
			crmpage.clickOnApplication(appdata.getApplicationSubject());
		
			Common.logstep("Step " + (logStep++) + " Click on 'Current Task' sub-panel.");
			crmpage.clickOnCurrentTask();
			
			Common.logMandetoryAssert(" Verify that 'Interview / Site Inspection' task is available in 'Current Task' sub-panel.");
			if (crmverify.verifyCurrentTask("Interview / Site Inspection")) {
				
				Common.optionalAssertPassed();
				Common.writeProperties(configFileName,"Review_Application_Task_Complete", "True", "");
				Common.logStatus("Pass");
			} else {
				Common.optionalAssertFailed();
				Common.writeProperties(configFileName,"Review_Application_Task_Complete", "False", "");
				numOfFailure++;
			}
			
			if (numOfFailure > 0) {
				Common.logStatus("Fail");
				Assert.assertTrue(false);
			}	
		}
	
	 
	 @Test(alwaysRun=true)
	 public void completeAdvancedApplicationInterviewInspectionTask() {

		  int numOfFailure = 0;
		  int logStep = 1;
		  
			String dataFileName="advancedApplicationData";
			String configFileName="advancedApplicationConfig";
		
		  
		  Common.logcase("Testcase Id: TC_CRM_016");
		  Common.logcase("Testcase Name: To verify Interview/Site inspection task to be available under Wildlife licencing queue and inpsecting screen to be available for the role -'Manager wildlife licencing'.");

			try {
				String Application_Created = Common.readProperties(configFileName,"Review_Application_Task_Complete");

				if (Application_Created.contains("False")) {
					throw new SkipException("Skipping Test case as Review Application Task is not completed successfully.");
				}
			} catch (Exception e) {
				throw new SkipException("Skipping Test case as Review Application Task is not completed successfully.");
			}
			
			appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));

		  Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
		  Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
		  
		  Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
		  crmpage.chooseLoginMethod();
		  Common.pause(2);
		  Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
		  crmpage.enterCRMUsername(TestData.getCRMInspecterUsername());
		  Common.pause(1);
		  crmpage.enterCRMPassword(TestData.getCRMInspectorPassword());
		  Common.pause(1);
		  crmpage.clickonCRMLoginbutton();
		  
		  Common.logstep("Step " + (logStep++) + " Feed the advance search options and filter current application :"+appdata.getApplicationSubject());
		  crmpage.advanceSearchForApplicationTask();
		  
			Common.logMandetoryAssert("Verify that Task status should be 'Interview/Site Inspection' ");
			if (crmverify.VerifycurrentTaskAndQueue("Interview / Site Inspection",appdata.getApplicationSubject(),"Wildlife Licensing Queue")) {
				Common.optionalAssertPassed();
			} else {
				Common.optionalAssertFailed();
				throw new SkipException("'Interview/Site Inspection' is not available to the Inspection User.");
			}
		  
			Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
			crmpage.clickOnActionlink(appdata.getApplicationSubject());
			
			Common.logstep("Step " + (logStep++) + " Click on Start link to Start 'Interview/Site Inspection' task.");
			crmverify=crmpage.clickOnStart();
		  
		  Common.pause(2);
		  Common.SwitchtoTab(driver,2);
		  
		  Common.logMandetoryAssert("Verify that the user is redirected to Application Details Page and 'Interview/Site Inspection' panel is expanded to the user.");
			if(crmverify.verifyInspectingOfficerAssessmentTabExpanded())
			{
				Common.optionalAssertPassed();
			}
			else
			{
				Common.optionalAssertFailed();
				Common.makeScreenshot(driver, "incorrectInspectionTask.png");
				numOfFailure++;
				
			}

			if (numOfFailure > 0) {
				Common.logStatus("Fail");
				Assert.assertTrue(false);
			}
		  
		  
		  Common.logstep("Step " + (logStep++) + " Complete 'INSPECTING OFFICER ASSESSMENT' form as below and click on Save button.");
		  crmpage.enteronDate(dataFileName);
		  crmpage.clickonsiteRadiobutton();
		  crmpage.enterPresentcomment(dataFileName);
		  crmpage.enterInspectionComment(dataFileName);
		  crmpage.enterConcernsComment(dataFileName);
		  crmpage.dropdown_selectDelegate();
		  crmpage.clickonApprovedRadiobutton();
		  crmpage.clickonSavebutton();
		  crmpage.clickonSubmiteToDelegate();
		  
		  Common.pause(5);	
		  driver.navigate().refresh();
			Common.logstep("Step " + (logStep++) + " Click on 'Current Task' sub-panel.");
			crmpage.clickOnCurrentTask();
		  
		  Common.logMandetoryAssert(" Verify that 'Approval By Delegate' task is available in 'Current Task' sub-panel.");
			if (crmverify.verifyCurrentTask("Approval By Delegate")) {
				Common.optionalAssertPassed();
				Common.writeProperties(configFileName,"Interview_Inspection_Task_Complete", "True", "");
				Common.logStatus("Pass");
			
			} else {
				Common.optionalAssertPassed();
				Common.writeProperties(configFileName,"Interview_Inspection_Task_Complete", "False", "");
				numOfFailure++;
			}
			
			if (numOfFailure > 0) {
				Common.logStatus("Fail");
				Assert.assertTrue(false);
			}
		 
	 }
	 
	 @Test(alwaysRun=true)
	 public void verifyAndCompleteAdvancedApplicationApproveDelegateTask() {

	  int numOfFailure = 0;
	  int logStep = 1;
	  
		String dataFileName="advancedApplicationData";
		String configFileName="advancedApplicationConfig";
	  
		  Common.logcase("Testcase Id: TC_CRM_017");
		  Common.logcase("Testcase Name: To verify Delegate Officer take the 'Approval By Delegate' task to 'Await payment confirmation' task.");

		  
			try {
				String interview_inspection = Common.readProperties(configFileName,"Interview_Inspection_Task_Complete");
				if (interview_inspection.contains("False")) {
					throw new SkipException("Skipping Test case as Interview / Site Inspection task is not completed successfully.");
				}
			} catch (Exception e) {
				throw new SkipException("Skipping Test case as Interview / Site Inspection task is not completed successfully.");
			}
		  

	  appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));

	  Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
	  Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
	  
	  Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
	  crmpage.chooseLoginMethod();
	  Common.pause(5);
	  Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
	  crmpage.enterCRMUsername(TestData.getCRMDelegateUsername());
	  Common.pause(1);
	  crmpage.enterCRMPassword(TestData.getCRMDelegatePassword());
	  Common.pause(1);
	  crmpage.clickonCRMLoginbutton();
	  
	  Common.logstep("Step " + (logStep++) + "  Advance search filter feeding with Application Name("+appdata.getApplicationSubject()+") and short Task in desanding order according to date created.");
	  crmpage.advanceSearchForApplicationTask();
	  
	  Common.logMandetoryAssert("Verify that Task status of application '"+appdata.getApplicationSubject()+"' should be 'Approval By Delegate' ");
	  if (crmverify.VerifycurrentTaskAndQueue("Approval By Delegate",appdata.getApplicationSubject(),"Wildlife Licensing Queue")) {
			Common.optionalAssertPassed();
	   Common.writeProperties(configFileName,"Task_status_Approval_By_Delegate", "True", "");
	  } else {
		Common.optionalAssertFailed();
	   Common.writeProperties(configFileName,"Task_status_Approval_By_Delegate", "False", "");
	   numOfFailure++;
	  }
	  
		Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
		crmpage.clickOnActionlink(appdata.getApplicationSubject());
		
		Common.logstep("Step " + (logStep++) + " Click on Start link to Start 'Approval By Delegate' task.");
		crmverify=crmpage.clickOnStart();
	  
	  Common.pause(2);
	  Common.SwitchtoTab(driver,2);
	  
	  Common.logMandetoryAssert("Verify that the user is redirected to Application Details Page and 'Delegate Decision' panel is expanded to the user.");
		if(crmverify.verifyInspectingOfficerAssessmentTabExpanded())
		{
			Common.optionalAssertPassed();
		}
		else
		{
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "incorrectDelegateScreen.png");
			numOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Comments provided by Inspecting officer is correctly displayed to the Delegate Officer.");
		if(crmverify.verifyInspectionComment(dataFileName))
		{
			Common.optionalAssertPassed();
		}
		else
		{
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "incorrectInspectionComment.png");
			numOfFailure++;
		}

		Common.logstep("Step " + (logStep++) + " Choose 'Approve' from Delegate 'Delegate Determination'.");
		crmpage.approveFromDelegate();
		
		Common.logstep("Step " + (logStep++) + " Click on 'Verify' button to validate Delegate Signed .");
		crmpage.clickOnVerifyButton();
		
		Common.logMandetoryAssert("Verify that Delegate Signature validated and 'Record Decision' button enable to Click.");
		if(crmverify.verifyDelegateSign())
		{
			Common.optionalAssertPassed();
		}
		else
		{
			Common.optionalAssertFailed();
			Common.makeScreenshot(driver, "incorrectInspectionComment.png");
			numOfFailure++;
		}
		
		Common.logstep("Step " + (logStep++) + " Click on 'Deleate Decision' button to complete Delegate Task .");
		crmpage.clickDelegateDecision();
		
		Common.pause(10);
		driver.navigate().refresh();
		
		Common.logstep("Step " + (logStep++) + " Click on 'Current Task' sub-panel.");
		crmpage.clickOnCurrentTask();
		
		Common.logMandetoryAssert("Verify that Approval By Delagate Task Completed successfully and 'Await payment confirmation' task is available in Current Task Panel.");
		if(crmverify.verifyCurrentTask("Await payment confirmation"))
		{
			Common.optionalAssertPassed();
			Common.writeProperties(configFileName,"Delegate_Approval_Task_Complete", "True", "");
			Common.logStatus("Pass");
		} else {
			Common.optionalAssertFailed();
			Common.writeProperties(configFileName,"Delegate_Approval_Task_Complete", "False", "");
			numOfFailure++;
		}
		
		if (numOfFailure > 0) {
			Common.logStatus("Fail");
			Assert.assertTrue(false);
		}
	}
	 
	 
	 @Test(alwaysRun=true)
	 public void completeAdvancedApplicationAwaitingPaymentConfirmationTask() {

	  int numOfFailure = 0;
	  int logStep = 1;
	  	
		String dataFileName="advancedApplicationData";
		String configFileName="advancedApplicationConfig";
	  
		  Common.logcase("Testcase Id: TC_CRM_018");
		  Common.logcase("Testcase Name: To verify OWLS internal user to complete 'Awaiting Payment Confirmation' task and to verify the Permit document is available for the online portal user to download.");
		  
			try {
				String interview_inspection = Common.readProperties(configFileName,"Delegate_Approval_Task_Complete");

				if (interview_inspection.contains("False")) {
					throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
				}
			} catch (Exception e) {
				throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
			}
			appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));

	  Common.logstep("Step " + (logStep++) + " : Open portal url:<a>" + testUrl + "</a>");
	  
		Common.logstep("Step " + (logStep++)+" Enter Username.");
		applicationIndexPage.enterAdminUsername();
		
		Common.logstep("Step " + (logStep++)+" Enter Password.");
		applicationIndexPage.enterAdminPassword();
		
		Common.logstep("Step " + (logStep++)+" Click on Login icon.");
		applicationIndexPage.clickonlogin();
		
		Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
		applicationIndexPage.clickPersonalMenu();
	
		Common.logstep("Step " + (logStep++)+" Click on Permit Menu.");
		applicationIndexPage.clickonPermit();
	
		Common.logstep("Step " + (logStep++)+" Get current number of Permit records.");
		crmpage.currentPermitRecords(dataFileName);
		
		Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
	  Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
	  
	  Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
	  crmpage.chooseLoginMethod();
	  Common.pause(5);
	  Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
	  crmpage.enterCRMUsername(TestData.getCRMUsername());
	  Common.pause(1);
	  crmpage.enterCRMPassword(TestData.getCRMPassword());
	  Common.pause(1);
	  crmpage.clickonCRMLoginbutton();
	  
	  Common.logstep("Step " + (logStep++) + "Advance search filter feeding with Application Name("+appdata.getApplicationSubject()+") and short Task in desanding order according to date created.");
	  crmpage.advanceSearchForApplicationTask();
	  
	  Common.logMandetoryAssert("Verify that Task status of application '"+appdata.getApplicationSubject()+"' should be 'Await payment confirmation' ");
	  if (crmverify.VerifycurrentTaskAndQueue("Await payment confirmation",appdata.getApplicationSubject(),"Wildlife Licensing Queue")) {
			Common.optionalAssertPassed();
	   Common.writeProperties(configFileName,"Task_status_Await_Payment_Confirmation", "True", "");
	  } else {
			Common.optionalAssertFailed();
	   Common.writeProperties(configFileName,"Task_status_Await_Payment_Confirmation", "False", "");
	   numOfFailure++;
		throw new SkipException("Skipping Test case as 'Await Payment Confirmation' task.");
	  }
	  
		Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
		crmpage.clickOnActionlink(appdata.getApplicationSubject());
		
		Common.logstep("Step " + (logStep++) + " Click on Start link to Start 'Approval By Delegate' task.");
		crmverify=crmpage.clickOnStart();
	  
	  Common.pause(2);
	  Common.SwitchtoTab(driver,2);
	  driver.navigate().refresh();
	  
	  Common.logstep("Step " + (logStep++) + " Click on Business Process icon available on the top right corner.");
	  crmverify=crmpage.clickOnBusinessProcess();
	  
	  Common.logstep("Step " + (logStep++) + " Choose 'Approved' Option to complete 'Await payment confirmation' task.");
	  crmpage.chooseApproveOption();

	  Common.pause(10);
	  Common.SwitchtoTab(driver,0);
	  driver.navigate().refresh();
	  
	  Common.logMandetoryAssert("Verify that the number of permit records are increased to 1.");
		if(!crmverify.verifyNumberOfPermitRecordss(dataFileName))
		{
			Common.logStatus("Pass");
			Common.optionalAssertPassed();
			Common.writeProperties(configFileName,"Await_Payment_Confirmation_Complete", "True", "");
		} else {
			Common.optionalAssertFailed();
			Common.writeProperties(configFileName,"Await_Payment_Confirmation_Complete", "False", "");
			numOfFailure++;
		}

		if (numOfFailure > 0) {
			Common.logStatus("Fail");
			Assert.assertTrue(false);
		}
	}
	 
		@Test(alwaysRun=true)
		public void validateDingoReviewAppliactionTask() {

			int numOfFailure = 0;
			int logStep = 1;
		
			String dataFileName="dingoApplicationData";
			String configFileName="dingoApplicationConfig";
		
			Common.logcase("Testcase Id: TC_CRM_019");
			Common.logcase("Testcase Name: To verify OWLS internal user reviews the \"Review Application\" task");
			
			try {
				String Application_Created = Common.readProperties(configFileName,"Application_Created_Sucessfully");
				
				if(Application_Created.contains("False"))
				{
					throw new SkipException("Skipping Test case as Application is not created in portal.");
				}
			}
			catch(Exception e) {
				throw new SkipException("Skipping Test case as Application is not created in portal.");
			}
			

			Common.logstep("Step " + (logStep++) + " : Open portal url:<a>" + testUrl + "</a>");
			appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));
			
			Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
			Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
			
			Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
			crmpage.chooseLoginMethod();
			Common.pause(5);
			Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
			crmpage.enterCRMUsername(TestData.getCRMUsername());
			Common.pause(1);
			crmpage.enterCRMPassword(TestData.getCRMPassword());
			Common.pause(1);
			crmpage.clickonCRMLoginbutton();
			
			Common.logstep("Step " + (logStep++) + " Advance search filter feeding with Application Name.");
			crmpage.advanceSearchForApplicationTask();
			
			Common.logMandetoryAssert("Verify that Task status should be 'Review Application' ");
			if (crmverify.VerifycurrentTaskAndQueue("Review Application",appdata.getApplicationSubject(),"Wildlife Licensing Queue")) {
				Common.optionalAssertPassed();
				Common.writeProperties(configFileName,"Task_status_Review_Application", "True", "");
				Common.logStatus("Pass");
			} else {
				numOfFailure++;
				Common.optionalAssertFailed();
				Common.writeProperties(configFileName,"Task_status_Review_Application", "False", "");
			}
			
			if (numOfFailure > 0) {
				Common.logstep("Fail");
				Assert.assertTrue(false);
			}
		}
		
		
		@Test(alwaysRun=true)
		public void completeDingoReviewApplicationTask() {

			int numOfFailure = 0;
			int logStep = 1;
			
			String dataFileName="dingoApplicationData";
			String configFileName="dingoApplicationConfig";
		
			Common.logcase("Testcase Id: TC_CRM_020");
			Common.logcase("Testcase Name: To verify OWLS Internal user to progress the \"Review Application\" task to \"Interview/Site Inspection\" task.");

		
			try
			{
				String Application_Created = Common.readProperties(configFileName,"Task_status_Review_Application");
				if(Application_Created.contains("False"))
				{
					throw new SkipException("Skipping Test case as Application Task is not Review Application Task.");
				}
			}
			catch(Exception e)
			{
				throw new SkipException("Skipping Test case as Application Task is not Review Application Task.");
			}
			
		
			appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));
			
			Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
			Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
			
			Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
			crmpage.chooseLoginMethod();
			Common.pause(5);
			Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
			crmpage.enterCRMUsername(TestData.getCRMUsername());
			Common.pause(1);
			crmpage.enterCRMPassword(TestData.getCRMPassword());
			Common.pause(1);
			crmpage.clickonCRMLoginbutton();
			
			Common.logstep("Step " + (logStep++) + " Feed the advance search options and filter current application :"+appdata.getApplicationSubject());
			crmpage.advanceSearchForApplicationTask();
			
			Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
			crmpage.clickOnActionlink(appdata.getApplicationSubject());
			
			Common.logstep("Step " + (logStep++) + " Click on Start link to Start Review Application Task.");
			crmverify=crmpage.clickOnStart();
			
			Common.logOptionalAssert("Verify that Validation Error for Review Application task persisted correctly.");
			if(crmverify.verifyValidationError())
			{
				Common.optionalAssertPassed();
			}
			else
			{
				Common.optionalAssertFailed();
				Common.makeScreenshot(driver, "incorrectValidationError.png");
				numOfFailure++;
			}
					
			Common.logstep("Step " + (logStep++) + " Mousehover on Completion option and choose 'Completed' option to completed Review Application Errors.");
			crmpage.mouseHoverOnCompletion(appdata.getApplicationSubject());
			
			Common.logstep("Step " + (logStep++) + " Click on the Application link to redirect to Application Detail Page.");
			crmpage.clickOnApplication(appdata.getApplicationSubject());
		
			Common.logstep("Step " + (logStep++) + " Click on 'Current Task' sub-panel.");
			crmpage.clickOnCurrentTask();
			
			Common.logMandetoryAssert(" Verify that 'Interview / Site Inspection' task is available in 'Current Task' sub-panel.");
			if (crmverify.verifyCurrentTask("Interview / Site Inspection")) {
				
				Common.optionalAssertPassed();
				Common.writeProperties(configFileName,"Review_Application_Task_Complete", "True", "");
				Common.logStatus("Pass");
			} else {
				Common.optionalAssertFailed();
				Common.writeProperties(configFileName,"Review_Application_Task_Complete", "False", "");
				numOfFailure++;
			}
			
			if (numOfFailure > 0) {
				Common.logStatus("Fail");
				Assert.assertTrue(false);
			}	
		}
		
		@Test(alwaysRun=true)
		 public void completeDingoInterviewInspectionTask() {

			  int numOfFailure = 0;
			  int logStep = 1;
			  
				String dataFileName="dingoApplicationData";
				String configFileName="dingoApplicationConfig";
			
			  
			  Common.logcase("Testcase Id: TC_CRM_021");
			  Common.logcase("Testcase Name: To verify Interview/Site inspection task to be available under respective regional queue and inpsecting screen to be available for the role -'Manager wildlife licencing'.");

				try {
					String Application_Created = Common.readProperties(configFileName,"Review_Application_Task_Complete");

					if (Application_Created.contains("False")) {
						throw new SkipException("Skipping Test case as Review Application Task is not completed successfully.");
					}
				} catch (Exception e) {
					throw new SkipException("Skipping Test case as Review Application Task is not completed successfully.");
				}
				
				appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));

			  Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
			  Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
			  
			  Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
			  crmpage.chooseLoginMethod();
			  Common.pause(2);
			  Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
			  crmpage.enterCRMUsername(TestData.getCRMInspecterUsername());
			  Common.pause(1);
			  crmpage.enterCRMPassword(TestData.getCRMInspectorPassword());
			  Common.pause(1);
			  crmpage.clickonCRMLoginbutton();
			  
			  Common.logstep("Step " + (logStep++) + " Feed the advance search options and filter current application :"+appdata.getApplicationSubject());
			  crmpage.advanceSearchForApplicationTask();
			  
				Common.logMandetoryAssert("Verify that Task status should be 'Interview/Site Inspection' ");
				if (crmverify.VerifycurrentTaskAndQueue("Interview / Site Inspection",appdata.getApplicationSubject(),"Loddon Mallee Queue")) {
					Common.optionalAssertPassed();
				} else {
					Common.optionalAssertFailed();
					throw new SkipException("'Interview/Site Inspection' is not available to the Inspection User.");
				}
			  
				Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
				crmpage.clickOnActionlink(appdata.getApplicationSubject());
				
				Common.logstep("Step " + (logStep++) + " Click on Start link to Start 'Interview/Site Inspection' task.");
				crmverify=crmpage.clickOnStart();
			  
			  Common.pause(2);
			  Common.SwitchtoTab(driver,2);
			  
			  Common.logMandetoryAssert("Verify that the user is redirected to Application Details Page and 'Interview/Site Inspection' panel is expanded to the user.");
				if(crmverify.verifyInspectingOfficerAssessmentTabExpanded())
				{
					Common.optionalAssertPassed();
				}
				else
				{
					Common.optionalAssertFailed();
					Common.makeScreenshot(driver, "incorrectInspectionTask.png");
					numOfFailure++;
					
				}

				if (numOfFailure > 0) {
					Common.logStatus("Fail");
					Assert.assertTrue(false);
				}
			  
			  
			  Common.logstep("Step " + (logStep++) + " Complete 'INSPECTING OFFICER ASSESSMENT' form as below and click on Save button.");
			  crmpage.enteronDate(dataFileName);
			  crmpage.clickonsiteRadiobutton();
			  crmpage.enterPresentcomment(dataFileName);
			  crmpage.enterInspectionComment(dataFileName);
			  crmpage.enterConcernsComment(dataFileName);
			  crmpage.dropdown_selectDelegate();
			  crmpage.clickonApprovedRadiobutton();
			  crmpage.clickonSavebutton();
			  crmpage.clickonSubmiteToDelegate();
			  
			  Common.pause(5);	
			  driver.navigate().refresh();
				Common.logstep("Step " + (logStep++) + " Click on 'Current Task' sub-panel.");
				crmpage.clickOnCurrentTask();
			  
			  Common.logMandetoryAssert(" Verify that 'Approval By Delegate' task is available in 'Current Task' sub-panel.");
				if (crmverify.verifyCurrentTask("Approval By Delegate")) {
					Common.optionalAssertPassed();
					Common.writeProperties(configFileName,"Interview_Inspection_Task_Complete", "True", "");
					Common.logStatus("Pass");
				
				} else {
					Common.optionalAssertPassed();
					Common.writeProperties(configFileName,"Interview_Inspection_Task_Complete", "False", "");
					numOfFailure++;
				}
				
				if (numOfFailure > 0) {
					Common.logStatus("Fail");
					Assert.assertTrue(false);
				}
			 
		 }
		 
		 @Test(alwaysRun=true)
		 public void verifyAndCompleteDingoApproveDelegateTask() {

			  int numOfFailure = 0;
			  int logStep = 1;
			  
			  String dataFileName="dingoApplicationData";
				String configFileName="dingoApplicationConfig";
			  
			  Common.logcase("Testcase Id: TC_CRM_022");
			  Common.logcase("Testcase Name: To verify Delegate Office take the 'Approval By Delegate' task to 'Await payment confirmation' task.");

			  
				try {
					String interview_inspection = Common.readProperties(configFileName,"Interview_Inspection_Task_Complete");
					if (interview_inspection.contains("False")) {
						throw new SkipException("Skipping Test case as Interview / Site Inspection task is not completed successfully.");
					}
				} catch (Exception e) {
					throw new SkipException("Skipping Test case as Interview / Site Inspection task is not completed successfully.");
				}
			  
			  appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));

			  Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
			  Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
			  
			  Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
			  crmpage.chooseLoginMethod();
			  Common.pause(5);
			  Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
			  crmpage.enterCRMUsername(TestData.getCRMDelegateUsername());
			  Common.pause(1);
			  crmpage.enterCRMPassword(TestData.getCRMDelegatePassword());
			  Common.pause(1);
			  crmpage.clickonCRMLoginbutton();
			  
			  Common.logstep("Step " + (logStep++) + "  Advance search filter feeding with Application Name("+appdata.getApplicationSubject()+") and short Task in desanding order according to date created.");
			  crmpage.advanceSearchForApplicationTask();
			  
			  Common.logMandetoryAssert("Verify that Task status of application '"+appdata.getApplicationSubject()+"' should be 'Approval By Delegate' ");
			  if (crmverify.VerifycurrentTaskAndQueue("Approval By Delegate",appdata.getApplicationSubject(),"Wildlife Licensing Queue")) {
					Common.optionalAssertPassed();
			   Common.writeProperties(configFileName,"Task_status_Approval_By_Delegate", "True", "");
			  } else {
				Common.optionalAssertFailed();
			   Common.writeProperties(configFileName,"Task_status_Approval_By_Delegate", "False", "");
			   numOfFailure++;
			  }
			  
				Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
				crmpage.clickOnActionlink(appdata.getApplicationSubject());
				
				Common.logstep("Step " + (logStep++) + " Click on Start link to Start 'Approval By Delegate' task.");
				crmverify=crmpage.clickOnStart();
			  
			  Common.pause(2);
			  Common.SwitchtoTab(driver,2);
			  
			  Common.logMandetoryAssert("Verify that the user is redirected to Application Details Page and 'Delegate Decision' panel is expanded to the user.");
				if(crmverify.verifyInspectingOfficerAssessmentTabExpanded())
				{
					Common.optionalAssertPassed();
				}
				else
				{
					Common.optionalAssertFailed();
					Common.makeScreenshot(driver, "incorrectDelegateScreen.png");
					numOfFailure++;
				}
				
				Common.logMandetoryAssert("Verify that Comments provided by Inspecting officer is correctly displayed to the Delegate Officer.");
				if(crmverify.verifyInspectionComment(dataFileName))
				{
					Common.optionalAssertPassed();
				}
				else
				{
					Common.optionalAssertFailed();
					Common.makeScreenshot(driver, "incorrectInspectionComment.png");
					numOfFailure++;
				}

				Common.logstep("Step " + (logStep++) + " Choose 'Approve' from Delegate 'Delegate Determination'.");
				crmpage.approveFromDelegate();
				
				Common.logstep("Step " + (logStep++) + " Click on 'Verify' button to validate Delegate Signed .");
				crmpage.clickOnVerifyButton();
				
				Common.logMandetoryAssert("Verify that Delegate Signature validated and 'Record Decision' button enable to Click.");
				if(crmverify.verifyDelegateSign())
				{
					Common.optionalAssertPassed();
				}
				else
				{
					Common.optionalAssertFailed();
					Common.makeScreenshot(driver, "incorrectInspectionComment.png");
					numOfFailure++;
				}
				
				Common.logstep("Step " + (logStep++) + " Click on 'Deleate Decision' button to complete Delegate Task .");
				crmpage.clickDelegateDecision();
				
				Common.pause(20);
				driver.navigate().refresh();
				
				Common.logstep("Step " + (logStep++) + " Click on 'Current Task' sub-panel.");
				crmpage.clickOnCurrentTask();
				
				Common.logMandetoryAssert("Verify that Approval By Delagate Task Completed successfully and 'Await payment confirmation' task is available in Current Task Panel.");
				if(crmverify.verifyCurrentTask("Await payment confirmation"))
				{
					Common.optionalAssertPassed();
					Common.writeProperties(configFileName,"Delegate_Approval_Task_Complete", "True", "");
					Common.logStatus("Pass");
				} else {
					Common.optionalAssertFailed();
					Common.writeProperties(configFileName,"Delegate_Approval_Task_Complete", "False", "");
					numOfFailure++;
				}
				
				if (numOfFailure > 0) {
					Common.logStatus("Fail");
					Assert.assertTrue(false);
				}
		}
		 
		 
		 @Test(alwaysRun=true)
		 public void completeDingoPaymentNoticeAndAwaitingTask() {

			  int numOfFailure = 0;
			  int logStep = 1;
			  
				
				String dataFileName="dingoApplicationData";
				String configFileName="dingoApplicationConfig";
			  
			  Common.logcase("Testcase Id: TC_CRM_005");
			  Common.logcase("Testcase Name: To verify OWLS internal user to complete 'Awaiting Payment Confirmation' task and to verify the Permit document is available for the online portal user to download.");

			  
				try {
					String interview_inspection = Common.readProperties(configFileName,"Delegate_Approval_Task_Complete");
					if (interview_inspection.contains("False")) {
						throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
					}
				} catch (Exception e) {
					throw new SkipException("Skipping Test case as 'Payment Notice' and 'Pay Fee' options are not available or Payment Notice has not yet been generated.");
				}
				appdata.setApplicationSubject(Common.readDataProperties(dataFileName,"ApplicationName"));


			  Common.logstep("Step " + (logStep++) + " : Open portal url:<a>" + testUrl + "</a>");
			  
				Common.logstep("Step " + (logStep++)+" Enter Username.");
				applicationIndexPage.enterAdminUsername();
				
				Common.logstep("Step " + (logStep++)+" Enter Password.");
				applicationIndexPage.enterAdminPassword();
				
				Common.logstep("Step " + (logStep++)+" Click on Login icon.");
				applicationIndexPage.clickonlogin();
				
				Common.logstep("Step " + (logStep++)+" Click on Personal Menu.");
				applicationIndexPage.clickPersonalMenu();
			
				Common.logstep("Step " + (logStep++)+" Click on Permit Menu.");
				applicationIndexPage.clickonPermit();
			
				Common.logstep("Step " + (logStep++)+" Get current number of Permit records.");
				crmpage.currentPermitRecords(dataFileName);
				
				Common.logstep("Step " + (logStep++) + " : Open CRM url:<a>" + TestData.getCRMURL() + "</a>");
			  Common.switchToNewtabWithUrl(driver,TestData.getCRMURL());
			  
			  Common.logstep("Step " + (logStep++) + " Choose 'Standard Authentication' login method from Drop down.");
			  crmpage.chooseLoginMethod();
			  Common.pause(5);
			  Common.logstep("Step " + (logStep++) + " Login to CRM as admin");
			  crmpage.enterCRMUsername(TestData.getCRMUsername());
			  Common.pause(1);
			  crmpage.enterCRMPassword(TestData.getCRMPassword());
			  Common.pause(1);
			  crmpage.clickonCRMLoginbutton();
			  
			  Common.logstep("Step " + (logStep++) + "Advance search filter feeding with Application Name("+appdata.getApplicationSubject()+") and short Task in desanding order according to date created.");
			  crmpage.advanceSearchForApplicationTask();
			  
			  Common.logMandetoryAssert("Verify that Task status of application '"+appdata.getApplicationSubject()+"' should be 'Await payment confirmation' ");
			  if (crmverify.VerifycurrentTaskAndQueue("Await payment confirmation",appdata.getApplicationSubject(),"Wildlife Licensing Queue")) {
					Common.optionalAssertPassed();
			   Common.writeProperties(configFileName,"Task_status_Await_Payment_Confirmation", "True", "");
			  } else {
					Common.optionalAssertFailed();
			   Common.writeProperties(configFileName,"Task_status_Await_Payment_Confirmation", "False", "");
			   numOfFailure++;
				throw new SkipException("Skipping Test case as 'Await Payment Confirmation' task.");
			  }
			  
				Common.logstep("Step " + (logStep++) + " Click on Action link of "+appdata.getApplicationSubject());
				crmpage.clickOnActionlink(appdata.getApplicationSubject());
				
				Common.logstep("Step " + (logStep++) + " Click on Start link to Start 'Approval By Delegate' task.");
				crmverify=crmpage.clickOnStart();
			  
			  Common.pause(2);
			  Common.SwitchtoTab(driver,2);
			  driver.navigate().refresh();
			  
			  Common.logstep("Step " + (logStep++) + " Click on Business Process icon available on the top right corner.");
			  crmverify=crmpage.clickOnBusinessProcess();
			  
			  Common.logstep("Step " + (logStep++) + " Choose 'Approved' Option to complete 'Await payment confirmation' task.");
			  crmpage.chooseApproveOption();

			  Common.pause(10);
			  Common.SwitchtoTab(driver,0);
			  driver.navigate().refresh();
			  
			  Common.logMandetoryAssert("Verify that the number of permit records are increased to 1.");
				if(!crmverify.verifyNumberOfPermitRecordss(dataFileName))
				{
					Common.logStatus("Pass");
					Common.optionalAssertPassed();
					Common.writeProperties(configFileName,"Await_Payment_Confirmation_Complete", "True", "");
				} else {
					Common.optionalAssertFailed();
					Common.writeProperties(configFileName,"Await_Payment_Confirmation_Complete", "False", "");
					numOfFailure++;
				}

				if (numOfFailure > 0) {
					Common.logStatus("Fail");
					Assert.assertTrue(false);
				}
		}
		 
*/	 
}
	
