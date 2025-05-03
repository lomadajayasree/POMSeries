package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
private WebDriver driver;
private ElementUtil elUtil;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;	
		elUtil=new ElementUtil(driver);
	}
	
	private By productSize=By.cssSelector("div.product-thumb");
	
	
	public int getResultsPageCount() {
		//int productResults= driver.findElements(productSize).size();
		int productResults=elUtil.waitForElementsVisible(productSize, AppConstants.DEFAULT_TIME_OUT).size();
		System.out.println("product count is:" +productResults);
		return productResults;
	}
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("product name is" +productName);
		elUtil.doClick(By.linkText(productName));
		//driver.findElement(By.linkText(productName)).click();
		return new ProductInfoPage(driver);
	}

}
