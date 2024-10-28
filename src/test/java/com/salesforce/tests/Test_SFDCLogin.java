package com.salesforce.tests;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.salesforce.base.BaseTest;
import com.salesforce.base.SalesForceBase;
import com.salesforce.pages.ForgtoPasswordPage;
import com.salesforce.pages.home.HomePage;
import com.salesforce.pages.login.LoginPage;
import com.salesforce.utility.CommonUtils;
import com.salesforce.utility.Constants;
import com.salesforce.utility.ExcelRead;
import com.salesforce.utility.PropertiesUtilClass;








@Listeners(com.salesforce.utility.SalesForceListenersUtility.class)
public class Test_SFDCLogin extends SalesForceBase{
	private Logger mylog = LogManager.getLogger(Test_SFDCLogin.class);
	
	
	@DataProvider(name = "loginDetails")
	public Object[][] loginCredentials() {
		ExcelRead xlRead = new ExcelRead();
		return xlRead.readAllDataSheet(Constants.DATAPROVIDER_XLPATH, Constants.SHEETNAME);
		
	}
	
//	
//	@Test(dataProvider = "loginDetails")
//	public void loginWithDifferentLoginDataSet(String username, String password) {
//		launchBrowser("chrome");
//		maximizeBrowserWindow();
//		getUrl(PropertiesUtilClass.readDataFromPropertyFile(Constants.PATH, "url"));
//		
//		LoginPage lp = new LoginPage(driver);
//		
//		try {
//		String actualWebPageTitle = "Home Page ~ Salesforce - Developer Edition";
//		
//		lp.enterUserName(username);
//		lp.enterPassword(password);
//		lp.clickLogin();
//		WebDriverWait wait = lp.addWait(10);
//		wait.until(ExpectedConditions.titleIs(actualWebPageTitle));
//		System.out.println("Tak screenshot");
//		getScreenShot(driver);
//		String  homepageTitle = driver.getTitle();
//		
//		mylog.info("homepageTitle: "+ homepageTitle);
//		
//		assertEquals(actualWebPageTitle, homepageTitle);
//		
//		}
//		finally {
//			closeDriverInstance();
//		}
//		catch (AssertionError e) {
//			mylog.error(e+"Assertion error");
			
//		}
//		catch(TimeoutException e) {
//			mylog.error(e+"Element to be displayed is not visible");
//		}
//	}
	
	
	@Parameters({"browsername"})
	@Test(groups = {"regression"})
	public void test_LoginErrorMessage(@Optional("chrome") String browser) {
		
		try {
			
			mylog.info(browser+" browser launched");
			WebDriver driver = launchBrowser(browser);
			LoginPage lp = new LoginPage(driver);
			String actualText = "Please enter your password.";
			
			
			lp.maximizeBrowserWindow();
			getUrl(PropertiesUtilClass.readDataFromPropertyFile(Constants.PATH, "url"));
			
			lp.enterUserName(PropertiesUtilClass.readDataFromPropertyFile(Constants.PATH, "username"));
			lp.clickLogin();
			
			String errorMessage = lp.getErrorMessage();
			assertEquals(actualText, errorMessage);
		}
		catch(AssertionError e) {
			mylog.error("Assertion error: "+ e.getMessage());
			throw new AssertionError();
		}
		catch(TimeoutException e) {
			mylog.error("Timeout Exception: "+e.getMessage());
			throw new TimeoutException();
		}

	}
	
