package readexceldata;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import lib.ExcelDataConfig;

public class CompoundInterest2_ExcelUtils {
	WebDriver driver;

	@Test
	public void f() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.moneycontrol.com/fixed-income/calculator/yes-bank/fixed-deposit-calculator-YB-BYB001.html?classic=true");
		driver.manage().window().maximize();
		Thread.sleep(3000);

		ExcelDataConfig excel = new ExcelDataConfig(".\\TestData\\Testdata.xlsx");
		
		int rowcount = excel.getRowCount("CompoundInterest");
		
		for (int i = 1; i <= rowcount; i++) {
			
			int principal = (int) excel.getNumericData("CompoundInterest", i, 0);
			int interest = (int) excel.getNumericData("CompoundInterest", i, 1);
			int period = (int) excel.getNumericData("CompoundInterest", i, 2);
			String frequency = excel.getStringData("CompoundInterest", i, 3);
			double maturityvalue_expected = excel.getNumericData("CompoundInterest", i, 4);

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
				excel.setcellData("CompoundInterest", i, 5, "Pass", ".\\TestData\\Testdata.xlsx");
				
			} else {
				System.out.println("Test Failed");
				excel.setcellData("CompoundInterest", i, 5, "Fail", ".\\TestData\\Testdata.xlsx");
			}
		}

		driver.quit();
		System.out.println("Updated test results successfully");
	}
}
