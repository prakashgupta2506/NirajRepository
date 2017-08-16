package com.ingcapgemini.wm.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtil {

	
	//for one time driver object initialization
	//private  static WebConnector SELENIUM=null;
	
	private static final Logger LOGGER = LogManager.getLogger(SeleniumUtil.class);
	private static String ScreenShotFolder = System.getProperty("user.dir") + "//ScreenShot";
	// private static WebConnector webConnector = WebConnector.getInstance();
	private static final int BROWSER_SYNC = Integer
			.parseInt(WebConnector.getConfigProperties().getProperty("BrowserSync"));
	private static final int WEBLEMENT_SYNC = Integer
			.parseInt(WebConnector.getConfigProperties().getProperty("WebElementSync"));

	
	
	/*//for one time driver object initialization
		public  static void initializeSeleniumConnector() {
			 SELENIUM = WebConnector.getInstance();
		}

		//for one time driver object initialization

		public static WebDriver getDriver() {
			return SELENIUM.getDriver();
		}*/
		
		public static WebElement getWebElement(WebDriver driver, By by) {
			LOGGER.debug("inside getWebElement");
			WebElement webElement = driver.findElement(by);
			return webElement;
		}
		
	/*public static WebElement getWebElement( By by) {
		LOGGER.debug("inside getWebElement");
		WebElement webElement = SELENIUM.getDriver().findElement(by);
		return webElement;
	}*/

	public static void takeScreenShot(WebDriver driver) throws IOException {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-sss");
		String strDate = formatter.format(date);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(ScreenShotFolder + "//ScreenShot" + strDate));
	}
	
/*	public static void takeScreenShot() throws IOException {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-sss");
		String strDate = formatter.format(date);
		File scrFile = ((TakesScreenshot) SELENIUM.getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(ScreenShotFolder + "//ScreenShot" + strDate));
	}
*/
	public static String getSimpleDateFormate(Date date, String formate) {

		SimpleDateFormat formatter = new SimpleDateFormat(formate);
		String strDate = formatter.format(date);
		return strDate;
	}

	public static String getTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	/*public static String getTitle() {
		return SELENIUM.getDriver().getTitle();
	}
*/
	/**
	 * Close browser
	 */

	public static void closeBrowser(WebDriver driver) {
		LOGGER.info("############################# Inside CloseBrowser #############################");
		driver.close();
		driver.quit();
		LOGGER.debug("###################   Closed Browser #############################");
	}

	
	/*public static void closeBrowser() {
		if(SELENIUM !=null && SELENIUM.getDriver() !=null){
		LOGGER.info("Inside CloseBrowser");
		SELENIUM.getDriver().close();
		SELENIUM.getDriver().quit();
		SELENIUM.setDriver(null);
		LOGGER.debug("Closed Browser");
		}
	}*/
	
	
	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}
	
	public static void ImplicitWait(WebDriver driver) {

		LOGGER.info(" print the sysc time " + BROWSER_SYNC);
		driver.manage().timeouts().implicitlyWait(BROWSER_SYNC, TimeUnit.SECONDS);

	}
	
	
/*
	public static void ImplicitWait() {

		LOGGER.info(" print the sysn time " + BROWSER_SYNC);
		SELENIUM.getDriver().manage().timeouts().implicitlyWait(BROWSER_SYNC, TimeUnit.SECONDS);

	}*/

	
	public static void cleanFolder(int day,String folder){
		LOGGER.info("delete log from logs folder before day :"+day);
		File file=new File(System.getProperty("user.dir")+"//"+folder+"//");
		for (File file1:file.listFiles()){
			long diff = new Date().getTime()-file1.lastModified();
			if (diff>day*24*60*60*1000){
				file1.delete();
			}
			
			LOGGER.debug("Logs deleted before days :"+day);
		}
		
		
	}
	
	public static void ValidateWebElementPresence(WebDriver driver, By by) {
		LOGGER.info("Inside ValidateWebElementPresence method"); 
		WebDriverWait explicitWait = new WebDriverWait(driver, WEBLEMENT_SYNC);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(by));

		LOGGER.info("Element is present: " + by);

	}
	
	
	/*public static void ValidateWebElementPresence(By by) {
		LOGGER.info("Inside ValidateWebElementPresence method"); 
		WebDriverWait explicitWait = new WebDriverWait(SELENIUM.getDriver(),WEBLEMENT_SYNC);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(by));

		LOGGER.info("Element is present: " + by);

	}
*/
	
	/*public static void selectWebList(By by, String val, String selectBy){
		LOGGER.info("Inside selectWebList");
			WebElement wb=SELENIUM.getDriver().findElement(by);
				Select sel=new Select(wb);
				*/
	public static void selectWebList(WebDriver driver, By by, String val, String selectBy){
		LOGGER.info("Inside selectWebList");
			WebElement wb=driver.findElement(by);
				Select sel=new Select(wb);
						

	
				
				switch(selectBy){
				case "selectByVisibleText":
					sel.selectByVisibleText(val);
					LOGGER.debug("Selected by-"+selectBy);
					 break;
				case "selectbyIndex":
					sel.selectByIndex(Integer.parseInt(val));
					LOGGER.debug("Selected by-"+selectBy);
					 break;
					 
				case "SelectByvalue"://or 2+3
					sel.selectByValue(val);
					LOGGER.debug("Selected by-"+selectBy);
					 break;
					 
					 
				default:
					LOGGER.info("Proper Value not selected");
			
			}
				
				


	}
	
	public static void robotSendKeys(int keyCode) throws Throwable{
		
		Robot robot=new Robot();
		robot.keyPress(keyCode);
		robot.keyRelease(keyCode);
		
		
	}
	
	
	
	/*public static void selectItemWebTable(By by,int columnfirst,int columnSecond,String firstvalue,String secondvalue,String selectValue){
		WebElement table = SELENIUM.getDriver().findElement(by);
		 List<WebElement> rows=table.findElements(By.tagName("tr"));*/
		 
		 
	public static void selectItemWebTable(WebDriver driver,By by,int columnfirst,int columnSecond,String firstvalue,String secondvalue,String selectValue){
		WebElement table = driver.findElement(by);
		 List<WebElement> rows=table.findElements(By.tagName("tr"));

		 
		 

	 
	
   for (WebElement eachRow : rows) 
   {
   	List<WebElement> columns =eachRow.findElements(By.tagName("td"));
   	//OR...
   	// for (int i=1;i<rows.size();i++){
   	// List<WebElement> columns =rows.get(i).findElements(By.tagName("td"));//no. of columns in that particular row
			 

       if((columns.get(columnfirst).findElement(By.tagName("a")).getText()).contains(firstvalue) && (columns.get(columnSecond).findElement(By.tagName("a")).getText()).contains(secondvalue) )
       {

         
         WebElement wb=columns.get(6).findElement(By.tagName("select"));
         Select sel = new Select(wb);
         sel.selectByVisibleText(selectValue);
         break;

       }
}
	
	
	
}
 public static void robotUploadFile(String filePath) throws Throwable{

	 StringSelection stringSelection = new StringSelection(filePath);
	   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        
        //Thread.sleep(3000);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.keyPress(KeyEvent.VK_ENTER);
       // robot.keyRelease(KeyEvent.VK_ENTER);
		
		
	}
	
}
