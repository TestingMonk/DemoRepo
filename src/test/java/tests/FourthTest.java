package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import resources.Base;

public class FourthTest extends Base {
	public WebDriver driver;
	@Test
	public void fourthTest() throws IOException, InterruptedException {
		
		
		System.out.println("Fourth Test");
		 driver = intializeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		Thread.sleep(2000);
		
		Assert.assertTrue(false);
		
	}
	
	@AfterMethod
	public void closeBrowser() {
		
		driver.quit();
	}

}
