package com.Magento.ActionDrivers;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Magento.Base.BaseClass;
import com.Magento.Listeners.listenersManager;


public class actionDriver extends BaseClass {
	
	public static Pattern p;
	public static Matcher m;
	public static WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	public static listenersManager tst;
	

	public static String getText(String locator) {

		String value = null;
		if (locator.endsWith("_xpath")) {
			value = driver.findElement(By.xpath(locators.getProperty(locator))).getText();

		} else if (locator.endsWith("_css")) {
			value = driver.findElement(By.cssSelector(locators.getProperty(locator))).getText();

		} else if (locator.endsWith("_id")) {
			value = driver.findElement(By.id(locators.getProperty(locator))).getText();
		}

		return value;
		
		
	}

	public static String getAttribute(String locator, String text) {

		String value = null;
		if (locator.endsWith("_xpath")) {
			value = driver.findElement(By.xpath(locators.getProperty(locator))).getAttribute(text);

		} else if (locator.endsWith("_css")) {
			value = driver.findElement(By.cssSelector(locators.getProperty(locator))).getAttribute(text);

		} else if (locator.endsWith("_id")) {
			value = driver.findElement(By.id(locators.getProperty(locator))).getAttribute(text);
		}

		return value;
	}

	public static void click(String locator) {
		if (locator.endsWith("_xpath")) {
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(locators.getProperty(locator))))).click();

		} else if (locator.endsWith("_css")) {
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(locators.getProperty(locator))))).click();

		} else if (locator.endsWith("_id")) {
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className(locators.getProperty(locator))))).click();
		}else if (locator.endsWith("_class")) {
			
		
			driver.findElement(By.linkText(locators.getProperty(locator))).click();
		}
		
		listenersManager.test.get().info("Clicked: " + locator );
	}

	public static String type(String locator, String value) {

		WebElement clear;
		if (locator.endsWith("_xpath")) {

			clear = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locators.getProperty(locator)))));
			clear.clear();
			clear.sendKeys(value);

		} else if (locator.endsWith("_css")) {

			clear = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(locators.getProperty(locator)))));
			clear.clear();
			clear.sendKeys(value);

		} else if (locator.endsWith("_id")) {

			clear = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(locators.getProperty(locator)))));
			clear.clear();
			clear.sendKeys(value);
		}

		
		listenersManager.test.get().info("Typed on: " + locator + " Entered: " + value);
		return locator;
	}

	public static void select(String locator, String value) {
		if (locator.endsWith("_xpath")) {
			Select select = new Select(driver.findElement(By.xpath(locators.getProperty(locator))));
			select.selectByVisibleText(value);

		} else if (locator.endsWith("_css")) {
			Select select = new Select(driver.findElement(By.cssSelector(locators.getProperty(locator))));
			select.selectByVisibleText(value);

		} else if (locator.endsWith("_id")) {
			Select select = new Select(driver.findElement(By.id(locators.getProperty(locator))));
			select.selectByVisibleText(value);
		}
		listenersManager.test.get().info("Clicked on: " + locator + " Selected: " + value);
	}
	
	public static void selectByIndex(String locator, int value) {
		if (locator.endsWith("_xpath")) {
			Select select = new Select(driver.findElement(By.xpath(locators.getProperty(locator))));
			select.selectByIndex(value);

		} else if (locator.endsWith("_css")) {
			Select select = new Select(driver.findElement(By.cssSelector(locators.getProperty(locator))));
			select.selectByIndex(value);

		} else if (locator.endsWith("_id")) {
			Select select = new Select(driver.findElement(By.id(locators.getProperty(locator))));
			select.selectByIndex(value);
		}

	}

	public static boolean isElementPresent(String locator) {
		try {
			if (locator.endsWith("_xpath")) {
				driver.findElement(By.xpath(locators.getProperty(locator)));
			} else if (locator.endsWith("_css")) {
				driver.findElement(By.cssSelector(locators.getProperty(locator)));
			} else if (locator.endsWith("_id")) {
				driver.findElement(By.id(locators.getProperty(locator)));
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static Matcher regularExpressions(String regex, String value)
	{
		p =  Pattern.compile(regex);
		m = p.matcher((CharSequence)value);
		return m;
		
	}
	
	public static String screenshot(String filePath)
	{
		String encodedBase64 = null;
		FileInputStream fileInputStreamReader = null;
		try {
			File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(filePath));
			
			  fileInputStreamReader = new FileInputStream(file);
			  byte[] bytes = new byte[(int)file.length()];
			  fileInputStreamReader.read(bytes);
			  encodedBase64 = new String(Base64.encodeBase64(bytes));
			;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "data:image/png;base64,"+encodedBase64;
		
		
	}
	
	public static String ramdomString()
	{
		return RandomStringUtils.randomAlphabetic(5);
		
		
	}
	
	
	


}
