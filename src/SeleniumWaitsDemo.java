import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.NoSuchElementException;

import java.time.Duration;


public class SeleniumWaitsDemo {
	
	public static void main (String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		
		//Implicit wait 
		
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		try {
			
			driver.manage().window().maximize();
			
			driver.get("https://the-internet.herokuapp.com/dynamic_loading/2"); 
			
			driver.findElement(By.xpath("//button[text()='Start']")).click();
			
			//explicit wait
			WebDriverWait explicitwait = new WebDriverWait(driver, Duration.ofSeconds(20));
			
			WebElement status = explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
			
			System.out.println("Explicit Wait :" + status.getText());
			
//			Wait<WebDriver> fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
//			
//			fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
			
	
			
			Thread.sleep(2000);
			
			
		}finally
		{driver.quit();}

}}
