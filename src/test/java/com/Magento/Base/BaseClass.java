/**
 * 
 */
package com.Magento.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.Magento.Listeners.listenersManager;
import com.Magento.Utilities.EmailManager;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Lerato.Motshabi
 *
 */
public class BaseClass {

	public static Properties locators = new Properties();
	public static Properties config = new Properties();
	public FileInputStream configfile;
	public FileInputStream locatorsfile;
	public static WebDriver driver;
	public static DesiredCapabilities cap = new DesiredCapabilities();
	
	
	@BeforeClass
	public void setup() {
		
		
		try {
			configfile = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/configFiles/configuration.properties");
			config.load(configfile);

			locatorsfile = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/configFiles/locators.properties");
			locators.load(locatorsfile);

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}

		if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
									
			WebDriverManager.chromedriver().setup();			
			driver = new ChromeDriver();

		} else if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
			FirefoxProfile profile =new FirefoxProfile();
			profile.setPreference("browser.helperApps.neverAsk.openFile", "application/octet-stream");
			
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			

		} else if (config.getProperty("browser").equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		 driver.get(config.getProperty("url"));
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		 driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));		 
		 driver.manage().window().maximize();
		
		 
	}

	
	 @AfterSuite
	 public void tearDown() { 
		 driver.quit();
		 EmailManager.SendEmail(listenersManager.fileName);	 
		 
	 }
	 
	 
	 public void parallelSetup() {
		 
			
			try {
				configfile = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/configFiles/configuration.properties");
				config.load(configfile);

				locatorsfile = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/configFiles/locators.properties");
				locators.load(locatorsfile);

			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
			
				e.printStackTrace();
			}

			if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
										
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.ANY);
				

			} else if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.ANY);
				
				

			} else if (config.getProperty("browser").equalsIgnoreCase("edge")) {
				cap.setBrowserName("edge");
				cap.setPlatform(Platform.ANY);
				
			}
			
			try {
				driver=new RemoteWebDriver(new URL(config.getProperty("hub")),cap);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 driver.get(config.getProperty("url"));
			 driver.manage().window().maximize();
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			 driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));		 
			 
			
			 
		}
	 
	 
		

}


