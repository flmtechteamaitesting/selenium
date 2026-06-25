import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class W3SchoolsTableDemo {
    public static void main(String[] args) throws IOException {
        // Set up WebDriver (Ensure you have chromedriver in your PATH)
        WebDriver driver = new ChromeDriver();
       
        // Navigate to the W3Schools HTML Table demo page
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        driver.manage().window().maximize();

        // 1. Reading values from the 'Customers' table
        // We target the table with id 'customers'
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='customers']/tbody/tr"));

        System.out.println("--- Reading Table Data ---");
        for (int i = 1; i < rows.size(); i++) { // Starting from 1 to skip the header row
            List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
            for (WebElement col : cols) {
                System.out.print(col.getText() + " | ");
            }
            System.out.println();
        }

        // 2. Taking a screen capture
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       
        // Ensure the directory exists
        File directory = new File("screenshots");
        if (!directory.exists()) {
            directory.mkdir();
        }
       
        File destinationFile = new File("screenshots/w3schools_table.png");
        FileUtils.copyFile(screenshotFile, destinationFile);
       
        System.out.println("\nScreenshot saved at: " + destinationFile.getAbsolutePath());

        driver.quit();
    }
}