package com.Magento.Listeners;

import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Magento.ActionDrivers.actionDriver;
import com.Magento.Utilities.extentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class listenersManager implements ITestListener {
	
	public static Date date = new Date();
	static String file = date.toString().replace(":", "_").toString().replace(" ", "_");
	public static String fileName =System.getProperty("user.dir") + "/src/test/resources/extentReports/" + " Extent.html";
	public static ExtentReports extent = extentManager.createInstatnce(fileName);
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static String scrPath = System.getProperty("user.dir") + "/src/test/resources/Screenshots/" ;

	public void onTestStart(ITestResult result) {

		ExtentTest testReport = extent
				.createTest(result.getClass() + " @TEST CASE " + result.getMethod().getMethodName());
		test.set(testReport);

	}

	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String text = methodName + ": PASSED";
		Markup m = MarkupHelper.createLabel(text, ExtentColor.GREEN);
		test.get().pass(m);

	}

	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName() +": FAILED";
		String detatils = result.getThrowable().getMessage();
		String message = "<details><summary><font color=red>" + "Exception occured: Click to see more"
				+ "</font></summary> <p>" + detatils.replaceAll(",", "<br>") + "</p></details>";

		Markup m = MarkupHelper.createLabel(methodName, ExtentColor.RED);
		test.get().fail(m);
		test.get().fail(message);		
		String title = "<a href="+actionDriver.screenshot(scrPath +  file + result.getMethod().getMethodName() + ".png")+" data-featherlight=\"image\"><summary>"+ "Screenshot captured: click to view" + "</summary></a>";
		test.get().log(Status.FAIL,MediaEntityBuilder.createScreenCaptureFromBase64String(actionDriver.screenshot(scrPath +  file + result.getMethod().getMethodName() + ".png"),title).build());
		System.out.println(actionDriver.screenshot(scrPath +  file + result.getMethod().getMethodName() + ".png"));

	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

		
		extent.flush();
	}
	
	

}
