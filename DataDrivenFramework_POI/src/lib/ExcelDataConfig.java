package lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {

	XSSFWorkbook workbook;
	XSSFSheet sheet;
	

	public ExcelDataConfig(String excelPath) {

		try {
			File src = new File(excelPath);
			FileInputStream fis = new FileInputStream(src);
			workbook = new XSSFWorkbook(fis);

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}

	public int getRowCount(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		return rowCount;

	}

	public String getStringData(String sheetName, int Row, int Column) {
		sheet = workbook.getSheet(sheetName);
		String data = sheet.getRow(Row).getCell(Column).getStringCellValue();
		return data;

	}

	public double getNumericData(String sheetName, int Row, int Column) {
		sheet = workbook.getSheet(sheetName);
		double data = sheet.getRow(Row).getCell(Column).getNumericCellValue();
		return data;

	}

	public void setcellData(String sheetName, int Row, int Column, String Result, String excelPath) throws IOException {
		sheet = workbook.getSheet(sheetName);
		sheet.getRow(Row).createCell(Column).setCellValue(Result);

		FileOutputStream fos = new FileOutputStream(excelPath);
		workbook.write(fos);
		fos.close();

	}

}
