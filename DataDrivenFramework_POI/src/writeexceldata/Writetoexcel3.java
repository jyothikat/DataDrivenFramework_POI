package writeexceldata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Writetoexcel3 {

	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Contact");
		
		Row r = sheet.getRow(0);
		Cell c = r.createCell(3);
		c.setCellValue("Age");
		
		FileOutputStream fos = new FileOutputStream(".\\TestData\\Testdata.xlsx");
		workbook.write(fos);
		fos.close();
		workbook.close();
		System.out.println("The column added successfully");

	}

}
