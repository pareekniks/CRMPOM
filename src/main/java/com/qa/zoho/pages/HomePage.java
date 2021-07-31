package com.qa.zoho.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.zoho.base.BasePage;
import com.qa.zoho.util.Constants;
import com.qa.zoho.util.ElementUtil;

public class HomePage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;

	By headerIcon = By.xpath("//div[@id='top-header-menu']/div[1]");
	By header = By.xpath("//div[text()='Contacts activity stream']");
	By accountName = By.cssSelector("div>.user-display");
	By contacts = By.xpath("//span[text()='Contacts']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getHomePageTitle() {
		return elementUtil.waitForTitlePresent(Constants.HOME_PAGE_TITLE, 15);
	}

	public boolean isHeaderPresent() {
		return elementUtil.isElementDisplayed(header, 10);
	}

	public String getHomePageHeaderTest() {
		return elementUtil.doGetText(header);
	}

	public boolean isAccountnamePresent() {
		return elementUtil.isElementDisplayed(accountName, 10);
	}

	public String getAccountName() {
		return elementUtil.doGetText(accountName);
	}

	public ContactsPage goToContacts() {
		elementUtil.mouseClick(contacts);
		return new ContactsPage(driver);

	}
}
