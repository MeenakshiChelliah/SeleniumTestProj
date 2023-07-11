package Pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Utilities.ExcelUtil;
import Utilities.Ext_Report;
import Utilities.PropertyUtil;
import driver.ApplicationConstants;
import driver.CommonMethods;

public class ProfilePage extends CommonMethods implements ApplicationConstants {
	public Properties prop;
	public static Map<String, String> dataMap;
	PropertyUtil propUtil = new PropertyUtil();
	ExcelUtil excel = new ExcelUtil();
	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./Reports/" + System.currentTimeMillis() + "extent.html");
	// create ExtentReports and attach reporter(s)
	ExtentReports extent = new ExtentReports();
	public ExtentTest test;
	LoginPage loginPage = new LoginPage(driver, test, extent);

	public ProfilePage(WebDriver driver, ExtentTest test) {
		super();
		this.driver = driver;
		this.extent = extent;
		this.test = test;

	}

	public void setupFiles(WebDriver driver, ExtentTest test, String TestCase_ID) throws Exception {
		excel.setExcelFile(DATA_FILEPATH, "Profile");
		dataMap = excel.getSheetData(TestCase_ID, "Profile");
		prop = propUtil.ReadProperty("./Properties/Profile.properties");
	}

	public void VerifyContactUpdate(WebDriver driver, ExtentTest test, String TestCase_ID)
			throws IOException, InterruptedException {
		try {
			setupFiles(driver, test, TestCase_ID);
			Thread.sleep(10000);
			driver.findElement(By.xpath("//div[@id='root']")).click();
			System.out.println("clicked workspace");
			WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(40)); // Wait
			driver.findElement(By.xpath("//header[@id='GlobalNavigation']")).click();
			// wait for account menu
			Wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(prop.getProperty("accountMenu"))));
			System.out.println("Wait completed");
			driver.findElement(By.className("SignInMenu-accountMenu")).click();
			// driver.findElement(By.className("//*[='GlobalNavigation']/div[2]/div/div/div[4]/div/button")).click();
			test.log(Status.INFO, "Signed in to the web page");
			// Click on Profile link
			driver.findElement(By.xpath("//ul[@id='account-dropdownMenu']//a[@title='profile']")).click();
			Wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("profile")));
			test.log(Status.INFO, "Profile Link clicked from Account menu");
			WebElement FirstName = driver.findElement(By.id(prop.getProperty("FirstName")));
			test.log(Status.INFO, "Profile page is opened");
			FirstName.clear();
			FirstName.sendKeys(dataMap.get("FirstName"));
			test.log(Status.INFO, "Entered First Name as " + dataMap.get("FirstName"));
			WebElement LastName = driver.findElement(By.id(prop.getProperty("LastName")));
			LastName.clear();
			LastName.sendKeys(dataMap.get("LastName"));
			test.log(Status.INFO, "Entered Last Name as " + dataMap.get("LastName"));
			Thread.sleep(1000);
			// if(driver.findElement(By.xpath("SaveChangesSuccessMessage")).isDisplayed())
			{
				// Wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("SaveChangesSuccessMessage")));
				test.log(Status.INFO, "Contact information have been updated");
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			GetScreenshot(driver, test, e.getLocalizedMessage());
			test.log(Status.FAIL, "Contact update is failed");
		}
		// extent.flush();
	}

	public void VerifyEmail(WebDriver driver, ExtentTest test, String TestCase_ID) throws IOException {

		try {
			VerifyContactUpdate(driver, test, TestCase_ID);

			Thread.sleep(10000);
			WebElement email = driver.findElement(By.name(prop.getProperty("Email")));
			Thread.sleep(1000);
			email.clear();
			Thread.sleep(1000);
			email.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			email.sendKeys(Keys.chord(Keys.BACK_SPACE));
			email.sendKeys(dataMap.get("Email"));
			test.log(Status.INFO, "Verifying email text field in the profile page");
			WebElement SaveChangeBtn = driver.findElement(By.className("ProfileForm-saveChangeButton"));
			SaveChangeBtn.click();
			Thread.sleep(1000);
			test.log(Status.INFO, "Entered email <b> " + dataMap.get("Email") + "</b> in the email field");
			WebElement EnterEmailErrorMsg = driver.findElement(By.xpath(prop.getProperty("ErrorMsg")));
			System.out.println(EnterEmailErrorMsg.getText());
			if (EnterEmailErrorMsg.getText().equalsIgnoreCase(dataMap.get("ErrorMessage"))) {
				test.log(Status.PASS, "Verified the mail error message as <b>" + EnterEmailErrorMsg.getText());
			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			GetScreenshot(driver, test, e.getLocalizedMessage());
		}

	}

	public void VerifyPasswordConfirmation(WebDriver driver, ExtentTest test, String TestCase_ID)
			throws IOException, InterruptedException {
		try {
			VerifyContactUpdate(driver, test, TestCase_ID);
			Thread.sleep(10000);
			WebElement SaveChangeBtn = driver.findElement(By.className("ProfileForm-saveChangeButton"));
			SaveChangeBtn.click();
			WebElement oldPassword = driver.findElement(By.name(prop.getProperty("oldPassword")));
			oldPassword.clear();
			oldPassword.sendKeys(dataMap.get("OldPassword"));
			WebElement newPassword = driver.findElement(By.name(prop.getProperty("newPassword")));
			newPassword.clear();
			newPassword.sendKeys(dataMap.get("NewPassword"));
			WebElement newPasswordConfirmation = driver
					.findElement(By.name(prop.getProperty("newPasswordConfirmation")));
			newPasswordConfirmation.clear();
			newPasswordConfirmation.sendKeys(dataMap.get("ConfirmNewPassword"));
			WebElement SignUp = driver.findElement(By.name(prop.getProperty("SignUp")));
			SignUp.click();
			System.out.println("Page Title== " + driver.getTitle());

		}

		catch (Exception e) {
			e.printStackTrace();
			GetScreenshot(driver, test, e.getLocalizedMessage());
			// test.log(Status.FAIL, "Login with user" + UserName + "is failed -Check UserID
			// or password is invalid");
		}

	}

	public void PasswordFieldValidation(WebDriver driver, ExtentTest test, String TestCase_ID) throws IOException, InterruptedException {
		try {
			VerifyContactUpdate(driver, test, TestCase_ID);
			WebElement SignUp = driver.findElement(By.name(prop.getProperty("SignUp")));
			SignUp.click();
			String ErrorMessages = dataMap.get("ErrorMessage");
			String Messages[] = ErrorMessages.split(",");
			for (String Error : Messages) {
				WebElement OldPasswordErrorMsg = driver.findElement(By.xpath(prop.getProperty("OldPasswordErrorMsg")));
				if (OldPasswordErrorMsg.getText().equalsIgnoreCase(Error)) {
					test.log(Status.ERROR, "Error Message displayed in Screen is <b>  " + Error);
				}
				WebElement NewPasswordErrorMsg = driver.findElement(By.xpath(prop.getProperty("NewPasswordErrorMsg")));
				System.out.println("NewPasswordErrorMsg===" + NewPasswordErrorMsg.getText());
				if (NewPasswordErrorMsg.getText().equalsIgnoreCase(Error)) {
					test.log(Status.ERROR, "Error Message displayed in Screen is <b> " + Error);
				}
				WebElement NewPasswordConfirmationErrorMsg = driver
						.findElement(By.xpath(prop.getProperty("NewPasswordConfirmationErrorMsg")));
				System.out.println("NewPasswordConfirmationErrorMsg==" + NewPasswordConfirmationErrorMsg.getText());
				if ((NewPasswordConfirmationErrorMsg.getText().equalsIgnoreCase(Error))) {
					test.log(Status.ERROR, "Error Message displayed in Screen is <b> " + Error);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			GetScreenshot(driver, test, e.getLocalizedMessage());
			System.out.println("exception==" + e.toString());
		}

	}
}
