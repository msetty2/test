package com.owls.crm.verification;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.owls.init.AbstractPage;
import com.owls.init.Common;


public class CRMverification extends AbstractPage  {


	public CRMverification(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Method will verify validation error contents 'Documents, Wildlife in possession and convictions'
	 * @return true/false
	 */
	
	@FindBy (xpath=".//span[@id='validation_errors_c']")
	WebElement validationError;

	public boolean verifyValidationError() {
		// TODO Auto-generated method stub
		Common.SwitchtoTab(driver, 2);
		
		return validationError.getText().contains("Documents") || validationError.getText().contains("Wildlife in possession") || validationError.getText().contains("conviction");
		
	}
	/**
	 * Method will verify Current Task for selected Application which will Assert 'Interview / Site Inspection' task available.
	 * @return
	 */
	
	@FindBy (xpath=".//a[contains(text(),'Interview/Site Inspection")
	WebElement interviewTask;
	
	public boolean verifyCurrentTask(String string) {
		// TODO Auto-generated method stub
		String start=".//a[contains(text(),'";
		String end="')]";
		WebElement currentTask=driver.findElement(By.xpath(start+string+end));
		return Common.isElementDisplayed(currentTask);
	}

	@FindBy(xpath = "//div[@id='delegate-screen-panel-body']")
	WebElement expandedinspectionTab;

	/**
	 * Method will verify the Inspection screen is displayed and expanded to the Inspection CRM user.
	 * @return
	 */
	public boolean verifyInspectingOfficerAssessmentTabExpanded() {
		
		Common.waitForElement(driver, expandedinspectionTab);
		Common.pause(5);
		
		return Common.isElementDisplayed(expandedinspectionTab);
 }
	
	@FindBy(xpath=".//*[@id='MassUpdate']/div[3]/table/tbody/tr[1]/td[5]//a")
	WebElement applicationstatus_first;
	
	@FindBy(xpath=".//*[@id='MassUpdate']/div[3]/table/tbody/tr[1]/td[7]//a")
	WebElement applicationRelatedTo;
	
	@FindBy(xpath=".//*[@id='MassUpdate']/div[3]/table/tbody/tr[1]/td[10]")
	WebElement queueName;


	/**
	 * Method will verify whether Approval by delegate task is available.
	 * @param string:"Approval By Delegate" task.
	 * @param applicationSubject
	 * @param Queue name
	 * @return
	 */
	
	public boolean VerifycurrentTaskAndQueue(String applicationStatustoMatch, String ApplicationSubject,
			String applicationQueue) {

		Common.waitForElement(driver, applicationstatus_first);

		String applicationStatus = applicationstatus_first.getText();
		String applicationName = applicationRelatedTo.getText();
		String queue = queueName.getText();

		System.err.println(applicationStatus + " " + "Approval By Delegate");
		System.err.println(applicationName + "=====" + ApplicationSubject);
		System.err.println(queue + "===" + "Wildlife Licensing Queue");
		
		return applicationStatus.contains(applicationStatustoMatch)
				&& applicationName.toLowerCase().contains(ApplicationSubject.toLowerCase())
				&& queue.contains(applicationQueue);

	}


	/**
	 * Method will verify Comment provided by Inspection is displayed correctly to delegate user.
	 * @return boolean
	 */
	
	public boolean verifyInspectionComment(String fileName) {
		// TODO Auto-generated method stub
		String start=".//table[@id='inspecting_officer_screen']//td[contains(text(),'";
		String end="')]";
		String comment=Common.readDataProperties(fileName,"Comment");
		String concern=Common.readDataProperties(fileName,"Concern_Comment");
		System.err.println(Common.readDataProperties(fileName,"Comment")+"\n ========== "+start+comment+end);
		System.err.println(Common.readDataProperties(fileName,"Concerns")+"\n ========== "+start+concern+end);

		WebElement commentText=driver.findElement(By.xpath(start+comment+end));
		WebElement concernsText=driver.findElement(By.xpath(start+concern+end));

		return Common.isElementDisplayed(commentText) && Common.isElementDisplayed(concernsText) ;

	}

	@FindBy(xpath=".//span[@id='everify']//font[contains(text(),'Valid')]")
	WebElement validationMessage;
	
	/**
	 * Method will verify 'Delegate' signature is correctly verified and Delegate Decision is enable to click.
	 * @return boolean
	 */
	public boolean verifyDelegateSign() {
		// TODO Auto-generated method stub
		Actions ac=new Actions(driver);
		ac.moveToElement(validationMessage);
		ac.perform();
		return Common.isElementDisplayed(validationMessage);
	}


	/**
	 * Method will get the current number of permit records from property file and compare it with current permit records.
	 * @return boolean
	 */
	
	@FindBy (xpath=".//*[@id='table-container_info']")
	WebElement records;
	
	public boolean verifyNumberOfPermitRecordss(String fileName) {
		// TODO Auto-generated method stub
		Common.waitForElement(driver, records);
		String[] str=records.getText().split(" ");
		
		System.err.println(" String "+str[(str.length)-2]);
		System.err.println(" Permit property : "+Common.readDataProperties(fileName,"Permit_records"));
		return str[(str.length)-2].equalsIgnoreCase(Common.readDataProperties(fileName,"Permit_records"));
	}

		
}
	
