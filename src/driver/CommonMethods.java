package driver;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utilities.ExcelUtil;
import Utilities.Ext_Report;
import Utilities.PropertyUtil;

public class CommonMethods extends Ext_Report implements ApplicationConstants {
	public WebDriver driver;
	public static Map<String, String> dataMap;
	PropertyUtil propUtil = new PropertyUtil();
	ExcelUtil excel = new ExcelUtil();
	 
	
	@BeforeSuite
	public void beforeSuite()
	
	{
		System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.cnbc.com");
		System.out.println("Launched URL");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	public void GetScreenshot(WebDriver driver, ExtentTest test,String testStep) throws IOException
	{
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		String fileName = "SS_" + "_" + "testStep" + "-" + calendar.get(Calendar.DATE)
		+ "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.YEAR) + "_" + calendar.get(Calendar.HOUR_OF_DAY)
		+ "-" + calendar.get(Calendar.MINUTE) + "-" + calendar.get(Calendar.SECOND) + ".png";
		File file = new File(SCREENSHOT_PATH + "" + fileName);
		try {
			FileUtils.copyFile(screenshot, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(Status.INFO, "Snapshot for  " + testStep + "  : "+ test.addScreenCaptureFromPath(REPORT_SCREENSHOT_PATH + "" + fileName));
	}
	
	public void WaitForElement(WebDriver driver,ExtentTest test) {
		
	}
	
	
	
	@AfterSuite
	public void afterSuite()
	{
		extent.flush();
	}
}
