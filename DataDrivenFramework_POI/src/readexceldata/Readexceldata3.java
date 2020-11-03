package readexceldata;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Readexceldata3 {

	public static void main(String[] args) throws IOException {
		
		FileInputStream fipath = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fipath);
		XSSFSheet sheet = workbook.getSheet("Contact");
		
		//Print the last row of the sheet
		int rowcount = sheet.getLastRowNum();
		System.out.println("Total number of rows are "+rowcount);
		
		//Count the last column in the sheet
//		int columncount = sheet.getRow(0).getLastCellNum();
//		System.out.println("Total number of columns are "+columncount);

		for (int i=0; i<=rowcount; i++) {
			XSSFRow r = sheet.getRow(i);
			for (int j=0; j<r.getLastCellNum(); j++) {
				XSSFCell c = r.getCell(j);
				System.out.print(c.getStringCellValue()+"  ||  ");
//				System.out.print(c.toString()+"  ||  ");
			}
			System.out.println();	
		}
			

	}

}
