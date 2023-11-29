package com.toneop.pages;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.qameta.allure.Description;

public class BaseTest extends CreateSession {

	
	public Properties prop , deviceConfig;
	
	public String platFormName = "Android";
	public String appPath = System.getProperty("user.dir")+"\\src\\test\\resources\\apps\\ToneOp-detox-dev.apk";
	
	public String appPackage = "com.toneop.mobile";		
	public String appActivity = "com.toneop.mobile.MainActivity";

	
	@Description("Session start")
	@BeforeTest
	public void setup() throws Exception 
	{
		startServer();
		driver = initializeDriver(platFormName, appPath, appPackage, appActivity);
		
	}

	
	@Description("Session close")
	@AfterTest
	public void tearDown()
	{
		stopServer();
	}
	
}
