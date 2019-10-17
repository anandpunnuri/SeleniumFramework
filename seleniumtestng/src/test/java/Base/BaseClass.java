package Base;

import java.io.File;
import java.util.Map;

//model.AbstractProject;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class BaseClass {
public static WebDriver driver;
public static DriverConfigValues driverconfigvalues;
public BaseClass()
		{
		}

public static DriverConfigValues getDriver()	throws ConfigurationException
{	
//	XMLConfiguration config = new XMLConfiguration(new File("D:\\WorkSpaceTest\\TestProj\\src\\test\\resources\\config.xml"));
	XMLConfiguration config = new XMLConfiguration(new File("./src/test/resources/config.xml"));
	String URL=null;
	String bwser = null;
	try
	{
	Map<String, String> env = System.getenv();
//	if(!(env.isEmpty()))
//	{
//		for (String envName : env.keySet()) 
//		{
//			System.out.format("%s=%s%n", envName, env.get(envName));
//		}	
//	}
	
	System.out.println("aaa: "+env.keySet().contains("JENKINS_URL"));
		if(env.keySet().contains("JENKINS_URL"))
		{
			bwser = env.get("BROWSER");
			URL=env.get("URL");
			System.out.println("Browser and URL from Jenkins parameters is: "+bwser+" "+URL);
			config.clearProperty("browser");
			config.setProperty("browser", bwser);
			config.save();
			config.clearProperty("URL");
			config.setProperty("URL", URL);
			config.save();
			System.out.println("browser and value set in config file after updating: "+config.getString("browser")+" "+config.getString("URL"));
		}
		
			bwser = config.getString("browser");
			System.out.println("URL is: "+config.getString("URL"));
			System.out.println("browser is "+bwser);
			if(bwser.equalsIgnoreCase("firefox"))
			{
				File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
				FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
				FirefoxProfile firefoxProfile = new FirefoxProfile();
				driver = new FirefoxDriver(ffBinary,firefoxProfile);
			}
			
			if(bwser.equalsIgnoreCase("googlechrome"))
			{
				System.setProperty("webdriver.chrome.driver", "D:\\WorkSpaceTest\\seleniumtestng\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			driver.manage().window().maximize();
			driverconfigvalues = new DriverConfigValues(driver, config);
	}
	catch(Exception syse)
	{
		System.out.println("Exception while reading the system env variables: "+syse.getMessage());
	}
	return driverconfigvalues;
}
	
	
	
	
	
	
	
	
//	try
//	{
//		browsername = System.getProperty("browserName");
//	}
//	catch(Exception e)
//	{
//		System.out.println("Exception while reading browsername: "+e.getMessage());
//	}
//	
//	if(browsername!=null)
//	{
//		if(browsername.equalsIgnoreCase("firefox"))
//		{
//			File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\Firefox.exe");
//			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
//			FirefoxProfile firefoxProfile = new FirefoxProfile();
//			driver = new FirefoxDriver(ffBinary,firefoxProfile);
//			
//			//driver = new FirefoxDiver();
//		}
//	}
//	else
//	{
//		if(bwser.equalsIgnoreCase("firefox"))
//		driver = new FirefoxDriver();
//	}
//	return driver;
//}
}
