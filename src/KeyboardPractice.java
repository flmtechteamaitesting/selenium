import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class KeyboardPractice {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		try {
		//Launch the website
		driver.get("https://adactinhotelapp.com/index.php");
		driver.manage().window().maximize();
		//login to the website
		driver.findElement(By.id("username")).sendKeys("pujatesting");
		driver.findElement(By.id("password")).sendKeys("12345678");
		driver.findElement(By.id("login")).click();
		WebElement location =driver.findElement(By.id("location"));
		Thread.sleep(2000);
		Select select = new Select(location);
		select.selectByVisibleText("Sydney");
		WebElement hotels =driver.findElement(By.id("hotels"));
		Select hotel = new Select(hotels);
		hotel.selectByVisibleText("Hotel Hervey");
		WebElement roomtype =driver.findElement(By.id("room_type"));
		Select room = new Select(roomtype);
		room.selectByVisibleText("Deluxe");
		WebElement noofrooms = driver.findElement(By.id("room_nos"));
		Select rooms = new Select(noofrooms);
		rooms.selectByIndex(3);
		driver.findElement(By.id("datepick_in")).clear();
		driver.findElement(By.id("datepick_in")).sendKeys("16/06/2026");
		Thread.sleep(2000);
		driver.findElement(By.id("datepick_out")).clear();
		driver.findElement(By.id("datepick_out")).sendKeys("17/06/2026");
		Thread.sleep(2000);
		WebElement adultrooms = driver.findElement(By.name("adult_room"));
		Select adultroom = new Select(adultrooms);
		adultroom.selectByIndex(2);
		WebElement childrooms = driver.findElement(By.name("child_room"));
		Select childroom = new Select(childrooms);
		childroom.selectByIndex(2);
		driver.findElement(By.id("Submit")).click();
		driver.findElement(By.name("radiobutton_0")).click();
		Thread.sleep(4000);
		driver.findElement(By.name("continue")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("first_name")).sendKeys("Puja");
		driver.findElement(By.name("last_name")).sendKeys("Zampani");
		driver.findElement(By.name("address")).sendKeys("6-142,Vellaturu,Guntur district");
		driver.findElement(By.name("cc_num")).sendKeys("1234567890123456");
		WebElement cctype = driver.findElement(By.id("cc_type"));
		Select cardtype = new Select(cctype);
		cardtype.selectByVisibleText("VISA");
		WebElement expirydate = driver.findElement(By.name("cc_exp_month"));
		Select cardexpirydate = new Select(expirydate);
		cardexpirydate.selectByIndex(2);
		WebElement expiryyear = driver.findElement(By.id("cc_exp_year"));
		Select expireyear = new Select(expiryyear);
		expireyear.selectByVisibleText("2029");
		driver.findElement(By.id("cc_cvv")).sendKeys("001");
		Thread.sleep(2000);
		driver.findElement(By.id("book_now")).click();
		Thread.sleep(10000);
		
		WebElement orderno = driver.findElement(By.name("order_no"));
	
		String order = orderno.getAttribute("value");
	
		System.out.println("Order No :" + order);
		driver.findElement(By.id("logout")).click();
		Thread.sleep(2000);
		
		
	} finally {
		driver.quit();
	}

}
}