package com.Magento.TestCases;

import java.util.Set;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Magento.ActionDrivers.actionDriver;
import com.Magento.Base.BaseClass;

public class CompareTestCase extends BaseClass {
	
	@BeforeTest
	public void goToWebsite()
	{
		
		 driver.get(config.getProperty("url"));
	}
	
	@Test
	public void verifyCompareProducts ()
	{
		
		SoftAssert soft = new SoftAssert();
		
		actionDriver.click("mobile_xpath");
		actionDriver.click("SonyXperiaAddToCompare_xpath");
		actionDriver.click("iPhoneAddToCompare_xpath");
		
		String iPhone = actionDriver.getText("iPhone_xpath");
		String SonyXperia = actionDriver.getText("SonyXperia_xpath");
		
		actionDriver.click("compareBtn_xpath");
		
		
		
		//Coder for parent window
		String  parentWindow = driver.getWindowHandle();
		
		//code to handle all new windows opened
		Set<String> childWindows = driver.getWindowHandles();
		
		for(String childWindow:childWindows)
		{
			if(!parentWindow.equalsIgnoreCase(childWindow))
			{
				driver.switchTo().window(childWindow);
				
				System.out.println(actionDriver.getText("compareProductsTitle_xpath"));
				
				
			}
		}
		
		soft.assertEquals(iPhone, actionDriver.getText("iPhoneCompareWindow_xpath"));
		soft.assertEquals(SonyXperia, actionDriver.getText("SonyCompareWindow_xpath"));
		actionDriver.click("closeWindow_xpath");
		driver.switchTo().window(parentWindow);
		
		
		soft.assertAll();
	}
	
	@AfterTest
	public void CloseWebsite()
	{
		 driver.quit();
	}

}
