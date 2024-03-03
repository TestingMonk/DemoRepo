package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public Properties prop ;

	public WebDriver intializeDriver() throws IOException {
		
		
		 prop = new Properties();
	
		// below line will given data.properties file path 
		String propPath = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties";
		
		FileInputStream fis = new FileInputStream(propPath);

		prop.load(fis);
		
		String browserName=prop.getProperty("browser");
		
		WebDriver driver = null;

		
		if(browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");

			//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);

		}else if(browserName.equalsIgnoreCase("firefox")) {

			//WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}else if(browserName.equalsIgnoreCase("edge")) {

			//WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}
  
	
	public String takeScreenShot(String testName, WebDriver driver) throws IOException {
		
		File SourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
		FileUtils.copyFile(SourceFile,new File(destinationFilePath));
		
		return destinationFilePath;
	}
}
