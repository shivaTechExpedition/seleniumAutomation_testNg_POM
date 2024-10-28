package com.salesforce.pages.home;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.pages.base.BasePage;




public class HomePage extends BasePage{
	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "a[class=brandPrimaryFgr]")
	public WebElement homeTabEle;
	
	@FindBy(css ="div[id=userNavButton]")
	public WebElement userDropDown;
	
	@FindBy(xpath = "//div[@id='userNav-menuItems']/a")
	public List<WebElement> userDropdownOptions;
	
	@FindBy(id="rememberedUserNameEle")
	public WebElement rememberedUserNameEle;
	
	@FindBy(xpath = "//a[@class='contactInfoLaunch editLink']/img")
	public WebElement editProfileEle;

	@FindBy(id="contactInfoContentId")
	public WebElement frameEle; 
	
	@FindBy(css = "li[id='aboutTab']")
	public WebElement aboutTabEle; 
	
	@FindBy(css = "input[id='lastName']" )
	public WebElement lastNameEle;
	
	@FindBy(css = "input[value='Save All']")
	public WebElement saveAllButtonEle; 
	
	public void clickUserDropDown() {
		clickElement(userDropDown, "User Dropdown");
	}
	
	@FindBy(xpath = "//a/img[@class='allTabsArrow']" )
	public WebElement allTabsEle;
	
	public void selectOption(String option) {
		dropDownElementSelection(userDropdownOptions, option, "Logout option");
		
	}

	public void clickEditProfile() {
		clickElement(editProfileEle, "Edit profile icon");
	}
	
	public void switchToEditProfileFrame() {
		switchToFrameElement(frameEle);
	}
			
	public void clickAboutElement() {
		clickElement(aboutTabEle,"About");
	}
	
	public void enterLastName() {
		enterText(lastNameEle, "abc", "last name text field");
	}
	
	public void clickSaveAllBtn() {
		clickElement(saveAllButtonEle, "save all");
	}
	
	public void switchToMainWindow() {
		switchToDefault("Window");
	}
	
	public void clickAllTabsIcon() {
		clickElement(allTabsEle, "All tabs");
	}
	
	
	
	
	
			
	
	



	
}
