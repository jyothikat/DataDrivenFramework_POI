package readexceldata;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelSheetData {
	
	public static void main(String[] args) throws IOException {
		
		FileInputStream fipath = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fipath);
		XSSFSheet ss = wb.getSheet("Login");
		
		//Count the total no. of rows
		int lastrow = ss.getLastRowNum();
		System.out.println("Total no. of rows are "+(lastrow+1));
		
		String data1 = ss.getRow(1).getCell(0).getStringCellValue();
		System.out.println("Data from excel is "+data1);
		wb.close();
		
		//Get all the data from the excel
		for (int i = 0; i <= lastrow; i++) {
		
			Row r = ss.getRow(i);
			
			for (int j = 0; j < r.getLastCellNum(); j++) {
				
			Cell c = r.getCell(j);
			System.out.print(c.getStringCellValue()+"  ||  ");
				
			}
			
			System.out.println();
		}
		
	}

}
