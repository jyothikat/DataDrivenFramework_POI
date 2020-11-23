package readexceldata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class Program1 {

	WebDriver driver;

	@Test
	public void f() throws InterruptedException, IOException {

		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.get(
				"https://www.moneycontrol.com/fixed-income/calculator/yes-bank/fixed-deposit-calculator-YB-BYB001.html?classic=true");
		driver.manage().window().maximize();
		Thread.sleep(2000);

		FileInputStream fipath = new FileInputStream(".\\TestData\\Testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fipath);
		XSSFSheet sheet = workbook.getSheet("SimpleInterest");

		int rowcount = sheet.getLastRowNum();

		for (int i = 1; i <= rowcount; i++) {

			XSSFRow row = sheet.getRow(i);

			int principle = (int) row.getCell(0).getNumericCellValue();
			int rateofinterest = (int) row.getCell(1).getNumericCellValue();
			int period = (int) row.getCell(2).getNumericCellValue();
			String frequency = row.getCell(3).getStringCellValue();
			double maturityvalue = row.getCell(4).getNumericCellValue();

			driver.findElement(By.id("principal")).sendKeys(String.valueOf(principle));
			driver.findElement(By.id("interest")).sendKeys(String.valueOf(rateofinterest));
			driver.findElement(By.id("tenure")).sendKeys(String.valueOf(period));

			WebElement duration = driver.findElement(By.id("tenurePeriod"));
			Select duration_dd = new Select(duration);
			duration_dd.selectByVisibleText("year(s)");

			WebElement frequencydd = driver.findElement(By.id("frequency"));
			Select frequencydd_1 = new Select(frequencydd);
			frequencydd_1.selectByVisibleText(frequency);

			driver.findElement(By.xpath("//*[@id=\"fdMatVal\"]/div[2]/a[1]/img")).click();
			Thread.sleep(2000);

			String actualamount = driver.findElement(By.id("resp_matval")).getText();
			System.out.println(actualamount);

			driver.findElement(By.xpath("//*[@id=\"fdMatVal\"]/div[2]/a[2]/img")).click();
			Thread.sleep(2000);

			if (Double.parseDouble(actualamount) == maturityvalue) {
				XSSFCell cell = row.createCell(5);
				cell.setCellValue("Pass");

			} else {
				XSSFCell cell = row.createCell(5);
				cell.setCellValue("Fail");
			}

		}

		driver.quit();
		FileOutputStream fos = new FileOutputStream(".\\TestData\\Testdata.xlsx");
		workbook.write(fos);
		fos.close();
		System.out.println("The test results updated successfully");
	}
}
