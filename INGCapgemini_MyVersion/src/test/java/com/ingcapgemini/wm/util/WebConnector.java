package com.ingcapgemini.wm.util;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebConnector {
	public static boolean driverStatus=false;
	private static final Logger LOGGER=LogManager.getLogger(WebConnector.class);
	private static WebConnector web;
	private WebDriver driver=null;
	private WebDriver fireFox=null;
	private WebDriver IE=null;
	private WebDriver chrome=null;
	private static Properties configProperties;//value initialized will remain same for the entire execution until u use setter(creating object of class and accessing variable)
	private static Properties sqlProperties;
	private static final String  configFile=System.getProperty("user.dir")+"//src//test//resources//config//Config.properties";
	private static String sqlFile=System.getProperty("user.dir")+"//src//test//resources//config//SQL.properties";
	//This code inside static block is executed only once: the first time you make an object of that class or the first time you access a static member of that class 
	static{
		configProperties=new Properties();
		sqlProperties=new Properties();
		try{
			FileInputStream fisConfig=new FileInputStream(configFile);
			
			configProperties.load(fisConfig);
			FileInputStream fisSQL=new FileInputStream(sqlFile);
			sqlProperties.load(fisSQL);
			
		}catch(Exception e){
			
		}
	}
	private WebConnector(){//if constructor is declared as private means this implements singleton concept..means object cannot be created from outside...means one instance of the class exist during runtime...
		
	}
	
	
	public static WebConnector getInstance(){
		if(web==null) {
			web= new WebConnector();
		}
		return web;	
	}

	public WebDriver getDriver(){//one instance of webconnector implies as one instance of driver....
		if (driver==null){
			driverStatus=true;
		//	if("firefox".equalsIgnoreCase(System.getProperty("Browser")) && fireFox==null){
				if("firefox".equalsIgnoreCase(System.getProperty("Browser"))){
				LOGGER.info("Inside Firefox browser initialization");
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\geckodriver.exe");
		    driver= new FirefoxDriver();
		    fireFox=driver;
		    LOGGER.debug("FireFox Browser launched successfully");
		}
		//else if("IE".equalsIgnoreCase(System.getProperty("Browser")) && IE==null){
				else if("IE".equalsIgnoreCase(System.getProperty("Browser"))){
			LOGGER.info("Inside IE browser initialization");
				System.out.println("i am inside IE");
				 System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\IEDriverServer_32.exe");
				driver= new InternetExplorerDriver();
				IE=driver;
				LOGGER.debug("IE Browser launched successfully");
			}
		//else if("Chrome".equalsIgnoreCase(System.getProperty("Browser")) && chrome==null){
			else if("Chrome".equalsIgnoreCase(System.getProperty("Browser"))){
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\chromedriver.exe");
					driver= new ChromeDriver();	
					chrome=driver;
				}
			
	}
		return driver;
	}
//used for killing driver object/set driver=null, in the same tc us need to close the browser and open a new one...
	
	/**
	 * method is used for......
	 * return type is void
	 * @param setDriver
	 */
	public void setDriver(WebDriver setDriver) {//setter..
		this.driver = setDriver;
	}

	/*public void setFireFox(WebDriver fireFox) {
		this.fireFox = fireFox;
	}

	public void setIE(WebDriver iE) {
		IE = iE;
	}

	public void setChrome(WebDriver chrome) {
		this.chrome = chrome;
	}*/


	public static Properties getConfigProperties() {
		return configProperties;
	}



	
}
