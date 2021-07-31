package com.qa.zoho.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.zoho.base.BasePage;
import com.qa.zoho.pages.ContactsPage;
import com.qa.zoho.pages.HomePage;
import com.qa.zoho.pages.LoginPage;
import com.qa.zoho.util.Constants;
import com.qa.zoho.util.ExcelUtil;

public class ContactsPageTest {
	BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;

	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_proprties();
		String browser = prop.getProperty("browser");
		driver = basePage.init_driver(browser);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.goToContacts();
	}

	@DataProvider
	public Object[][] getContactsTestData() {
		Object[][] data = ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
	}

	@Test(dataProvider = "getContactsTestData")
	public void createContactsTest(String firstName, String lastName, String eMail) throws InterruptedException {
		contactsPage.createNewContact(firstName, lastName, eMail);
		Assert.assertEquals(contactsPage.getContactName(), firstName + " "+lastName );

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
