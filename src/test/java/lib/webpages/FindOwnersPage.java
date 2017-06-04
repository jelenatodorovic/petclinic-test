package lib.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindOwnersPage extends Page{

	@FindBy(id = "lastname")
	private WebElement lastnameInput;
	
	@FindBy(xpath = "id('search-owner-form')/div[2]/div/button")
	private WebElement findOwnerButton;
	
	@FindBy(xpath = "//a[contains(@href,'/owners/new')]")
	private WebElement addOwnerButton;
	
	public FindOwnersPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	//public OwnersPage searchMoreThanOneOwner(String s)
	
	public OwnerInformationPage searchOneOwner(String lastname) {
		return null;
	}
	public void searchNonExistingOwner(String s) {
		sendKeysLastnameInput(s);
		clickOnFindOwnerButton();
	}
	public void clickOnFindOwnerButton() {
		findOwnerButton.click();
	}
	
	public void sendKeysLastnameInput(String sInput) {
		lastnameInput.sendKeys(sInput);
	}
}
