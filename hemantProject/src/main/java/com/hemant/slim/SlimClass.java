package com.hemant.slim;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hemant.qa.base.BaseClass;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class SlimClass extends BaseClass {

	public WebElement findElement(String elementToFind) 
	{
		WebElement element = null;
		String ele = prop.getProperty(elementToFind);
		String [] s = ele.split(":", 2);
		String locatorType = s[0];
		String locatorValue = s[1];
		
		if(locatorType.equalsIgnoreCase("css")) 
		{
			element = driver.findElement(By.cssSelector(locatorValue));
		}else if(locatorType.equalsIgnoreCase("id"))
		{
			element = driver.findElement(By.id(locatorValue));
		}else if(locatorType.equalsIgnoreCase("xpath")) 
		{
			element = driver.findElement(By.xpath(locatorValue));
		}
		return element;
	}
	
	public void clickOnElement(WebElement element) 
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public void moveToElement(WebElement target) 
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(target).build().perform();
	}
	
	public void clickHoldAndRelease(WebElement target) 
	{
		Actions actions = new Actions(driver);
		actions.clickAndHold(target).release().build().perform();
	}

	public void contextClick(WebElement target) 
	{
		Actions actions = new Actions(driver);
		actions.contextClick(target).build().perform();
	}
	
	public void openInNewTab(WebElement target) 
	{
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).click(target).build().perform();
	}
	
	public void takeScreenshot(String filePath,String fileName) 
	{
		TakesScreenshot screenshot = ((TakesScreenshot) driver);
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destinFile = new File(filePath + fileName + ".png");
		try {
			FileUtils.copyFile(srcFile, destinFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
}
