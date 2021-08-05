package com.initbanking.testcases;

import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.initbanking.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readconfig = new ReadConfig();
	
	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
		ChromeOptions options = new ChromeOptions();
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
		options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
		driver = new ChromeDriver(options);
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		else if(br.equals("edge"))
		{
			System.setProperty("webdriver.ie.driver",readconfig.getEdgepath());
			driver = new EdgeDriver();
		}
		
		driver.get(baseURL);
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
	}
	
	@AfterClass		
	public void tearDown()
	{
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public  String randomestring() {
		String generatedstring= RandomStringUtils.randomAlphabetic(8);// Predefined method in Java
		return(generatedstring);
	}
	
	public  static String randomeNum() {
		String generatedstring2= RandomStringUtils.randomNumeric(4);// Predefined method in Java
		return(generatedstring2);
	}
	
}
