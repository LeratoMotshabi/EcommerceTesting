package com.Magento.TestCases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Magento.ActionDrivers.actionDriver;
import com.Magento.Base.BaseClass;

public class PurchasedProductTestCase extends BaseClass {

	public Double shippingFee;
	public Double subTotlaAmount;
	public Double Total;
	public Double grandTotal;

	
	
	@Test(priority = 0)
	public void verifyShippingFeeGenerated() {

		SoftAssert soft = new SoftAssert();

		actionDriver.click("account_xpath");
		actionDriver.click("login_xpath");
		actionDriver.type("userName_xpath", "leramotshab@gmail.com");
		actionDriver.type("passCode_id", "Password1");
		actionDriver.click("loginButton_xpath");
		actionDriver.click("myWishList_xpath");

		if (actionDriver.isElementPresent("noIteminYourWushlist_xpath")) {
			actionDriver.click("TV_xpath");
			actionDriver.click("LGaddToWishlist_xpath");

		}

		actionDriver.click("addToCart_xpath");
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

		soft.assertTrue(actionDriver.isElementPresent("shippingFee_xpath"));
		actionDriver.getText("shippingFee_xpath");

		soft.assertAll();
	}

	@Test(priority = 1)
	public void verifyShippingCostAdded() {

		SoftAssert soft = new SoftAssert();

		shippingFee = Double.parseDouble(actionDriver.getText("shippingFee_xpath").replace("$", "").replace(",", ""));
		System.out
				.println("shipping fee " + actionDriver.getText("shippingFee_xpath").replace("$", "").replace(",", ""));
		actionDriver.click("shippingMethodContinueButton_xpath");
		actionDriver.click("checkOrder_xpath");
		actionDriver.click("paymentSaveContinueButton_xpath");
		subTotlaAmount = Double.parseDouble(actionDriver.getText("subTotal_xpath").replace("$", "").replace(",", ""));
		System.out.println("subtotal  " + actionDriver.getText("subTotal_xpath").replace("$", "").replace(",", ""));
		grandTotal = Double.parseDouble(actionDriver.getText("total_xpath").replace("$", "").replace(",", ""));
		Total = subTotlaAmount + shippingFee;

		System.out.println("grandtotal  " + actionDriver.getText("total_xpath").replace("$", "").replace(",", ""));
		System.out.println("total fee " + Total);

		soft.assertEquals(Total, grandTotal);

		soft.assertAll();
	}

	//@Test(priority = 2)
	public void verifyOrderNumberGenerated() {
		SoftAssert soft = new SoftAssert();
		
		actionDriver.click("placeOrder_xpath");
		soft.assertTrue(actionDriver.isElementPresent("orderNumber_xpath"));
		System.out.println("Order number " +actionDriver.getText("orderNumber_xpath"));

		soft.assertAll();
	}
	
	@AfterTest
	public void CloseWebsite()
	{
		 driver.quit();
	}

}
