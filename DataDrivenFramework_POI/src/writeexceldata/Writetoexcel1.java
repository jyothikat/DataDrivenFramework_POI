package writeexceldata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Writetoexcel1 {

	public static void main(String[] args) throws IOException {
		
		FileInputStream fipath = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fipath);
		XSSFSheet sheet = workbook.getSheet("FBLogin");
				
		Row row = sheet.getRow(0);
		Cell cell = row.createCell(2);
		cell.setCellValue("Test Results");
		FileOutputStream fos = new FileOutputStream(".\\TestData\\Testdata.xlsx");
		workbook.write(fos);
		fos.close();
		System.out.println("Created column successfully");


	}

}
