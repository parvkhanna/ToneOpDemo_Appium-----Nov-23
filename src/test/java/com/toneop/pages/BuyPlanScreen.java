package com.toneop.pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.toneop.utils.Actions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class BuyPlanScreen extends BaseTest{

	Actions action;
	AppiumDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    KeyEvent keyEvent ;
	
	public BuyPlanScreen(AppiumDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//android.widget.TextView[@text='Get 1 + 1 Coach' and @class='android.widget.TextView']")
	private WebElement  bannerWeightLossPlan;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Buy Now' and @class='android.widget.TextView']")
	private WebElement btnBuyNow;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Price Breakup' and @class='android.widget.TextView']")
	private WebElement priceBreakup;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Weight Loss Plan' and @class='android.widget.TextView']")
	private WebElement planNameTxt1;

	@FindBy(xpath = "//android.widget.TextView[@text='Diabetes Care Plan' and @class='android.widget.TextView']")
	private WebElement planNameTxt2;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Power Detox Plan' and @class='android.widget.TextView']")
	private WebElement planNameTxt3;
	
	
	@FindBy(xpath = "//android.widget.Button[@resource-id='redesign-v15-cta']")
	private WebElement btnPayNow;
	
	@FindBy(xpath = "(//android.widget.TextView[@text='Get 2 / 3 Coaches' and @class='android.widget.TextView'])[1]")
	private WebElement bannerDiabetesCarePlan;
	
	@FindBy(xpath = "(//android.widget.TextView[@text='Get 1 Coach' and @class='android.widget.TextView'])[1]")
	private WebElement powerDetoxPlan;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Buy Plan' and @class='android.widget.TextView']")
    private WebElement tabBuyPlan;
	
	@FindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1']")
	private WebElement confirmAlert;
	
	public String purchaseWeightLossPlan()
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		wait.until(ExpectedConditions.elementToBeClickable(bannerWeightLossPlan)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(btnBuyNow)).click();
		
		String planName = wait.until(ExpectedConditions.visibilityOf(planNameTxt1)).getText();
        
        wait.until(ExpectedConditions.elementToBeClickable(btnBuyNow)).click();
        
        wait.until(ExpectedConditions.visibilityOf(btnPayNow));
        
        return planName;
	}
	
	public String purchaseDiabetesConditionPlan()
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		KeyEvent keyEvent = new KeyEvent(AndroidKey.BACK);
        ((AndroidDriver)driver).pressKey(keyEvent);

        ((AndroidDriver)driver).pressKey(keyEvent);

        wait.until(ExpectedConditions.elementToBeClickable(confirmAlert)).click();
        
        ((AndroidDriver)driver).pressKey(keyEvent);
		
        wait.until(ExpectedConditions.elementToBeClickable(tabBuyPlan)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(bannerDiabetesCarePlan)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(btnBuyNow)).click();
		
        String planName = wait.until(ExpectedConditions.visibilityOf(planNameTxt2)).getText();
        
        wait.until(ExpectedConditions.elementToBeClickable(btnBuyNow)).click();
        
        wait.until(ExpectedConditions.visibilityOf(btnPayNow));
        
        return planName;
	}
	
	public String toneOpExperiencePlan() throws IOException, InterruptedException
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		action = new Actions();
		keyEvent = new KeyEvent(AndroidKey.BACK);
        
		((AndroidDriver)driver).pressKey(keyEvent);

        ((AndroidDriver)driver).pressKey(keyEvent);

        wait.until(ExpectedConditions.elementToBeClickable(confirmAlert)).click();
        
        ((AndroidDriver)driver).pressKey(keyEvent);
        
		wait.until(ExpectedConditions.elementToBeClickable(tabBuyPlan));
		
		action.swipeUp(driver);
		
		wait.until(ExpectedConditions.visibilityOf(powerDetoxPlan)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(btnBuyNow)).click();
        
		String planName = wait.until(ExpectedConditions.visibilityOf(planNameTxt3)).getText();
        
		wait.until(ExpectedConditions.elementToBeClickable(btnBuyNow)).click();
        
		wait.until(ExpectedConditions.elementToBeClickable(btnPayNow));
		
		return planName;
	}
}
