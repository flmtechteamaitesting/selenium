import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * ============================================================================
 *  TestNG Annotations + Attributes — LIVE DEMO (Selenium 4 + TestNG)
 * ============================================================================
 *
 *  Run order of the configuration annotations (outer -> inner):
 *
 *      @BeforeSuite          (once for the whole testng.xml suite)
 *        @BeforeTest         (once per <test> tag in testng.xml)
 *          @BeforeClass      (once per class)
 *            @BeforeMethod   (before EVERY @Test)
 *                @Test       (the actual test)
 *            @AfterMethod    (after EVERY @Test)
 *          @AfterClass       (once per class)
 *        @AfterTest          (once per <test> tag)
 *      @AfterSuite           (once for the whole suite)
 *
 *  NOTE: @BeforeSuite/@AfterSuite and @BeforeTest/@AfterTest only fire when you
 *        run through a suite file (testng.xml). Running a single @Test directly
 *        will only trigger Class + Method level hooks.
 * ============================================================================
 */
public class TestNGAnnotationsDemo {

    private WebDriver driver;
    private static final String BASE_URL = "https://the-internet.herokuapp.com/";

    /* ===================== SUITE LEVEL (runs ONCE) ===================== */

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("==> @BeforeSuite : one-time global setup (config, reports, DB pool)");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("==> @AfterSuite  : one-time global teardown (flush reports)\n");
    }

    /* ============ TEST LEVEL (runs ONCE per <test> in testng.xml) ============ */

    @BeforeTest
    public void beforeTest() {
        System.out.println("  --> @BeforeTest : runs before all classes inside this <test> tag");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("  --> @AfterTest  : runs after all classes inside this <test> tag");
    }

    /* ===================== CLASS LEVEL (runs ONCE per class) ===================== */

    @BeforeClass
    public void beforeClass() {
        System.out.println("    ---> @BeforeClass : launch the browser (one time for this class)");
        ChromeOptions options = new ChromeOptions();
        // Uncomment the next line to run head-less (useful on CI / when projecting a demo):
        // options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        System.out.println("    ---> @AfterClass : quit the browser (one time for this class)");
        if (driver != null) {
            driver.quit();
        }
    }

    /* ============ METHOD LEVEL (runs before/after EACH @Test) ============ */

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("      ----> @BeforeMethod : open base URL (fresh state before EVERY test)");
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("      ----> @AfterMethod : per-test cleanup (e.g. screenshot on failure)\n");
    }

    /* ============================ TEST CASES ============================ */

    /**
     * Attribute: priority = 1  -> lowest number runs FIRST.
     * Tests without a priority default to priority 0 and run before this one.
     */
    @Test(priority = 1)
    public void openHomePage() {
        System.out.println("        [TEST] openHomePage  (priority = 1)");
        String title = driver.getTitle();
        System.out.println("        Page title = " + title);
        Assert.assertTrue(title.contains("The Internet"), "Home page title mismatch");
    }

    /**
     * Attribute: dependsOnMethods = {"openHomePage"}
     *  -> This test runs ONLY if openHomePage PASSES.
     *  -> If openHomePage fails, TestNG marks this test as SKIPPED (not failed).
     *  -> dependency also forces order: this always runs AFTER openHomePage.
     */
    @Test(priority = 2, dependsOnMethods = {"openHomePage"})
    public void navigateToJavaScriptAlerts() {
        System.out.println("        [TEST] navigateToJavaScriptAlerts  (priority = 2, dependsOnMethods = openHomePage)");
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("javascript_alerts"),
                "Did not navigate to the JavaScript Alerts page");
    }

    /**
     * Attribute: enabled = false
     *  -> TestNG IGNORES this test completely. It will NOT appear in the run.
     *  -> Use it to temporarily switch off a flaky / unfinished test.
     */
    @Test(priority = 3, enabled = false)
    public void featureNotReadyYet() {
        System.out.println("        [TEST] featureNotReadyYet -- you should NEVER see this (enabled = false)");
    }

    /**
     * Plain test with the highest priority number -> runs LAST among these.
     */
    @Test(priority = 4)
    public void addRemoveElement() {
        System.out.println("        [TEST] addRemoveElement  (priority = 4)");
        driver.findElement(By.linkText("Add/Remove Elements")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        int deleteButtons = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        Assert.assertEquals(deleteButtons, 1, "Clicking 'Add Element' should create exactly one 'Delete' button");
    }
}
