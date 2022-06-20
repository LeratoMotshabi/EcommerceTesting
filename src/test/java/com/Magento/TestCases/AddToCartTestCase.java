package com.Magento.TestCases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Magento.ActionDrivers.actionDriver;
import com.Magento.Base.BaseClass;


public class AddToCartTestCase extends BaseClass {
	
	
	
	@Test(priority=0)
	public void verfyMaximumQuantityError()
	{
		
		SoftAssert soft = new SoftAssert();
		
		actionDriver.click("mobile_xpath");
		actionDriver.click("SonyXperiaAddCart_xpath");
		actionDriver.type("quantity_xpath", "501");
		actionDriver.click("update_xpath");				
		soft.assertTrue(actionDriver.isElementPresent("quantityError_css"), "\"The maximum quantity allowed for purchase is 500\" IS NOT SHOWN");
						
		soft.assertAll();
		
	}
	
	
	@Test(priority=1)
	public void verfyCartIsEmpty()
	{
		
		SoftAssert soft = new SoftAssert();		
		
		actionDriver.click("emptyCart_xpath");
		soft.assertTrue(actionDriver.isElementPresent("cartIsEmpty_xpath"),"\"SHOPPING CART IS EMPTY\" is not shown");
		
		soft.assertAll();
		
	}
	
	@AfterTest
	public void CloseWebsite()
	{
		 driver.quit();
	}

	
}
