package com.toneop.pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;


/*
 * @FindBy is a general annotation for both web and mobile automation, 
 * while @AndroidFindBy is specifically for Android mobile automation, 
 * and @AndroidBy is used with driver.findElement(By) for Android element 
 * location in your test scripts. Your choice between them depends on the 
 * type of automation project you are working on (web, Android, or a combination of both).
 */
public class HomeScreen extends BaseTest{
	
	AppiumDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
	
	public HomeScreen(AppiumDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

    @FindBy(xpath = "//android.widget.EditText[@text='Enter mobile number' and @class='android.widget.EditText']")
    private WebElement tfMobileNo;
    
    
    @FindBy(xpath = "//android.widget.TextView[@text='Proceed' and @class='android.widget.TextView']")
    private WebElement btnProceed;
    
    @FindBy(xpath = "//android.widget.TextView[@text='Buy Plan' and @class='android.widget.TextView']")
    private WebElement tabBuyPlan;
    
    @FindBy(xpath = "//android.widget.TextView[@text='Dashboard' and @class='android.widget.TextView']")
    private WebElement tabDashboard;
    
    @FindBy(xpath = "//android.widget.TextView[@text='Free Trial' and @class='android.widget.TextView']")
    private WebElement tabFreeTrial;
   
    @FindBy(xpath = "//android.widget.TextView[@text='Care' and @class='android.widget.TextView']")
    private WebElement tabCare;

    
    
	public String login(String MobileNo) 
	{
		wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(tfMobileNo)).sendKeys(MobileNo);
		wait.until(ExpectedConditions.elementToBeClickable(btnProceed)).click();		
		String buyPlanTab = wait.until(ExpectedConditions.visibilityOf(tabBuyPlan)).getText();
		return buyPlanTab;
	}
	
	
}

