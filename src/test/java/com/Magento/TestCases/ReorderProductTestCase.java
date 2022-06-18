package com.Magento.TestCases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Magento.ActionDrivers.actionDriver;
import com.Magento.Base.BaseClass;

public class ReorderProductTestCase extends BaseClass {

	@BeforeTest
	public void goToWebsite()
	{
		driver.get(config.getProperty("url"));
	}
	
	@Test(priority = 0)
	public void verifyGrantTotal() {
		SoftAssert soft = new SoftAssert();

		actionDriver.click("account_xpath");
		actionDriver.click("login_xpath");
		actionDriver.type("userName_xpath", "leramotshab@gmail.com");
		actionDriver.type("passCode_id", "Password1");
		actionDriver.click("loginButton_xpath");
		actionDriver.click("myOrder_xpath");

		actionDriver.click("reorder_xpath");
		Double BeforegrandTotal = Double
				.parseDouble(actionDriver.getText("grandTotal_xpath").replace("$", "").replace(",", ""));
		actionDriver.type("quantity_xpath", "3");
		actionDriver.click("update_xpath");

		Double AftergrandTotal = Double
				.parseDouble(actionDriver.getText("grandTotal_xpath").replace("$", "").replace(",", ""));

		soft.assertTrue(AftergrandTotal >= BeforegrandTotal);
		System.out.println("After grand total " + AftergrandTotal + " Before grand Total " +BeforegrandTotal);

		soft.assertAll();
	}

	@Test(priority = 1)
	public void verifyOrderGenerated() {
		SoftAssert soft = new SoftAssert();

		actionDriver.click("proceedToCheckout_xpath");

		if (actionDriver.isElementPresent("billingAddress_xpath")) {

			actionDriver.click("billingSaveContinueButton_xpath");

		} else {
			actionDriver.type("address_id", "ABC");
			actionDriver.type("city_id", "New york");
			actionDriver.select("state_id", "New York");
			actionDriver.type("pCode_id", "123456");
			actionDriver.select("country_id", "United States");
			actionDriver.type("telephone_id", "0846382639");
			actionDriver.click("shippingSaveContinueButton_xpath");
		}

		actionDriver.click("shippingMethodContinueButton_xpath");
		actionDriver.click("checkOrder_xpath");
		actionDriver.click("paymentSaveContinueButton_xpath");
		actionDriver.click("placeOrder_xpath");
		soft.assertTrue(actionDriver.isElementPresent("orderNumber_xpath"));
		System.out.println("Order number " + actionDriver.getText("orderNumber_xpath"));

		soft.assertAll();
	}
	
	@AfterTest
	public void CloseWebsite()
	{
		 driver.quit();
	}

}
