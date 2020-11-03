package writeexceldata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Writedatatoexcel {

	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Employee");
		
		int rowcount = sheet.getLastRowNum();
		
		for (int i=1; i<=rowcount; i++) {
		
			Row row = sheet.getRow(i);
			Cell cell = row.createCell(2);
			cell.setCellValue("Yes");
						
		}
		
		FileOutputStream fos = new FileOutputStream(".\\TestData\\Testdata.xlsx");
		workbook.write(fos);
		fos.close();
		System.out.println("Updated excel sucessfully");
	}

}
