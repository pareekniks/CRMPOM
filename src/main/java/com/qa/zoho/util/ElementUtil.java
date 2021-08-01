package com.qa.zoho.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This method is used to create the webElement on the basis of By Locator
	 * 
	 * @param locator
	 * @return WebElement
	 */
	public WebElement getElement(By locator) {

		WebElement element = null;
		try {

			element = driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("Some exception occured while creating the webElement");
			System.out.println(e.getMessage());
		}
		return element;
	}

	/**
	 * 
	 * This method is used to click on the webElement on the basis of By Locator
	 * 
	 * @param locator
	 */
	public void doClick(By locator) {
		try {
			getElement(locator).click();
		} catch (Exception e) {
			System.out.println("Some exception occured while clicking on the WebElement");
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSendkeys(By locator, String value) {

		try {
			waitForElement(locator, 15);
			getElement(locator).sendKeys(value);
		} catch (Exception e) {
			System.out.println("Some exception occured while sending the value in the WebElement");
			System.out.println(e.getMessage());
		}

	}

	/**
	 * This method is used to close the browser
	 * 
	 * @param driver
	 */

	public void closeBrowser() {
		try {
			driver.close();
		} catch (Exception e) {
			System.out.println("Some exception occured while closing the browser");
			System.out.println(e.getMessage());
		}

	}

	/**
	 * This method get the value from any webElement
	 * 
	 * @param locator
	 * @return
	 */

	public String getTextOfElement(By locator) {
		String textValue = null;
		try {
			textValue = getElement(locator).getText();
		} catch (Exception e) {
			System.out.println("Some exception occured while  capturing the values");
			System.out.println(e.getMessage());
		}
		return textValue;
	}

	/**
	 * This method wait for element
	 * 
	 * @param locator
	 * @param driver
	 * @param waitTime
	 */
	public void waitForElement(By locator, int waitTime) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		} catch (Exception e) {
			System.out.println("Some exception occured while  waiting for the element");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method wait for title
	 * 
	 * @param locator
	 * @param driver
	 * @param waitTime
	 * @return
	 */
	public String waitForTitlePresent(String title, int waitTime) {

		String returnedTitle = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.titleContains(title));
			return driver.getTitle();

		} catch (Exception e) {
			System.out.println("Some exception occured while  waiting for the element");
			System.out.println(e.getMessage());
		}
		return returnedTitle;
	}

	public boolean isElementDisplayed(By locator, int Time) {
		try {
			waitForElement(locator, Time);
			getElement(locator).isDisplayed();
			return true;
		} catch (Exception e) {
			System.out.println("Some exception occured");
			System.out.println(e.getMessage());
			return false;
		}

	}

	public String doGetText(By locator) {
		try {
			return getElement(locator).getText();
		} catch (Exception e) {
			System.out.println("Some exception occured while getting the text for the WebElement");
			System.out.println(e.getMessage());
			return null;
		}

	}

	public String waitForURL(String urlText, int waitTime) {

		String returnedURL = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.urlContains(urlText));
			return driver.getCurrentUrl();

		} catch (Exception e) {
			System.out.println("Some exception occured while  waiting for the url");
			System.out.println(e.getMessage());
		}
		return returnedURL;
	}

	public void waitForInvisiblityOfElement(int waitTime, By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

		} catch (Exception e) {
			System.out.println("Some exception occured while  waiting for the element");
			System.out.println(e.getMessage());
		}
	}

	public void mouseClick(By locator) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).click().build().perform();
	}

	public void pageRefresh() {
		driver.navigate().to(driver.getCurrentUrl());

	}

	public void implicitWait(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

}
