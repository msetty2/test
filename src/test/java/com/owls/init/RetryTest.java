package com.owls.init;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

public class RetryTest implements IRetryAnalyzer {

	private int retryCounter = 0;
	private int maxRetryCounter = 1;

	public boolean retry(ITestResult result) {
		if (!result.isSuccess()) {

			result.setStatus(ITestResult.FAILURE);
			System.out.println("retryCounter : "+retryCounter+", maxRetryCounter : "+ maxRetryCounter);
			if (retryCounter <= maxRetryCounter) {
				System.out.println("Retrying test Execution: "
						+ result.getName() + " with status "
						+ getResultStatusName(result.getStatus()) + " for the "
						+ (retryCounter + 1) + " time(s).");
				retryCounter++;
				return true;
			} else {
				result.setStatus(ITestResult.FAILURE);
				Reporter.setCurrentTestResult(result);
			}
		}
	
		return false;
	}

	public String getResultStatusName(int status) {
		String resultName = null;
		if (status == 1)
			resultName = "SUCCESS";
		if (status == 2)
			resultName = "FAILURE";
		if (status == 3)
			resultName = "SKIP";
		return resultName;
	}

}
