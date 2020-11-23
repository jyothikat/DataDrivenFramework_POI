package readexceldata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Readexcelsheet10 {
	
	WebDriver driver;
	
  @Test
  public void f() throws IOException {
	  
//	  System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
//	  driver = new ChromeDriver();
	  
	  FileInputStream fipath = new FileInputStream(".\\TestData\\Testdata.xlsx");
	  XSSFWorkbook workbook = new XSSFWorkbook(fipath);
	  XSSFSheet sheet = workbook.getSheet("SimpleInterest");
	  
	  //Count no. of rows
	  int rowcount = sheet.getLastRowNum();
	  System.out.println("The no. of rows in the excel are: "+(rowcount+1));
	  
	  for (int i=0; i<=rowcount; i++) {
		  XSSFRow row = sheet.getRow(i);
		  
		  for (int j=0; j<row.getLastCellNum(); j++) {
			  XSSFCell cell = row.getCell(j);
			  System.out.print(cell.toString()+"  ||  ");
		  }
		  System.out.println();
	  }
  }
}
