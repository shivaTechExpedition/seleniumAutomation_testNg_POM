package com.salesforce.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import com.salesforce.base.BaseTest;
import com.salesforce.pages.login.LoginPage;

import net.bytebuddy.utility.RandomString;

public class CommonUtils {
	public static String getStringDateAndTimeStamp() {
		String value = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		return value;
	}
	
	public static String generateRandomString() {
		return  RandomString.make();

	}
	
	
}