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

import com.aventstack.extentreports.Status;

public class CreateAccount extends CommonMethods {
	public Properties prop;
	public static Map<String, String> dataMap;
	LoginPage login = new LoginPage(driver, test, extent);

	@Test
	@Parameters({ "TestCase_ID" })
	public void SignUP(String TestCase_ID) throws Exception {
		test = extent.createTest("SigninTest", "Sample description");
		login.CreateAccount(driver, test,TestCase_ID);
		
	}

	@AfterTest
	public void testCal() {
		// close the app
		 driver.quit();

	}
}
