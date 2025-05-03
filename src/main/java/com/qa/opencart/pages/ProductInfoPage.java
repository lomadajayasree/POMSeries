package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
private WebDriver driver;
private ElementUtil elUtil;
private Map<String,String> productMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;	
		elUtil=new ElementUtil(driver);
		
	}
	
	private By productHeader=By.tagName("h1");
	private By productImages=By.cssSelector("ul.thumbnails img");
	private By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	
	public String getProductHeader() {
		//String productHeaderText=driver.findElement(productHeader).getText();
		String productHeaderText=elUtil.waitForElementVisible(productHeader, AppConstants.DEFAULT_TIME_OUT).getText();
		System.out.println("product header test:" + productHeaderText);
		return productHeaderText;
	}
	
	
	public int getProductImagesCount() {
		int imagesCount=elUtil.waitForElementsPresence(productImages, AppConstants.DEFAULT_TIME_OUT).size();
		System.out.println("images count:" +imagesCount);
		return imagesCount;
	}
	
	public Map<String, String> getCompleteProductInfo() {
		productMap=new HashMap<String,String>();
		productMap.put("header", getProductHeader());
		productMap.put("images", getProductImagesCount()+"");
		getProductMetaData();
		getProductPriceData();
		return productMap;
	}
	
	private void getProductMetaData() {
		List<WebElement> metaList=elUtil.waitForElementsPresence(productMetaData, AppConstants.DEFAULT_TIME_OUT);
		for(WebElement e:metaList) {
			String metaText=e.getText();
			String meta[]=metaText.split(":");
			String metaKey=meta[0].trim();
			String metaValue=meta[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}
		
		private void getProductPriceData() {
			List<WebElement> priceList=elUtil.waitForElementsPresence(productPriceData,AppConstants.DEFAULT_TIME_OUT);
			String productPrice=priceList.get(0).getText().trim();
			String productExTax=priceList.get(1).getText().split(":")[1].trim();
			productMap.put("exTax", productExTax);
			productMap.put("price", productPrice);
		}
	

}
