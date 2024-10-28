package com.salesforce.base;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.salesforce.pages.AccountsPage;
import com.salesforce.pages.AllTabsPage;
import com.salesforce.pages.base.BasePage;
import com.salesforce.pages.home.HomePage;
import com.salesforce.pages.login.LoginPage;
import com.salesforce.utility.Constants;
import com.salesforce.utility.PropertiesUtilClass;

import net.bytebuddy.utility.RandomString;

public class SalesForceBase extends BaseTest  {
	BasePage pg = new BasePage(driver);
	//String dirPath = System.getProperty("user.dir");
	//protected String filePath =  dirPath+"/src/test/resources/env.properties";
	
	
	// get the username from the .properties file
	public String getUserName(String filePath) {
		System.out.println("Fetch username from properties file");
		return PropertiesUtilClass.readDataFromPropertyFile(filePath, "username");
	}
	
	// get the password from the .properties file
	public String getPswd(String filePath) {
		System.out.println("Fetch password from properties file");
		return PropertiesUtilClass.readDataFromPropertyFile(filePath, "pswd");
	}
	
	
	
	//login to the salesforce app
	public void loginToSalesForce(String browser) {
		launchBrowser(browser);
		mylog.info(browser + " browser launched.");
		getUrl(PropertiesUtilClass.readDataFromPropertyFile(Constants.PATH, "url"));
		LoginPage lp = new LoginPage(driver);
		lp.enterUserName(PropertiesUtilClass.readDataFromPropertyFile(Constants.PATH, "username"));
		lp.enterPassword(PropertiesUtilClass.readDataFromPropertyFile(Constants.PATH, "pswd"));
		lp.clickLogin();
		WebDriverWait wait = lp.addWait(10);
		HomePage hp = new HomePage(driver);
		wait.until(ExpectedConditions.elementToBeClickable(hp.userDropDown));
		mylog.info("login to salesforce app successful!");
		
		
	}
	
	//login to the salesforce app and click userDropDown
	public void loginAndClickUserDropDown(String browser) {

		loginToSalesForce(browser);
		HomePage hp = new HomePage(driver);
		hp.clickUserDropDown();
	}
	
	@AfterMethod
	public synchronized void tearDownAfterMethod() {
		closeDriverInstance();
	}
	
	
	

		
		
		
//		launchBrowser(browserName);
//		
//		getUrl("https://login.salesforce.com");
//		
//		WebElement userNameEle = driver.findElement(By.id("username"));
//		enterText(userNameEle, username, "username text field");
//		
//		
//		WebElement pswdEle = driver.findElement(By.id("password"));
//		enterText(pswdEle, password, "Password text field");
//		
//		WebElement loginEle = driver.findElement(By.id("Login"));
//		clickElement(loginEle, "Login");
//		
//		addWait(5);
//		
//		String  homepageTitle = getWebPageTitle();
//		
//		System.out.println("homepageTitle: "+ homepageTitle);
//		
//		boolean bool = validatePartialText(homepageTitle, "Home Page");
//		
//		if(bool)
//			System.out.println("\"login successful. Homepage is displayed\"");
//		else
//			System.out.println("Failed to login, please try again with valid username and password.");
//		return bool;

	
	public void deletePostFileLink(boolean post, boolean file, boolean link) {
		
	}
	
	
	
	public void clickAccountsLink() {
		HomePage hp = new HomePage(driver);
		AllTabsPage allTabs = new AllTabsPage(driver);
		
		hp.clickAllTabsIcon();
		
		WebDriverWait wait = hp.addWait(4);
		wait.until(ExpectedConditions.visibilityOf(allTabs.AllTabsTextEle));
		allTabs.clickLink(allTabs.Accounts);
		AccountsPage ap = new AccountsPage(driver);
		wait.until(ExpectedConditions.visibilityOf(ap.accountTextEle));
	}
	
	public void createAccount(String name) {
		
		WebElement newEle = driver.findElement(By.name("new"));
		pg.clickElement(newEle, "New tab");
		
		pg.addWait(3);
		
		String accountName = name;
		
		WebElement accoutNameTextEle = driver.findElement(By.id("acc2"));
		pg.enterText(accoutNameTextEle,accountName,"Account Name");
		
		WebElement typeDropDown = driver.findElement(By.id("acc6"));
		
		
		Select sel = new Select(typeDropDown);
		sel.selectByValue("Technology Partner");
		
		WebElement customerPriority = driver.findElement(By.id("00Nbm000004Ld2U"));
		Select selObj = new Select(customerPriority);
		selObj.selectByVisibleText("High");
		
		WebElement saveBtn = driver.findElement(By.name("save"));
		pg.clickElement(saveBtn, "Save");
	}
	
	public void clickOppurtunities() {
		WebElement allTabsEle = driver.findElement(By.xpath("//a/img[@class=\"allTabsArrow\"]"));
		pg.clickElement(allTabsEle, "all tabs icon");
		pg.addWait(2);
		
		WebElement leadsEle = driver.findElement(By.linkText("Leads"));
		pg.mouseOverElement(leadsEle, "Oppurtunities");
		pg.clickElement(leadsEle,"Leads");
		pg.addWait(3);
		
	}
	
	public void clickLeads() {
		WebElement allTabsEle = driver.findElement(By.xpath("//a/img[@class=\"allTabsArrow\"]"));
		pg.clickElement(allTabsEle, "all tabs icon");
		pg.addWait(2);
		
		WebElement oppEle = driver.findElement(By.linkText("Leads"));
		pg.mouseOverElement(oppEle, "Leads");
		pg.clickElement(oppEle,"Leads");
		pg.addWait(3);
	}
	
	public void clickContacts() {
		
		WebElement allTabsEle = driver.findElement(By.xpath("//a/img[@class='allTabsArrow']"));
		pg.clickElement(allTabsEle, "all tabs icon");
	
		pg.addWait(2);
		
		WebElement contactsEle  = driver.findElement(By.cssSelector("a[class='listRelatedObject contactBlock title']"));
		pg.mouseOverElement(contactsEle, "Contacts");
		pg.clickElement(contactsEle, "Contacts");
		
		pg.addWait(2);
	}
	
	public WebElement getRandomElement(List<WebElement> list)
    {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
	
	
	


}
