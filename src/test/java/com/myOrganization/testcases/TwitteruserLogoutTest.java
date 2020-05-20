package com.myOrganization.testcases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.myOrganization.base.TestBase;

public class TwitteruserLogoutTest extends TestBase {
	
	@Test
	public void twitteruserLogoutTest()  {
		
		
		
		log.debug("Executing Test case---TwitteruserLogoutTest");
		

		
		click("profile_XPATH");
		
		click("logout_XPATH");
		
		
		
	//	Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
		
		log.debug(driver.getTitle());
		log.debug("Test case---TwitteruserLogoutTest---successfully executed");
		Reporter.log("Test case---TwitteruserLogoutTest---successfully executed");
		}
	

}
