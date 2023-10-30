package com;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DemoWebshop_Registration 
{
	WebDriver driver;
	Properties pro=new Properties();
	
	@BeforeSuite
	public void openBrowser() throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\SCI\\Desktop\\SELENIUM\\Chrome 118\\chromedriver-win64 (2)\\chromedriver-win64\\chromedriver.exe");
		driver=new ChromeDriver();
		FileInputStream fis=new FileInputStream("C:\\Users\\SCI\\Desktop\\PRIYANKA\\TESTING\\AUTOMATION\\SELENIUM\\ADVANCE SELENIUM\\TestNG_Properties\\DemoWebshopRegistration.properties");
		pro.load(fis);
	}
	
	@BeforeTest
	public void OpenUrl()
	{
		driver.get(pro.getProperty("url"));
	}
	
	@BeforeClass
	public void maximizeBrowser()
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	@BeforeMethod
	public void GetCookie()
	{
		Set s=driver.manage().getCookies();
		System.out.println(s);
	}
	
	@Test
	public void Registration() throws Exception
	{
		Thread.sleep(1000);
		driver.findElement(By.id("gender-female")).click();
		driver.findElement(By.name("FirstName")).sendKeys(pro.getProperty("fname"));
		driver.findElement(By.name("LastName")).sendKeys(pro.getProperty("lname"));
		driver.findElement(By.name("Email")).sendKeys(pro.getProperty("email"));
		driver.findElement(By.name("Password")).sendKeys(pro.getProperty("password"));
		driver.findElement(By.name("ConfirmPassword")).sendKeys(pro.getProperty("ConfirmPassword"));
		driver.findElement(By.id("register-button")).click();	
	}
	
	@AfterMethod
	public void takeScreenshot() throws Exception
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFileToDirectory(src, new File("C:\\Users\\SCI\\Desktop\\PRIYANKA\\TESTING\\AUTOMATION\\SELENIUM\\ADVANCE SELENIUM\\ScreenShot"));
	}
	
	@AfterClass
	public void deletCookie()
	{
		driver.manage().deleteAllCookies();
	}
	
	@AfterTest
	public void dbclose()
	{
		System.out.println("db close");
	}
	
	@AfterSuite
	public void driverClose()
	{
		driver.close();
	}
}
