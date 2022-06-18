package com.Magento.TestCases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Magento.ActionDrivers.actionDriver;
import com.Magento.Base.BaseClass;
import com.Magento.Utilities.dataProvider;

public class CreateAccountTestCase extends BaseClass {

	public String email;

	
	
	@Test(priority = 0, dataProvider = "Data", dataProviderClass = dataProvider.class)
	public void verifyAccountRegistration(String name, String middleName, String lastName, String emailAddress,
			String password, String confirmPassword) {

		SoftAssert soft = new SoftAssert();

		actionDriver.click("account_xpath");
		actionDriver.click("myAccount_xpath");
		actionDriver.click("createdAccount_xpath");

		actionDriver.type("firstName_id", name);
		actionDriver.type("middleName_id", middleName);
		actionDriver.type("lasName_id", lastName);
		actionDriver.ramdomString();
		actionDriver.type("emailAddress_id", actionDriver.ramdomString()+"@gmail.com");
		email = actionDriver.getAttribute("emailAddress_id", "value");

		actionDriver.type("password_id", password);
		actionDriver.type("confirmPassword_id", confirmPassword);

		actionDriver.click("registerButton_xpath");

		if (actionDriver.regularExpressions("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$", email)
				.find() == true) {

			soft.assertTrue(actionDriver.isElementPresent("registeredSuccefully_xpath"),
					"email format is incorrect but registration was successfully");
			System.out.println("CORRECT!!! " + email);

		} else if (actionDriver.regularExpressions("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$", email)
				.find() != true || email.isBlank()) {
			soft.assertFalse(actionDriver.isElementPresent("registeredSuccefully_xpath"),
					"email format is incorrect but registration was successfully");
			System.out.println("INCORRECT!!! " + email);
		}

		soft.assertAll();
	}

	@Test(priority = 1)
	public void verifyWhishlistSharedSuccefully() {
		SoftAssert soft = new SoftAssert();
		actionDriver.click("TV_xpath");
		actionDriver.click("LGaddToWishlist_xpath");
		actionDriver.click("shareWishlist_xpath");
		actionDriver.type("emailAddress_id", "LeratoMotshabi@gmail.com");
		String email2 = actionDriver.getAttribute("emailAddress_id", "value");

		actionDriver.type("message_xpath", "this is the message");
		actionDriver.click("shareWishlist_xpath");

		if (actionDriver.regularExpressions("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$", email2)
				.find() == true) {

			soft.assertTrue(actionDriver.isElementPresent("sharedSuccefully_xpath"),
					"email format is incorrect but shared successfully");
			System.out.println("SHARED!!! " + email2);

		} else if (actionDriver.regularExpressions("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$", email2)
				.find() != true || email2.isBlank()) {
			soft.assertFalse(actionDriver.isElementPresent("registeredSuccefully_xpath"),
					"email format is incorrect but registration was successfully");
			System.out.println("DIDN'T SHARE!!! " + email2);
		}

		soft.assertAll();

	}
	
	@AfterTest
	public void CloseWebsite()
	{
		 driver.quit();
	}

}
