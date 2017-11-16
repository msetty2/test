package com.owls.crm.indexpage;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.owls.crm.verification.CRMverification;
import com.owls.init.AbstractPage;
import com.owls.init.Common;
import com.owls.init.TestData;

public class CRMIndexpage extends AbstractPage {

	public CRMIndexpage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * WebElement to choose login method on CRM portal.
	 */
	@FindBy(xpath="//select[@name='method_login_select_box']")
	WebElement combobox_chooseLoginMethod;
	
	/**
	 * Method to select 'Login method' from combo box.
	 */
	public void chooseLoginMethod()
	{
		Common.pause(2);
		Common.selectFromComboByVisibleText(combobox_chooseLoginMethod, "Standard Authentication");
		
	}

	/**
	 * WebElemet for 'CRM user' name textbox.
	 */
	@FindBy(xpath="//input[@name='user_name']")
	WebElement textbox_CRMUsername;
	
	/**
	 * WebElemet for CRM password textbox.
	 */
	@FindBy(xpath="//input[@id='user_password']")
	WebElement textbox_CRMPassword;
	
	/**
	 * WebElemet for CRM Login button.
	 */
	@FindBy(xpath="//input[@title='Log In']")
	WebElement button_CRMLogin;
	
	/**
	 * Method to Enter Username on CRM Login page. 
	 * TestData.getCRMUsername() is reading CRM user name from 'Resource/Credentials.xlsx' sheet.
	 */
	public void enterCRMUsername(String username)
	{
		System.err.println(username+"       "+TestData.getCRMDelegateUsername());
		Common.waitForElement(driver, textbox_CRMUsername);
		Common.type(textbox_CRMUsername,username);
		Common.log("CRM User Name : " + textbox_CRMUsername.getAttribute("value"));
	}
	
	/**
	 * Method to Enter Password on CRM Login page. 
	 * TestData.getCRMUsername() is reading CRM user name from 'Resource/Credentials.xlsx' sheet.
	 */
	public void enterCRMPassword(String password)
	{
		System.out.println("Password: "+password);
		Common.type(textbox_CRMPassword, password);
		Common.log("CRM Password : " + textbox_CRMPassword.getAttribute("value"));

	}
	
	/**
	 * Method to click on CRM 'Login' button on CRM Login page.
	 */
	public void clickonCRMLoginbutton()
	{
		Common.pause(2);
		Common.clickOn(driver, button_CRMLogin);
	}
	
	/**
	 * WebElement of 'Application' menu on CRM.
	 */
	@FindBy(xpath="//span/a[text()='Application'][@module='owls_Application']")
	WebElement headermenu_Application;
	
	/**
	 * Method to click on 'Application' header menu.
	 */
	public void clickonApplicationMenu()
	{
		Common.waitForElement(driver, headermenu_Application);
		Common.pause(2);
		Common.clickOn(driver, headermenu_Application);
	}
	
	/**
	 * WebElement of 'Tasks' menu on CRM.
	 */
	@FindBy(xpath="//li/span/a[text()='Tasks'][@id='moduleTab_9_Tasks']")
	WebElement headermenu_Tasks;
	
	/**
	 * Method to click on 'Tasks' header menu.
	 */
	public void clickonTasksMenu()
	{
		Common.waitForElement(driver, headermenu_Tasks);
		Common.pause(2);
		Common.jsClick(driver,  headermenu_Tasks);
	}
	
	/**
	 * WebElement for Application header display on CRM's application page.
	 */
	@FindBy(xpath="//h2/a[text()='Tasks']")
	WebElement breadcrumb_Tasks;
	
	/**
	 * WebElement of filter icon on top of the Applications list.
	 */
	@FindBy(xpath="//ul[contains(@class,'searchLink')]//a")
	List<WebElement> filterIcon;
	
	/**
	 * Method to click on 'Filter icon' available at top of application list.
	 */
	
	public void clickonFilterIcon()
	{
		Common.waitForElement(driver, breadcrumb_Tasks);
		Common.pause(2);
		Common.clickOn(driver, filterIcon.get(0));
		Common.pause(2);
	}
	
	/**
	 * WebElement for 'Advanced Filter' tab on FILTER popup.
	 */
	@FindBy(xpath="//a[text()='Advanced Filter']")
	WebElement tab_AdvanceFilter;
	
