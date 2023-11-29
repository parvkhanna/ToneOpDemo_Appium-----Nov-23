package com.toneop.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.toneop.pages.BaseTest;
import com.toneop.pages.BuyPlanScreen;
import com.toneop.pages.HomeScreen;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;

@Feature("Topeon-Care")
public class DemoTest extends BaseTest {
	
	/*
	 * Please note the following
	 * 
	 * 1.It is required to enter the otp for login test case. 
	 *   Kindly update the mobile number and enter the Otp accordingly
	 *   
	 * 2. I have intentionally not implemented the config properties and data properties(for assertion values etc.)
	 *  
	 */
	
	String MobileNo = "Please enter your registered Mobile number";
	
	HomeScreen homescreen;
	BuyPlanScreen buyPlan;
	SoftAssert softassert = new SoftAssert();
	
	
	
	@Description ("Validate the login functionality")
	@Test(priority = 1  )
	public void loginTest() throws Exception
	{
		homescreen = new HomeScreen(driver);
		String buyPlanTab = homescreen.login(MobileNo);
		Assert.assertEquals(buyPlanTab, "Buy Plan");
	}
	
	
	@Description ("Validate the purchase of Weight Management Plans")
	@Test   (priority = 2, dependsOnMethods = {"loginTest"} )
	public void buyWightLossPlan() throws IOException
	{
		buyPlan = new BuyPlanScreen(driver);
		String planName = buyPlan.purchaseWeightLossPlan();
		softassert.assertEquals(planName,"Weight Loss Plan");
		softassert.assertAll();
	}
	
	@Description("Validate the purchase of Medical Condition Plans")
	@Test (priority = 3)
	public void buyMedicalConditionPlan() throws IOException
	{
		buyPlan = new BuyPlanScreen(driver);
		String planName = buyPlan.purchaseDiabetesConditionPlan();
		softassert.assertEquals(planName,"Diabetes Care Plan");
		softassert.assertAll();
	}
	
	
	@Description("Validate the purchase of TopeOn Experience Plans")
	@Test (priority = 4)
	public void buyTopeOnExperiencePlan() throws IOException, InterruptedException
	{
		buyPlan = new BuyPlanScreen(driver);
		String planName = buyPlan.toneOpExperiencePlan();
		softassert.assertEquals(planName,"Power Detox Plan");
		softassert.assertAll();
	}
	
}
