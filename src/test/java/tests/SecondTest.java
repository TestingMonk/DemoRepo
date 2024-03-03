package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.Base;

public class SecondTest extends Base {
	
	@Test
	public void secondTest() throws IOException, InterruptedException {
		
		System.out.println("SecondTest");
		
		WebDriver driver = intializeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		Thread.sleep(2000);
		driver.quit();
	}

}
