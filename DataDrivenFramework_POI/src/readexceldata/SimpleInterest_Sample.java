package readexceldata;

import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;

public class SimpleInterest_Sample {

	WebDriver driver;

	@BeforeClass
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Test
	public void f() throws IOException, InterruptedException {
		
		driver.get("https://www.moneycontrol.com/fixed-income/calculator/yes-bank/fixed-deposit-calculator-YB-BYB001.html?classic=true");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		
		FileInputStream fipath = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fipath);
		XSSFSheet sheet = workbook.getSheet("SimpleInterest");

		int rowcount = sheet.getLastRowNum();
		System.out.println("No. of rows are: " + rowcount);

		for (int i = 1; i <= rowcount; i++) {
			XSSFRow row = sheet.getRow(i);

//			XSSFCell principlecell = row.getCell(0);
//			int principle = (int) principlecell.getNumericCellValue();

			int principle = (int) row.getCell(0).getNumericCellValue(); // type casting for numeric value
			int interest = (int) row.getCell(1).getNumericCellValue();
			int period = (int) row.getCell(2).getNumericCellValue();
			String frequency = row.getCell(3).getStringCellValue();
			int maturityvalue = (int) row.getCell(4).getNumericCellValue();

			driver.findElement(By.id("principal")).sendKeys(String.valueOf(principle));
			driver.findElement(By.id("interest")).sendKeys(String.valueOf(interest));
			driver.findElement(By.id("tenure")).sendKeys(String.valueOf(period));

			WebElement duration_dd = driver.findElement(By.id("tenurePeriod"));
			Select duration1_dd = new Select(duration_dd);
			duration1_dd.selectByVisibleText("year(s)");

			WebElement frequency_dd = driver.findElement(By.id("frequency"));
			Select frequency1_dd = new Select(frequency_dd);
			frequency1_dd.selectByVisibleText(frequency);

			driver.findElement(By.xpath("//*[@id=\"fdMatVal\"]/div[2]/a[1]")).click();
			Thread.sleep(2000);

			String actualmvalue = driver.findElement(By.xpath("//span[@id='resp_matval']")).getText();
			System.out.println("The actual maturity value is: " + actualmvalue);
			driver.findElement(By.xpath("//*[@id=\"fdMatVal\"]/div[2]/a[2]/img")).click();

			if (Double.parseDouble(actualmvalue) == maturityvalue) {
				System.out.println("Test Passed");

				row.createCell(5).setCellValue("Pass");

			} else {
				System.out.println("Test failed");
				row.createCell(5).setCellValue("Fail");
			}

		}
		
		driver.quit();
		FileOutputStream fos = new FileOutputStream(".\\TestData\\Testdata.xlsx");
		workbook.write(fos);
		fos.close();
	}

}
