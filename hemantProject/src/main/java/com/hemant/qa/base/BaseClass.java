package com.hemant.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hemant.slim.SlimClass;
import com.hemant.util.TestUtil;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static SlimClass sc;
	
	public BaseClass() 
	{
		try 
		{
			prop = new Properties();
//			File f1 = new File("config.properties");
//			String path = f1.getAbsolutePath();
			FileInputStream input = new FileInputStream(".//com.configuration//config.properties");
//			FileInputStream input = new FileInputStream("F://Testing Stuff//Automation//SeicProject//com.configuration//config.properties");
			prop.load(input);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void initialization()
	{
		String browser = prop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")) 
		{
			launchChromeBrowser();
		}else if(browser.equalsIgnoreCase("firefox")) 
		{
			launchFirefoxBrowser();
		}
		
		sc = new SlimClass();
		wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, 180);
		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUTS, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		TestUtil.waitForPageLoad();
	}
	
	public void launchChromeBrowser() 
	{
		driver = new ChromeDriver();
	}
	
	@SuppressWarnings("deprecation")
	public void launchFirefoxBrowser() 
	{
		System.setProperty("webdriver.gecko.driver", ".//geckodriver.exe");
//		new DesiredCapabilities();
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		ProfilesIni myProfile =  new ProfilesIni();
		FirefoxProfile profile = myProfile.getProfile("Automation");
		profile.setPreference("browser.startup.homepage",
				"http://www.google.com");
		cap.setCapability("marionette", true);
		cap.setCapability(FirefoxDriver.PROFILE, profile);
		driver = new FirefoxDriver(cap);
//		System.setProperty("webdriver.firefox.marionette", ".//geckodriver.exe");
		
		//Creating new Profile
		
		
//		driver = new FirefoxDriver(profile);
//		FirefoxOptions options = new FirefoxOptions();
//		options.setLegacy(true);
//		FirefoxOptions options = new FirefoxOptions();
//		options.setLogLevel(FirefoxDriverLogLevel.DEBUG);
//		driver = new FirefoxDriver(options);
	}
}
