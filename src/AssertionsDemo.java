import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AssertionsDemo {
	WebDriver driver;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}
	@BeforeSuite
	public void setup() {
		
		driver = new ChromeDriver(); 
		driver.get("https://www.saucedemo.com/");
	}
	
	@Test
	public void login()
	{
		
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce"); 
		driver.findElement(By.id("login-button")).click();				
		
	}

	@Test
	public void testDasboardUI() throws InterruptedException
	{

		String expectedurl="https://www.saucedemo.com/invent";
		Thread.sleep(2000);
		String actualurl = driver.getCurrentUrl();
		
		Assert.assertEquals(actualurl, expectedurl, "Login failed, URL Mismatch");
		
	}
	
	@AfterSuite
	public void closedriver()
	{
		driver.quit();
	}
}
