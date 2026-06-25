import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.Robot;
import java.awt.event.KeyEvent;
//import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class E2Esaucedemo {
    public static void main(String[] args) {
       
     //   WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
       
        // Initialize Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
       
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

            driver.get("https://www.saucedemo.com/");

            // 3. Login Flow
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            System.out.println("Login Successful.");

            // --- NEW: HANDLE CHANGE PASSWORD ALERT/MODAL ---
            
//            Robot robot = new Robot();
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//            
            try {
                // Scenario A: It's a JavaScript Alert (Browser Popup)
                if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
                    Alert alert = driver.switchTo().alert();
                    System.out.println("JS Alert detected: " + alert.getText());
                    alert.dismiss(); // Clicks 'Cancel' or 'Close'
                }
            } catch (Exception e) {
                // Scenario B: It's an HTML Modal (Web Element)
                // Check if a "Close" or "Later" button exists for a modal
                try {
                    WebElement closeButton = driver.findElement(By.xpath("//button[text()='Later']"));
                    if (closeButton.isDisplayed()) {
                        closeButton.click();
                        System.out.println("HTML Password Modal dismissed.");
                    }
                } catch (Exception modalEx) {
                    System.out.println("No password alert or modal appeared. Continuing flow...");
                }
            }
            // -----------------------------------------------

            // 4. Select Product
            driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).click();
            System.out.println("Product Selected.");

            // 5. Add to Cart
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            System.out.println("Product Added to Cart.");

            // 6. Navigate to Cart
            driver.findElement(By.className("shopping_cart_link")).click();

            // 7. Checkout Flow
            driver.findElement(By.id("checkout")).click();
           
            driver.findElement(By.id("first-name")).sendKeys("Kishore");
            driver.findElement(By.id("last-name")).sendKeys("Dubakunta");
            driver.findElement(By.id("postal-code")).sendKeys("560001");
            driver.findElement(By.id("continue")).click();
           
            driver.findElement(By.id("finish")).click();
           
            String successMsg = driver.findElement(By.className("complete-header")).getText();
            System.out.println("Checkout Status: " + successMsg);

            // 8. Signout Flow
            driver.findElement(By.id("react-burger-menu-btn")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();
            System.out.println("Signout Successful.");

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}