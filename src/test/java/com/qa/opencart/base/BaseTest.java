
package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.chaintest.service.ChainPluginService;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultsPage;

import io.qameta.allure.Description;

//@Listeners(ChainTestListener.class)
public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	
	protected Properties prop;
	protected CommonsPage commonsPage;
	protected LoginPage loginpage;
	protected HomePage homepage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName) {
		
		df=new DriverFactory();
		prop=df.initProp();
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop);
		loginpage=new LoginPage(driver);
		commonsPage=new CommonsPage(driver);
		ChainPluginService.getInstance().addSystemInfo("Build#", "1.0");
		ChainPluginService.getInstance().addSystemInfo("Headless#", prop.getProperty("headless"));
		ChainPluginService.getInstance().addSystemInfo("Incognito", prop.getProperty("incognito"));
		ChainPluginService.getInstance().addSystemInfo("Owner#", "jayasree36961@gmail.com");
		
	}
	
	@Description("teardown:taking the screenshot only if test is failed...")
	@AfterMethod
	public void attachScreenshot(ITestResult result) {
		if(!result.isSuccess()) {
		    //ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
			//ChainTestListener.embed(DriverFactory.getScreenshotByte(), "image/png");
			ChainTestListener.embed(DriverFactory.getScreenshotBase64(), "image/png");
	    }
	}
	
	
	
	@Description("closing the browser")
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
