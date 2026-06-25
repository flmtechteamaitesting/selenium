import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.*;

public class DataDrivenLogin {
    public static void main(String[] args) throws IOException {
        // 1. Setup Excel path
        String filePath = "TestData.xlsx";
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        // 2. Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 3. Loop through 10 rows of data
        for (int i = 1; i <= 10; i++) {
            String user = sheet.getRow(i).getCell(0).getStringCellValue();
            String pass = sheet.getRow(i).getCell(1).getStringCellValue();

            // Navigate to the specific login page
            driver.get("https://practicetestautomation.com/practice-test-login/");

            // Perform Login using the site's specific IDs
            driver.findElement(By.id("username")).sendKeys(user);
            driver.findElement(By.id("password")).sendKeys(pass);
            driver.findElement(By.id("submit")).click();

            // Simple validation: print the current URL to verify if login was successful
            System.out.println("Attempted login with: " + user + " | Current URL: " + driver.getCurrentUrl());
        }

        // 4. Cleanup
        workbook.close();
        fis.close();
        driver.quit();
    }
}