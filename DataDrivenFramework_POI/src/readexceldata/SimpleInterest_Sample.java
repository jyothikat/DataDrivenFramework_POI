package readexceldata;

import org.testng.annotations.Test;

import lib.ExcelDataConfig;

import java.io.IOException;

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
		
		ExcelDataConfig excel = new ExcelDataConfig(".\\TestData\\Testdata.xlsx");
		int rowcount = excel.getRowCount("SimpleInterest");

		for (int i = 1; i <= rowcount; i++) {
			
			int principal = (int) excel.getNumericData("SimpleInterest", i, 0);
			int interest = (int) excel.getNumericData("SimpleInterest", i, 1);
			int period = (int) excel.getNumericData("SimpleInterest", i, 2);
			String frequency = excel.getStringData("SimpleInterest", i, 3);
			double maturityvalue =  excel.getNumericData("SimpleInterest", i, 4);
			System.out.println("The expected maturity value is "+maturityvalue);

			driver.findElement(By.id("principal")).sendKeys(String.valueOf(principal));
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
				excel.setcellData("SimpleInterest", i, 5, "Pass", ".\\TestData\\Testdata.xlsx");
				
			} else {
				System.out.println("Test failed");
				excel.setcellData("SimpleInterest", i, 5, "Fail", ".\\TestData\\Testdata.xlsx");
			}

		}
		
		driver.quit();
		
	}

}
