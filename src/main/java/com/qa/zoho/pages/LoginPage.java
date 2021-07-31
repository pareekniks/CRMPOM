package com.qa.zoho.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.zoho.base.BasePage;
import com.qa.zoho.util.Constants;
import com.qa.zoho.util.ElementUtil;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;

	// Page Objects/OR
	By emailId = By.name("email");
	By password = By.name("password");
	By loginButton = By.xpath("//div[text()='Login']");
	By signUpLink = By.xpath("//a[text()='Sign Up']");
	By forgotPasswordLink = By.linkText("Forgot your password?");
	By classicCRMLink = By.linkText("Classic CRM");

	// Constructor

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);

	}

	// Page actions/methods

	/**
	 * Verifying the title
	 * 
	 * @return title
	 */
	public String getPageTitle() {
		String title = elementUtil.waitForTitlePresent(Constants.LOGIN_PAGE_TITLE, 15);
		System.out.println("Login page title is: " + title);
		return title;
	}

	/**
	 * Verifying the signup link
	 * 
	 * @return boolean value of the signup link
	 */
	public boolean verifySignUpLink() {
		return elementUtil.isElementDisplayed(signUpLink, 20);
	}

	/**
	 * Login into the application
	 * 
	 * @param username
	 * @param pwd
	 * @return
	 */
	public HomePage doLogin(String username, String pwd) {
		elementUtil.doSendkeys(emailId, username);
		elementUtil.doSendkeys(password, pwd);
		elementUtil.doClick(loginButton);
		elementUtil.waitForInvisiblityOfElement(10, loginButton);
		return new HomePage(driver);
	}

	public boolean verifyForgotPasswordLink() {
		return elementUtil.isElementDisplayed(forgotPasswordLink, 10);
	}

	public boolean verifyClassicCRMLink() {
		
		return elementUtil.isElementDisplayed(classicCRMLink, 10);
		
	}

}
