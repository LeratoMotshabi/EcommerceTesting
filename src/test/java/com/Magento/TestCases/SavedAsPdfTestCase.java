package com.Magento.TestCases;

import java.awt.print.PrinterJob;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Magento.ActionDrivers.actionDriver;
import com.Magento.Base.BaseClass;

public class SavedAsPdfTestCase extends BaseClass {
	
	
	
	@Test(priority=0)
	public void verifyOrderIsDisplayed()
	{
		
		SoftAssert soft = new SoftAssert();

		actionDriver.click("account_xpath");
		actionDriver.click("login_xpath");
		actionDriver.type("userName_xpath", "leramotshab@gmail.com");
		actionDriver.type("passCode_id", "Password1");
		actionDriver.click("loginButton_xpath");
		actionDriver.click("myOrder_xpath");
		
		if(actionDriver.isElementPresent("orderTable_xpath"))
		{
			actionDriver.getText("orderNumber_xpath");
			soft.assertEquals(actionDriver.getText("pendingStatus_xpath"), "Pending");
		
		}
		
		soft.assertAll();
		
	}
	
	@Test(priority=1)
	public void verifySavedAsPDF()
	{
		
		SoftAssert soft = new SoftAssert();
		PrinterJob pj = PrinterJob.getPrinterJob();
		
		
		if(actionDriver.isElementPresent("orderTable_xpath"))
		{						
			actionDriver.click("viewOrder_xpath");
			actionDriver.click("printOrder_xpath");		
			pj.cancel();
			
			
			//actionDriver.click("printer_xpath");			//actionDriver.select("saveAs_xpath", "Save as PDF");
			//actionDriver.click("saveButton_xpath");
		}
		
		soft.assertAll();
		
	}
	
	@AfterTest
	public void CloseWebsite()
	{
		 driver.close();
	}

}
