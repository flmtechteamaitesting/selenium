import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;

public class SeleniumScrollAndDragDrop {
    public static void main(String[] args) {
        // 1. Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            // 2. Navigate to the site
            driver.get("https://jqueryui.com/droppable/");

            // --- SCROLL SCENARIO START ---
            // 3. Locate the iframe (or any element you need to scroll to)
            WebElement demoFrame = driver.findElement(By.className("demo-frame"));

            // 4. Use JavascriptExecutor to scroll the element into view
            JavascriptExecutor js = (JavascriptExecutor) driver;

             //Alternative Scroll Methods:
              js.executeScript("window.scrollBy(0,500)"); // Scroll down by 500 pixels
              Thread.sleep(4000);
              
              js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // Scroll to bottom
              
              Thread.sleep(4000);          
            // --- SCROLL SCENARIO END ---

            // 5. Switch to the frame to interact with elements inside
            driver.switchTo().frame(demoFrame);

            // 6. Locate Source and Target elements
            WebElement source = driver.findElement(By.id("draggable"));
            WebElement target = driver.findElement(By.id("droppable"));

            // 7. Perform Drag and Drop using Actions class
            Actions actions = new Actions(driver);
            actions.dragAndDrop(source, target).perform();

            // 8. Verification
            if (target.getText().equals("Dropped!")) {
                System.out.println("Success: Scrolled and Dropped successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}