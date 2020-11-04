package readexceldata;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SimpleInterest2 {
	
	WebDriver driver;
	
  @Test
  public void f() throws InterruptedException {
	  
	  System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.get("https://www.moneycontrol.com/fixed-income/calculator/yes-bank/fixed-deposit-calculator-YB-BYB001.html?classic=true");
	  driver.manage().window().maximize();
	  Thread.sleep(8000);
		
		driver.findElement(By.id("principal")).sendKeys("20000");
		driver.findElement(By.id("interest")).sendKeys("10");
		driver.findElement(By.id("tenure")).sendKeys("2");
		
		WebElement duration_dd = driver.findElement(By.id("tenurePeriod"));
		Select duration1_dd = new Select (duration_dd);
		duration1_dd.selectByVisibleText("year(s)");
		
		WebElement frequency_dd = driver.findElement(By.id("frequency"));
		Select frequency1_dd = new Select (frequency_dd);
		frequency1_dd.selectByVisibleText("Simple Interest");
		
		driver.findElement(By.xpath("//*[@id=\"fdMatVal\"]/div[2]/a[1]")).click();
		Thread.sleep(4000);
		
		String actualamount = driver.findElement(By.xpath("//span[@id='resp_matval']")).getText();
		System.out.println("The amount is: "+actualamount);
  }
}
