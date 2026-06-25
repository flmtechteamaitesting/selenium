import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class KeysboardDemo {

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.google.com/");
		
	WebElement searchbox = 	driver.findElement(By.name("q"));
	
	Actions actions = new Actions(driver);
	
actions.sendKeys(searchbox, "Selenium Webdriver info");
	
	actions.sendKeys(Keys.TAB).perform();
	actions.sendKeys(Keys.TAB).perform();
	actions.sendKeys(Keys.F5).perform();
	
	actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();

	
	
	

	
	

	}

}
