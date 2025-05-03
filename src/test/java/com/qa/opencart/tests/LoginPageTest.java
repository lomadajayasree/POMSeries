package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic 100:design login page for open cart")
@Story("US 101:design the various features of opencart login page")
@Feature("Feature 50:Login page feature")
@Owner("jayasree.lomada")



public class LoginPageTest extends BaseTest {
	@Description("checking login page test")
	@Severity(SeverityLevel.MINOR)
	
	@Test
	public void loginPageTitleTest() {
		ChainTestListener.log("loginPageTitleTest");
		String actLoginTitle=loginpage.getLoginPageTitle();
		Assert.assertEquals(actLoginTitle, AppConstants.LOGIN_PAGE_TITLE,AppError.TITLE_NOT_FOUND_ERROR);
	}
	@Description("checking login page url")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageUrlTest() {
		
		String loginUrl=loginpage.getLoginPageUrl();
		Assert.assertTrue(loginUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION),AppError.URL_NOT_FOUND_ERROR);
	}
	@Description("checking forgot pwd link")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginpage.isForgotPwdLinkExist(),AppError.ELEMENT_NOT_FOUND_ERROR);
	}
	@Description("checking user is able to login with credintials")
	@Severity(SeverityLevel.BLOCKER)
	
	
	@Test(priority=Integer.MAX_VALUE)
	public void loginTest() {
		homepage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homepage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE,AppError.TITLE_NOT_FOUND_ERROR);
	}
	@Description("checking page logo")
	@Severity(SeverityLevel.CRITICAL)
	
	
	@Test
	
	public void logoTest() {
		Assert.assertTrue(commonsPage.isLogoDisplayed(),AppError.LOGO_NOT_FOUND_ERROR);
	}
	@DataProvider
	public Object[][] getProviderTest() {
		return new Object[][] {
			{"About Us"},
			{"Delivery Information"},
			{"Privacy Policy"},
			{"Terms & Conditions"},
			{"Returns"}	
		};
		
	}
	@Description("checking footers link")
	@Severity(SeverityLevel.MINOR)
	
	
	@Test(dataProvider="getProviderTest")
	
	public void footerTest(String footerLink) {
		Assert.assertTrue(commonsPage.checkFooterLink(footerLink));
	}
	
	

}
