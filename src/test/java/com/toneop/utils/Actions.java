package com.toneop.utils;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Actions {


	public Actions() throws IOException {
		super();
	}

	public static PointerInput finger ;
	static Point sourceLocation ;
	static Point destLocation;
	static Dimension sourceSize ;
	static Dimension destSize;
	
	public void longClickGestureByElements(AppiumDriver driver , WebElement element)
	{
		driver.executeScript("mobile: longClickGesture" , ImmutableMap.of(
				
				"elementId", ((RemoteWebElement)element).getId(),
//				"x" , 203,
//				"y" , 596,
				
				"duaration", 100000
				));
	}
	
	
	public void clickGesture(AppiumDriver driver, WebElement element)
	{
		driver.executeScript("mobile: clickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) element).getId()
			));
	}
	
	public void dragGesture(AppiumDriver driver , WebElement element)
	{
		driver.executeScript("mobile: clickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) element).getId()
			));
	}
	
	
	
	public void pinchOpenGesture(AppiumDriver driver)
	{
		driver.executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
			    "left", 199,
			    "top", 400,
			    "width" , 600,
			    "height" , 600,
			    "percent", 0.75
			));
	}
	
	public void pinchCloseGesture(AppiumDriver driver)
	{
		driver.executeScript("mobile: pinchCloseGesture", ImmutableMap.of(
			    "left", 199,
			    "top", 400,
			    "width" , 600,
			    "height" , 600,
			    "percent", 0.75
			));
	}
	
	
	public void swipe(AppiumDriver driver, String direction, WebElement ele  )
	{
		 driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
              //"left", 200, "top", 1674, "width", 0, "height", 1374,
               "elementId",  ((RemoteWebElement) ele).getId(),
               "direction", direction,
               "percent", 0.75
       ));
	}
	
	public void swipeUp(AppiumDriver driver)
	{
		Dimension size = driver.manage().window().getSize();
		int startX = size.getWidth()/2;
		int startY = size.getHeight()/2;
		int endX = startX;
		int endY = (int)(size.getHeight()*0.25);
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
		Sequence sequence = new Sequence(finger,  1);
				sequence.addAction(finger.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),startX,startY));
				sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
				sequence.addAction(new Pause (finger, Duration.ofMillis(200)));
				sequence.addAction(finger.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport() ,endX , endY));
				sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			
			driver.perform(List.of(sequence));
			
				
				
				
				
				
	}
	public static void performSingleTap(WebElement ele, AppiumDriver driver) {

		/*
		 * we need to locate our element on the screen.
		 * We will use the getLocation() and getSize() methods
		 * from Selenium to calculate the 'given elements' center coordinates:
		 */
		sourceLocation = ele.getLocation();
		sourceSize = ele.getSize();

		int centerX = sourceLocation.getX() + sourceSize.getWidth() / 2;
		int centerY = sourceLocation.getY() + sourceSize.getHeight() / 2;

		
		//Now we need our pointer input that will represent a touch input device:
		finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");


		// We will call our sequence object tap and associate it with the finger input device:
		Sequence tap = new Sequence(finger, 1);

		//Below are the pointer actions |"Move"|"PointerDown"|"PointerUp"|

		//First, we move to our button:
		tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX,centerY));

		//Then we perform a touch down event on the button using the left mouse button:
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

		//And one for the touch up event:
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		/*
		 * Once all pointer actions are added we can execute the tap sequence of pointer actions 
		 * using the perform() method of the driver object, 
		 * which is an instance of the WebDriver interface. 
		 * The List.of(tap) method call creates a list of 
		 * one Sequence object to be executed by the perform() method:
		 */
		driver.perform(List.of(tap));
	}

	public static void slider(WebElement ele, AppiumDriver driver)
	{
		// Calculate start and end coordinates
		Point sliderLocation = ele.getLocation();
		int startX = sliderLocation.getX() + 10;
		int endX = sliderLocation.getX() + ele.getSize().getWidth() - 10;
		int centerY = sliderLocation.getY() + ele.getSize().getHeight() / 2;

		// Create a sequence of actions to slide the slider
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence drag = new Sequence(finger, 0);
		drag.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, centerY));
		drag.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		drag.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, centerY));
		drag.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform the slider action
		driver.perform(Arrays.asList(drag));
	}

	public void dragAndDrop(WebElement ele,WebElement ele1, AndroidDriver driver)
	{
		sourceLocation = ele.getLocation();
		sourceSize = ele.getSize();
		
		destLocation = ele1.getLocation();
		destSize = ele1.getSize();

		int centerX = sourceLocation.getX() + sourceSize.getWidth() / 2;
		int centerY = sourceLocation.getY() + sourceSize.getHeight() / 2;

		int centerX2 = destLocation.getX() + destSize.getWidth()/2;
		int centerY2 = destLocation.getY() + destSize.getHeight()/2;
		Sequence dragNDrop = new Sequence(finger, 1);
		dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, centerY));
		dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(),centerX2, centerY2));
		dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
	}
	
	public static void slide(WebElement ele,RemoteWebDriver driver)
	{
		Point source = ele.getLocation();
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence sequence = new Sequence(finger, 1);
		sequence.addAction(finger.createPointerMove(Duration.ofMillis(0),
		                PointerInput.Origin.viewport(), source.x, source.y));
		        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
		sequence.addAction(new Pause(finger, Duration.ofMillis(600)));
		sequence.addAction(finger.createPointerMove(Duration.ofMillis(600),
		                PointerInput.Origin.viewport(), source.x + 600, source.y));
		        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));

		driver.perform(List.of(sequence));
	}

}
