package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.Base;

public class ThirdTest extends Base{

	@Test
	public void thirdTest() throws IOException, InterruptedException {
		
		System.out.println("Third test");
		WebDriver driver = intializeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		Thread.sleep(2000);
		driver.quit();
	}
	}
