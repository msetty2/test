package com.owls.init;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.internal.Utils;

import com.owls.CreateApp.indexpage.CreateApplicationIndexPage;
import com.owls.CreateApp.verification.CreateApplicationVerificationPage;
import com.owls.corporate.indexpage.CorporateApplicationIndexPage;
import com.owls.corporate.verification.CorporateApplicationVerificationPage;
import com.owls.crm.indexpage.CRMIndexpage;
import com.owls.crm.verification.CRMverification;



public class SeleniumInit {



	public String suiteName = "";
	public String testName = "";
	
	public String dataFileName="";
	public String configFileName="";
	public String applicationType="";
	public String queueName="";
	
	/* Minimum requirement for test configur ation */
	protected String testUrl; // Test url
	public static String seleniumHub; // Selenium hub IP
	public static String seleniumHubPort; // Selenium hub port
	public static String targetBrowser; // Target browser
	protected static String test_data_folder_path = null;
	public static String currentWindowHandle = "";// Get Current Window handle
	public static String browserName = "";
	public static String osName = "";
	public static String browserVersion = "";

	//public LoginIndexPage loginIndexpage;
	//public LoginVerificationPage loginVerificationPage;
	public CreateApplicationIndexPage applicationIndexPage;
	public CreateApplicationVerificationPage applicationVerificatioPage;
	
	public CorporateApplicationIndexPage corporateapplicationIndexPage;
	public CorporateApplicationVerificationPage corporateapplicationVerificatioPage;

	
	 public CRMIndexpage crmpage;
	 public CRMverification crmverify;
	
	public ApplicationData appdata=new ApplicationData();
	//===============================================================CC
	
	
//	public ContactDetailsVerification contactDetailsVerification;
//	public ContactDetailsIndexpage contactDetailsIndexpage;
 
	
	 
	protected static String screenshot_folder_path = null;
	public static String currentTest; // current running test

	protected static Logger logger = Logger.getLogger("testing");
	protected WebDriver driver;

	// Common Common = new Common(driver);

	/* Page's declaration */

	/**
	 * Fetches suite-configuration from XML suite file.
	 * 
	 * @param testContext
	 */

	@BeforeTest(alwaysRun = true)
	public void fetchSuiteConfiguration(ITestContext testContext) {
		testUrl = testContext.getCurrentXmlTest().getParameter("selenium.url");
		/* System.out.println("======" + testUrl + "========="); */
		seleniumHub = testContext.getCurrentXmlTest().getParameter(
				"selenium.host");
		seleniumHubPort = testContext.getCurrentXmlTest().getParameter(
				"selenium.port");
		targetBrowser = testContext.getCurrentXmlTest().getParameter(
				"selenium.browser");
		dataFileName=testContext.getCurrentXmlTest().getParameter("dataFileName");
		System.err.println(" Data File Name : "+dataFileName);
		configFileName=testContext.getCurrentXmlTest().getParameter("configFileName");
		System.err.println(" Config File Name : "+configFileName);
		applicationType=testContext.getCurrentXmlTest().getParameter("ApplicationType");
		System.err.println(" Application Type : "+applicationType);
		queueName=testContext.getCurrentXmlTest().getParameter("QueueName");
		System.err.println(" Queue Name :" + queueName);
		
	}

	/**
	 * WebDriver initialization
	 * 
	 * @return WebDriver object
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method, ITestContext testContext)
			throws IOException, InterruptedException {

		/*
		 * Runtime runtime = Runtime.getRuntime(); runtime.exec(
		 * "java -jar D:\\NFHS_JARS\\selenium-server-standalone-2.46.0.jar -role hub"
		 * );
		 * System.out.println("==================Server started================="
		 * ); Thread.sleep(2000);
		 * 
		 * 
		 * Runtime runtime2 = Runtime.getRuntime(); runtime2.exec(
		 * "java -jar D:\\NFHS_JARS\\selenium-server-standalone-2.46.0.jar -role node -port 5554"
		 * ); System.out.println(
		 * "=======================Node started====================");
		 * Thread.sleep(2000);
		 */
		String path = "";
		if (System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
			//path = "/Users/Jignesh/developer/test-automation";
		} else {
			//path = "c:\\Downloads_new";
		}
		File theDir = new File(path);
		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("creating directory: ");
			boolean result = false;

