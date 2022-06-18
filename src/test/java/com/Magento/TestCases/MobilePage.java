package com.Magento.TestCases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Magento.ActionDrivers.actionDriver;
import com.Magento.Base.BaseClass;


public class MobilePage extends BaseClass {

	
	
	
	@Test
	public void verifyItemInMobileList() {

		SoftAssert soft = new SoftAssert();		

		String mobile = actionDriver.getText("homePageTitle_xpath");
		System.out.println(mobile);
		soft.assertEquals(mobile.toUpperCase(), "This is demo site for   ".toUpperCase());

		actionDriver.click("mobile_xpath");
		String mobliTitle = actionDriver.getText("mobileTitle_xpath");
		System.out.println(mobliTitle);
		soft.assertEquals(mobliTitle.toUpperCase(), "Mobile".toUpperCase());

		List<WebElement> BeforeSortNames = driver.findElements(By.xpath(locators.getProperty("ItemName_xpath")));
		List<String> BeforeSortNamesList = new ArrayList<String>();

		for (WebElement p : BeforeSortNames) {
			BeforeSortNamesList.add(p.getText());

		}

		actionDriver.select("SortBy_xpath", "Name");

		List<WebElement> AfterSortNames = driver.findElements(By.xpath(locators.getProperty("ItemName_xpath")));
		List<String> AfterSortNamesList = new ArrayList<String>();

		for (WebElement p : AfterSortNames) {
			AfterSortNamesList.add(p.getText());

		}

		Collections.sort(BeforeSortNamesList);

		soft.assertEquals(BeforeSortNamesList, AfterSortNamesList);

		soft.assertAll();

	}
	
	@AfterTest
	public void CloseWebsite()
	{
		 driver.quit();
	}

}
