package utilities;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

//import Base.DriverConfigValues;
//
//public class ElementFactory{
//	private ElementFactory()
//	{
//		
//	}
//		
//	public static WebElement getElement(String identifier) throws ConfigurationException
//	{
//		XMLConfiguration config = new XMLConfiguration(new File("./src/test/resources/config.xml"));
//		DriverConfigValues driverconfigvalues = new DriverConfigValues();
//		String locatortype = config.getString(identifier+"[@locator]");
////		switch(locatortype)
////		{
////		case "id": driverconfigvalues.getDriver().findElement(By.id(config.getString(identifier)));
////		break;
////		
////		case "xpath": 
////		
////		}
//		return null;
//		
//	}
//}
