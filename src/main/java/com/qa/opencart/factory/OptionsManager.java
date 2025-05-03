package com.qa.opencart.factory;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	
	public OptionsManager(Properties prop) {
		this.prop=prop;
	}
	
	private static final Logger log = LogManager.getLogger(OptionsManager.class);
	
	public ChromeOptions getChromeOptions() {
		co=new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			//System.out.println("===Running in HeadLess Mode===");
			log.info("===Running in HeadLess Mode===");
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			//System.out.println("===Running in Incognito Mode===");
			log.info("===Running in Incognito Mode===");
			co.addArguments("--incognito");
		}
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo=new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			//System.out.println("===Running in HeadLess Mode===");
			log.info("===Running in HeadLess Mode===");
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			//System.out.println("===Running in Incognito Mode===");
			log.info("===Running in Incognito Mode===");
			co.addArguments("--incognito");
		}
		return fo;
	}
	
	public EdgeOptions getEdgeOptions() {
		eo=new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			//System.out.println("===Running in HeadLess Mode===");
			log.info("===Running in HeadLess Mode===");
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			//System.out.println("===Running in Incognito Mode===");
			log.info("===Running in Incognito Mode===");
			co.addArguments("--inPrivate");
		}
		return eo;
	}
	
	

}
