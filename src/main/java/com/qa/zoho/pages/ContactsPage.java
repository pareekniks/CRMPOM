package com.qa.zoho.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.zoho.base.BasePage;
import com.qa.zoho.util.Constants;
import com.qa.zoho.util.ElementUtil;

public class ContactsPage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;

	By createButton = By.xpath("//button[@class='ui linkedin button']/i[@class='edit icon']");
	By saveButton = By.xpath("//i[@class='save icon']/parent::button");
	By firstName = By.xpath("//input[@name='first_name']");
	By lastName = By.name("last_name");
	By eMail = By.xpath("//input[@placeholder='Email address']");
	By contactName = By.cssSelector(".light-black");
	By firstContactName =By.xpath("(//td/a[text()])[1]");

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public void getContactsPageTitle() {
		elementUtil.waitForTitlePresent(Constants.CONTACTS_PAGE_TITLE, 10);
	}

	public void createNewContact( String FN, String LN, String mail) throws InterruptedException  {
		Thread.sleep(5000);
		elementUtil.waitForElement(createButton, 20);
		elementUtil.doClick(createButton);
		elementUtil.pageRefresh();
		elementUtil.waitForElement(eMail,20);
		elementUtil.doSendkeys(firstName,FN);
		elementUtil.doSendkeys(lastName, LN);
		elementUtil.doSendkeys(eMail,  mail);
		elementUtil.waitForElement(saveButton,  20);
		elementUtil.mouseClick( saveButton);
		elementUtil.waitForURL("-",  20);
	}
	
	public String getContactName() {
		elementUtil.waitForElement(contactName, 20);
		return elementUtil.getTextOfElement(contactName);
	}
	

}
