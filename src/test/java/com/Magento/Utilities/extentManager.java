package com.Magento.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentManager {
	
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	
	public static ExtentReports createInstatnce(String fileName)
	{
		htmlReporter = new ExtentSparkReporter(fileName);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setReportName(fileName);
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.setSystemInfo("Tester: ", "Lerato Motshabi");
		extent.setSystemInfo("Enviroment: ", "Windows 10");
		extent.attachReporter(htmlReporter);
		return extent;
	}

}