	@Parameters({"browsername"})
	@Test(groups = {"smoke","regression","sanity"})
	public void test_LoginWithValidLoginCredentials(@Optional("chrome") String browser){
		try {
			
			mylog.info(browser + " browser launched");
			WebDriver driver = launchBrowser(browser);
			LoginPage lp = new LoginPage(driver);
			lp.maximizeBrowserWindow();
			getUrl(PropertiesUtilClass.readDataFromPropertyFile(Constants.PATH, "url"));
			String actualWebPageTitle = "Home Page ~ Salesforce - Developer Edition";
			
			lp.enterUserName(PropertiesUtilClass.readDataFromPropertyFile(Constants.PATH, "username"));
			lp.enterPassword(PropertiesUtilClass.readDataFromPropertyFile(Constants.PATH, "pswd"));
			lp.clickLogin();
			WebDriverWait wait = lp.addWait(10);
			wait.until(ExpectedConditions.titleIs(actualWebPageTitle));
			String  homepageTitle = driver.getTitle();
			
			mylog.info("homepageTitle: "+ homepageTitle);
			
			assertEquals(actualWebPageTitle, homepageTitle);
		}
		catch (AssertionError e) {
			mylog.error("Assertion error: "+ e.getMessage());
			throw new AssertionError();
		}
		catch(TimeoutException e) {
			mylog.error("Timeout Exception: "+e.getMessage());
			throw new TimeoutException();
		}
		
	}	
	@Parameters({"browsername"})
	@Test(groups = {"regression"})
	public void test_CheckRememberMe(@Optional("chrome") String browser) {
		
		try {
			mylog.info(browser + " browser launched");
			WebDriver driver = launchBrowser(browser);
			LoginPage lp = new LoginPage(driver);
			HomePage hp = new HomePage(driver);
			lp.maximizeBrowserWindow();
			getUrl(PropertiesUtilClass.readDataFromPropertyFile(Constants.PATH, "url"));
			
			lp.enterUserName(PropertiesUtilClass.readDataFromPropertyFile(Constants.PATH, "username"));
			lp.enterPassword(PropertiesUtilClass.readDataFromPropertyFile(Constants.PATH, "pswd"));
			lp.clickRememberMeCheckBox();
			lp.clickLogin();
			WebDriverWait wait = lp.addWait(10);
			wait.until(ExpectedConditions.elementToBeClickable(hp.userDropDown));
			hp.clickUserDropDown();
			hp.selectOption("Logout");
			wait.until(ExpectedConditions.visibilityOf(lp.userNameWithRememberedNameEle));
			
			String rememberedUserName = lp.extractText(lp.userNameWithRememberedNameEle, "username");
			
			mylog.info("rememberedUserName: "+ rememberedUserName);
			assertEquals(rememberedUserName, PropertiesUtilClass.readDataFromPropertyFile(Constants.PATH, "username"));
		}
		
		catch (AssertionError e) {
			mylog.error("Assertion error: "+ e.getMessage());
			throw new AssertionError();
		}
		catch(TimeoutException e) {
			mylog.error("Timeout Exception: "+e.getMessage());
			throw new TimeoutException();
		}
		
	
	}
	
	@Parameters({"browsername"})
	@Test(groups= {"regression"})
	public void test_ForgotPassword(@Optional("chrome") String browser) {
		try {
			
			mylog.info(browser + " browser launched");
			WebDriver driver = launchBrowser(browser);
			LoginPage lp = new LoginPage(driver);
			lp.maximizeBrowserWindow();
			ForgtoPasswordPage fp = new ForgtoPasswordPage(driver);
			getUrl(PropertiesUtilClass.readDataFromPropertyFile(Constants.PATH, "url"));
			
			lp.clickForgotPswdEle();
			
			fp.enterUserName();
			fp.clickContinueBtn();
			
			WebDriverWait wait = lp.addWait(10);
			wait.until(ExpectedConditions.visibilityOf(fp.passwordResetMessageEle));
			
			String passwordResetMessage = fp.getPasswordResetMessage();
			
			mylog.info("\nExtracted passwordResetMessage: " + passwordResetMessage + "\n");
			
			boolean bool = lp.validatePartialText(passwordResetMessage, "sent you an email with a link to finish resetting your password." );
			
			assertEquals(true, bool);
		}
		catch (AssertionError e) {
			mylog.error("Assertion error: "+ e.getMessage());
			throw new AssertionError();
		}
		catch(TimeoutException e) {
			mylog.error("Timeout Exception: "+e.getMessage());
			throw new TimeoutException();
		}
		
		
	}
	
	@Parameters({"browsername"})
	@Test(groups= {"regression"})
	public void test_LoginWithInvalidUserNamePswd(@Optional("chrome") String browser) {
		
		try {
			mylog.info(browser + " browser launched");
			WebDriver driver = launchBrowser(browser);
			LoginPage lp = new LoginPage(driver);
			lp.maximizeBrowserWindow();
			getUrl(PropertiesUtilClass.readDataFromPropertyFile(Constants.PATH, "url"));
			lp.enterUserName("123");
			lp.enterPassword("22131");
			lp.clickLogin();
			
			String errorMsg = lp.extractErrorMessage();
				
			mylog.info("Extracted error message: " + errorMsg);
			
			boolean validateErrorMsg = lp.validatePartialText(errorMsg, "Please check your username and password.");
			assertEquals(validateErrorMsg, true);
		}
		catch (AssertionError e) {
			mylog.error("Assertion error: "+ e.getMessage());
			throw new AssertionError();
		}
		catch(TimeoutException e) {
			mylog.error("Timeout Exception: "+e.getMessage());
			throw new TimeoutException();
		}
		
	}
	
	

}
