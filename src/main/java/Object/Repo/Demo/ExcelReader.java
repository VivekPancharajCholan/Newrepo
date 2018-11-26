package Object.Repo.Demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExcelReader {
	
	public static ExtentReports report;
	public static ExtentTest test;
	public static Workbook book;
	public static Sheet sheet;
	public static UserDetails user;
	public static Properties prop;
	public static String FileExtension;
	public String filepath;
	public static List<UserDetails> getTestData() throws Throwable
	{
 
	 report = ExtentManager.getInstance();
	   test = report.startTest("Excution started");
		 prop = new Properties();
		File file1 = new File("C:\\Users\\vivekp\\test\\Demo\\src\\test\\resources\\projectconfig.properties");
		FileInputStream input1 = new FileInputStream(file1);
		prop.load(input1);
		test.log(LogStatus.INFO, "Property details are readed", "It should initiate the Process");
		ArrayList<UserDetails> list = new ArrayList<UserDetails>();
		//File file = new File("C:\\Users\\vivekp\\test\\Demo\\src\\test\\resources\\Test.xlsx");
		File file = new File(prop.getProperty("filepath"));
		String paths = prop.getProperty("filepath");
		FileExtension = paths.substring(paths.indexOf(".x"));
		
		
		try {
			if(FileExtension.equals(".xlsx"))
			{
				FileInputStream input = new FileInputStream(file);
				book = new XSSFWorkbook(input);
				
				test.log(LogStatus.INFO, "Found .xlsx sheet", "XSSFWorkbook Initiated");
			}
			else if(FileExtension.equals(".xls"))
			{
				FileInputStream input = new FileInputStream(file);
				book = new HSSFWorkbook(input);
				
				test.log(LogStatus.INFO, "Found .xls sheet", "HSSFWorkbook Initiated");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExcelReader.writeLog("Error occur in whiling reading the excel");
			ExcelReader.writeLog("Exception is:" + e.getMessage());
		}
		
		sheet = book.getSheet("Testcases");
		int rowcount = sheet.getLastRowNum() + 1;
		for(int x=1; x<rowcount; x++)
		{
			
			UserDetails user =  new UserDetails(sheet.getRow(x).getCell(0).getStringCellValue().toString(), sheet.getRow(x).getCell(1).getStringCellValue().toString(),sheet.getRow(x).getCell(2).getStringCellValue().toString(), sheet.getRow(x).getCell(3).getStringCellValue().toString(), sheet.getRow(x).getCell(4).getStringCellValue().toString());
			list.add(user);
		}
		test.log(LogStatus.INFO, "Excel file readed", "all details are stored in Userdetails");

		return list;
		
	}
	
	public static void writeLog(String logData) {
		BufferedWriter bw = null;
 		FileWriter fw = null;
      try {
			File fi = new File("C:\\Users\\vivekp\\test\\Demo\\Results\\log.txt");
			if(!fi.exists())
			{
				fi.createNewFile();
			}
			
			fw = new FileWriter(fi.getAbsolutePath(), true);
			bw = new BufferedWriter(fw);
			bw.write(logData);
			bw.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block

			ExcelReader.writeLog("Error occured at writelog Method");
			ExcelReader.writeLog("Exception is:" + e.getMessage());
		}
      
      
      finally 
		{

			try 
			{

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			}
			catch (IOException ex) 
 			{

 				ExcelReader.writeLog("Error Occured in Write Log Method.");
 				ExcelReader.writeLog("Error Description: " + ex.getMessage());
 				
 			}
      
	}


}
	
}
