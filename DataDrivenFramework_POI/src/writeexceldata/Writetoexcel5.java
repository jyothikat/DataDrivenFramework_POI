package writeexceldata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Writetoexcel5 {

	public static void main(String[] args) throws IOException {
		
		FileInputStream fipath = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fipath);
		XSSFSheet sheet = workbook.getSheet("GmailLogin");
		
		XSSFRow row = sheet.getRow(0);
		XSSFCell cell = row.createCell(2);
		cell.setCellValue("TestResults");
		
		FileOutputStream fos = new FileOutputStream(".\\TestData\\Testdata.xlsx");
		workbook.write(fos);
		fos.close();
		System.out.println("Added the column in the excel successfully");
		
		
	}

}
