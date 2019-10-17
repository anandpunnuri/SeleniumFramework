package Base;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerClass {
	static Logger infologger=null;
	public static Logger createLogger()
	{
	if(infologger==null)
	{
	infologger = Logger.getLogger("infoLogger");
	PropertyConfigurator.configure("log4j.properties");
	}
	return infologger;
	}
	
}
