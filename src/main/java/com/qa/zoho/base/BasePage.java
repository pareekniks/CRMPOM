package com.qa.zoho.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	WebDriver driver;
	Properties prop;

	/**
	 * This method is used to initialize on the basis of browser name
	 * 
	 * @param browserName
	 * @return
	 */

	public WebDriver init_driver(String browserName) {
		if (browserName.equals("chrome")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			WebDriverManager.chromedriver().setup();
			chromeOptions.addArguments("--incognito");
			driver = new ChromeDriver(chromeOptions);

		} else if (browserName.equals("ff")) {
			WebDriverManager.firefoxdriver();
			driver = new FirefoxDriver();
		} else {
			System.out.println(browserName + "Browser valeu wrong, provide correct browser");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		return driver;
	}

	/**
	 * this method us used to read the property file
	 * 
	 * @return prop
	 */
	public Properties init_proprties() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					"D:\\Practice\\POMMavenProject\\src\\main\\java\\com\\qa\\zoho\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("Config file is missing, please check it");
		} catch (IOException e) {
			System.out.println("Property file not loaded");
		}
		return prop;
	}
	/**
	 * This method is used to take the screenshot It will return the path of
	 * screenshot
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot)init_driver("chrome")).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
