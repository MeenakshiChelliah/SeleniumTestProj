package Pages;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utilities.ExcelUtil;
import Utilities.PropertyUtil;
import driver.ApplicationConstants;
import driver.CommonMethods;

public class LoginPage extends CommonMethods implements ApplicationConstants {
	Properties prop;
	public static Map<String, String> dataMap;
	PropertyUtil propUtil = new PropertyUtil();
	ExcelUtil excel = new ExcelUtil();
	WebElement UserName;
	WebElement PassWord;

	public LoginPage(WebDriver driver, ExtentTest test, ExtentReports extent) {
		super();
		this.driver = driver;
		this.extent = extent;
		this.test = test;

	}

	public void ClickOnSignInLink(WebDriver driver, ExtentTest test) throws IOException {
		prop = propUtil.ReadProperty("./Properties/Login.properties");
		WebElement Sign_IN = driver.findElement(By.xpath(prop.getProperty("SignIN_link")));
		Sign_IN.click();
		test.log(Status.INFO, "Clicked on Sign in link in home page ");
	}

	public void VerifyLoginErrorMessage(WebDriver driver, ExtentTest test, String TestCase_ID) throws IOException {
		try {
			if (driver.findElement(By.xpath(prop.getProperty("LoginError"))).isDisplayed()) {
				test.log(Status.FAIL, driver.findElement(By.xpath(prop.getProperty("LoginError"))).getText());
				test.log(Status.INFO, "Sign in failed with Username= <b> " + dataMap.get("Email")
						+ "</b> and Password = <b> " + dataMap.get("Password"));
			} else {
				test.log(Status.PASS, "Signed in with the username and Password successfully");
				test.log(Status.INFO, "Sign in Passed with Username = <b>" + dataMap.get("Email")
						+ " </b> and  Password = <b>" + dataMap.get("Password"));
			}
		} catch (Exception e) {
			GetScreenshot(driver, test, e.getLocalizedMessage());
		}
	}

	public void VerifyPasswordErrorMessage(WebDriver driver, ExtentTest test, String TestCase_ID) throws IOException {

		try {
			if (driver.findElement(By.xpath(prop.getProperty("PasswordErrorMsg"))).isDisplayed()) {
				test.log(Status.FAIL, driver.findElement(By.xpath(prop.getProperty("PasswordErrorMsg"))).getText());
				test.log(Status.INFO, "Sign in failed with Username= <b>" + dataMap.get("Email")
						+ "</b> and Password = <b> " + dataMap.get("Password"));
			} else {
				test.log(Status.PASS, "Signed in with the username and Password successfully");
				test.log(Status.INFO, "Sign in Passed with Username = <b>" + dataMap.get("Email")
						+ " </b> and Password = <b>" + dataMap.get("Password"));
			}
		} catch (Exception e) {
			GetScreenshot(driver, test, e.getLocalizedMessage());
		}
	}

	public void VerifyForgotPassword(WebDriver driver, ExtentTest test, String TestCase_ID) throws Exception {
		try {
			excel.setExcelFile("./TestData/Data.xlsx", "SignIn");
			dataMap = excel.getSheetData(TestCase_ID, "SignIn");
			ClickOnSignInLink(driver, test);
			WebElement ForgotPassword = driver.findElement(By.xpath(prop.getProperty("ForgotPassword")));
			ForgotPassword.click();
			test.log(Status.INFO, "Clicked on Forgot password link");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement ForgotPwdEmail = driver.findElement(By.xpath(prop.getProperty("ForgotPwdEmail")));
			ForgotPwdEmail.sendKeys(dataMap.get("ForgotPwdEmail"));
			test.log(Status.INFO, "Entered recovery email as " + dataMap.get("ForgotPwdEmail"));
			WebElement ForgotPwdContinueBtn = driver.findElement(By.xpath(prop.getProperty("ForgotPwdContinueBtn")));
			ForgotPwdContinueBtn.click();
			test.log(Status.INFO, "Clicked on Forgot password Continue Button");
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			WebElement ForgotPwdCloseBtn = driver.findElement(By.xpath(prop.getProperty("ForgotPwdCloseBtn")));
			ForgotPwdCloseBtn.click();
			test.log(Status.INFO, "Clicked on Forgot password close button");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			GetScreenshot(driver, test, e.getLocalizedMessage());

		}
	}

	public void Login(WebDriver driver, ExtentTest test, String TestCase_ID) throws Exception {
		System.out.println("test case id==" + TestCase_ID);
		excel.setExcelFile(DATA_FILEPATH, "SignIn");
		dataMap = excel.getSheetData(TestCase_ID, "SignIn");

		try {

			ClickOnSignInLink(driver, test);
			UserName = driver.findElement(By.name(prop.getProperty("UserName")));
			UserName.sendKeys(dataMap.get("Email"));
			PassWord = driver.findElement(By.name(prop.getProperty("Password")));
			PassWord.sendKeys(dataMap.get("Password"));
			Thread.sleep(1000);
			WebElement SignInBtn = driver.findElement(By.name(prop.getProperty("SignIn")));
			SignInBtn.click();
			Thread.sleep(5000);
			GetScreenshot(driver, test,"");
			

		} catch (Exception e) {
			e.printStackTrace();
			GetScreenshot(driver, test,e.getLocalizedMessage());

		}

	}
	public void CreateAccount(WebDriver driver, ExtentTest test,String TestCase_ID) throws IOException {
		prop = propUtil.ReadProperty("./Properties/Login.properties");
		try {
			excel.setExcelFile(DATA_FILEPATH, "SignIn");
			dataMap = excel.getSheetData(TestCase_ID, "SignIn");
		
		WebElement CreateFreeAccount = driver.findElement(By.className(prop.getProperty("CreateFreeAccount")));
		CreateFreeAccount.click();
		test.log(Status.INFO, "Clicked on <b> CREATE FREE ACCOUNT </b> link in home page ");
		UserName=driver.findElement(By.name(prop.getProperty("UserName")));
		UserName.sendKeys(dataMap.get("Email"));
		PassWord = driver.findElement(By.name(prop.getProperty("Password")));
		PassWord.sendKeys(dataMap.get("Password"));
		WebElement TermsOfDerviceChkBox = driver.findElement(By.xpath(prop.getProperty("TermsOfDerviceChkBox")));
		TermsOfDerviceChkBox.click();
		WebElement SignUp = driver.findElement(By.name(prop.getProperty("SignUp")));
		SignUp.click();
		Thread.sleep(5000);
		test.log(Status.INFO, "created account using Email <b> "+dataMap.get("Email")+ "</b> and password <b>"+dataMap.get("Password"));
		
		
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		GetScreenshot(driver, test, e.getLocalizedMessage());
	}
}
}
