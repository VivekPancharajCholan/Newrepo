package Object.Repo.Demo;



import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class Controller {

	//public static void main(String[] args) throws Throwable
	public static ExtentManager extent;
	  public static ExtentTest test2;
	@Test
	public void execution() throws Throwable
	{
		// TODO Auto-generated method stub
		
	List<UserDetails> excel= ExcelReader.getTestData();
		
		BaseTest test = new BaseTest();
	    BaseTest.initialize();
      BaseTest.initiateDriver(excel.get(0).getBrowser());
	      test.getURl();
	     
	     for(int x = 0; x<=2;x++)
       {
    	   if(excel.get(x).getExecute().equalsIgnoreCase("Yes"))
    	   {
      test.getUsername("UserName_xpath", excel.get(x).getUsername());
      test.getPassword("password_xpath", excel.get(x).getPassword());
      test.clickSubmit("submit_xpath");
      Thread.sleep(3000);
      test.cardList("card_xpath");
      test.wishList("wish_class");
      test.logOut("logOut_xpath");
      
    
    	   }
    	   else if(excel.get(x).getExecute().equalsIgnoreCase("No"))
    	   {
    		   
    		 System.out.println("Test case not executable mode");
    	   }
       }
      
     BaseTest.testend();
	}

	
}
