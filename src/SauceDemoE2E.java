import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class SauceDemoE2E {
    public static void main(String[] args) {
        
        // 1. Setup: Initialize Chrome Driver
       // WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
        try {
            // Implicit Wait to handle element loading
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

            // 2. Navigation: Open Website
            driver.get("https://www.saucedemo.com/");

            // 3. Login Flow
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            System.out.println("Login Successful.");

            // 4. Select Product: Click on 'Sauce Labs Backpack'
            driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).click();
            System.out.println("Product Selected.");

            // 5. Add to Cart
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            System.out.println("Product Added to Cart.");

            // 6. Navigate to Cart
            driver.findElement(By.className("shopping_cart_link")).click();

            // 7. Checkout Flow
            driver.findElement(By.id("checkout")).click();
            
            // Fill Checkout Information
            driver.findElement(By.id("first-name")).sendKeys("Kishore");
            driver.findElement(By.id("last-name")).sendKeys("Dubakunta");
            driver.findElement(By.id("postal-code")).sendKeys("560001");
            driver.findElement(By.id("continue")).click();
            
            // Finish Checkout
            driver.findElement(By.id("finish")).click();
            
            // Verify Success Message
            String successMsg = driver.findElement(By.className("complete-header")).getText();
            System.out.println("Checkout Status: " + successMsg);

            // 8. Signout Flow
            // Click Burger Menu
            driver.findElement(By.id("react-burger-menu-btn")).click();
            // Click Logout (using LinkText)
            driver.findElement(By.id("logout_sidebar_link")).click();
            System.out.println("Signout Successful.");

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // 9. Close Browser
            driver.quit();
        }
    }
}
