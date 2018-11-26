package Object.Repo.Demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	
	public static Properties prop;
   public static ExtentReports extent;
   public static ExtentTest test;
   public static WebDriver d;
   public static WebElement element;
  public static TakesScreenshot shot;
	public static File newfile;
	
	public static void initialize() throws Throwable
	{
		try {
			extent = ExtentManager.getInstance();
			test  = extent.startTest("Properties detials are readed");
			File file = new File("C:\\Users\\vivekp\\test\\Demo\\src\\test\\resources\\projectconfig.properties");
			prop = new Properties();
			FileInputStream input = new FileInputStream(file);
			prop.load(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			ExcelReader.writeLog("Error occur at Propertied initialize method");
			ExcelReader.writeLog("Exception is:" + e.getMessage());
		}
		
		
	}

	
	public static void initiateDriver(String Browser)
	{
		
		
  try {
	if(Browser.equalsIgnoreCase("firefox"))
	 {
		 System.setProperty("webdriver.gecko.driver", prop.getProperty("Driverpath"));
		 test.log(LogStatus.INFO, "Browser initiatlise", "Firefox Browser Initiated");
		 d = new FirefoxDriver();
	 }
	  else if(Browser.equalsIgnoreCase("chrome"))
	  {
		  System.setProperty("webdriver.chrome.driver", prop.getProperty("Driverpath"));
		  test.log(LogStatus.INFO, "Browser initiatlise", "Chrome Browser Initiated");
		  d = new ChromeDriver();
	  }
	
	
	d.manage().window().maximize();
	d.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
} catch (Exception e) {
	// TODO Auto-generated catch block
	ExcelReader.writeLog("Error occur at Browser initialize method");
	ExcelReader.writeLog("Exception is:" + e.getMessage());
}
	}
	
	public void getURl()
	{
		try {
			test.log(LogStatus.INFO, "Filpkart page", "Lanched");
			d.get(prop.getProperty("Browserpath"));
			d.manage().window().maximize();
			d.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExcelReader.writeLog("Error occur at lanching the flipkart page");
			ExcelReader.writeLog("Exception is:" + e.getMessage());
		}
	}
	
	public static WebElement getElements(String locatorkey)
	{
		

		if(locatorkey.endsWith("_class"))
		{
			
			element = d.findElement(By.className(prop.getProperty(locatorkey)));
		}
		
		else if(locatorkey.endsWith("_xpath"))
		{
			
			element = d.findElement(By.xpath(prop.getProperty(locatorkey)));
		}
		
		
		return element;

	}
	
	public void getUsername(String user, String data)
	{
		try {
			test.log(LogStatus.INFO, "Username is ", user);
			getElements(user).sendKeys(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExcelReader.writeLog("Error occur in username");
			ExcelReader.writeLog("Exception is:" + e.getMessage());
		}
	}
	
	public void getPassword(String pass, String data) throws Throwable
	{
		try {
			test.log(LogStatus.INFO, "Password is ", "*****");
			getElements(pass).sendKeys(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			ExcelReader.writeLog("Error occur in password");
			ExcelReader.writeLog("Exception is:" + e.getMessage());
			
			test.log(LogStatus.FAIL, "error Occured", "failing the test case");
			BaseTest.screenShot();	
			BaseTest.testend();
			
			
		}
	}
	
	
	public void clickSubmit(String value)
	{
		try {
			test.log(LogStatus.INFO, "click on submit button", "user name and Password verified");
			getElements(value).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExcelReader.writeLog("Error occur in submit");
			ExcelReader.writeLog("Exception is:" + e.getMessage());
		}
	}
	
	public void cardList(String value)
	{
		try {
			test.log(LogStatus.INFO, "clicking a card ", "to move to wish list");
			getElements(value).click();;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExcelReader.writeLog("Error occur in card");
			ExcelReader.writeLog("Exception is:" + e.getMessage());
		}
	}
	public void wishList(String value)
	{
		try {
			test.log(LogStatus.INFO, "clicking a wishlist ", "to move to logout screen");
			getElements(value).click();;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExcelReader.writeLog("Error occur in wishlist");
			ExcelReader.writeLog("Exception is:" + e.getMessage());
		}
	}
	
	public void logOut(String value)
	{
		try {
			test.log(LogStatus.INFO, "clicking a logout ", "Test case end");
			getElements(value).click();;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExcelReader.writeLog("Error occur in logout");
			ExcelReader.writeLog("Exception is:" + e.getMessage());
		}
	}
	
	
	public static void screenShot() throws Throwable
	{
		
		try {
			Date date = new Date();
			test.log(LogStatus.INFO, "taking screen of failed one");
			String path = "//ScreenShot//" + date.toString().replace(" ", "_").replace(":", "_") + ".png";
			newfile = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(newfile, new File(System.getProperty("user.dir") + path));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExcelReader.writeLog("Error occur in screenshot");
			ExcelReader.writeLog("Exception is:" + e.getMessage());
		}
		
	}
	

	
	public static void testend()
	{
		 extent.endTest(test);
		extent.flush();
		extent.close();
		d.quit();
	}
	}