	/**
	 * WebElement for 'Contacts' textbox on FILTER popup.
	 */
	@FindBy(xpath=".//*[@id='contacts_owls_application_1_name_advanced']")
	WebElement textbox_FilterbyContacts;
	
	/**
	 * Method to click on 'Advanced Filter' tab and wait for Advance filter form to open.
	 */
	public void clickonAdvancedFilterTab()
	{	
		Common.waitForElement(driver, tab_AdvanceFilter);
		Common.pause(4);
		Common.clickOn(driver, tab_AdvanceFilter);
		Common.pause(5);
		Common.waitForElement(driver, textbox_FilterbyApplicationSubject);
		Common.pause(2);
	}
	
	@FindBy(xpath=".//*[@id='name_advanced']")
	WebElement textbox_FilterbyApplicationSubject;
	
	public void EnterApplicationSubjectonFilterPopup()
	{
		Common.type(textbox_FilterbyApplicationSubject, appdata.getApplicationSubject());
	}
	
	@FindBy(xpath="//*[@id='orderBySelect']")
	WebElement combobox_FilterOrderbyColumn;
	
	public void SelectOrderByColumn()
	{
		Common.selectFromComboByVisibleText(combobox_FilterOrderbyColumn, "Date Created");
	}
	
	@FindBy(xpath="//*[@id='sort_order_desc_radio']")
	WebElement radiobutton_sortDirectionDescending;
	
	public void clickonDescendingRadiobutton()
	{
		Common.clickOn(driver, radiobutton_sortDirectionDescending);
	}
	
	@FindBy(xpath="//*[@id='search_form_submit_advanced']")
	WebElement button_searchonFilterPopup;
	
	public void clickonSerachbuttononFilter()
	{
		Common.clickOn(driver, button_searchonFilterPopup);
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table/tbody/tr[1]/td[2]//a")));
	}
	
	@FindBy(xpath="//table/tbody/tr[1]/td[2]//a")
	WebElement link_ApplicationSubject;
	
