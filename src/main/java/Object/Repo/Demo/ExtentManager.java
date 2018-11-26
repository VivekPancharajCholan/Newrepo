package Object.Repo.Demo;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	public static ExtentReports report;
	
	public static ExtentReports getInstance()
	{
		
		try {
			
			Date date = new Date();
			String FolderPath = date.toString().replace(" ", "_").replace(":", "_") + ".html";
			report = new ExtentReports(System.getProperty("user.dir")+ "//Reports//" + FolderPath, true, DisplayOrder.NEWEST_FIRST);
			report.loadConfig(new File(System.getProperty("user.dir")+ "//ReportsConfig.xml"));
			report.addSystemInfo("Enviroment", "Vivek System").addSystemInfo("Selenium", "DemoReport");
			
		} catch (Exception e) {

			ExcelReader.writeLog("Error occured at generating the Report");
			ExcelReader.writeLog("Exception is:" + e.getMessage());
		}
		return report;
		
	}

}
