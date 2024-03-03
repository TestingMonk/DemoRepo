package listerners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import utilities.ExtentReporter;

public class Listeners extends Base implements ITestListener{
	
	WebDriver driver=null;
	ExtentReports extentReport =ExtentReporter.getExtentReport();
	ExtentTest extentTest ;
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
	

	@Override
	public void onTestStart(ITestResult result) {
		
		    String testName=result.getName();
		    extentTest = extentReport.createTest(testName);
		    extentTestThread.set(extentTest);  // this start threadsafe Mode
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		 String testName=result.getName();
		//extentTest.log(Status.PASS, testName + " gotPassed");
		 extentTestThread.get().log(Status.PASS,"Test Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		
		  String testName = result.getName();   // returns the name of the method of the test
		  
		  //extentTest.fail(result.getThrowable());
		  extentTestThread.get().fail(result.getThrowable());
		  
		  try {
				driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				String screenshotFilePath=takeScreenShot(testName,driver);   // calls takeScreenShot from the base.java class
				extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath, testName);  // adding screenshot to extent reports 
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();  // this will write all the reports 
		
	}
	
	

}
