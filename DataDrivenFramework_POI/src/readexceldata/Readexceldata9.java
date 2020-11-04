package readexceldata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Readexceldata9 {

	public static void main(String[] args) throws IOException {
		
		FileInputStream fipath = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fipath);
		XSSFSheet sheet = workbook.getSheet("SimpleInterest");
		
		//Count no. of rows
		int rowcount = sheet.getLastRowNum();
		System.out.println("Total rows in excel are: "+rowcount);
		
		for (int i = 0; i <= rowcount; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				XSSFCell cell = row.getCell(j);
				System.out.print(cell.toString()+"   ||   ");
			}
			System.out.println();
		}
		

	}

}
