package com.testing.petclinic.lib.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.testing.petclinic.lib.config.ConfigVariables;

//@Component
public class Driver {


	public static WebDriver openBrowser(String browser) {
	
		WebDriver driver = null;
		if(browser.equals("firefox")) {
			String geckoDriverLocation = Driver.class.getResource("/tools/geckodriver.exe").getPath();
			System.setProperty("webdriver.gecko.driver", geckoDriverLocation);
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile firebugProfile = profile.getProfile("selenium");
			driver = new FirefoxDriver(firebugProfile);
			 
		}
		else if(browser.equals("chrome")) {
		     String chromeDriverLocation = Driver.class.getResource("/tools/chromedriver.exe").getPath();
			 System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
			 driver = new ChromeDriver();
		}
		else if(browser.equals("ie")) {
			String ieDriverLocation = Driver.class.getResource("/tools/IEDriverServer.exe").getPath();
			System.setProperty("webdriver.ie.driver", ieDriverLocation);
			driver = new InternetExplorerDriver();
		}
		else {
			System.err.println("Browser not supported!!");
		}
		return driver;
	}
	
}
