package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the Secure Area page:
 *   https://the-internet.herokuapp.com/secure  (shown only after a valid login)
 */
public class SecureAreaPage {

    private final WebDriver driver;

    private final By flashMessage = By.id("flash");
    private final By logoutButton = By.cssSelector("a[href='/logout']");

    public SecureAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    /** The green "You logged into a secure area!" banner. */
    public String getFlashMessage() {
        return driver.findElement(flashMessage).getText();
    }

    public boolean isLogoutButtonVisible() {
        return driver.findElement(logoutButton).isDisplayed();
    }

    /** Click Logout and return to the Login page object (fluent chaining). */
    public LoginPage logout() {
        driver.findElement(logoutButton).click();
        return new LoginPage(driver);
    }
}
