package readexceldata;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleSearch_Sample {

	WebDriver driver;

	@BeforeClass
	public void setUp() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		

	}

	@Test
	public void f() throws InterruptedException, IOException {
		
				
		FileInputStream fipath = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fipath);
		XSSFSheet sheet = workbook.getSheet("GoogleSearch");

		// Count no. of row
		int rowcount = sheet.getLastRowNum();

		for (int i = 1; i <= rowcount; i++) {
			XSSFRow row = sheet.getRow(i);
//			XSSFCell cell0 = row.getCell(0);
			
			String searchtext = row.getCell(0).getStringCellValue();
			
			driver.get("https://www.google.com");
			driver.manage().window().maximize();
			Thread.sleep(2000);
			
			driver.findElement(By.name("q")).sendKeys(searchtext);
			Thread.sleep(6000);
			WebElement search = driver.findElement(By.xpath("(//input[@name='btnK'])[2]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", search);
			Thread.sleep(6000);
			String title = driver.getTitle();
			driver.findElement(By.name("q")).clear();
			
			if (title.contains("Google Search")) {
				System.out.println("Search was successful");
				XSSFCell cell = row.createCell(1);
				cell.setCellValue("Pass");	
			} else {
				System.out.println("Search failed");
				XSSFCell cell = row.createCell(1);
				cell.setCellValue("Fail");
			}
			
		}

		FileOutputStream fos = new FileOutputStream(".\\TestData\\Testdata.xlsx");
		workbook.write(fos);
		fos.close();
		System.out.println("Updated the test results successfully");
	}

	@AfterMethod
	public void close() {
		driver.quit();
	}

}
