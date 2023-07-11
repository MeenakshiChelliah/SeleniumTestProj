package Testcase;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import Utilities.Ext_Report;
import driver.CommonMethods;
import Pages.LoginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class SignIn_TC3 extends CommonMethods {
	public Properties prop;
	// private ExtentTest test;
	public static Map<String, String> dataMap;
	LoginPage login = new LoginPage(driver, test, extent);
	
	@Test
	@Parameters({ "TestCase_ID" })
	public void VerifyLoginWithInvalidPassword(String TestCase_ID) throws Exception {
		test = extent.createTest("SigninTest", "Sample description");
		login.Login(driver, test, TestCase_ID);
		login.VerifyPasswordErrorMessage(driver, test, TestCase_ID);
	}

	@AfterTest
	public void testCal() {
		// close the app
		driver.quit();

	}
}
