package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

/**
 * ============================================================================
 *  AddRemoveElementsTest — a SECOND test class, also extending BaseTest.
 * ============================================================================
 *
 *  Why a second class + a second <test> tag in testng.xml?
 *  So students can watch @BeforeClass run AGAIN (a new browser for this class)
 *  and @BeforeTest run AGAIN (because it sits in its own <test> tag), while
 *  @BeforeSuite still runs only ONCE for the entire run.
 *
 *  Feature under test: https://the-internet.herokuapp.com/add_remove_elements/
 * ============================================================================
 */
public class AddRemoveElementsTest extends BaseTest {

    private final By addButton     = By.xpath("//button[text()='Add Element']");
    private final By deleteButtons = By.cssSelector(".added-manually");

    @Test(priority = 1, description = "Adding one element creates exactly one Delete button")
    public void addSingleElement() {
        System.out.println("        [TEST] addSingleElement (priority = 1)");
        driver.get(BASE_URL + "/add_remove_elements/");

        driver.findElement(addButton).click();

        Assert.assertEquals(driver.findElements(deleteButtons).size(), 1,
                "Clicking 'Add Element' once should create one Delete button");
    }

    @Test(priority = 2,
          dependsOnMethods = { "addSingleElement" },
          description = "Adding three elements creates three Delete buttons")
    public void addMultipleElements() {
        System.out.println("        [TEST] addMultipleElements (priority = 2, dependsOnMethods = addSingleElement)");
        driver.get(BASE_URL + "/add_remove_elements/");

        for (int i = 0; i < 3; i++) {
            driver.findElement(addButton).click();
        }

        Assert.assertEquals(driver.findElements(deleteButtons).size(), 3,
                "Clicking 'Add Element' three times should create three Delete buttons");
    }
}
