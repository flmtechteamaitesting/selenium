
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;

public class ElementDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		
		//WebElement usernameField =  driver.findElement(By.id("user-name"));
		
		WebElement usernameField = driver.findElement(By.xpath("//input[@id='user-name']"));
		
		usernameField.sendKeys("standard_user");
		
		//WebElement passwordField =  driver.findElement(By.name("password"));
		
		WebElement passwordField =  driver.findElement(By.xpath("//input[@name='password']"));
		passwordField.sendKeys("secret_sauce");
		
		//WebElement loginButton =driver.findElement(By.id("login-button"));
		
		WebElement loginButton =driver.findElement(By.xpath("//input[@type='submit']"));
		
		loginButton.click();
		
		driver.quit();
	}

}
