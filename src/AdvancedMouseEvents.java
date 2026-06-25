import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
//import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class AdvancedMouseEvents {
    public static void main(String[] args) {
       
  //      WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Actions actions = new Actions(driver);

        try {
            // --- 1. CONTEXT CLICK (Right Click) ---
            driver.get("https://the-internet.herokuapp.com/context_menu");
            WebElement hotSpot = driver.findElement(By.id("hot-spot"));
           
            // Perform Right Click
            actions.contextClick(hotSpot).perform();
            System.out.println("Right Click performed.");
            
            

            // Handle the alert that appears after right-clicking
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert text after Right Click: " + alert.getText());
            alert.accept();

            // --- 2. DOUBLE CLICK ---
            // Using a demo site designed for double clicks
            driver.get("https://demo.guru99.com/test/simple_context_menu.html");
            WebElement doubleClickBtn = driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
           
            // Perform Double Click
            actions.doubleClick(doubleClickBtn).perform();
            System.out.println("Double Click performed.");

            // Handle the alert
            Alert alert2 = driver.switchTo().alert();
            System.out.println("Alert text after Double Click: " + alert2.getText());
            alert2.accept();

            // --- 3. DRAG AND DROP ---
            driver.get("https://jqueryui.com/resources/demos/droppable/default.html");
           
            WebElement source = driver.findElement(By.id("draggable"));
            WebElement target = driver.findElement(By.id("droppable"));

            // Perform Drag and Drop
            actions.dragAndDrop(source, target).perform();
           
            // Verify the drop was successful by checking the text change
            String droppedText = target.getText();
            System.out.println("Status after Drag and Drop: " + droppedText);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}