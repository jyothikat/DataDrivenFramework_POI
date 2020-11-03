package readexceldata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Readexcelsheet8 {

	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("GmailLogin");
		
		//Count the no. of rows
		int rowcount = sheet.getLastRowNum();
		System.out.println("The no. of rows in the excel are: "+(rowcount+1));
		
		for (int i = 0; i <= rowcount; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				XSSFCell cell = row.getCell(j);
				System.out.print(cell.getStringCellValue()+"   ||   ");
			}
			System.out.println();
		}
		
		
	}

}
