package football;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.AssertJUnit;

import junit.framework.AssertionFailedError;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import utilities.getTD;
import Base.BaseClass;
import Base.DriverConfigValues;
import Base.LoggerClass;

public class googlefootball{
	public WebDriver driver;
	String URL;
	public DriverConfigValues driverconfigvalues;
	public XMLConfiguration config;
	public XSSFWorkbook workbook;
	public static Logger logger;
	
	@BeforeClass(alwaysRun=true)
	public void logger()
	{
		
	}
	
	@AfterClass(alwaysRun=true)
	public void closelogger()
	{
		//logger = null;
		//final Logger logger = Logger.getLogger(this.getClass());
	}
	
	@BeforeTest(alwaysRun=true)
	public void utilitysetup()
	{	
		try 
		{
			logger = LoggerClass.createLogger();
			FileInputStream fis = new FileInputStream(new File("D:\\WorkSpaceTest\\seleniumtestng\\src\\test\\resources\\TestData\\TestDataExcel.xlsx"));
			workbook = new XSSFWorkbook(fis);			
		} 
		catch (FileNotFoundException fnf) 
		{
			System.out.println("Input data file not found exception: "+fnf.getMessage());
		}
		catch (IOException ioe) 
		{
			System.out.println("Unable to create workbook instance :"+ioe.getMessage());
		}
		//DB instance creation
		//datasheet obj creation
		//listener
	}
	
	@AfterTest(alwaysRun=true)
	public void closeutilitysetup()
	{
		//DB instance creation
		//datasheet obj creation
		//listener
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setup(Method method) throws ConfigurationException
	{			
		driverconfigvalues = BaseClass.getDriver();
		driver = driverconfigvalues.driver;
		config = driverconfigvalues.config;
		URL = config.getString("URL");
		logger.debug("STARTED EXECUTION OF TEST CASE: "+method.getName());
	}
	
	@Test(dataProvider="data",priority=1)
	public void testfootball(String searchtext, String textToVerify) throws InterruptedException
	{
		try
		{
		driver.get(URL);		
		logger.debug("Google URL opened");
		driver.findElement(By.id("lst-ib")).sendKeys(searchtext);
		driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		//Assert.assertTrue(condition, message);
		Assert.assertTrue(driver.getPageSource().contains(textToVerify),"football text not found");
		logger.debug("ASSERTION DONE NOW. VERIFIED IF "+textToVerify+" IS PRESENT IN THE WEBPAGE.");
		
		}
		catch(AssertionFailedError afe)
		{
			System.out.println("Assertion Failed error: "+afe.getMessage());
			Assert.fail("Assertion failed due to: "+afe.getMessage());
		}
	}	
	
	@Test(dataProvider="data",priority=0,groups={"smoketest"})
	public void testassociation(String searchtext, String textToVerify) throws InterruptedException
	{
		try
		{
		driver.get(URL);
		logger.info("google URL opened now");
		driver.findElement(By.id("lst-ib")).sendKeys(searchtext);
		driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		Assert.assertTrue(driver.getPageSource().contains(textToVerify),"Dissociation text not found");
		logger.info("ASSERTION DONE NOW. VERIFIED IF TEXT "+textToVerify+" IS PRESENT IN WEBPAGE");
		}
		catch(AssertionFailedError afe)
		{
			System.out.println("Assertion Failed error: "+afe.getMessage());
			Assert.fail("Assertion failed due to: "+afe.getMessage());
		}
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown(Method method)
	{
		logger.debug("COMPLETED EXECUTION OF TEST CASE: "+method.getName());
		logger.info("-------------------------------------------------------------------------------------------------------------");
		driver.quit();
	}
	
	@DataProvider
	public Object[][] data(Method method)
	{
		System.out.println(method.getName());
		String testcasename=method.getName();
		Object[][] obj = getTD.getTableArray(workbook, "TestSheet1", testcasename);
		return obj;
	}
	
}
