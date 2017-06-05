package lib.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page {

	protected WebDriver pDriver;
	
	@FindBy(xpath = "id('main-navbar')/ul/li[2]")
	private WebElement home;
	
	@FindBy(xpath = "id('main-navbar')/ul/li[3]/a")
	private WebElement findOwners;
	
	@FindBy(xpath = "id('main-navbar')/ul/li[4]")
	private WebElement veterinarians;
	
	@FindBy(xpath = "id('main-navbar')/ul/li[5]")
	private WebElement error;
	
	public Page(WebDriver driver) {
		this.pDriver = driver;
		PageFactory.initElements(pDriver, this);
	}
	
	public HomePage openHomePage() {
		home.click();
		return PageFactory.initElements(pDriver, HomePage.class);
	}
	
	public FindOwnersPage openFindOwnersPage() {
		findOwners.click();
		return PageFactory.initElements(pDriver, FindOwnersPage.class);
	}
	
	
}