			try {
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				System.out.println("DIR created");
			}
		}

		currentTest = method.getName(); // get Name of current test.
		URL remote_grid = new URL("http://" + seleniumHub + ":"
				+ seleniumHubPort + "/wd/hub");

		String SCREENSHOT_FOLDER_NAME = "screenshots";
		String TESTDATA_FOLDER_NAME = "test_data";

		test_data_folder_path = new File(TESTDATA_FOLDER_NAME)
				.getAbsolutePath();
		screenshot_folder_path = new File(SCREENSHOT_FOLDER_NAME)
				.getAbsolutePath();

		DesiredCapabilities capability = null;
		if (targetBrowser == null || targetBrowser.contains("firefox")) {
			FirefoxProfile profile = new FirefoxProfile();
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Kiwiqa-Chirag\\geckodriver.exe");
			if (System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
				path = "/Users/Jignesh/developer/test-automation";
			} else {
				path = "c:\\Downloads_new";
			}

			profile.setPreference("dom.max_chrome_script_run_time", "999");
			profile.setPreference("dom.max_script_run_time", "999");
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.dir", path);
			profile.setPreference(
					"browser.helperApps.neverAsk.openFile",
					"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
			profile.setPreference(
					"browser.helperApps.neverAsk.saveToDisk",
					"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
			profile.setPreference("browser.download.manager.showWhenStarting",
					false);
			profile.setPreference("browser.download,manager.focusWhenStarting",
					false);
			// profile.setPreference("browser.download.useDownloadDir",true);
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			profile.setPreference("browser.download.manager.alertOnEXEOpen",
					false);
			profile.setPreference("browser.download.manager.closeWhenDone",
					false);
			profile.setPreference(
					"browser.download.manager.showAlertOnComplete", false);
			profile.setPreference("browser.download.manager.useWindow", false);
			profile.setPreference("browser.download.manager.showWhenStarting",
					false);

			profile.setPreference(
					"services.sync.prefs.sync.browser.download.manager.showWhenStarting",
					false);
			profile.setPreference("pdfjs.disabled", true);
			profile.setAcceptUntrustedCertificates(true);
			profile.setPreference("security.OCSP.enabled", 0);
			profile.setEnableNativeEvents(false);
			profile.setPreference("network.http.use-cache", false);

			// added Dependancy to disable hardware acceleration.

			/*
			 * profile.setPreference("gfx.direct2d.disabled",true);
			 * profile.setPreference("layers.acceleration.disabled", true);
			 */

			profile.setPreference("gfx.direct2d.disabled", true);
			profile.setPreference("layers.acceleration.disabled", true);
			// profile.setPreference("webgl.force-enabled", true);
			// Proxy proxy = new Proxy().setHttpProxy("localhost:3129");

			// cap.setCapability(CapabilityType.PROXY, proxy);

			capability = DesiredCapabilities.firefox();
			// proxy code
			// capability.setCapability(CapabilityType.PROXY,proxy);
			capability.setJavascriptEnabled(true);
			capability.setCapability(FirefoxDriver.PROFILE, profile);
			browserName = capability.getBrowserName();
			osName = System.getProperty("os.name");
			browserVersion = capability.getVersion().toString();

			System.out.println("=========" + "firefox Driver " + "==========");
			driver = new RemoteWebDriver(remote_grid, capability);

		} else if (targetBrowser.contains("ie8")) {

			capability = DesiredCapabilities.internetExplorer();
			capability.setPlatform(Platform.ANY);
			capability.setBrowserName("internet explorer");
			// capability.setVersion("8.0");
			capability
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			capability.setCapability(
					CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
					true);
			capability.setJavascriptEnabled(true);
			browserName = capability.getBrowserName();
			osName = capability.getPlatform().name();
			browserVersion = capability.getVersion();
		} else if (targetBrowser.contains("ie9")) {
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("internet explorer");
			capability
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			capability.setCapability(
					CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
					true);
			capability.setJavascriptEnabled(true);
			browserName = capability.getBrowserName();
			osName = capability.getPlatform().name();
			browserVersion = capability.getVersion();
		} else if (targetBrowser.contains("ie11")) {
			capability = DesiredCapabilities.internetExplorer();
			System.setProperty("webdriver.ie.driver",
					"D:/Automation Driver/IEDriverServer_x64_2.48.0/IEDriverServer.exe");

			capability.setBrowserName("internet explorer");
			capability
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			capability.setCapability(
					CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
					true);
			capability.setJavascriptEnabled(true);
			browserName = capability.getVersion();
			osName = capability.getPlatform().getCurrent().name();
			browserVersion = capability.getVersion();

			driver = new RemoteWebDriver(remote_grid, capability);

		} /*else if (targetBrowser.contains("opera")) {
			capability = DesiredCapabilities.opera();
			System.setProperty("webdriver.opera.driver",
					"C:/Users/KQSPL_R/Desktop/Automation Driver/operadriver_win32/operadriver.exe");

			capability.setJavascriptEnabled(true);
			browserName = capability.getVersion();
			osName = capability.getPlatform().getCurrent().name();
			browserVersion = capability.getVersion();

			driver = new OperaDriver(capability);

		} */else if (targetBrowser.contains("chrome")) {

			ChromeOptions options = new  ChromeOptions();
			String downloadFilepath = new File("Resource/Downloads").getAbsolutePath();
			   HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			   chromePrefs.put("profile.default_content_settings.popups", 0);
			   chromePrefs.put("download.default_directory", downloadFilepath);
			   options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("no-sandbox");
			capability = DesiredCapabilities.chrome();
			capability.setCapability(ChromeOptions.CAPABILITY, options);
			File ChromeDriver = new File("Resource\\chromedriver.exe");
			System.err.println(ChromeDriver.getAbsolutePath());
			System.setProperty("webdriver.chrome.driver","‪Resource\\chromedriver.exe");
			capability.setBrowserName("chrome");
			capability.setJavascriptEnabled(true);
			browserName = capability.getVersion();
			osName = capability.getPlatform().name();
			browserVersion = capability.getVersion();
			driver = new RemoteWebDriver(remote_grid, capability);

		} else if (targetBrowser.contains("safari")) {

			// System.setProperty("webdriver.safari.driver","/Users/jesus/Desktop/SafariDriver.safariextz");
			// driver = new SafariDriver();
			SafariDriver profile = new SafariDriver();

			capability = DesiredCapabilities.safari();
			capability.setJavascriptEnabled(true);
			capability.setBrowserName("safari");
			browserName = capability.getBrowserName();
			osName = capability.getPlatform().name();
			browserVersion = capability.getVersion();
			// capability.setCapability(SafariDriver.CLEAN_SESSION_CAPABILITY,
			// profile);
			this.driver = new SafariDriver(capability);
		}
		suiteName = testContext.getSuite().getName();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		testUrl = TestData.getAdminURL();
		driver.get(testUrl);
		driver.manage().window().maximize();
		currentWindowHandle = driver.getWindowHandle();
		System.out.println("Current Window Handle ID:--->"
				+ currentWindowHandle);

		suiteName = testContext.getSuite().getName();
		System.out.println("Current Xml Suite is:---->" + suiteName);

		
		
		applicationIndexPage = new CreateApplicationIndexPage(driver);
		applicationVerificatioPage = new CreateApplicationVerificationPage(driver);
		
		corporateapplicationIndexPage=new CorporateApplicationIndexPage(driver);
		corporateapplicationVerificatioPage=new CorporateApplicationVerificationPage(driver);

		
		  crmpage = new CRMIndexpage(driver);
		  crmverify = new CRMverification(driver);
	}

		  
	

	/**
	 * Log For Failure Test Exception.
	 * 
	 * @param tests
	 */
	private void getShortException(IResultMap tests) {

		for (ITestResult result : tests.getAllResults()) {

			Throwable exception = result.getThrowable();
			List<String> msgs = Reporter.getOutput(result);
			boolean hasReporterOutput = msgs.size() > 0;
			boolean hasThrowable = exception != null;
			if (hasThrowable) {
				boolean wantsMinimalOutput = result.getStatus() == ITestResult.SUCCESS;
				if (hasReporterOutput) {
					log("<h3>"
							+ (wantsMinimalOutput ? "Expected Exception"
									: "Failure Reason:") + "</h3>");
				}

				// Getting first line of the stack trace
				String str = Utils.stackTrace(exception, true)[0];
				Scanner scanner = new Scanner(str);
				String firstLine = scanner.nextLine();
				log(firstLine);
				scanner.close();
			}
		}
	}
	
	public static String readProperties(String propertieName)
	 {
	    String result="";
	    File file = new File("Data/config.properties");
	    FileInputStream fileInput = null;
	    try {
	     fileInput = new FileInputStream(file);
	    } catch (FileNotFoundException e) {
	     e.printStackTrace();
	    }
	    Properties prop = new Properties();
	    try {
	      prop.load(fileInput);
	      result = prop.getProperty(propertieName);
	      System.out.println(result);
	    } catch (Exception e) {
	     System.out.println("Exception: " + e);
	    } finally {
	    }
	    
	    return result;
	 }
	 
	 public static void writeProperties(String propertieName,String propertieValue,String Comment)
	 {
	   Properties propwrite = new Properties();
	    
	      try {
	       //set the properties value
	       propwrite.setProperty(propertieName, propertieValue);
	  
	       //save properties to project root folder
	       propwrite.store(new FileOutputStream("Data/config.properties",true), Comment);
	  
	      } catch (IOException ex) {
	       ex.printStackTrace();
	         }
	 }

	/**
	 * After Method
	 * 
	 * @param testResult
	 */

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult testResult) {

		ITestContext ex = testResult.getTestContext();

		try {
			testName = testResult.getName();
			System.err.println("----------->         " +testResult.getTestName() );
			if (!testResult.isSuccess()) {

				/* Print test result to Jenkins Console */
				System.out.println();
				System.out.println("======================++++   >>  ");
				System.out.println("TEST FAILED - " + testName);
				System.out.println();
				System.out.println("ERROR MESSAGE: "
						+ testResult.getThrowable());
				System.out.println("\n");
				Reporter.setCurrentTestResult(testResult);

				/* Make a screenshot for test that failed */
				String screenshotName = Common.getCurrentTimeStampString()
						+ testName;
				Reporter.log("<br> <b>Please look to the screenshot - </b>");
				Common.makeScreenshot(driver, screenshotName);
				// Reporter.log(testResult.getThrowable().getMessage());
				getShortException(ex.getFailedTests());
				if(testName.contains("verifyCreateApplication"))
			    {  Properties propwrite = new Properties();
			     propwrite.setProperty("ApplicationName", "False");
			     propwrite.store(new FileOutputStream("Data/"+configFileName+".properties", false), "");
			     
			     Properties propwrites = new Properties();
			     propwrites.setProperty("Primary_Address", "nan");
			     propwrites.store(new FileOutputStream("Data/"+dataFileName+".properties", false), "");
			 }
				
			/*xxxxx*/
				
			/*	if(testName.contains("verifyCreateSpecimenApplication()"))
			    {  Properties propwrite = new Properties();
			     propwrite.setProperty("Application_Created_Sucessfully", "False");

			     propwrite.store(new FileOutputStream("Data/specimenApplicationIndexSuite.properties", false), "");
			     
			     Properties propwrites = new Properties();
			     propwrites.setProperty("ApplicationName", "nan");
			     propwrites.store(new FileOutputStream("Data/specimenApplicationData.properties", false), "");
			    }
				
				if(testName.contains("verifyCreateAdvancedApplication()"))
			    {  Properties propwrite = new Properties();
			     propwrite.setProperty("Application_Created_Sucessfully", "False");

			     propwrite.store(new FileOutputStream("Data/advancedApplicationIndexSuite.properties", false), "");
			     
			     Properties propwrites = new Properties();
			     propwrites.setProperty("ApplicationName", "nan");
			     propwrites.store(new FileOutputStream("Data/advancedApplicationData.properties", false), "");
			    }*/
			} else {
				try {
					Common.pause(5);
					/*
					 * driver.findElement(
					 * By.xpath("//div[@class='container']//a[contains(.,'Logout')]"
					 * )) .click();
					 */
					Common.pause(5);
				} catch (Exception e) {
					log("<br></br> Not able to perform logout");
				}

				System.out.println("TEST PASSED - " + testName + "\n"); // Print
																		// test
																		// resule
																		// to
																		// Jenkins
																		// Console
			}

			/*
			 * final File folder = new File("C:/Downloads_new"); File files[] =
			 * folder.listFiles();
			 * 
			 * if (files.length > 0) { for (File f : files) { if (f.delete()) {
			 * System.out .println("file deleted From Downloads_new folder"); }
			 * }
			 * 
			 * }
			 */
		
			System.out.println("here is test status--------------------"
					+ testResult.getStatus());

			driver.manage().deleteAllCookies();
			
			
			driver.close();
			driver.quit();

		} catch (Throwable throwable) {
			System.out.println("message from tear down"
					+ throwable.getMessage());
		}
	}
	
	public static void logStatus(final int test_status) {

		switch (test_status) {

		case ITestStatus.PASSED:
			log("<font color=#009900><b>--Passed</b></font></br></br>");
			break;

		case ITestStatus.FAILED:
			log("<font color=#FF0000><b>--Failed</b></font></br></br>");
			break;

		case ITestStatus.SKIPPED:
			log("<font color=#FFFF00><b>--Skipped</b></font></br></br>");
			break;

		default:
			break;
		}

	}

	/**
	 * Log given message to Reporter output.
	 * 
	 * @param msg
	 *            Message/Log to be reported.
	 */
	public static void log(String msg) {
		System.out.println(msg);
		Reporter.log("<br></br>" + msg);
	}

}
