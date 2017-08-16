package com.ingcapgemini.wm.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ingcapgemini.wm.pageobjectmodel.HomePage_PO;
import com.ingcapgemini.wm.pageobjectmodel.Login_PO;
import com.ingcapgemini.wm.pageobjectmodel.WelcomePage_PO;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

/*public class CommonUtil {
public static WebDriver driver=SeleniumUtil.getDriver();
private static final Logger LOGGER=LogManager.getLogger(CommonUtil.class);

public static void login(String userId){
	LOGGER.info("############  INSIDE LOGIN ########################");
	driver.manage().window().maximize();*/

public class CommonUtil {
	
	private static final Logger LOGGER = LogManager.getLogger(CommonUtil.class);
	static WebConnector webConnector = WebConnector.getInstance();
	static WebDriver driver = webConnector.getDriver();// browser has been open
		
	@Given("^User \"(.*?)\" with password \"(.*?)\" login into the application with correct credentials$")
	public void user_with_password_login_into_the_application(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		LOGGER.info("user "+arg1+" is logged into the application"+LOGGER.getClass().getCanonicalName());
		CommonUtil.login(arg1,arg2);
		
	   
	}
	
	@When("^User successfully log out of the application after clicking on log out button$")
	public void user_successfully_log_out_of_the_application_after_clicking_on_log_out_button() throws Throwable {
	    CommonUtil.logOut();
	   
	}
		
	

	
	public static void logOut(){
		
		LOGGER.info("############  INSIDE LOGOUT  ########################");
		driver.findElement(By.cssSelector(WelcomePage_PO.WELCOMEPAGE_SIGNOUT_CSS)).click();
		SeleniumUtil.ValidateWebElementPresence(driver, By.id(Login_PO.LOGIN_PAGE_SIGNIN_BUTTON_ID));
			
		//Assert.assertTrue("LogOut Successful", true);
		LOGGER.debug("############  LOGOUT SUCCESSFUL  ########################");
		
		
		
		
		
	}

	public static void login(String userId, String password) {		
		
		LOGGER.info("############  INSIDE LOGIN ########################");
		driver.manage().window().maximize();
		SeleniumUtil.ImplicitWait(driver);
		driver.get(WebConnector.getConfigProperties().getProperty(System.getProperty("ENV") + "_App_URL"));		
		Assert.assertEquals("the page title is not matching", HomePage_PO.HOME_PAGE_TITLE, SeleniumUtil.getTitle(driver));
		SeleniumUtil.ValidateWebElementPresence(driver, By.className(HomePage_PO.HOME_PAGE_SIGNIN_CLASSNAME));
		driver.findElement(By.className(HomePage_PO.HOME_PAGE_SIGNIN_CLASSNAME)).click();
		SeleniumUtil.ValidateWebElementPresence(driver, By.cssSelector(Login_PO.LOGIN_PAGE_EMAIL_CSS));
		Assert.assertTrue("Login Page is shown successfully", true);
		LOGGER.info("############  Login Page is shown successfully ########################");
		if (userId != null) {
			driver.findElement(By.cssSelector(Login_PO.LOGIN_PAGE_EMAIL_CSS)).sendKeys(userId);
			// OR SeleniumUtil.getWebElement(driver,By.id(Login.LOGIN_PAGE_EMAIL_CSS)).sendKeys(userId);
		}
		if (password != null) {
			driver.findElement(By.cssSelector(Login_PO.LOGIN_PAGE_PASSWORD_CSS)).sendKeys(password);
		}

		driver.findElement(By.id(Login_PO.LOGIN_PAGE_SIGNIN_BUTTON_ID)).click();

		SeleniumUtil.ValidateWebElementPresence(driver, By.cssSelector(WelcomePage_PO.WELCOMEPAGE_SIGNOUT_CSS));
		LOGGER.debug("############  LOGIN SUCCESSFUl ########################");

	}

}
