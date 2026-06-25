import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.time.Duration;

public class FileUploadExample {
    public static void main(String[] args) {
        
        // 1. Setup Driver
      //  WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

            // 2. Navigate to the upload page
            driver.get("https://the-internet.herokuapp.com/upload");

            // 3. Create a dummy file to upload (for demonstration)
            // In a real scenario, you would use the path of an existing file
//            File uploadFile = new File("test_upload.txt");
//            if (!uploadFile.exists()) {
//                uploadFile.createNewFile();
//            }
            String absolutePath = "C:\\Users\\dubak\\Downloads\\upload.txt";
            System.out.println("Uploading file from: " + absolutePath);

            // 4. Locate the 'Choose File' input element
            // Note: We do NOT click this. We send the path to it.
            WebElement fileInput = driver.findElement(By.id("file-upload"));
            
            // 5. Send the absolute path of the file
            fileInput.sendKeys(absolutePath);

            // 6. Click the 'Upload' button
            driver.findElement(By.id("file-submit")).click();

            // 7. Verify the upload success
            WebElement successHeader = driver.findElement(By.tagName("h3"));
            if (successHeader.getText().equals("File Uploaded!")) {
                System.out.println("Test Passed: File uploaded successfully.");
            } else {
                System.out.println("Test Failed: Upload message not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 8. Cleanup: Delete dummy file and close browser
            driver.quit();
        }
    }
}