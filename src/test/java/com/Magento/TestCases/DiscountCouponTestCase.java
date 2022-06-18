package com.Magento.TestCases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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
		Double expectedDiscount = -25.00;
		
		soft.assertEquals(expectedDiscount, discount);
		System.out.println("expected " + expectedDiscount + " found " + discount );
		
		soft.assertAll();
	}
	
	@AfterTest
	public void CloseWebsite()
	{
		 driver.quit();
	}

}
