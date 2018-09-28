package com.hemant.qa.pages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.StringTokenizer;

import org.openqa.selenium.Cookie;

import com.hemant.qa.base.BaseClass;

public class LoginPage extends BaseClass{

	public static Set<Cookie> cookies;
	
	public LoginPage() 
	{
		
	}
	
	public void navigateToSingIn() 
	{
		sc.clickOnElement(sc.findElement("signInBtn"));
//		String firstHandle = driver.getWindowHandle();
		
//		sc.openInNewTab(sc.findElement("signInBtn"));
//		sc.openInNewTab(sc.findElement("signInBtn"));
		
//		Set<String> windows = driver.getWindowHandles();
//		windows.remove(firstHandle);
//		String secondHandle = windows.iterator().next();
//		driver.switchTo().window(secondHandle);
//		driver.switchTo().window(firstHandle);
		
	}
	
	public void signIn() 
	{
		sc.findElement("userName").sendKeys(prop.getProperty("username1"));
		sc.findElement("password").sendKeys(prop.getProperty("password1"));
		sc.clickOnElement(sc.findElement("submitBtn"));
	}
	
}


