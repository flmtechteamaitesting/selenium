import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;

public class KeyboardEventsDemo {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
       
        // Find the search box
        WebElement searchBox = driver.findElement(By.name("q"));
       
        // Initialize Actions class
        Actions actions = new Actions(driver);
       
        // 1. Sending text and pressing ENTER
        actions.sendKeys(searchBox, "Selenium WebDriver").sendKeys(Keys.ENTER).perform();
       
        // 2. Sending ESCAPE key (useful for closing popups/modals)
        // We use the driver to send the key to the active element
        driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
       
        // 3. Sending FUNCTION keys (e.g., F5 to refresh, F12 for dev tools)
        // Note: Some browsers may block F12 or F5 due to security/browser policies
        actions.sendKeys(Keys.F5).perform();
       
        // 4. Using Tab to navigate between fields
        // This moves focus from the current element to the next
        actions.sendKeys(Keys.TAB).perform();
       
        // 5. Complex key combination (e.g., Ctrl + A to select all text)
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
       
        // Close the browser
        driver.quit();
    }}