package com.qa.zoho.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.zoho.base.BasePage;
import com.qa.zoho.listeners.TestAllureListener;
import com.qa.zoho.pages.LoginPage;
import com.qa.zoho.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners(TestAllureListener.class) // this listener added to add screenshot in case of any failures
public class LoginPageTest {

	BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;

	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_proprties();
		String browser = prop.getProperty("browser");
		driver = basePage.init_driver(browser);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
	}

	@Description("Verify login page title test.....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void verifyLoginPageTitleTest() {
		Reporter.log("Starting verification of login page title.");
		Assert.assertEquals(loginPage.getPageTitle(), Constants.LOGIN_PAGE_TITLE, "Login page title mismatched");

	}

	@Description("Verify sign up link.....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void verifysignUpLinkTest() {
		Assert.assertTrue(loginPage.verifySignUpLink(), "Sign Up link not displayed");
	}

	@Description("Verify login")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Description("Verify CRM LINK")
	@Severity(SeverityLevel.TRIVIAL)
	@Test(priority = 3)
	public void verifyClassicCRMTest() {
		Assert.assertTrue(loginPage.verifyClassicCRMLink());
	}

	@Test(priority = 4)
	public void verifyForgotPasswordTest() {
		Assert.assertTrue(loginPage.verifyForgotPasswordLink());
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
