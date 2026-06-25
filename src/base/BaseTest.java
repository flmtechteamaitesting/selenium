package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

/**
 * ============================================================================
 *  BaseTest — the home of ALL the TestNG lifecycle (configuration) annotations.
 * ============================================================================
 *
 *  In a real framework you DON'T repeat setup/teardown in every test class.
 *  You put it ONCE in a base class, and every test class simply `extends` it.
 *  That is exactly what LoginTest and AddRemoveElementsTest do.
 *
 *  Execution order when run through testng.xml (outer -> inner):
 *
 *      @BeforeSuite            once for the whole <suite>
 *        @BeforeTest           once per <test> tag
 *          @BeforeClass        once per test class      (browser opens here)
 *            @BeforeMethod     before EVERY @Test       (fresh state)
 *                @Test         the actual scenario
 *            @AfterMethod      after EVERY @Test
 *          @AfterClass         once per test class      (browser quits here)
 *        @AfterTest            once per <test> tag
 *      @AfterSuite             once for the whole <suite>
 *
 *  `alwaysRun = true` makes sure teardown still runs even if a test fails.
 * ============================================================================
 */
public abstract class BaseTest {

    /** Shared with subclasses so the @Test methods can drive the browser. */
    protected WebDriver driver;

    protected static final String BASE_URL = "https://the-internet.herokuapp.com";

    /* ===================== SUITE LEVEL (runs ONCE) ===================== */

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        System.out.println("==> @BeforeSuite : global setup (start reports, read config) — ONCE per suite");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        System.out.println("==> @AfterSuite  : global teardown (flush reports) — ONCE per suite\n");
    }

    /* ============ TEST LEVEL (runs ONCE per <test> in testng.xml) ============ */

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        System.out.println("  --> @BeforeTest  : runs ONCE per <test> tag in testng.xml");
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        System.out.println("  --> @AfterTest   : runs ONCE per <test> tag in testng.xml");
    }

    /* ===================== CLASS LEVEL (runs ONCE per class) ===================== */

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        System.out.println("    ---> @BeforeClass : launching Chrome — ONCE for " + getClass().getSimpleName());
        ChromeOptions options = new ChromeOptions();
        // Uncomment to run head-less (handy when projecting to a class / on CI):
        // options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(BASE_URL); // land somewhere so cookie reset in @BeforeMethod is safe
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("    ---> @AfterClass : quitting Chrome for " + getClass().getSimpleName());
        if (driver != null) {
            driver.quit();
        }
    }

    /* ============ METHOD LEVEL (runs before/after EACH @Test) ============ */

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        System.out.println("      ----> @BeforeMethod : clearing cookies for a FRESH session before every @Test");
        driver.manage().deleteAllCookies();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        System.out.println("      ----> @AfterMethod : per-test cleanup (screenshot on failure, etc.)\n");
    }
}
