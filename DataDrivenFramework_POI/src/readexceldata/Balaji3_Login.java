package readexceldata;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Balaji3_Login {

	WebDriver driver;

	@Test
	public void f() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

			
		FileInputStream fipath = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fipath);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		int rowcount = sheet.getLastRowNum();
		
		for (int i = 1; i <= rowcount; i++) {
			XSSFRow row = sheet.getRow(i);
			
			String firstname = row.getCell(0).getStringCellValue();
			String lastname = row.getCell(1).getStringCellValue();
			long mobile = (long) row.getCell(2).getNumericCellValue();
			String addressline1 = row.getCell(3).getStringCellValue();
			String addressline2 = row.getCell(4).getStringCellValue();
			String city = row.getCell(5).getStringCellValue();
			String country = row.getCell(6).getStringCellValue();
			String state = row.getCell(7).getStringCellValue();
			int zipcode = (int) row.getCell(8).getNumericCellValue();
			String photoidproof = row.getCell(9).getStringCellValue();
			String photoidcard = row.getCell(10).getStringCellValue();
			String username = row.getCell(11).getStringCellValue();
			String password = row.getCell(12).getStringCellValue();
			String confirmpassword = row.getCell(13).getStringCellValue();
			
				
			driver.get("https://tirupatibalaji.ap.gov.in/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			driver.findElement(By.linkText("Sign Up")).click();
			Thread.sleep(3000);
			
			driver.findElement(By.name("fName")).sendKeys(firstname);
			driver.findElement(By.name("lName")).sendKeys(lastname);
			driver.findElement(By.id("indiMobile")).sendKeys(String.valueOf(mobile));
			driver.findElement(By.xpath("(//input[@name='gender'])[2]")).click();
			driver.findElement(By.name("address1")).sendKeys(addressline1);
			driver.findElement(By.name("address2")).sendKeys(addressline2);
			driver.findElement(By.name("cityS")).sendKeys(city);

			Select countrydd = new Select(driver.findElement(By.name("countryS")));
			countrydd.selectByVisibleText(country);

			Select statedd = new Select(driver.findElement(By.name("stateS")));
			statedd.selectByVisibleText(state);

			driver.findElement(By.name("zipCode")).sendKeys(String.valueOf(zipcode));

			Select photoid = new Select(driver.findElement(By.name("proofS")));
			photoid.selectByVisibleText(photoidproof);

			driver.findElement(By.id("proofid")).sendKeys(photoidcard);
			driver.findElement(By.name("emailId")).sendKeys(username);
			driver.findElement(By.name("password")).sendKeys(password);
			driver.findElement(By.name("repassword")).sendKeys(confirmpassword);
			driver.findElement(By.id("regi_continue")).click();
			Thread.sleep(6000);

			
		}
		
		driver.quit();
	}
}
