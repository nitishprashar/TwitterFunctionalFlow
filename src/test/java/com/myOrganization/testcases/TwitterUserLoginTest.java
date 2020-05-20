package com.myOrganization.testcases;

import java.util.Hashtable;

import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.myOrganization.base.TestBase;
import com.myOrganization.utilities.TestUtil;

public class TwitterUserLoginTest extends TestBase {
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void twitterUserLoginTest(Hashtable<String, String> data) throws InterruptedException {
		
		if(!data.get("runmode").equalsIgnoreCase("Y")) {
			throw new SkipException("Skipping the test step as runmode for the test dat is NO");
		}
		
		log.debug("Starting to execute testcase--- twitterUserLoginTest()");
		click("loginBtn1_XPATH");
		type("username_XPATH",data.get("username"));
		
		type("password_XPATH",data.get("password"));
		
		Thread.sleep(2000);
		
		click("loginBtn2_XPATH");
		
		Thread.sleep(5000);
		
		
		log.debug("Successfully executed testcase--- twitterUserLoginTest()");
		Reporter.log("Test case---twitterUserLoginTest---successfully executed");
	}
	
	


}
