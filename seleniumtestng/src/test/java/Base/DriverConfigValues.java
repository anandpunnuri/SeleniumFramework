package Base;

import org.apache.commons.configuration.XMLConfiguration;
import org.openqa.selenium.WebDriver;

public class DriverConfigValues {
public WebDriver driver;
public XMLConfiguration config =null;

public DriverConfigValues(WebDriver driver, XMLConfiguration config)
{
	this.config = config;
	this.driver = driver;
}

public DriverConfigValues(){};

public WebDriver getDriver()
{
	return driver;
}

public XMLConfiguration getConfig()
{
	return config;
}

}
