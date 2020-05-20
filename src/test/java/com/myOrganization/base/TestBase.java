package com.myOrganization.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.myOrganization.utilities.ExcelReader;
import com.myOrganization.utilities.ExtentManager;
import com.myOrganization.utilities.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {


	
	public static WebDriver driver; 
	public static Properties config=new Properties();
	public static Properties OR= new Properties();
	public static FileInputStream fis;
	public static Logger log= Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep= ExtentManager.getInstance();
	public static ExtentTest test;
	
	
	@BeforeSuite
	public void setUp() {
		
		if(driver==null) {
			
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("Object repository file loaded!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
				log.debug("Testdata excel file loaded!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(config.getProperty("browser").equals("firefox")) {
				// if WebDriver 3
				// System.setProperty("webdriver.gecko.driver", "gecko.exe")
				driver= new FirefoxDriver();
				log.debug("Firefox launched!!");
			}
			
			if(config.getProperty("browser").equals("chrome")) {
			
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
				driver= new ChromeDriver();
				log.debug("Chrome launched!!");
			}
			
			if(config.getProperty("browser").equals("IE")) {
				
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver= new InternetExplorerDriver();
				log.debug("IE launched!!");
			}
			
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Opening URL---"+config.getProperty("testsiteurl") );
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.MILLISECONDS);
			wait= new WebDriverWait(driver,5); 
			
		
		}
		
		
		
	}
	
	
	public boolean isElementPresent(By by) {
		
		try {
			driver.findElement(by);
			return true;
		}catch(NoSuchElementException e) {
			return false;
		}
	}
	
	
	public static void verifyEquals(String expected, String actual) throws IOException {
	
		try {
			
			Assert.assertEquals(actual, expected);
			
			
		}catch(Throwable t) {
			
			TestUtil.captureScreenshot();
			
			//ReportNG
			Reporter.log("<br>"+"Verification failed : "+t.getMessage()+"<br>");
			Reporter.log("<a target=\"_blank\" href= \"+TestUtil.screenshotName +\")><img scr=\"+TestUtil.screenshotName +\" height=100 width=100></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			
			//Extent Report
			
			test.log(LogStatus.FAIL, "----Verification failed with Exception : "+t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
			
			
		}
		
		
	}
	
public void click(String locator) {
	
	if(locator.endsWith("_CSS")) {
		driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
	} else if(locator.endsWith("_XPATH")) {
		driver.findElement(By.xpath(OR.getProperty(locator))).click();
	} else if(locator.endsWith("_ID")) {
		driver.findElement(By.id(OR.getProperty(locator))).click();
	} else if(locator.endsWith("_XPATH")) {
		driver.findElement(By.xpath(OR.getProperty(locator))).click();
	} else if(locator.endsWith("_NAME")) {
		driver.findElement(By.name(OR.getProperty(locator))).click();
	} else if(locator.endsWith("_LINK")) {
		driver.findElement(By.linkText(OR.getProperty(locator))).click();
	}
	driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.MILLISECONDS);
	test.log(LogStatus.INFO, "Clicking on---"+locator);
}


public void type(String locator, String value) {
	
	if(locator.endsWith("_CSS")) {
		driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
	} else if(locator.endsWith("_XPATH")) {
		driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
	}  else if(locator.endsWith("_ID")) {
		driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
	}  else if(locator.endsWith("_NAME")) {
		driver.findElement(By.name(OR.getProperty(locator))).sendKeys(value);
	} else if(locator.endsWith("_LINK")) {
		driver.findElement(By.linkText(OR.getProperty(locator))).sendKeys(value);
	}
	driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.MILLISECONDS);
	test.log(LogStatus.INFO, "Typing in---"+locator+ "  Entered value as--"+value);
}


static WebElement dropdown;

public void select(String locator, String value) {
	
	 
	
	if(locator.endsWith("_CSS")) {
		dropdown= driver.findElement(By.cssSelector(OR.getProperty(locator)));
	} else if(locator.endsWith("_XPATH")) {
		dropdown= driver.findElement(By.xpath(OR.getProperty(locator)));
	}  else if(locator.endsWith("_ID")) {
		dropdown= driver.findElement(By.id(OR.getProperty(locator)));
	}  else if(locator.endsWith("_NAME")) {
		dropdown= driver.findElement(By.name(OR.getProperty(locator)));
	} else if(locator.endsWith("_LINK")) {
		dropdown= driver.findElement(By.linkText(OR.getProperty(locator)));
	}
	
	if(dropdown.isEnabled() && !dropdown.isSelected()) {
	
	Select select= new Select(dropdown);
	select.selectByVisibleText(value);
	}
	driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.MILLISECONDS);
	test.log(LogStatus.INFO, "Selecting from Dropdown---"+locator+ "   value as--"+value);
}
	
	static WebElement radio;
	
public void radioselect(String locator) {
	if(locator.endsWith("_CSS")) {
		radio= driver.findElement(By.cssSelector(OR.getProperty(locator)));
	} else if(locator.endsWith("_XPATH")) {
		radio= driver.findElement(By.xpath(OR.getProperty(locator)));
	}  else if(locator.endsWith("_ID")) {
		radio= driver.findElement(By.id(OR.getProperty(locator)));
	}  else if(locator.endsWith("_NAME")) {
		radio= driver.findElement(By.name(OR.getProperty(locator)));
	} else if(locator.endsWith("_LINK")) {
		radio= driver.findElement(By.linkText(OR.getProperty(locator)));
	}
	
	if(radio.isEnabled() && !radio.isSelected()) {
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.MILLISECONDS);
		radio.click();
	}
}
	
    @AfterSuite 
 	public void tearDown() {
    	
    	if(driver!=null) {
    		driver.quit();
    		log.debug("Test Execution completed");
    	}
    	
 		
 	}
	
	
}
