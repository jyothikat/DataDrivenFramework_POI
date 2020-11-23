package readexceldata;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import lib.ExcelDataConfig;

public class FBLogin_ExcelUtils {

	WebDriver driver;

	@BeforeClass
	public void setUp() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Test
	public void f() throws IOException, InterruptedException {

		ExcelDataConfig excel = new ExcelDataConfig(".\\TestData\\Testdata.xlsx");
		
		int rowcount = excel.getRowCount("FBLogin");
		
		System.out.println("No. of rows in the excel are: "+rowcount);
		
		for (int i = 1; i <= rowcount; i++) {

			String username = excel.getStringData("FBLogin", i, 0);
			String password = excel.getStringData("FBLogin", i, 1);

			driver.get("https://www.facebook.com");
			driver.manage().window().maximize();
			Thread.sleep(3000);
			driver.findElement(By.name("email")).sendKeys(username);
			driver.findElement(By.id("pass")).sendKeys(password);
			driver.findElement(By.name("login")).click();
			Thread.sleep(10000);
			String actualtitle = driver.getTitle();
			System.out.println(actualtitle);

			if (actualtitle.equalsIgnoreCase("Facebook")) {
				System.out.println("Login successful");
				excel.setcellData("FBLogin", i, 2, "Test Pass", ".\\TestData\\Testdata.xlsx");

			} else {
				System.out.println("Login failed");
				excel.setcellData("FBLogin", i, 2, "Test Fail", ".\\TestData\\Testdata.xlsx");
			}

		}
		System.out.println("Test Results updated successfully");
	}

	@AfterMethod
	public void close() {
		driver.quit();
	}

}
