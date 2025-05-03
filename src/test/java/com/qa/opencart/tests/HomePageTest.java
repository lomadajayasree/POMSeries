package com.qa.opencart.tests;

import java.util.List;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

@Epic("Epic 100:design home page for open cart")
@Story("US 101:design the various features of opencart home page")
@Feature("Feature 51:home page feature")
@Owner("jayasree.lomada")


public class HomePageTest extends BaseTest {
	@BeforeClass
	public void homePageSetUp() {
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Description("checking home page test")
	@Severity(SeverityLevel.MINOR)
	
	@Test
	public void HomePageTitleTest() {
		Assert.assertEquals(homepage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE,AppError.TITLE_NOT_FOUND_ERROR);
	}
	@Description("checking home page url")
	@Severity(SeverityLevel.MINOR)
	
	@Test
	public void HomePageUrlTest() {
		Assert.assertTrue(homepage.getHomePageUrl().contains(AppConstants.HOME_PAGE_URL_FRACTION),AppError.URL_NOT_FOUND_ERROR);
	}
	@Description("checking logout link exist or not")
	@Severity(SeverityLevel.MINOR)
	
	@Test
	public void logoutLinkExistTest() {
		Assert.assertTrue(homepage.isLogoutLinkExist(),AppError.ELEMENT_NOT_FOUND_ERROR);
	}
	@Description("getting headers list")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void headersTest() {
		List<String> actualHeaders=homepage.getHeadersList();
		System.out.println("headers list are:"+actualHeaders);
	}
	
	@DataProvider
	public Object[] [] getSearchData() {
		return new Object[] [] {
			{"macbook",3},
			{"imac",1},
			{"samsung",2},
			{"canon",1},
			{"airtel",0}	
		};
	}
	@Description("searching the particular product")
	@Severity(SeverityLevel.MINOR)
	@Test(dataProvider="getSearchData")
	public void searchTest(String searchKey,int resultCount) {
		searchResultsPage=homepage.doSearch(searchKey);
		Assert.assertEquals(searchResultsPage.getResultsPageCount(), resultCount);
	}
	

}
