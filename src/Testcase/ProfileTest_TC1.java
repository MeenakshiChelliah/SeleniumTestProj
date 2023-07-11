package Testcase;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import Utilities.Ext_Report;
import Utilities.PropertyUtil;
import driver.CommonMethods;
import Pages.LoginPage;
import Pages.ProfilePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentTest;

import Utilities.Ext_Report;

public class ProfileTest_TC1 extends CommonMethods {
	
	public Properties prop;
	// private ExtentTest test;
	// public static Map<String, String> dataMap;
	LoginPage login = new LoginPage(driver, test, extent);
	ProfilePage profile = new ProfilePage(driver, test);
	// private ExtentReporter extent;

//	@BeforeTest
//	public void setUp() {
//		System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.get("https://www.cnbc.com");
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//	}
	@Test
	@Parameters({ "TestCase_ID" })
	public void ProfileFlow(String TestCase_ID) throws Exception {
		test = extent.createTest("ProfileTest", "Sample description");
		 login.Login(driver, test, "TC1");
		 profile.VerifyContactUpdate(driver, test,TestCase_ID);
		 //profile.PasswordFieldValidation(driver, test, TestCase_ID);

	}

	

	@AfterTest
	public void testCal() {
		// close the app
		driver.quit();

	}
}
