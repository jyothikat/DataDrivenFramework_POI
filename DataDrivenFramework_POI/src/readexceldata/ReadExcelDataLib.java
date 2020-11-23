package readexceldata;

import org.testng.annotations.Test;

import lib.ExcelDataConfig;

public class ReadExcelDataLib {
	
		
  @Test
  public void f() {
	  
	 ExcelDataConfig excel = new ExcelDataConfig(".\\TestData\\Testdata.xlsx");
	   
	 System.out.println(excel.getStringData("FBLogin", 2, 0));
	  
	  
  }
}
