package readexceldata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Readexceldata7 {
	
	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Contact");
		
		//Count no. of rows
		int rowcount = sheet.getLastRowNum();
		System.out.println("Total no. of rows are "+rowcount);
		
//		//Count no. of columns
//		int colcount = sheet.getRow(0).getLastCellNum();
//		System.out.println("Total no. of columns are "+colcount);
		System.out.println("The excel values are: ");
		
		for (int i = 0; i <= rowcount; i++) {
			Row r = sheet.getRow(i);
			for (int j = 0; j < r.getLastCellNum(); j++) {
				Cell c = r.getCell(j);
				System.out.print(c.getStringCellValue()+"    ||   ");
			}
			System.out.println();
	}
			
		
		
		
	}

}
