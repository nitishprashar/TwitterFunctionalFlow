package com.myOrganization.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.DataProvider;

import com.myOrganization.base.TestBase;

public class TestUtil extends TestBase {
	
	public static String screenshotPath;
	public static String screenshotName;
	
	public static void captureScreenshot() throws IOException {
		
		Date d=new Date();
		screenshotName=d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcFile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenshotName));
		
		
	}
	
	@DataProvider (name="dp")
    public Object[][] getData(Method m){
		
		String sheetName= m.getName();
		int rows=excel.getRowCount(sheetName);
		
		int cols=excel.getColumnCount(sheetName);
		
		Object[][] data= new Object[rows-1][1];
		
		Hashtable<String, String> table = null;
		
		for(int rowNum =2;rowNum <=rows;rowNum++) {
			
			table= new Hashtable<String, String>();
			
			for(int colNum =0; colNum< cols; colNum++) {
				
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum -2][0] = table;
			}
		}
		return data;
		
	}
	
	static boolean flag;
	
	public static boolean isTestRunnable(String testname, ExcelReader excel) {
		
		
		
		String sheetName= "test_suite";
		int rows= excel.getRowCount(sheetName);
		
		for(int rNum=2;rNum<=rows;rNum++) {
			String testCase= excel.getCellData(sheetName, "testCase_ID", rNum);
			
			if(testCase.equalsIgnoreCase(testname)) {
				
				String runmode= excel.getCellData(sheetName, "runmode", rNum);
				
				if(runmode.equalsIgnoreCase("Y")) {
					flag= true;
				}
					else
						flag= false;
				}
			
			}
		return flag;
		}
	}


