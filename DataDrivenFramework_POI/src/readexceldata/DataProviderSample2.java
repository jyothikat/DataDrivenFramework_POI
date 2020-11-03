package readexceldata;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderSample2 {

	WebDriver driver;

	@BeforeClass
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Test (dataProvider = "dataprovider")
	public void gmailLogin(String searchtext) throws InterruptedException {
	
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		Thread.sleep(6000);
		
		driver.findElement(By.name("q")).sendKeys(searchtext);
		Thread.sleep(6000);
		WebElement search = driver.findElement(By.xpath("(//input[@name='btnK'])[2]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", search);
		Thread.sleep(6000);
		
		
	}
	
	@DataProvider(name = "dataprovider")
	public Object [][] getData() {
		
		Object [][] testdata = new Object [4][1];
		
		testdata [0][0] = "Selenium";
			
		testdata [1][0] = "TestNG";
				
		testdata [2][0] = "Apache POI";
				
		testdata [3][0] = "Maven";
		
		
		return testdata;
		
	}
}
