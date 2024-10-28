package com.salesforce.base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;
import com.salesforce.pages.home.HomePage;
import com.salesforce.pages.login.LoginPage;
import com.salesforce.utility.CommonUtils;
import com.salesforce.utility.Constants;
import com.salesforce.utility.PropertiesUtilClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class  BaseTest {
	protected  static WebDriver driver = null;
	protected  static Logger mylog = LogManager.getLogger(BaseTest.class);

	
	
	// launch browser based on the browserName 
	public WebDriver launchBrowser(String browserName) {
		
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
			
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "ie":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;

		default:
			mylog.error("Invalid browser name.");
			break;
		}
		return driver;
	}

	
	// open the webpage with the provided url string
	public  void getUrl(String url) {
		driver.get(url);
	}
	
	
	// Take screenshot on test failure
	public  void takeScreenShot(String filepath) {
		// String path = Constants.SCREENSHOTS_PATH + CommonUtils.getStringDateAndTimeStamp()+".png";
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		File dst = new File(filepath);
		try {
			Files.copy(src, dst);
			mylog.info("screen captured");
		}
		catch (IOException e) {
			mylog.error(e.getMessage());
			mylog.error("Not able to capture the screenshot");
			
		}
		
	}
	
	// sclose driver instance
	public  void closeDriverInstance() {
		mylog.info("Closing driver instance.");
		driver.close();
	}
	
	
	


}
