package readexceldata;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Readexceldata2 {

	public static void main(String[] args) throws IOException {
		
		FileInputStream path = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(path);
		XSSFSheet ss = wb.getSheet("Contact");
		
		//Count the total number of rows
		int lastrow = ss.getLastRowNum();
		System.out.println("Total no. of rows are "+(lastrow+1));
		wb.close();
		for (int i=0; i <= lastrow; i++) {
			Row r = ss.getRow(i);
			
			for (int j=0; j < r.getLastCellNum() ; j++) {
				Cell c = r.getCell(j);
				System.out.print(c.getStringCellValue()+"  ||  ");
			}
			System.out.println();
		}
		
	}

}
