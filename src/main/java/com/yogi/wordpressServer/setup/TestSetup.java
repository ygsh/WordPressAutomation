package com.yogi.wordpressServer.setup;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public abstract class TestSetup {
	public static WebDriver serverDriver=null;
	public static AndroidDriver clientDriver=null;
	public java.util.Date date= new java.util.Date();
	

	public static WebDriver getServerDriver(){
		return serverDriver;
	}
	
	public static AndroidDriver getClientDriver() throws MalformedURLException{
		clientDriver=setClientDriver();
		return clientDriver;
	}
	

	@SuppressWarnings("deprecation")
	public static AndroidDriver setClientDriver() throws MalformedURLException{
		File appDir=new File("resources/apps");
		File app=new File(appDir,"wordpress.apk");
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		//Uncomment below if you want to install app your self.
		caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		
		//USe app activity and app package if you want to work with already installed apps.
		caps.setCapability(MobileCapabilityType.APP_PACKAGE, "org.wordpress.android");
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 0);
		clientDriver = new AndroidDriver(new URL("http://localhost:4723/wd/hub") ,caps);
		return clientDriver;	
	}
	
	@BeforeClass //When two BeforeClass Methods are defined, their order of execution is alphabetic
	public void appiumStartServer(){
		//Start the appium server
		
		try{
			AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
			if (service.isRunning())
				System.out.println("Appium state: RUNNING");	
			else{
				System.out.println("APPIUM state: NOT RUNNING");
				System.out.println("Starting Appium server, please wait..");
				service.start();
				Thread.sleep(6000); //Give Appium time to boot.
			}
				
		}catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	

	@Parameters({"server_url","browser"})
	public void initializeServerDriver(String server_url, String browser){
		serverDriver=setDriver(server_url,browser);
	}

	public static WebDriver setDriver(String server_url, String browser){

		if(browser.equalsIgnoreCase("firefox"))
		{
			serverDriver = new FirefoxDriver();
			
		}else if (browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "resources/libs/chromedriver");
			serverDriver = new ChromeDriver();
			
		}
		else
			throw new IllegalArgumentException();
		serverDriver.get(server_url);
		return serverDriver;		
	}
	
	public String takeScreenshot(String testName) throws IOException{
		String screenshotsDir = "resources/screenshots";
		String filename = testName+ ".png";
		Path screenshotPath = Paths.get(screenshotsDir, filename);
		File SrcFile = ((TakesScreenshot)serverDriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(SrcFile, screenshotPath.toFile());
		return filename;
	}
	//Only take screenshots in case of failure.// Put aftermethod to trigger after each failed test
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException 
	{ 
		if (testResult.getStatus() == ITestResult.FAILURE) 
			{ 
			File scrFile = ((TakesScreenshot)serverDriver).getScreenshotAs(OutputType.FILE); 
			String destFile=takeScreenshot(getClass().getSimpleName()+"_"+testResult.getName());
			Reporter.log("<a href=../resources/screenshots/" + destFile + "><img src=/screenshots/" + destFile + " style=width:100px;height:100px;/>" + destFile + "</a><br");
			} 
	}
	
		public WebElement getWhenVisibleServer(By locator, int timeout) {
		
				WebElement element = null;
				
				WebDriverWait wait = new WebDriverWait(serverDriver, timeout);
				
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				
				return element;
		
		}
		
		public WebElement getWhenVisibleClient(By locator, int timeout) {
			
			WebElement element = null;
			
			WebDriverWait wait = new WebDriverWait(clientDriver, timeout);
			
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			
			return element;
	
	}

}
