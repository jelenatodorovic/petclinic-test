package lib.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class OwnersPage extends Page{

	public OwnersPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, OwnersPage.class);
		// TODO Auto-generated constructor stub
	}

}
