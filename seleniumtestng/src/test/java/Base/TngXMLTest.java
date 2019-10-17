package Base;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import utilities.Listeners;
import utilities.Transform;

public class TngXMLTest {

	static XSSFWorkbook masterwb;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell cell;
	
	@SuppressWarnings("unchecked")
	@Test
	public static void testng() throws InvalidFormatException, IOException
	{
		List<String> tests = new ArrayList<String>();
		masterwb = new XSSFWorkbook(new File("D:\\WorkSpaceTest\\seleniumtestng\\src\\test\\resources\\MasterSheet.xlsx"));
		sheet = masterwb.getSheet("MasterSheet");
		int rows;
		rows=sheet.getPhysicalNumberOfRows();
		String testname;
		for(int i=1;i<rows;i++)
		{			
			testname = sheet.getRow(i).getCell(0).getStringCellValue();
			if(tests.contains(testname))
			{
				continue;
			}
			else
			{
				tests.add(testname);
			}
			
		}
		
		
		TestNG tng = new TestNG();
		XmlSuite suite = new XmlSuite();
		suite.setName("SeleniumTestSuite");
		tng.setListenerClasses(Arrays.<Class<? extends ITestNGListener>>asList(Listeners.class));
		//XmlTest test = new XmlTest(suite);
		
		for(String test: tests)
		{
			XmlTest testngtest = new XmlTest(suite);
			testngtest.setName(test);
			testngtest.setPreserveOrder(true);
			List<String> classnames = getClassNames(sheet,test);
			List<XmlClass> classes = new ArrayList<XmlClass>();
			for(String testclass:classnames)
			{
				XmlClass testngClass = new XmlClass(testclass);
				classes.add(testngClass);
				
				List<String> testcasenames = getTestCaseNames(sheet, testclass);
				List<XmlInclude> testcases = new ArrayList<XmlInclude>();
				for(String testcase:testcasenames)
				{
					testcases.add(new XmlInclude(testcase));
				}
				testngClass.setIncludedMethods(testcases);
			}
			
			testngtest.setXmlClasses(classes);
			
		}
		
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		tng.setXmlSuites(suites);
		System.out.println("------------------------------PRINTING THE TESTNG XML: -------------------------------------------------------------------");
		System.out.println(suite.toXml());
		tng.run();
		
		
		//List<XmlClass> classes = new ArrayList<XmlClass>();
//		classes.add(new XmlClass("football.googlefootball"));
//		classes.add(new XmlClass("cricket.googlecricket"));
//		test.setXmlClasses(classes);
//		
//		List<XmlSuite> suites = new ArrayList<XmlSuite>();
//		suites.add(suite);
//		tng.setXmlSuites(suites);
//		
//		System.out.println("TESTNG XML IS: ");
//		System.out.println(suites);
//		tng.run();
	}
	
	public static List<String> getClassNames(XSSFSheet sheet, String testngTestName)
	{
		List<String> testngClassNames = new ArrayList<String>();
		String testname,classname;
		int rows=sheet.getPhysicalNumberOfRows();
		for(int i=1;i<rows;i++)
		{
			testname=sheet.getRow(i).getCell(0).getStringCellValue();
			classname=sheet.getRow(i).getCell(1).getStringCellValue();
			if(testname.equalsIgnoreCase(testngTestName))
			{
				if(testngClassNames.contains(classname))
				{
					continue;
				}
				else
				{
				testngClassNames.add(classname);
				}
			}
		}
		//search all rows and get the class names corresponding to testngtestname parameter and return the list of classnames.
		return testngClassNames;
		
	}
	
	public static List<String> getTestCaseNames(XSSFSheet sheet, String testngClassName)
	{
		List<String> testngTestCaseNames = new ArrayList<String>();
		String testcasename,classname;
		int rows=sheet.getPhysicalNumberOfRows();
		for(int i=1;i<rows;i++)
		{
			classname=sheet.getRow(i).getCell(1).getStringCellValue();
			testcasename=sheet.getRow(i).getCell(2).getStringCellValue();
			if(classname.equalsIgnoreCase(testngClassName))
			{
				String runNorun = sheet.getRow(i).getCell(3).getStringCellValue();
				if(runNorun.equalsIgnoreCase("Y") || runNorun.equalsIgnoreCase("YES") )
				{
				if(testngTestCaseNames.contains(testcasename))
				{
					continue;
				}
				else
				{
				testngTestCaseNames.add(testcasename);
				}
				}
			}
		}
		//search all rows and get the class names corresponding to testngtestname parameter and return the list of classnames.
		return testngTestCaseNames;
		
	}
	
}
