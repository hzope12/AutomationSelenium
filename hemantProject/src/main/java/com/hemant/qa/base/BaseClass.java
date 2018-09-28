package com.hemant.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
//			FileInputStream input = new FileInputStream("com/configuration/config.properties");
			FileInputStream input = new FileInputStream("F://Testing Stuff//Automation//SeicProject//com.configuration//config.properties");
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
			driver = new ChromeDriver();
			sc = new SlimClass();
			wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, 180);
			driver.manage().window().maximize();
//			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUTS, TimeUnit.SECONDS);
			driver.get(prop.getProperty("url"));
			TestUtil.waitForPageLoad();
		}
	}
	
}
