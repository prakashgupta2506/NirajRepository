package com.ingcapgemini.wm.pageobjectmodel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//import com.ingcapgemini.wm.story.leave.ApplyLeave;
import com.ingcapgemini.wm.util.SeleniumUtil;


public class Login1 {
	private static final Logger LOGGER=LogManager.getLogger(Login1.class);
    public static final String LOGIN_PAGE_TITLE="OrangeHRM";
	public static final String LOGIN_USERNAME_ID="txtUsername";
	public static final String LOGIN_PASSWORD_ID="txtPassword";
	public static final String LOGIN_BUTTON_ID="btnLogin";
	
	
	public static void loginToApp(WebDriver driver,String userName ,String password){
		LOGGER.info("inside loginToApp method");
		
		if (userName!=null){
			SeleniumUtil.getWebElement(driver, By.id(LOGIN_USERNAME_ID)).sendKeys(userName);
		}
		if (password!=null){
			SeleniumUtil.getWebElement(driver, By.id(LOGIN_PASSWORD_ID)).sendKeys(password);
		}
		
		SeleniumUtil.getWebElement(driver, By.id(LOGIN_BUTTON_ID)).click();
		
	}
	
	
	
}
