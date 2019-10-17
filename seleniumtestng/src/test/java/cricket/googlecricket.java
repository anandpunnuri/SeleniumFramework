package cricket;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.AssertJUnit;

import junit.framework.AssertionFailedError;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import utilities.DBUtils;
import utilities.getTD;
import Base.BaseClass;
import Base.DriverConfigValues;
import Base.LoggerClass;



public class googlecricket
{
	public WebDriver driver;
	String URL;
	public DriverConfigValues driverconfigvalues;
	public XMLConfiguration config;
	public static Logger logger;
	public XSSFWorkbook workbook;
	DBUtils dbutils;
	@BeforeClass
	public void logger()
	{
		logger.info("in before class");
	}
	
	@AfterClass(alwaysRun=true)
	public void closelogger()
	{
		logger = null;
		//final Logger logger = Logger.getLogger(this.getClass());
	}
	
//	@BeforeSuite
//	public void loggersetup()
//	{
//		logger = Logger.getLogger("infoLogger");
//		PropertyConfigurator.configure("log4j.properties");
//	}
//	
//	@AfterSuite
//	public void loggerTeardown()
//	{
//		logger = null;
//	}
	
	@BeforeTest(alwaysRun=true)
	public void utilitysetup()
	{		
		try 
		{
			//dbutils = new DBUtils();
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
		catch (Exception e) 
		{
			System.out.println("Unable to create logger instance :"+e.getMessage());
		}
		//DB instance creation
		//datasheet obj creation
		//listener
	}
	
	@AfterTest(alwaysRun=true)
	public void closeutilitysetup()
	{
		//dbutils.closeconn();
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
	
	@Test(dataProvider="data",priority=1, groups = {"smoketest"})
	public void testcricket(String searchText, String textToVerify) throws InterruptedException
	{
		try
		{
		driver.get(URL);
		driver.findElement(By.id("lst-ib")).sendKeys(searchText);
		driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		AssertJUnit.assertTrue("Cricket text not found", driver.getPageSource().contains(textToVerify));
		}
		catch(AssertionFailedError afe)
		{
			System.out.println("Assertion Failed error: "+afe.getMessage());
			Assert.fail("Assertion failed due to: "+afe.getMessage());
		}
	}	
	
	@Test(dataProvider="data",priority=0)
	public void testwireless(String searchText, String textToVerify) throws InterruptedException
	{
		try
		{
		driver.get(URL);
		driver.findElement(By.id("lst-ib")).sendKeys(searchText);
		driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		AssertJUnit.assertTrue("Wireless text not found", driver.getPageSource().contains(textToVerify));
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
		logger.debug("COMPLETED THE EXECUTION OF TEST CASE: "+method.getName());
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
