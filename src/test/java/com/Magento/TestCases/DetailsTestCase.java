package com.Magento.TestCases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Magento.ActionDrivers.actionDriver;
import com.Magento.Base.BaseClass;


public class DetailsTestCase extends BaseClass {

	
	
	
	
	@Test
	public void verifyDetails() {
		SoftAssert soft = new SoftAssert();
		actionDriver.click("mobile_xpath");
		String SonyCost = actionDriver.getText("SonyXperiaPrice_xpath");
		System.out.println("Sony Cost " + actionDriver.getText("SonyXperiaPrice_xpath"));
		actionDriver.click("SonyXperia_xpath");

		String DetailPrice = actionDriver.getText("SonyXperiaPrice_xpath");

		System.out.println("Detail Price " + actionDriver.getText("SonyXperiaPrice_xpath"));

		soft.assertEquals(SonyCost, DetailPrice);

		soft.assertAll();

	}
	
	@AfterTest
	public void CloseWebsite()
	{
		 driver.quit();
	}

}
