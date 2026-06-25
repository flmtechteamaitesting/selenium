import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class MasterElementsDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		try {
			
			
			driver.get("https://trytestingthis.netlify.app/");
			driver.manage().window().maximize();
			
			WebElement dropdown = driver.findElement(By.id("option"));
		
			
			Select selectOption = new Select(dropdown);
			
			//selectOption.selectByVisibleText("Option 3");
			//selectOption.selectByValue("option 2");
			selectOption.selectByIndex(1);
			
			
		} catch (Exception e) {
			
			System.out.println("Exception Block");
			
		}

	}

}
