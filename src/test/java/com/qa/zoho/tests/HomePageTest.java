package com.qa.zoho.tests;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.zoho.base.BasePage;
import com.qa.zoho.pages.HomePage;
import com.qa.zoho.pages.LoginPage;
import com.qa.zoho.util.Constants;

public class HomePageTest {
	BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;

	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_proprties();
		String browser = prop.getProperty("browser");
		driver = basePage.init_driver(browser);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		Assert.assertEquals(homePage.getHomePageTitle(), Constants.HOME_PAGE_TITLE, "Home Page Title mismatch");
	}

	@Test(priority = 2)
	public void verifyHomePageHeaderTest() {
		Assert.assertTrue(homePage.isHeaderPresent(), "Home Page header not present");
		Assert.assertEquals(homePage.getHomePageHeaderTest(), Constants.HOME_PAGE_HEADER);
	}

	@Test(priority = 3)
	public void verifyLoggedInUserTest() {
		Assert.assertTrue(homePage.isAccountnamePresent());
		Assert.assertEquals(homePage.getAccountName(), prop.getProperty("accountname"));
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
