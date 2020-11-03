package readexceldata;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FBLogin {

	WebDriver driver;

	@BeforeClass
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Test
	public void f() throws IOException, InterruptedException {

		FileInputStream fipath = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fipath);
		XSSFSheet sheet = workbook.getSheet("FBLogin");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row r = sheet.getRow(i);
			Cell cell0 = r.getCell(0);
			Cell cell1 = r.getCell(1);

			driver.get("https://www.facebook.com");
			driver.manage().window().maximize();
			Thread.sleep(3000);
			driver.findElement(By.name("email")).sendKeys(cell0.getStringCellValue());
			driver.findElement(By.id("pass")).sendKeys(cell1.getStringCellValue());
			driver.findElement(By.name("login")).click();
			Thread.sleep(10000);
			String actualtitle = driver.getTitle();
			System.out.println(actualtitle);

			if (actualtitle.equalsIgnoreCase("Facebook")) {
				System.out.println("Login successful");
				Cell cell = r.createCell(2);
				cell.setCellValue("Pass");

			} else {
				System.out.println("Login failed");
				Cell cell = r.createCell(2);
				cell.setCellValue("Fail");
			}
			
			FileOutputStream fos = new FileOutputStream(".\\TestData\\Testdata.xlsx");
			workbook.write(fos);
			fos.close();
			System.out.println("Updated the test results successfully");


		}

	}
	
	@AfterMethod
	public void close() {
		driver.quit();
	}
	
}
