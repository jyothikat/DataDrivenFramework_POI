package readexceldata;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class test {
	WebDriver driver;
  @Test
  public void f() throws InterruptedException {
	  
	  System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.get("https://www.facebook.com/");
	  driver.manage().window().maximize();
	  Thread.sleep(4000);
	  
	  driver.findElement(By.id("email")).sendKeys("h2otestingtools@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("selenium@7");
	  driver.findElement(By.name("login")).click();
	  Thread.sleep(10000);
	 	  
	  String actualtext = driver.getTitle(); //Error
	  
	  
	  System.out.println("The title is "+actualtext);
	 
  }
}
