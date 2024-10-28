package com.salesforce.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.salesforce.pages.EditViewPage;
import com.salesforce.pages.base.BasePage;

public class CleanUpUtitlity extends BasePage {
	public CleanUpUtitlity(WebDriver driver) {
		super(driver);
		
	}

	public void removeEditViewSelectedFields(String optionValue) {
		EditViewPage ep = new EditViewPage(driver);
		try {
			ep.selectDropDownOption(ep.dropdownEle, optionValue, optionValue);
		
		
		mylog.info("Remove the option " + optionValue +" from the Selected field dropdown menu");
		ep.clickRemoveBtn();
		}
		catch (Exception e) {
			mylog.error(e + ": Option "+ optionValue + " not present in the dropdown" );
		}
		
	}
	
	// delete account
	// remove selected tabs
	

}
