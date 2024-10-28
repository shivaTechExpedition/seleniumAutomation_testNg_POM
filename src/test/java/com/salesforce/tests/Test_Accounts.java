package com.salesforce.tests;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salesforce.base.BaseTest;
import com.salesforce.base.SalesForceBase;
import com.salesforce.pages.AccountsPage;
import com.salesforce.pages.CreateNewViewPage;
import com.salesforce.pages.EditViewPage;
import com.salesforce.pages.NewAccountsPage;
import com.salesforce.pages.base.BasePage;
import com.salesforce.utility.CleanUpUtitlity;
import com.salesforce.utility.CommonUtils;






@Listeners(com.salesforce.utility.SalesForceListenersUtility.class)
public class Test_Accounts extends SalesForceBase {
	
	
	

	
	@BeforeMethod
	@Parameters({"browsername"})
	@Override
	public void loginToSalesForce(@Optional("chrome") String browser) {
		super.loginToSalesForce(browser);
		super.clickAccountsLink();
	}

		@Test
		public void test_createAccounts() {
		
		
		
		AccountsPage ap = new AccountsPage(driver);
		NewAccountsPage newAp = new NewAccountsPage(driver);
		ap.clickNewTab();
		WebDriverWait wait = ap.addWait(3);
		wait.until(ExpectedConditions.visibilityOf(newAp.newAccountTextEle));
		String accountName = CommonUtils.generateRandomString();
		
		newAp.enterAccountName(accountName);
		String dropDownOption = "Technology Partner";
		String customerPriorityOption = "High";
		
		newAp.selectByVisibleText(newAp.typeDropDown, dropDownOption, dropDownOption);
		
		newAp.selectByVisibleText(newAp.customerPriority, customerPriorityOption, customerPriorityOption);
		
		newAp.clickSaveBtn();
		
		
		
		String newAccountTitle = newAp.getWebPageTitle();
		
		mylog.info("New account title: " + newAccountTitle);
		
		boolean bool = newAp.validatePartialText(newAccountTitle, accountName);
		
		assertEquals(bool, true);
		
		}
		
		
		@Test
		public void test_createNewView() {
			CreateNewViewPage vp  = new CreateNewViewPage(driver);
			WebDriverWait wait = vp.addWait(4);
			wait.until(ExpectedConditions.visibilityOf(vp.createNewViewLink));
			AccountsPage ap = new AccountsPage(driver);
			vp.clickCreateNewView();
			
			
			String accountName = CommonUtils.generateRandomString();
			
			vp.enterViewName(accountName);
			vp.enterUniqueView();
			
			vp.clickSave();
			
			
			
			wait.until(ExpectedConditions.visibilityOf(ap.viewDropDown));
			
			boolean bool = vp.verifyPresenceOfDDOption(ap.viewDropDown, accountName);
			
			assertEquals(bool, true);
	
		}
		
		@Test
		public void test_EditView() {
			EditViewPage ep = new EditViewPage(driver);
			WebDriverWait wait = ep.addWait(4);
			wait.until(ExpectedConditions.visibilityOf(ep.editEle));
			
		
			
			//Edit link click
			ep.clickEditView();
			
			// change the view name to new view name
			String newViewName = ep.enterNewViewName(ep.viewNameEle);
			
			
			
			// Change the unique view name to new view name
			ep.enterUniqueViewName(ep.uniqueViewNameEle, newViewName);
			
			
			ep.selectFieldDDOPtion("ACCOUNT.NAME");
			
			
			
			ep.selectOperatorDD("contains");
			
			
			ep.enterValue("a");
			
			boolean elementPresence = ep.verifyPresenceOfDDOption(ep.availableFieldDDEle, "Last Activity");
			ep.selectDropDownOption(ep.availableFieldDDEle, "ACCOUNT.LAST_ACTIVITY", "Available field : Last Activity ");
			
			if(!elementPresence) {
				//com.salesforce.utility.CleanUpUtitlity obj = new com.salesforce.utility.CleanUpUtitlity();
				CleanUpUtitlity obj = new CleanUpUtitlity(driver);
				obj.removeEditViewSelectedFields("Last Activity");
				ep.selectDropDownOption(ep.availableFieldDDEle, "ACCOUNT.LAST_ACTIVITY", "Available field : Last Activity ");
			}
			
			ep.clickAdd();
			
			ep.clickSave();
			AccountsPage ap = new AccountsPage(driver);
			
			Select viewDDELe = new Select(ap.viewDropDown);
			List<WebElement>selectedOptions = viewDDELe.getAllSelectedOptions();
			
			
			for(WebElement option : selectedOptions) {
				assertEquals(option.getText(), newViewName);
				break;
				
	 		}
			
			
		}
		
//		public void test_CreateAccountsReport(){
//			SalesForceApp app = new SalesForceApp();
//			app.loginToSalesForce("chrome", false);
//			
//			app.clickAccountsLink();
//			
//			WebElement reportEle = driver.findElement(By.linkText("Accounts with last activity > 30 days"));
//			mouseOverElement(reportEle, "Account with last activity > 30 days Link");
//			clickElement(reportEle, "Account with last activity");
//			
//			addWait(3);
//			
//			//validate : in Unsaved Reports page
//			WebElement unsavedReport = driver.findElement(By.className("pageDescription"));
//			boolean bool = validatePartialText(unsavedReport.getText(),"Unsaved Report");
//			if(bool) {
//				System.out.println("Unsaved Reports page display successful");
//			}
//			else {
//				System.out.println("Fail to display Unsaved Reports page");
//			}
//			
//			WebElement DateField = driver.findElement(By.id("ext-gen148"));
//			clickElement(DateField, "Date field dropdown");
//			
//			List<WebElement> DateFieldDDEles = driver.findElements(By.xpath("//div[@id='ext-gen273']/div"));
//			for(WebElement elem : DateFieldDDEles) {
//				if(elem.getText().equals("Created Date")){
//					clickElement(elem,"'Created Date' Dropdown option");
//					break;
//				}
//			}
//			
//			WebElement fromDateEle = driver.findElement(By.id("ext-gen152"));
//			clickElement(fromDateEle, "From Date Picker");
//			
//			WebElement fromDateTodayEle = driver.findElement(By.id("ext-gen293"));
//			clickElement(fromDateTodayEle, "From Date: Today");
//			
//			WebElement ToDateEle = driver.findElement(By.id("ext-gen154"));
//			clickElement(ToDateEle, "To Date Picker");
//			
//			WebElement ToDateTodayEle = driver.findElement(By.id("ext-gen308"));
//			clickElement(ToDateTodayEle, "To Date: Today");
//			
//			WebElement reportRunBtn = driver.findElement(By.id("ext-gen63"));
//			clickElement(reportRunBtn, "Report Run");
//			
//			addWait(4);
//			
//			WebElement reportTxt = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']"));
//			
//			boolean reportTxtBool = validateText("Account List Report",extractText(reportTxt, "Account List Report"));
//			
//			WebElement reportGenerationStatus = driver.findElement(By.cssSelector("div[class='progressIndicator'] div[id='status']"));
//			
//			if(reportTxtBool && extractText(reportGenerationStatus, "Report Generation Status")=="Complete") {
//				System.out.println("TC 14 : Create Account Report Passed");
//			}
//			else
//				System.out.println("TC 14 :Unable to Create Account Report : Test Case Failed");
//			
//			closeDriverInstance();
//		}
		
			
		

}
