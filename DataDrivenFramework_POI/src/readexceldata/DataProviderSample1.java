package readexceldata;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class DataProviderSample1 {
	
	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test(dataProvider = "dp")
	public void f(String UserName, String Password) throws InterruptedException {

		driver.get("https://www.facebook.com");
		driver.manage().window().maximize();
		Thread.sleep(6000);

		driver.findElement(By.name("email")).sendKeys(UserName);
		driver.findElement(By.id("pass")).sendKeys(Password);
		driver.findElement(By.name("login")).click();
		Thread.sleep(10000);

	}
	
	
	@DataProvider(name = "dp")
	public Object[][] getData() {

		Object[][] testData = new Object[3][2];

		testData[0][0] = "test1@gmail.com";
		testData[0][1] = "test@1";

		testData[1][0] = "test2@gmail.com";
		testData[1][1] = "test@2";

		testData[2][0] = "test3@gmail.com";
		testData[2][1] = "test@3";

		return testData;

	}
}
