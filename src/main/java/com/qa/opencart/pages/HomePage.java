package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class HomePage {
	private WebDriver driver;
	private ElementUtil elUtil;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;	
		elUtil=new ElementUtil(driver);
	}
	
   private By logOutLink=By.linkText("Logout");
   private By headers=By.cssSelector("div#content>h2");
   private By search=By.name("search");
   private By searchIcon=By.cssSelector("div#search button");
   
   
   public String getHomePageTitle() {
	  String title= elUtil.waitForTitleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
	   //String title=driver.getTitle();
	   System.out.println("home page title:" +title);
	   return title;
   }
   public String getHomePageUrl() {
	  String url= elUtil.waitForURLContains(AppConstants.HOME_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
	   //String url=driver.getCurrentUrl();
	   System.out.println("home page url:"+url);
	   return url;
   }
   public boolean isLogoutLinkExist() {
	   //return driver.findElement(logOutLink).isDisplayed();
	   return elUtil.doIsElementDisplayed(logOutLink);
   }
   public List<String> getHeadersList() {
	   //List<WebElement> headersList=driver.findElements(headers);
	   List<WebElement> headersList=elUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_TIME_OUT);
	   List<String> headersValList=new ArrayList<String>();
	   for(WebElement e:headersList) {
		   String text=e.getText();
		   headersValList.add(text);
	   }
	   return headersValList;
   }
   public void logout() {
	   if(isLogoutLinkExist()) {
		  //driver.findElement(logOutLink).click();
		   elUtil.doClick(logOutLink);
	   }
   }
   public SearchResultsPage doSearch(String searchKey) {
	   System.out.println("search key is:"+searchKey);
	   //driver.findElement(search).sendKeys(searchKey);
	   WebElement searchEle=elUtil.waitForElementVisible(search, AppConstants.DEFAULT_TIME_OUT);
	   searchEle.clear();
	   searchEle.sendKeys(searchKey);
	   //driver.findElement(searchIcon).click();
	   elUtil.waitForElementVisible(searchIcon, AppConstants.DEFAULT_TIME_OUT).click();
	   return new SearchResultsPage(driver);
   }
   

}
