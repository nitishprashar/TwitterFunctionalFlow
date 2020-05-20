package com.myOrganization.roughWork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.myOrganization.base.TestBase;


public class RoughWork extends TestBase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver= new ChromeDriver();
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Workspace\\TwitterTestFramework\\src\\test\\resources\\executables\\chromedriver.exe");
		driver.get("https://twitter.com/explore");
		
	System.out.println(driver.getTitle());
		
		
	}

}
