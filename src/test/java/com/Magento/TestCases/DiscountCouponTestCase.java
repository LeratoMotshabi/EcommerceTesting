package com.Magento.TestCases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Magento.ActionDrivers.actionDriver;
import com.Magento.Base.BaseClass;

public class DiscountCouponTestCase extends BaseClass{
	
	
	
	@Test
	public void verifyPriceDiscounted()
	{
		SoftAssert soft = new SoftAssert();
		
		actionDriver.click("mobile_xpath");
		actionDriver.click("iPhoneAddCart_xpath");
		actionDriver.type("discountCode_xpath", "GURU50");
		actionDriver.click("applyButton_xpath");
		Double discount = Double.parseDouble(actionDriver.getText("discount_xpath").replace("$", ""));
		
		
		soft.assertTrue(actionDriver.isElementPresent("discount_xpath"));
		System.out.println("Discounted by " + discount );
		
		soft.assertAll();
	}
	
	@AfterTest
	public void CloseWebsite()
	{
		 driver.quit();
	}

}
