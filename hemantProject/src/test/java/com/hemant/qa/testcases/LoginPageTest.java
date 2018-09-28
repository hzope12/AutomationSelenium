package com.hemant.qa.testcases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.hemant.qa.base.BaseClass;
import com.hemant.qa.pages.LoginPage;

public class LoginPageTest extends BaseClass{

	LoginPage lp;
	
	@BeforeSuite
	public void beforeSuite()
	{
		initialization();
		lp = new LoginPage();
	}
	
	@Test
	public void verifySignIn() throws InterruptedException
	{
		
		lp.navigateToSingIn();
		lp.signIn();
		Thread.sleep(2000);
		
	}
	
	
	@AfterSuite
	public void afterSuite() 
	{
		driver.close();
		driver.quit();
	}
	
}
