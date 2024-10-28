package com.salesforce.tests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.salesforce.base.BaseTest;
import com.salesforce.base.SalesForceBase;
import com.salesforce.pages.MySettingsPage;
import com.salesforce.pages.home.HomePage;
import com.salesforce.pages.login.LoginPage;
import com.salesforce.utility.Constants;
import com.salesforce.utility.PropertiesUtilClass;







@Listeners(com.salesforce.utility.SalesForceListenersUtility.class)
public class Test_UserMenuDropDown extends SalesForceBase{
	
	protected Logger mylog = LogManager.getLogger(Test_UserMenuDropDown.class);
	
	
	@BeforeMethod
	@Parameters({"browsername"})
	@Override
	public void loginAndClickUserDropDown(@Optional("chrome") String browser) {
		super.loginAndClickUserDropDown(browser);
	}
		

	
	@Test
	public void test_SelectUserMenuDropDown() {
		
		HomePage hp = new HomePage(driver);
		// actual user dropdown menu list
		List<String> actualUserDropDownList = new ArrayList<String>();
		actualUserDropDownList.addAll(Arrays.asList("My Profile", "My Settings", "Developer Console", "Switch to Lightning Experience", "Logout"));
		mylog.info("actual user DropDown Menu " + actualUserDropDownList);
		
		
		// get all the dropdown menu elements and extract text
		List<WebElement> elems =  hp.userDropdownOptions;
		
		List<String> userDropDownMenu = new ArrayList<String>();
		for(WebElement elem : elems) {
			String txt = hp.extractText(elem, "drop down element");
			userDropDownMenu.add(txt);
		}
		
		mylog.info("userDropDownMenu " + userDropDownMenu);
		
		// validate the extracted text list with the actual dropdown menu list
		assertEquals(actualUserDropDownList, userDropDownMenu);
		
		
		// closeDriverInstance();

		
			
	}		
		
	@Test
	public void test_MyProfileOption() {
		HomePage hp = new HomePage(driver);
		hp.selectOption("My Profile");
		hp.clickEditProfile();
		WebDriverWait wait = hp.addWait(10);
		wait.until(ExpectedConditions.visibilityOf(hp.frameEle));
		hp.switchToEditProfileFrame();
		
	}
	
	@Test
	public void test_LogoutOption() {
		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		hp.selectOption("Logout");
		WebDriverWait wait = hp.addWait(5);
		wait.until(ExpectedConditions.visibilityOf(lp.userNameEle));
		String webPageTitle = hp.getWebPageTitle();
		
		mylog.info("Web page title: " + webPageTitle);
		boolean bool = hp.validatePartialText(webPageTitle, "Login");
		
		assertEquals(bool, true);
		
	}
	
	@Test
	public void test_DeveloperConsoleOption() {
		HomePage hp = new HomePage(driver);
		hp.selectOption("Developer Console");
		
		String mainWindowHandle = hp.getCurrentWindowHandle();
		
		hp.switchWindow(mainWindowHandle);
		
		// extract the Developer console window title to validate Developer console window is open
		String webPageTitle = hp.getWebPageTitle();
		
		mylog.info("WebPage Title: " + webPageTitle);
		
		hp.switchToDefault("Developer console Window");
		
		Boolean bool = hp.validateText("Developer Console", webPageTitle);
		assertEquals(bool, true);
		
		
		
	}
	
	@Test
	public void test_MySettings() {
		HashMap<String, String> availableTabOptions = new HashMap<String, String>();
		availableTabOptions.put("Chatter", "Chatter");
		availableTabOptions.put("Reports", "Reports");
		HomePage hp = new HomePage(driver);
		MySettingsPage sp = new MySettingsPage(driver);
		String partialTitle = "Login History";
		hp.selectOption("My Settings");
		sp.clickPersonalTab();
		sp.mouseOvertoLoginHistoryOptionAndClick();
		// Validate login history by extracting the title of the webpage
		String pageTitle = hp.getWebPageTitle();
		mylog.info("login history webpage title: "+ pageTitle);
		boolean bool = hp.validatePartialText(pageTitle, partialTitle );
		assertEquals(bool, true);
		// (validate the .csv download)
		sp.clickDisplayAndLayoutTab();
		sp.selectDisplayAndLayOutOption("Customize My Tabs");
		sp.selectAvailableTabsOption(availableTabOptions.get("Chatter"));
		sp.clickAddBtn();
		sp.selectAvailableTabsOption(availableTabOptions.get("Reports"));
		sp.clickAddBtn();
		
		
		
		// validation of the added option in selected tabs,
		// Reports field is added to Selected Tabs list and 
		// also added in the links available in top of salesforce page and sales force chatter page and 
		// sales and marketing pages.
		
		
		
	}

	
	
	

}
