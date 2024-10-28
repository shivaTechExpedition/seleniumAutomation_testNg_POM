package com.salesforce.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.salesforce.pages.base.BasePage;
import com.salesforce.utility.CommonUtils;



public class EditViewPage extends BasePage{

	public EditViewPage(WebDriver driver) {
		super(driver);
		
	}
	
			@FindBy(linkText = "Edit")
			public WebElement editEle;
			
			@FindBy(id = "fname")
			public WebElement viewNameEle;
			
			@FindBy(id="devname")
			public WebElement uniqueViewNameEle; 
			
			
			@FindBy(id = "fcol1")
			public WebElement fieldDDELe; 
			
			@FindBy(id = "fop1")
			public WebElement operatorDDEle ;
			
			@FindBy(id = "fval1" )
			public WebElement valueEle; 
			
			@FindBy(id = "colselector_select_0")
			public WebElement availableFieldDDEle;
			
			public void clickEditView(){
			clickElement(editEle, "Edit view");
			}
			
			public void selectFieldDDOPtion(String option) {
			selectDropDownOption(fieldDDELe, option, "field: " + option);
			}
			
			public void selectOperatorDD(String option) {
			selectByVisibleText(operatorDDEle, option, "operator: "+option);
			}
			
			public void enterValue(String value) {
			enterText(valueEle, value, "Value text field");
			}
			
			@FindBy(id = "colselector_select_0_left")
			public WebElement removeBtn; 
			
			@FindBy(id="colselector_select_1")
			public WebElement dropdownEle;
			
			@FindBy(css = "#colselector_select_0_right")
			public WebElement addBtn; 
			
			public void clickAdd() {
			clickElement(addBtn, "Add");
			}
			
			@FindBy(name = "save")
			public WebElement saveBtn; 
			
			public void clickSave() {
			clickElement(saveBtn, "Save");
			}
			
			public String enterNewViewName(WebElement ele) {
				//String viewName = extractText(ele, "view name text field");
				clearText(ele, "View Name");
				String newViewName = CommonUtils.generateRandomString();
				enterText(ele, newViewName, "View Name: New View Name");
				return newViewName;
			}
			
			public void enterUniqueViewName(WebElement ele, String newViewName) {
				clearText(ele, "unique view");
				enterText(ele,newViewName, "Unique View Name TextField");
			}
			
			public void clickRemoveBtn() {
				clickElement(removeBtn, "Remove");
			}
	

}
