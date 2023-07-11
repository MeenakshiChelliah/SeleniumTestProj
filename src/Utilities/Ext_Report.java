package Utilities;


import java.util.Date;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



public class Ext_Report  {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	
	@BeforeTest
	public void ExtentReport ()
	{
		Date date = new Date();
		long timeStamp =date.getTime();
		
		 htmlReporter = new ExtentHtmlReporter("./Reports"+"//"+"CNBC"+timeStamp+".html");
		 htmlReporter.config().setEncoding("utf-8");
		 htmlReporter.config().setDocumentTitle("CNBC");
		 htmlReporter.config().setReportName("CNBC");
		 extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        //extent.setSystemInfo("Sample", "View EHS");
        
	}
@AfterTest
public void ReportEnd()
{
	extent.flush();
}


@AfterMethod
public void tearDown(ITestResult result)
{
	String methodName,logText;
	Markup m;
	methodName =result.getMethod().getMethodName();
	if(result.getStatus()==ITestResult.FAILURE)
	{
		 
		logText = "<b>"+ "TEST CASE: - "+methodName.toUpperCase()+" FAILED" +"<b>";
		 m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		test.fail(m);
		
	}
	if(result.getStatus()==ITestResult.SKIP)
	{
		 
		 logText = "<b>"+ "TEST CASE: - "+methodName.toUpperCase()+" SKIPPED" +"<b>";
		 m = MarkupHelper.createLabel(logText, ExtentColor.BLUE);
		test.fail(m);
	}
	if(result.getStatus()==ITestResult.SUCCESS)
	{
		 
		 logText = "<b>"+ "TEST CASE: - "+methodName.toUpperCase()+" PASSED" +"<b>";
		 m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.pass(m);
		
	}
}
	
}
