package readexceldata;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Readexcelsheet4 {

	public static void main(String[] args) throws IOException {
		
		
		FileInputStream fipath = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fipath);
		XSSFSheet sheet = workbook.getSheet("Employee");

		// Count no. of rows
		int rowcount = sheet.getLastRowNum();
		System.out.println("Total no. of rows are " + rowcount);

//		Count no. of columns
//		int colcount = sheet.getRow(0).getLastCellNum();
//		System.out.println("Total no. of columns are " + colcount);
		workbook.close();

		for (int i = 0; i <= rowcount; i++) {
			XSSFRow r = sheet.getRow(i);

			for (int j = 0; j < r.getLastCellNum(); j++) {
				XSSFCell c = r.getCell(j);
				System.out.print(c.toString() + " ||  ");

			}
			System.out.println();
			
		}

	}

}
