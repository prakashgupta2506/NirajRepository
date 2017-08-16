package com.ingcapgemini.wm.common;

import java.util.Collection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

//import com.ingcapgemini.wm.story.leave.ApplyLeave;
import com.ingcapgemini.wm.util.CommonUtil;
import com.ingcapgemini.wm.util.SeleniumUtil;
import com.ingcapgemini.wm.util.WebConnector;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class GlobalStepDefinition {
	  WebConnector webConnector= WebConnector.getInstance(); //getting object of WebConnector since this is singleton class
	  WebDriver driver=webConnector.getDriver();
	 private static final Logger LOGGER=LogManager.getLogger(GlobalStepDefinition.class);
	
	 @Before(order = 0)
	public void Setup(Scenario scenario) {
		System.out.println("before setup");
		String scenarioName = scenario.getName();
		System.out.println("print scenario name " + scenarioName);

		Collection<String> col = scenario.getSourceTagNames();
		// Iterator it= col.iterator();
		for (String tagname : col) {

			System.out.println("print tagname " + tagname);

		}
	}
	 
	 
	/*	@Given("^User \"(.*?)\" with password \"(.*?)\" login into the application$")
		public void user_with_password_login_into_the_application(String arg1, String arg2) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
			LOGGER.info("user "+arg1+" is logged into the application"+LOGGER.getClass().getCanonicalName());
			CommonUtil.login(arg1,arg2);
			
		   
		}*/
		
		
	
		/*@Given("^user \"(.*?)\" is logged into the application$")
		public void user_is_logged_into_the_application(String userId) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
			LOGGER.info("user "+userId+" is logged into the application"+LOGGER.getClass().getCanonicalName());
			CommonUtil.login(userId,pwd);
				
		    
		}*/	
	
	@After(order = 0)
	public void TearDown(Scenario scenario) {
		System.out.println("Tear down");
		if (WebConnector.driverStatus==true){
		if (scenario.isFailed()) {
			System.out.println("###################  i am inside screen shot after fail ####################");
			final byte[] screenshot = ((TakesScreenshot) webConnector.getDriver())
					.getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png"); // stick it in the report
		}
		}
		
		
		
		//set driver object to null
		if (WebConnector.driverStatus==true){
			System.out.println("######################  i am inside kill browser ############################");
		SeleniumUtil.closeBrowser(driver);
		webConnector.setDriver(null);
		}
		
	}
	

	
}
