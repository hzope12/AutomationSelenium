package com.hemant.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.hemant.qa.base.BaseClass;


public class TestUtil extends BaseClass {

	public static int PAGE_LOAD_TIMEOUTS = 120;
	public static int IMPLICIT_WAIT = 5;
	static File file;
	static FileInputStream inputStream;
	static FileOutputStream outputStream;
	static Workbook book;
	static Sheet sheet; 
	static String finalFile = "C:\\Users\\Swapnil\\Desktop\\test.xls";
	static String sheetName = "Sheet1";

	/*public static int setupExcel(String filePath, String fileName, String sheetName) 
	{
		String finalFile = filePath+ "\\"+ fileName;
		
		file = new File(finalFile);
		try {
			inputStream = new FileInputStream(file);
			book = new HSSFWorkbook(inputStream);
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		sheet = book.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		System.out.println(rowCount);
		return rowCount;
	}
	
	public static Cell readExcel(int rowNum, int colNum) 
	{
		Row row = sheet.getRow(rowNum);
//		System.out.println(row.getCell(colNum));
		Cell value = row.getCell(colNum);
		return value;
	}
	
	public static void writeExcel(int rowCount, int colNum, String cellValue) 
	{
		Row newRow = sheet.createRow(rowCount+1);
		Cell newCell = newRow.createCell(colNum);
		newCell.setCellValue(cellValue);
		try {
			inputStream.close();
			outputStream = new FileOutputStream(file);
			book.write(outputStream);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
	public static void finalExcelWrite(int cellNum0, String cellValue0, int cellNum1, String cellValue1) {
	{
		file = new File(finalFile);
		try {
			inputStream = new FileInputStream(file);
			book = new HSSFWorkbook(inputStream);
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		sheet = book.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		
		Row newRow = sheet.createRow(rowCount+1);
		Cell newCell = newRow.createCell(cellNum0);
		newCell.setCellValue(cellValue0);
		newCell = newRow.createCell(cellNum1);
		newCell.setCellValue(cellValue1);
		
		try {
			inputStream.close();
			outputStream = new FileOutputStream(file);
			book.write(outputStream);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	}
	

	public static boolean isDisplayed(WebElement element) 
	{
		boolean flag = false;
		
		try {
			element.isDisplayed();
			return true;
		} catch (Exception e) {
			return flag;
		}
	}

	public static void waitForPageLoad() {
		   
	    ExpectedCondition<Boolean> pageLoaded = new ExpectedCondition<Boolean>() {
			
			public Boolean apply(WebDriver input) {
				// TODO Auto-generated method stub
				return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
			}
		};
		wait.until(pageLoaded);
	}
	
	public static void writeCookieInFile(String filePath, String fileName) throws IOException 
	{
		File file = new File(filePath+fileName+".data");
		FileWriter writer = new FileWriter(file);
		BufferedWriter bos = new BufferedWriter(writer);
		for(Cookie ck: driver.manage().getCookies())
		{
			bos.write(ck.getName()+";"+ck.getValue()+";"+ ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure());
			bos.newLine();
		}
		bos.flush();
		bos.close();
		writer.close();
	}
	
	public static void readAndAddCookieFromFile(String filePath, String fileName) 
	{
		try {
			File file = new File(filePath+fileName);
			FileReader fReader = new FileReader(file);
			BufferedReader bRead = new BufferedReader(fReader);
				while(bRead.readLine()!=null) 
				{
					StringTokenizer str = new StringTokenizer(bRead.readLine(), ";");
					while(str.hasMoreTokens()) 
					{
						String name = str.nextToken();
						String value = str.nextToken();
						String domain = str.nextToken();
						String path = str.nextToken();
						Date expiry = null;
						String dt;
						if(!(dt=str.nextToken()).equals("null")){
						expiry = new Date(dt);
						}
						String x= str.nextToken();
						boolean isSecure = Boolean.parseBoolean(x);
						Cookie ck = new Cookie(name,value,domain,path,expiry,isSecure);
						driver.manage().addCookie(ck);
					}
					
				}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	
}
