
import org.testng.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class StudentTest {
	
	@BeforeSuite
	public void beforeSuite() {
		
		System.out.println("----@Before Suite: Setting Global variables-----");
	}
	
	
	@BeforeClass
	public void beforeClass() {
		
		System.out.println("----@BeforeClass: Intaliasing the Test cases-----");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		
		System.out.println("----@BeforeMethod:Preparing a test-----");
	}
	
	@Test(priority = 1)
	public void login() {
		
		System.out.println("Testing the login");
	}
	
	@Test(priority = 1)
	public void hardAssertionTest1() {
		
		System.out.println("Testing the login");
	}
	
	
	@Test(priority = 2)
	public void hardAssertionTest2() {
		
		Assert.assertEquals( "Swag Labss","Swag Labs");
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals("Swag Labss","Swag Labs");
		
		softAssert.assertAll();
		
		System.out.println("Testing the login");
	}
	
	@Test
	public void hardAssertionTest3() {
		
		System.out.println("Testing the login");
	}
	
  @Test(priority = 2,dependsOnMethods = {"login"} )
  
	public void DashboardCheck() {
		
		System.out.println("Testing the login");
	}
	
}
