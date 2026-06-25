import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;

public class SimpleFileOps {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        // --- FILE UPLOAD ---
        driver.get("https://the-internet.herokuapp.com/upload");
       
        // Locate the file input field
        WebElement uploadElement = driver.findElement(By.id("file-upload"));
       
        // Provide the absolute path to a file on your computer
        // Ensure you have a file named 'testfile.txt' in your project root
        String filePath = System.getProperty("user.dir") + "/testfile.txt";
        uploadElement.sendKeys(filePath);
       
        driver.findElement(By.id("file-submit")).click();
        System.out.println("File upload initiated.");

        // --- FILE DOWNLOAD ---
        driver.get("https://the-internet.herokuapp.com/download");
       
        // Click on the file link to trigger the download
        driver.findElement(By.linkText("some-file.txt")).click();
       
        // Wait for the download to finish
        Thread.sleep(3000);
       
        System.out.println("File download triggered. Check your default 'Downloads' folder.");

        driver.quit();
    }
}