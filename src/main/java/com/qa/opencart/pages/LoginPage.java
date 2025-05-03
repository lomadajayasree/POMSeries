package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil elUtil;
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elUtil=new ElementUtil(driver);
	}
	
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By forgotPwdLink=By.linkText("Forgotten Password");
	
	@Step("getting login page title")
	
	public String getLoginPageTitle() {
		String title=elUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		//String title=driver.getTitle();
		System.out.println("login page title is:" +title);
		
		ChainTestListener.log("login page title==>" +title);
		return title;
	}
	
	@Step("getting login page url")
	public String getLoginPageUrl() {
		String url=elUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
		//String url=driver.getCurrentUrl();
		System.out.println("login page url is:" +url);
		return url;
	}
	@Step("checking forgot pwd link exist")
	public boolean isForgotPwdLinkExist() {
		//return driver.findElement(forgotPwdLink).isDisplayed();
		return elUtil.doIsElementDisplayed(forgotPwdLink);
	}
	@Step("log in with username:{0} with pwd {1}")
	public HomePage doLogin(String username,String pwd) {
		System.out.println("login credentials:" +username+":"+pwd);
		//driver.findElement(emailId).sendKeys(username);
		elUtil.waitForElementVisible(emailId, AppConstants.DEFAULT_TIME_OUT).sendKeys(username);
		//driver.findElement(password).sendKeys(pwd);
		elUtil.doSendKeys(password, pwd);
		//driver.findElement(loginBtn).click();
		elUtil.doClick(loginBtn);
		return new HomePage(driver);
	}
	
	
	
	

}
