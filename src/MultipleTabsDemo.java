
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
public class MultipleTabsDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		
		
		System.out.println("Opening Tab1....");
		driver.get("https://www.google.com");
		String firstHandle = driver.getWindowHandle();
		System.out.println("1 st tab window Handle is :" + firstHandle);
		
		System.out.println("Opening Tab2....");
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://www.wikipedia.org");
		
		driver.switchTo().window(firstHandle);
		
		//driver.close();
		//driver.quit();

	}

}
