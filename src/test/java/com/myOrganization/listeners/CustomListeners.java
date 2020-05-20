package com.myOrganization.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;



import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.myOrganization.base.TestBase;
import com.myOrganization.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends TestBase implements ITestListener, ISuiteListener {
	
public String messageBody;
	

	public void onTestStart(ITestResult argO) {  

		test= rep.startTest(argO.getName().toUpperCase());
		if(!TestUtil.isTestRunnable(argO.getName(), excel)) {
			
			throw new SkipException("Skipping the test---"+ argO.getName()+" as the run mode is set to NO");
		}
		
	}  
	  
	public void onTestSuccess(ITestResult argO) {  

		test.log(LogStatus.PASS, argO.getName().toUpperCase()+"----PASS");
		rep.endTest(test);
		rep.flush();
	}  
	  
	public void onTestFailure(ITestResult argO) {  
	
	
	System.setProperty("org.uncommons.reportng.escape-output", "false");
	
	try {
		TestUtil.captureScreenshot();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	test.log(LogStatus.FAIL, argO.getName().toUpperCase()+"----Failed with Exception : "+argO.getThrowable());
	test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
	
	Reporter.log("Capaturing Screenshot");
	Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName +">Screenshot</a>");
	Reporter.log("<br>");
	Reporter.log("<br>");
	Reporter.log("<a target=\"_blank\" href= \"+TestUtil.screenshotName +\")><img scr=\"+TestUtil.screenshotName +\" height=100 width=100></img></a>");
	rep.endTest(test);
	rep.flush();
	
	}  
	  
	public void onTestSkipped(ITestResult argO) {  
	// TODO Auto-generated method stub  
	test.log(LogStatus.SKIP, "Skip of test cases and its details are : "+argO.getName());  
	rep.endTest(test);
	rep.flush();
	
	}  
	  

	public void onTestFailedButWithinSuccessPercentage(ITestResult argO) {  
	// TODO Auto-generated method stub  
	test.log(LogStatus.FAIL, "Failure of test cases and its details are : "+argO.getName());  
	rep.endTest(test);
	rep.flush();
	
	}  
	
	public void onFinish(ISuite arg0) {  
	


	
}
	
}
