import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class StudentLearningTest {

    @BeforeSuite
    public void beforeSuite() { System.out.println("--- @BeforeSuite: Setting up global environment ---"); }

    @BeforeClass
    public void beforeClass() { System.out.println("--- @BeforeClass: Initializing Test Class ---"); }

    @BeforeMethod
    public void beforeMethod() { System.out.println("--- @BeforeMethod: Preparing for a new test ---"); }

    @Test(priority = 1)
    public void hardAssertionTest() {
        System.out.println("Executing Hard Assertion Test");
        Assert.assertEquals("Login", "Login"); // Passes
        // Assert.assertEquals("Login", "Logout"); // If this fails, the line below won't execute
        System.out.println("Hard Assertion Passed!");
    }

    @Test(priority = 2, dependsOnMethods = {"hardAssertionTest"})
    public void softAssertionTest() {
        System.out.println("Executing Soft Assertion Test");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("Title", "WrongTitle"); // Fails, but test continues
        System.out.println("This message prints even after Soft Assertion failure!");
        softAssert.assertAll(); // Reports the failure at the end
    }

    @Test(enabled = false)
    public void skippedTest() { System.out.println("This test is disabled and won't run."); }

    @AfterMethod
    public void afterMethod() { System.out.println("--- @AfterMethod: Cleaning up after test ---\n"); }

    @AfterClass
    public void afterClass() { System.out.println("--- @AfterClass: Finalizing Test Class ---"); }

    @AfterSuite
    public void afterSuite() { System.out.println("--- @AfterSuite: Closing all resources ---"); }
}