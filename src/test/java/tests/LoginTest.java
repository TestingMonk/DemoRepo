package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AccountPage;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {
	public WebDriver driver;
	Logger log ;
	
	@Test(dataProvider="getLoginData")
	public void login(String email, String password, String expectedResult) throws IOException, InterruptedException {
		
	
		
		LandingPage landingpage = new LandingPage(driver);
		landingpage.myAccountDropdown().click();
		log.debug("Clicked on My Account dropdown");
		landingpage.loginOption().click();
		
		Thread.sleep(3000);
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailAddressField().sendKeys(email);
		log.debug("Email addressed got entered");
		loginpage.passwordField().sendKeys(password);
		log.debug("Password got entered");
		loginpage.loginButton().submit();
		log.debug("Clicked on Login Button");
		
		AccountPage accountpage = new AccountPage(driver);
		
		String actualResult=null;
		
		try{
			
			
			if(accountpage.editAccountInformationOption().isDisplayed()) {
				 log.debug("User got logged in");
			actualResult="Successful";
			}
		}catch(Exception e) {
			log.debug("User didn't log in");
			
			actualResult="Failure";
			}
		
if(actualResult.equals(expectedResult)) {
			
			log.info("Login Test got passed");
			
		}else {
			
			log.error("Login Test got failed");
		}
		

	}
	
	
	@BeforeMethod
	public void openApplication() throws IOException {
		
		log = LogManager.getLogger(LoginTest.class.getName());
		
		 driver = intializeDriver();
		 log.debug("Browser got launched");
			
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to application URL");
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		log.debug("Browser got closed");
	}
	
	@DataProvider
	public Object[][] getLoginData() {
		
		Object[][] data = {{"testuser7@gmail.com","qwerty@123","Successful"},{"test1user@hmail.com","qw@123","Failure"}};
		
		return data;
	}

}
