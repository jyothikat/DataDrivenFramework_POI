package readexceldata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TirupatiBalaji_Login {

	WebDriver driver;

	@BeforeClass

	public void setUp() {

		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Test
	public void balaji_register() throws IOException, InterruptedException {


		FileInputStream fipath = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fipath);
		XSSFSheet sheet = workbook.getSheet("BalajiLogin");

		// Count total no. of rows
		int rowcount = sheet.getLastRowNum();
		System.out.println("Total no. of rows are "+rowcount);

		for (int i = 1; i <= rowcount; i++) {

			XSSFRow row = sheet.getRow(i);
			
			XSSFCell cell0 = row.getCell(0);
			XSSFCell cell1 = row.getCell(1);
			XSSFCell cell2 = row.getCell(2);
			XSSFCell cell3 = row.getCell(3);
			XSSFCell cell4 = row.getCell(4);
			XSSFCell cell5 = row.getCell(5);
			XSSFCell cell6 = row.getCell(6);
			XSSFCell cell7 = row.getCell(7);
			XSSFCell cell8 = row.getCell(8);
			XSSFCell cell9 = row.getCell(9);
			XSSFCell cell10 = row.getCell(10);
			XSSFCell cell11 = row.getCell(11);
			XSSFCell cell12 = row.getCell(12);
			XSSFCell cell13 = row.getCell(13);

			driver.get("https://tirupatibalaji.ap.gov.in/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			driver.findElement(By.linkText("Sign Up")).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			driver.findElement(By.name("fName")).sendKeys(cell0.getStringCellValue());
			driver.findElement(By.name("lName")).sendKeys(cell1.getStringCellValue());
			
			DataFormatter formatter = new DataFormatter();
			String mobileNumber = formatter.formatCellValue(cell2);
			driver.findElement(By.name("mobNo")).sendKeys(mobileNumber);
			

			driver.findElement(By.xpath("(//input[@name='gender'])[2]")).click();
			driver.findElement(By.name("address1")).sendKeys(cell3.getStringCellValue());
			driver.findElement(By.name("address2")).sendKeys(cell4.getStringCellValue());
			driver.findElement(By.name("cityS")).sendKeys(cell5.getStringCellValue());

			Select country = new Select(driver.findElement(By.name("countryS")));
			country.selectByVisibleText(cell6.getStringCellValue());
			Select state = new Select(driver.findElement(By.name("stateS")));
			state.selectByVisibleText(cell7.getStringCellValue());
			
			DataFormatter formatter1 = new DataFormatter();
			String zipcode = formatter1.formatCellValue(cell8);
			driver.findElement(By.name("zipCode")).sendKeys(zipcode);

			//driver.findElement(By.name("zipCode")).sendKeys(cell8.getStringCellValue());

			Select photoid = new Select(driver.findElement(By.name("proofS")));
			photoid.selectByVisibleText(cell9.getStringCellValue());

			driver.findElement(By.id("proofid")).sendKeys(cell10.getStringCellValue());
			driver.findElement(By.name("emailId")).sendKeys(cell11.getStringCellValue());
			driver.findElement(By.name("password")).sendKeys(cell12.getStringCellValue());
			driver.findElement(By.name("repassword")).sendKeys(cell13.getStringCellValue());
			driver.findElement(By.id("regi_continue")).click();
			Thread.sleep(6000);

		}
	}

 
}
