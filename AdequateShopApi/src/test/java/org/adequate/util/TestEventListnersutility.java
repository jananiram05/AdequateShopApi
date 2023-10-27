package org.adequate.util;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestEventListnersutility implements ITestListener {

	protected static ExtentReportsUtility extentreport = null;

	public void onTestStart(ITestResult result) {

		extentreport.startSingleTestReport(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentreport.logTestpassed(result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentreport.logTestFailed(result.getMethod().getMethodName());
		

		//String path = ob.getScreenshotOfThePage(driver);
		// extentreport.logTestFailedWithException(result.getThrowable());
		//extentreport.logTestScreenshot(path);

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		extentreport = ExtentReportsUtility.getInstance();
		extentreport.startExtentReport();

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentreport.endReport();
	}

}
