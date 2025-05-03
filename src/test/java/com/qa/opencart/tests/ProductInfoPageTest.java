package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetUp() {
		homepage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][]{
			{"macbook","MacBook"},
			{"macbook","MacBook Air"},
			{"macbook","MacBook Pro"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test(dataProvider="getProductData")
	public void productSearchHeaderTest(String searchKey,String productName) {
		searchResultsPage=homepage.doSearch(searchKey);
		productInfoPage=searchResultsPage.selectProduct(productName);
		String actualHeader=productInfoPage.getProductHeader();
		Assert.assertEquals(actualHeader, productName);
	}
	@DataProvider
	public Object[][] getProductImagesCount() {
		return new Object[] [] {
			{"macbook","MacBook",5},
			{"macbook","MacBook Air",4},
			{"macbook","MacBook Pro",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7}
		};
	}
	
	@Test(dataProvider="getProductImagesCount")
	public void productImagesCountText(String searchKey,String productName,int imagesCount) {
		searchResultsPage=homepage.doSearch(searchKey);
		productInfoPage=searchResultsPage.selectProduct(productName);
		int actualImagesCount=productInfoPage.getProductImagesCount();
		Assert.assertEquals(actualImagesCount, imagesCount);
	}
	
	
	@Test
	public void productInfoTest() {
		searchResultsPage=homepage.doSearch("macbook");
		productInfoPage=searchResultsPage.selectProduct("MacBook Pro");
		Map<String,String> productInfoMap=productInfoPage.getCompleteProductInfo();
		productInfoMap.forEach((k,v)->System.out.println(k+":"+v));
		SoftAssert softAssert=new SoftAssert();
		
		softAssert.assertEquals(productInfoMap.get("header"),"MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("Brand"),"Apple");
		softAssert.assertEquals(productInfoMap.get("Availability"),"Out Of Stock");
		softAssert.assertEquals(productInfoMap.get("Reward Points"),"800");
		softAssert.assertEquals(productInfoMap.get("Product Code"),"Product 18");
		softAssert.assertEquals(productInfoMap.get("price"),"$2,000.00");
		softAssert.assertEquals(productInfoMap.get("exTax"),"$2,000.00");
		softAssert.assertAll();
		
	}

}
