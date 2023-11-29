package com.toneop.pages;

import java.io.File;
import java.time.Duration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class CreateSession {
	public AppiumDriver driver;
	static AppiumServiceBuilder builder;
	public static AppiumDriverLocalService service;
	
	String MainJsPath =  
	"C:\\Users\\Asus\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";

	
	public void startServer()
	{
		  AppiumServiceBuilder builder = new AppiumServiceBuilder()
	        .withIPAddress("127.0.0.1")
	            .usingPort (4723)
	            .withAppiumJS (new File (MainJsPath))
	            .withArgument (GeneralServerFlag.SESSION_OVERRIDE)
	            .withArgument (GeneralServerFlag.LOG_LEVEL, "debug")
	            
	            .withTimeout(Duration.ofSeconds(600));
	     	        service = AppiumDriverLocalService.buildService(builder);
	        service.start();
	}
	
	
	public AppiumDriver initializeDriver(String platformName,String appPath, String appPackage, String appActivity) throws Exception {
		
		switch(platformName)
		{
		case "Android" :
		
		UiAutomator2Options options = new UiAutomator2Options()
		
		.setNewCommandTimeout(Duration.ofSeconds(300))
		.setAppWaitForLaunch(true)
		.setAutoGrantPermissions(true)
		.setFullReset(false)
		.setAppWaitForLaunch(true)
		.eventTimings()
	
		.setApp(appPath);
//		.setAppActivity(appActivity)
//		
//		.setAppPackage(appPackage);
		
		
		return driver = new AndroidDriver(service.getUrl(),options);
		
		
		case "ios" :
			XCUITestOptions iosoptions = new XCUITestOptions()
			.setUdid("")
			.setBundleId("")
			.setApp("iso appPath")
			.setAutoAcceptAlerts(true)
			.setAutomationName("xcuitest");
		
		return driver = new IOSDriver(service.getUrl(),iosoptions);
			
		default:
			
			throw new Exception("Invalid platform");
		}
	}

	

	public void stopServer()
	{
		driver.quit();
		service.stop();
	}
	
}
