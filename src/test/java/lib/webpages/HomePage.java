package lib.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Page {

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, HomePage.class);
		// TODO Auto-generated constructor stub
	}

}