	public void clickonApplicationSubjectLink()
	{
		Common.clickOn(driver, link_ApplicationSubject);
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath("//h2[contains(text(),'"+appdata.getApplicationSubject()+"')]"))));
	}
	
	@FindBy(xpath="//select[@id='parent_type_advanced']")
	WebElement combobox_RelatedTo;

	public void select_RelatedTo()
	{
		Common.pause(3);
		Common.selectFromComboByVisibleText(combobox_RelatedTo, "Application");
	}
	
	@FindBy(xpath="//*[@id='parent_name_advanced']")
	WebElement textbox_RelatedTo;
	
	public void enterApplicationSubjectinRelatedTo(String appSubject)
	{
		System.err.println("Application status: "+appSubject);
		Common.pause(3);
		textbox_RelatedTo.click();
		Common.pause(1);
		Common.type(textbox_RelatedTo, appSubject);
	}
	
	@FindBy (xpath=".//select[@id='status_advanced']")
	WebElement selectStatus;
	
	@FindBy (xpath=".//select[@id='status_advanced']/option[2]")
	WebElement startedSelectOption;
	
	public void selectStatus()
	{
		Select select=new Select(selectStatus);
		
		Actions builder=new Actions(driver);
		builder.keyDown(Keys.CONTROL).click(select.getOptions().get(0)).click(select.getOptions().get(1)).keyUp(Keys.CONTROL);
		builder.build().perform();
		
	}
	
	/**
	 * Method will complete Advance Search form and Filter the Application Task.
	 */
	
	
	public void advanceSearchForApplicationTask()
	{
		Common.log(" -> Click on Task Menu.");
		clickonTasksMenu();
		
		Common.log(" -> Click on Filter Icon.");
		clickonFilterIcon();
		
		Common.log(" -> Click on 'Advanced Filter Option' button");
		clickonAdvancedFilterTab();
		
		Common.log(" -> Click on 'Clear' button to clear existing filtered feeds.");
		clickOnClearButton();
		
		Common.log(" -> Enter 'Related To' option as 'Application'.");
		select_RelatedTo();
		
		Common.log(" -> Enter 'Application Subject' :" +appdata.getApplicationSubject());
		enterApplicationSubjectinRelatedTo(appdata.getApplicationSubject());
		
		Common.log(" -> Select Status : Created,Started");
		selectStatus();
		
		SelectOrderByColumn();
	
		clickonDescendingRadiobutton();
		
		clickonSerachbuttononFilter();
	}

	/**
	 * Method will click on Clear button and which will clear all previous filter feeds.
	 * 
	 */
	
	@FindBy (xpath=".//*[@id='search_form_clear_advanced']")
	WebElement clearButton;
	
	private void clickOnClearButton() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, clearButton);
	}

	/**
	 * Method will click on Action link for Application has Review Application Task. 
	 * @param suject 
	 */
	
	public void clickOnActionlink(String subject) {
		// TODO Auto-generated method stub
		
		
		String start=".//a[contains(text(),'";
		String end="')]//..//..//a[contains(text(),'Action')]";
		WebElement actionLink=driver.findElement(By.xpath(start+subject+end));
		Common.waitForElement(driver, actionLink);

		actionLink.click();
		Common.pause(2);
		
	}
	
	/**
	 * Method will click on Start link to Start Review Application Task. 
	 *
	 */
	
	@FindBy (xpath=".//div[@class='dropdown open']//a[text()='Start']")
	WebElement startLink;
	
	public CRMverification clickOnStart() {
		// TODO Auto-generated method stub
	
		Common.clickOn(driver, startLink);
		Common.pause(2);
		return new CRMverification(driver);
	}

	/**
	 * Method will mouse hover on Completion link and click on Completed option to complete Review Application Errors task. 
	 * 
	 */
	
	@FindBy (xpath=".//a[text()='Completion'][@tabindex='-1']")
	WebElement completionOption;
	
	@FindBy (xpath=".//a[text()='Complete'][@class='actionCompletion']")
	WebElement completeReviewApplication;
	
	public void mouseHoverOnCompletion(String subject) {
		// TODO Auto-generated method stub
		
		Common.SwitchtoTab(driver,1);
		
		driver.navigate().refresh();
		Common.pause(5);
		clickOnActionlink(subject);
		Common.mouseHover(driver, completionOption);
		Common.pause(1);
		Common.clickOn(driver, completeReviewApplication);
		Common.pause(2);
	}

	
	/**
	 * Method will click on Application and navigate to Application Details.
	 * @param applicationSubject
	 */
	
	public void clickOnApplication(String applicationSubject) {
		// TODO Auto-generated method stub
		String start=".//a[contains(text(),'";
		String end="')]";
		driver.navigate().refresh();
		Common.log(start+applicationSubject+end);
		WebElement applicationLink=driver.findElement(By.xpath(start+applicationSubject+end));
		applicationLink.click();	
		Common.pause(5);
		
	}

	/**
	 * Method will click on 'Current Task' sub-panel from the Application Detail Page.
	 */
	
	@FindBy (xpath=".//a[@id='subpanel_title_activities']/div")
	WebElement currentTask;
	
	public void clickOnCurrentTask() {
		// TODO Auto-generated method stub
		Common.pause(5);
		Common.jsClick(driver, currentTask);
	}
	 /**
	  *  WebElement for 'On' textfield to enter Date.
	  */
	 @FindBy(xpath=".//*[@id='datepicker']")
	 WebElement textfield_onDate;
	
	 /**
	  * Method will enter current date in Date field.
	  */
	 
	 public void enteronDate(String fileName)
	 {
	  Common.pause(2);
	  
	  Date date = new Date();
	  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	  String datetext = sdf.format(date);
	  Common.writeDataProperties(fileName,"Assessment_On",datetext,"");
	  Common.type(textfield_onDate, datetext+Keys.ENTER);
	  Common.log(" -> Assessment Date Entered : "+textfield_onDate.getAttribute("value"));

	 }
	 
	 @FindBy(xpath="//input[@value='SiteInspection']")
	 WebElement radiobutton_siteVisit;
	 
	 /**
	  * Method will select 'Site Visit' radio button 
	  */
	 
	 public void clickonsiteRadiobutton()
	 {
	  Common.pause(1);
	  Common.clickOn(driver, radiobutton_siteVisit);
	  Common.log(" -> Selected 'How' Assessment Option : Site Visit");
	 }
	 
	 @FindBy(xpath="//*[@id='other_attending_officers_c']")
	 WebElement textfield_Present;
	 
	 /**
	  * Method will enter 'Present' comment.
	  * 
	  */
	 
	 public void enterPresentcomment(String fileName)
	 {
	  Common.pause(1);
	  String present_Comment = "Present_Comment_"+Common.generateRandomChars(10);
	  
	  Common.writeDataProperties(fileName,"Present_Comment",present_Comment,"");
	  Common.type(textfield_Present, present_Comment);
	  Common.log(" -> Entered Present Comment : "+textfield_Present.getAttribute("value"));
	 }
	 
	 @FindBy(xpath=".//*[@id='summary_of_issues_c']")
	 WebElement textfield_Comments;
	 
	 /**
	  * 
	  * Method will enter comment in Comment textbox.
	  */
	 public void enterInspectionComment(String fileName)
	 {
	  Common.pause(1);
	  String comment = "Comment_"+Common.generateRandomChars(10);
	  Common.writeDataProperties(fileName,"Comment",comment, "");
	  Common.type(textfield_Comments, comment);
	  Common.log(" -> Entered Comment Text: "+textfield_Comments.getAttribute("value"));
	 }
	 
	 @FindBy(xpath=".//*[@id='advice_c']")
	 WebElement textfield_Concerns;
	 
	 /**
	  * @enterConcernsComment Method will enter random text in Concern Comment textbox.
	  */
	 
	 public void enterConcernsComment(String fileName)
	 {
	  Common.pause(1);
	  String concernsComment = "Concerns_Comment_"+Common.generateRandomChars(10);
	  Common.writeDataProperties(fileName,"Concern_Comment", concernsComment,"");
	  
	  Common.writeDataProperties(fileName,"inspectionConcerns", concernsComment, "");
	  Common.type(textfield_Concerns, concernsComment);
	  Common.log(" -> Entered Concern Comment: "+textfield_Concerns.getAttribute("value"));
	 }
	 
	 @FindBy(xpath=".//*[@id='delegate']")
	 WebElement select_Delegate;
	 
	 /**
	  * Method will select 'Delegate Officer' from Delegate officer. 
	  */
	 public void dropdown_selectDelegate()
	 {
	  Common.pause(1);
	  Select select=new Select(select_Delegate);
	  Common.selectFromComboByVisibleText(select_Delegate, "DO Auto");
	  Common.log(" -> Selected Delegate option:" + select.getFirstSelectedOption().getText());
	 }
	 
	 @FindBy(xpath="//input[@value='Approved']")
	 WebElement radiobutton_Approved;
	 
	 /**
	  * Method will select 'APPROVE' from Recommendation options.
	  */
	 
	 public void clickonApprovedRadiobutton()
	 {
	  Common.pause(1);
	  Common.clickOn(driver, radiobutton_Approved);
	  Common.log(" -> Selected Recommendation Option: APPROVE");

	 }
	 
	 @FindBy(xpath=".//*[@id='submitinspection']")
	 WebElement button_saveInspection;
	 
	 /**
	  * Method will click on 'Save Inspection' button.  
	  */
	 
	 public void clickonSavebutton()
	 {
	  Common.pause(1);
	  Common.clickOn(driver, button_saveInspection);
	 }
	 
	 @FindBy(xpath=".//*[@id='submit_recommendation']")
	 WebElement button_clickonSubmitrecommedation;
	
	 /**
	  * Method will click on Submit Recommendation button.
	  */
	
	 public void clickonSubmiteToDelegate()
	 {
	  Common.pause(1);
	  Common.clickOn(driver, button_clickonSubmitrecommedation);
	 }

	 @FindBy (xpath=".//input[@value='Rework']//..//input[@value='Approved']")
	 WebElement delegate_approve;
	 /**
	  * Method will click choose 'Approve' option from Delegate Determination.
	  * @return
	  */
	public void approveFromDelegate() {
		// TODO Auto-generated method stub
		Actions ac=new Actions(driver);
		
		ac.moveToElement(delegate_approve);
		ac.perform();
		Common.jsClick(driver, delegate_approve);
	}

	@FindBy (xpath=".//textarea[@id='delegate_rejection_reason_c']")
	WebElement delegateReasonTextArea;
	 
	 /**
	  * Method will enter random text in Reason textbox of Delegate Detail screen.
	  */
	 
	 public void enterDelegateReason(String fileName)
	 {
	  Common.pause(1);
	  String delegate_reason = "Delegate_Reason_"+Common.generateRandomChars(10);
	  Common.writeDataProperties(fileName,"Delegate_Reason", delegate_reason,"");
	  
	  Common.type(delegateReasonTextArea, delegate_reason);
	  Common.log(" -> Entered Delegate Reason : "+delegateReasonTextArea.getAttribute("value"));
	 }
	 
	 @FindBy (xpath=".//input[@id='verifySignature']")
	 WebElement verifyButton;

	 /**
	  * Method will click on 'VERIFY' button to validate delegate user signed.
	  */
	public void clickOnVerifyButton() {
		// TODO Auto-generated method stub
		Actions ac=new Actions(driver);
		ac.moveToElement(verifyButton);
		ac.perform();
		Common.jsClick(driver, verifyButton);
		Common.pause(1);
	}

	/**
	 * Method will click on 'Delegate Decision' button.
	 */
	
	@FindBy (xpath=".//input[@id='delegate_button']")
	WebElement recordDecisionButton;
	
	public void clickDelegateDecision() {
		// TODO Auto-generated method stub
		Common.jsClick(driver, recordDecisionButton);
	}

	
	/**
	 * Method will click on Business Process icon.
	 * @return CRMverification class which will refresh page factory.
	 */
	
	@FindBy (xpath=".//img[@id='img_bsn_process']")
	WebElement businessProcessButton;
	
	public CRMverification clickOnBusinessProcess() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, businessProcessButton);
		Common.pause(1);
		return new CRMverification(driver);
	}

	/**
	 * Method will choose 'Approved' option from Business Process completion option.
	 */
	
	@FindBy (xpath=".//a[text()='Approved']")
	WebElement approveOption;
	
	public void chooseApproveOption() {
		// TODO Auto-generated method stub

		Common.clickOn(driver, approveOption);
		Common.pause(10);
	}	 
	
	/**
	 * Method will get current numbers of permit records and write it to Property file.
	 */
	
	@FindBy (xpath=".//*[@id='table-container_info']")
	WebElement records;

	public void currentPermitRecords(String fileName) {
		// TODO Auto-generated method stub
		Common.waitForElement(driver, records);
		String[] str=records.getText().split(" ");
		System.err.println(" Number of String lenght :"+str.length);
		System.err.println(" String :"+records.getText());
		System.err.println(" get current permit string : "+str[(str.length)-2]);
		
		Common.writeDataProperties(fileName,"Permit_records", str[(str.length)-2], "");
	}
	
	@FindBy (xpath=".//span[text()='OWLS ADMINISTRATOR']")
	WebElement owlsAdmin;

	public void clickOnAdministrator() {
		// TODO Auto-generated method stub
		Common.pause(5);
		Common.jsClick(driver, owlsAdmin);
	}
	
	@FindBy (xpath=".//a[@id='admin_link']")
	WebElement admin;

	public void clickOnAdmin() {
		// TODO Auto-generated method stub
		Common.pause(2);
		Common.jsClick(driver, admin);
		Common.pause(3);
	}

	@FindBy (xpath=".//a[@id='TestDataCleanup']")
	WebElement cleanUpLink;
	
	public void clickOnTestDataCleanUP() {
		// TODO Auto-generated method stub
	Common.jsClick(driver, cleanUpLink);
		Common.pause(3);
	}
	
	@FindBy (xpath=".//select[@name='id_contacts[]']")
	WebElement selectUser;

	public void selectUser() {
		// TODO Auto-generated method stub
		Common.selectFromComboByVisibleText(selectUser, "Mr. Chirag CHUDASAMA");
		Common.pause(3);
	}

	@FindBy (xpath=".//input[@id='alfresco_saveSettingBtn']")
	WebElement runCleanUpButton;
	
	public void clickRunCleanUp() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, runCleanUpButton);
		Alert al=driver.switchTo().alert();
		al.accept();
		Common.waitForElement(driver, runCleanUpButton);
	}
	
	@FindBy (xpath=".//a[text()='Go to Condition']")
	WebElement goToCondition;

	public void clickGotoCondition() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, goToCondition);
		Common.pause(5);
	}
	
	@FindBy (xpath=".//input[@value='freetext']")
	WebElement freeTextOption;

	public void chooseFreeText() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, freeTextOption);
	}
	
	
	
}
	
