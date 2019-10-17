package utilities;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class Keywords{
	XMLConfiguration config;
	WebElement ele=null;
	public Keywords()
	{
	try {
		config = new XMLConfiguration(new File("./src/test/resources/config.xml"));
	} catch (ConfigurationException ce) {
		System.out.println("XMLConfiguration exception in Keywords class :"+ce.getMessage());
	}
	}
	
	public void enterText(String locator,String text)
	{
		try
		{
			
		}
		catch(ElementNotVisibleException env)
		{
			System.out.println("Element is not visible exception :"+env.getMessage());
		}
		catch(ElementNotFoundException enf)
		{
			System.out.println("Element is not found exception :"+enf.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Exception in enter Text method :"+e.getMessage());
		}
	}
}
