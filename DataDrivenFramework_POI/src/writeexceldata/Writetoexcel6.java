package writeexceldata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Writetoexcel6 {

	public static void main(String[] args) throws IOException {
		
		FileInputStream fipath = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fipath);
		XSSFSheet sheet = workbook.getSheet("GmailLogin");
		
		//Count no. of rows
		int rowcount = sheet.getLastRowNum();
		System.out.println("The no. of rows in the excel are: "+rowcount);
		
		for (int i = 1; i <= rowcount; i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell cell = row.createCell(2);
			cell.setCellValue("Pass");
			
		}
		
		FileOutputStream fos = new FileOutputStream(".\\TestData\\Testdata.xlsx");
		workbook.write(fos);
		fos.close();
		System.out.println("Updated excel successfully");

	}

}
