package readexceldata;

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
import org.testng.annotations.Test;

public class CompoundInterest {
	WebDriver driver;

	@Test
	public void f() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.moneycontrol.com/fixed-income/calculator/yes-bank/fixed-deposit-calculator-YB-BYB001.html?classic=true");
		driver.manage().window().maximize();
		Thread.sleep(3000);

		FileInputStream fipath = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fipath);
		XSSFSheet sheet = workbook.getSheet("CompoundInterest");

		int rowcount = sheet.getLastRowNum();
		System.out.println("Total rows in excel are: " + rowcount);

		for (int i = 1; i <= rowcount; i++) {
			XSSFRow row = sheet.getRow(i);

			int principal = (int) row.getCell(0).getNumericCellValue();
			int interest = (int) row.getCell(1).getNumericCellValue();
			int period = (int) row.getCell(2).getNumericCellValue();
			String frequency = row.getCell(3).getStringCellValue();
			double maturityvalue_expected = row.getCell(4).getNumericCellValue();

			System.out.println("Expected mvalue: " + maturityvalue_expected);

			driver.findElement(By.id("principal")).sendKeys(String.valueOf(principal));
			driver.findElement(By.id("interest")).sendKeys(String.valueOf(interest));
			driver.findElement(By.id("tenure")).sendKeys(String.valueOf(period));

			WebElement tenureperiod = driver.findElement(By.id("tenurePeriod"));
			Select tenureperiod_dd = new Select(tenureperiod);
			tenureperiod_dd.selectByVisibleText("year(s)");

			WebElement freq = driver.findElement(By.id("frequency"));
			Select freq_dd = new Select(freq);
			freq_dd.selectByVisibleText(frequency);

			driver.findElement(By.xpath("//*[@id=\"fdMatVal\"]/div[2]/a[1]/img")).click();
			Thread.sleep(2000);
			String maturityvalue_actual = driver.findElement(By.id("resp_matval")).getText();
			System.out.println(maturityvalue_actual);
			driver.findElement(By.xpath("//*[@id=\"fdMatVal\"]/div[2]/a[2]/img")).click();

			if (Double.parseDouble(maturityvalue_actual) == maturityvalue_expected) {
				System.out.println("Test Passed");
				XSSFCell cell = row.createCell(5);
				cell.setCellValue("Test Passed");

			} else {
				System.out.println("Test Failed");
				XSSFCell cell = row.createCell(5);
				cell.setCellValue("Test Failed");
			}
		}

		driver.quit();
		FileOutputStream fos = new FileOutputStream(".\\TestData\\Testdata.xlsx");
		workbook.write(fos);
		fos.close();
		System.out.println("Updated test results in the document successfully");
	}
}
