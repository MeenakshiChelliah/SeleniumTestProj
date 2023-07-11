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

public class ProfileTest_TC3 extends CommonMethods {
	
	public Properties prop;
	LoginPage login = new LoginPage(driver, test, extent);
	ProfilePage profile = new ProfilePage(driver, test);
	

	@Test
	@Parameters({ "TestCase_ID" })
	public void EmailValidation(String TestCase_ID) throws Exception {
		test = extent.createTest("Email Validation", "Sample description");
		login.Login(driver, test, "TC1");
		//profile.VerifyEmail(driver, test, TestCase_ID);	
		profile.PasswordFieldValidation(driver, test, TestCase_ID);
	}	

	@AfterTest
	public void testCal() {
		// close the app
		driver.quit();

	}
}
