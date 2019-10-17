package utilities;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.xml.XmlSuite;

import Base.BaseClass;

//import Base.BaseClass;
//import Base.DriverConfigValues;

public class Listeners implements ITestListener {
	//public DriverConfigValues driverconfigvalues;
	public WebDriver driver;
	public Rbot robot = new Rbot();
	public Logger logger = Logger.getLogger(this.getClass());
//	@BeforeMethod
//	public void getDriverConfigInstance()
//	{
//	driverconfigvalues = new DriverConfigValues();
//	}
//	
//	@AfterMethod
//	public void closeDriverConfigInstance()
//	{
//		driverconfigvalues = null;
//	}
	//--------ITestListeners------------
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("-------------------------Test Started: "+result.getName());
		Reporter.log("------This test started: "+result.getName(), true);
		logger.debug("Listener Says STARTED EXECUTION OF TEST CASE: "+result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("-------------------------Test success: "+result.getName());
		Reporter.log("------This test sucess: "+result.getName(), true);
		logger.debug("Listener Says TEST CASE: "+result.getName()+" PASSED");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("-------------------------Test failure: "+result.getName());
		Reporter.log("------This test failed: "+result.getName(), true);
		logger.error("TEST CASE FAILED "+result.getName());

	try
		{
		String classname = getClassname(result);
		String testcasename = getTestCasename(result);
	//	driverconfigvalues = new DriverConfigValues(); 
		driver = BaseClass.driver;
				//driverconfigvalues.getDriver();
		String screenshotpath = takeScreenShot(driver, classname, testcasename);
		System.out.println("Screenshot can be found at: "+screenshotpath);
		Reporter.log("--------------SCREENSHOT CAN BE FOUND AT: "+screenshotpath+"------------");
		}
		catch(IOException ioe)
		{
			System.out.println("IO Exception while taking screenshot: "+ioe.getMessage());
			Reporter.log("IO Exception while taking screenshot: "+ioe.getMessage(), true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Thread sleep exception :"+e.getMessage());
		}
		

			
		//---------------------------Using Robot class------------------------------------------------------------------
		//---------------------------Using Robot class------------------------------------------------------------------
	//	robot.capturescrenshot(getClassname(result), getTestCasename(result));
		
	}
	
	public String getClassname(ITestResult result)
	{
	String[] fullclassname = result.getInstanceName().trim().split("\\.");
	String classname=fullclassname[fullclassname.length-1];
	return classname;
	}
	
	public String getTestCasename(ITestResult result)
	{
	String testcasename = result.getMethod().getMethodName();
	return testcasename;
	}
	
	public String takeScreenShot(WebDriver driver, String classname, String testcasename) throws IOException, InterruptedException
	{
		File file = new File("./target/Screenshots/"+classname);
		if(!(file.exists()))
		{
			System.out.println("Directory created");
			file.mkdir();
		}
		File sourcefile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File targetfile = new File("./target/Screenshots/"+classname+"/"+testcasename+".png");
		if(targetfile.exists())
		{
			System.out.println("Target FILE IS BEING DELETED");
			targetfile.delete();
			Thread.sleep(5000);
		}
		FileUtils.copyFile(sourcefile, targetfile);
		String screenshotpath = targetfile.getAbsolutePath();		
		return screenshotpath;
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("-------------------------Test skipped: "+result.getName());
		Reporter.log("------This test skipped: "+result.getName(), true);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("-------------------------Test class started: "+context.getName());
		Reporter.log("------This testclass started: "+context.getName(), true);
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("-------------------------Test class finished: "+context.getName());
		Reporter.log("------This testclass finished: "+context.getName(), true);
	}
	//--------ITestListeners------------
	
	
//	//--------IInvokedMethdListener------------
//	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
//		// TODO Auto-generated method stub
//		System.out.println("This method is being executed now: "+method.getTestMethod().getMethodName()+"---Following is the result:---"+testResult.getName());
//		Reporter.log("This method is being executed now: "+method.getTestMethod().getMethodName()+"---Following is the result:---"+testResult.getName(), true);
//	}
//
//	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
//		// TODO Auto-generated method stub
//		System.out.println("This method has finished execution now: "+method.getTestMethod().getMethodName()+"---Following is the result:---"+testResult.getName());
//		Reporter.log("This method has finished execution now: "+method.getTestMethod().getMethodName()+"---Following is the result:---"+testResult.getName(), true);
//		
//	}

	//--------IInvokedMethdListener------------
	
	
	
	

}
