package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SecureAreaPage;

/**
 * ============================================================================
 *  LoginTest — an END-TO-END login/logout flow.
 * ============================================================================
 *
 *  Notice this class has NO setup/teardown code: it `extends BaseTest`, so it
 *  INHERITS @BeforeSuite/@BeforeTest/@BeforeClass/@BeforeMethod (and the
 *  matching After hooks). Here we only write the @Test methods.
 *
 *  This is where the THREE attributes are demonstrated:
 *      • priority           -> controls run order (lower runs first)
 *      • dependsOnMethods   -> run only if the prerequisite test PASSED
 *      • enabled = false    -> skip a test entirely
 * ============================================================================
 */
public class LoginTest extends BaseTest {

    private static final String VALID_USER = "tomsmith";
    private static final String VALID_PASS = "SuperSecretPassword!";

    /** STEP 1 of the E2E flow — log in with valid credentials. */
    @Test(priority = 1, description = "E2E Step 1: a valid user lands on the secure area")
    public void validLoginShowsSecureArea() {
        System.out.println("        [TEST] validLoginShowsSecureArea (priority = 1)");

        SecureAreaPage secureArea = new LoginPage(driver)
                .open(BASE_URL)
                .loginAs(VALID_USER, VALID_PASS);

        Assert.assertTrue(secureArea.getFlashMessage().contains("You logged into a secure area!"),
                "Success banner was not shown after a valid login");
        Assert.assertTrue(secureArea.isLogoutButtonVisible(),
                "Logout button should be visible on the secure area");
    }

    /**
     * STEP 2 of the E2E flow — log out.
     * dependsOnMethods: runs ONLY if validLoginShowsSecureArea passed; otherwise
     * TestNG marks this test SKIPPED (not failed) and also guarantees order.
     */
    @Test(priority = 2,
          dependsOnMethods = { "validLoginShowsSecureArea" },
          description = "E2E Step 2: a logged-in user can log out")
    public void userCanLogout() {
        System.out.println("        [TEST] userCanLogout (priority = 2, dependsOnMethods = validLoginShowsSecureArea)");

        LoginPage loginPage = new LoginPage(driver)
                .open(BASE_URL)
                .loginAs(VALID_USER, VALID_PASS)   // @BeforeMethod cleared cookies, so log in again
                .logout();

        Assert.assertTrue(loginPage.getFlashMessage().contains("You logged out of the secure area!"),
                "Logout banner was not shown");
    }

    /** Negative path — wrong password must be rejected. */
    @Test(priority = 3, description = "Negative: an invalid password is rejected")
    public void invalidLoginShowsError() {
        System.out.println("        [TEST] invalidLoginShowsError (priority = 3)");

        LoginPage loginPage = new LoginPage(driver).open(BASE_URL);
        loginPage.loginAs(VALID_USER, "wrong-password");

        Assert.assertTrue(loginPage.getFlashMessage().contains("Your password is invalid!"),
                "Expected the invalid-password error banner");
    }

    /**
     * enabled = false -> TestNG IGNORES this test. It will NOT run and will NOT
     * appear as failed. Use this to park a flaky or not-yet-built test.
     */
    @Test(priority = 4, enabled = false, description = "Disabled: 'Remember me' not built yet")
    public void rememberMeFeature() {
        System.out.println("        [TEST] rememberMeFeature — you should NEVER see this (enabled = false)");
    }
}
