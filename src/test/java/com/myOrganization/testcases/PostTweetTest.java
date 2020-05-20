package com.myOrganization.testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Hashtable;

import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.myOrganization.base.TestBase;
import com.myOrganization.utilities.TestUtil;

public class PostTweetTest extends TestBase {
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void postTweetTest(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {
	
	if(!data.get("runmode").equalsIgnoreCase("Y")) {
		throw new SkipException("Skipping the test step as runmode for the test dat is NO");
	}
	
	
	log.debug("Executing Test case---addCustomer");
	
	click("tweetBtn1_XPATH");
	
	click("tweetText_XPATH");
	
	Robot robot = new Robot();

    StringSelection tweet= new StringSelection(data.get("tweetText"));
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(tweet, null); 
    Thread.sleep(5000);  
    robot.keyPress(KeyEvent.VK_CONTROL); 
    robot.keyPress(KeyEvent.VK_V); 
    robot.keyRelease(KeyEvent.VK_V); 
    robot.keyRelease(KeyEvent.VK_CONTROL); 
    
	click("tweetBtn2_XPATH");
	
	Thread.sleep(4000);
	
	log.debug("Custommer added---Alert validated");
	Reporter.log("Custommer added---Alert validated");
	

	
	
	log.debug("Test case---addCustomer---successfully executed");
	Reporter.log("Test case---addCustomer---successfully executed");
	
	
	}

	

}
