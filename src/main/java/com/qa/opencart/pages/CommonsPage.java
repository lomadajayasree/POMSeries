package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class CommonsPage {
	
	private WebDriver driver;
	private ElementUtil elUtil;
	
	
	public CommonsPage(WebDriver driver) {
		this.driver=driver;
		elUtil=new ElementUtil(driver);
	}
	
	private By logo=By.className("img-responsive");
	private By footer=By.xpath("//footer//a");
	
	
	
	public boolean isLogoDisplayed() {
		return elUtil.doIsElementDisplayed(logo);
	}
	
	
	public List<String> getFootersList() {
		List<WebElement> footersList=elUtil.waitForElementsPresence(footer, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("footers list are:" +footersList);
		List<String> footers=new ArrayList<String>();
		for(WebElement e:footersList) {
			String text=e.getText();
			footers.add(text);
		}
		return footers;
		
	}
	
	
	public boolean checkFooterLink(String footerLink) {
		return getFootersList().contains(footerLink);
		
	}

}
