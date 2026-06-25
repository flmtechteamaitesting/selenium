package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the Login page:  https://the-internet.herokuapp.com/login
 *
 * Page Objects keep the LOCATORS and PAGE ACTIONS out of the test classes,
 * so the @Test methods read like plain English and locators live in one place.
 */
public class LoginPage {

    private final WebDriver driver;

    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton   = By.cssSelector("button[type='submit']");
    private final By flashMessage  = By.id("flash");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /** Navigate to the login page and return this page object (fluent). */
    public LoginPage open(String baseUrl) {
        driver.get(baseUrl + "/login");
        return this;
    }

    /** Fill credentials, submit, and hand control to the Secure Area page. */
    public SecureAreaPage loginAs(String username, String password) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        return new SecureAreaPage(driver);
    }

    /** Read the red/green flash banner (used for the negative test). */
    public String getFlashMessage() {
        return driver.findElement(flashMessage).getText();
    }
}
