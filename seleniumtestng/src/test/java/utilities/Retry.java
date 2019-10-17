package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Retry implements IRetryAnalyzer {

	public boolean retry(ITestResult result) {
		int count=0,retrycount=2;
		if(count<retrycount)
		{
			count++;
			System.out.println("Retrying the test case "+result.getMethod()+" which had "+getTestCaseStatus(result)+" for the "+count+" time");
			Reporter.log("Retrying the test case "+result.getMethod()+" which had "+getTestCaseStatus(result)+" for the "+count+" time", true);
			return true;
		}		
		return false;
	}
	
	public String getTestCaseStatus(ITestResult result)
	{
		String status = null;
		if(result.getStatus()==1)
		{
			status = "PASSED";
		}
		if(result.getStatus()==2)
		{
			status ="FAILED";
		}
		if(result.getStatus()==3)
		{
			status ="SKIPPED";
		}
		return status;
	}

}
