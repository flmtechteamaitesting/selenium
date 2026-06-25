
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
public class CheckboxRadioDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub\
		
		WebDriver driver = new ChromeDriver();
		
		driver.navigate().to("https://demo.guru99.com/test/radio.html");
		
		//Radio Buttons
		
		WebElement Option1 = driver.findElement(By.id("vfb-7-1"));
		WebElement Option2 = driver.findElement(By.id("vfb-7-2"));
		WebElement Option3 = driver.findElement(By.id("vfb-7-3"));
		
		Option2.click();
		
		System.out.println("Radio Option1 status: " + Option1.isSelected() ) ;
		System.out.println("Radio Option2 status: " + Option2.isSelected() ) ;
		System.out.println("Radio Option3 status: " + Option3.isSelected() ) ;
		
     // Check Boxes
		
		WebElement checkbox1 = driver.findElement(By.id("vfb-6-0"));
		WebElement checkbox2 = driver.findElement(By.id("vfb-6-1"));
		WebElement checkbox3 = driver.findElement(By.id("vfb-6-2"));
		
		checkbox1.click();
		checkbox2.click();
		
		System.out.println("CheckBox1 status: " + checkbox1.isSelected() ) ;
		System.out.println("CheckBox2 status: " + checkbox2.isSelected() ) ;
		System.out.println("CheckBox3 status: " + checkbox3.isSelected() ) ;
		
		if (checkbox1.isSelected()) {
			checkbox1.click();
		}
		
		System.out.println("CheckBox1 status after unselecting: " + checkbox1.isSelected() ) ;
		System.out.println("CheckBox2 status after unselecting: " + checkbox2.isSelected() ) ;
		System.out.println("CheckBox3 status after unselecting: " + checkbox3.isSelected() ) ;
		
		
	}

}
