package lib.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddOwnerPage extends Page {

	@FindBy(id = "firstName")
	private WebElement firstNameInput;
	
	@FindBy(id = "lastName")
	private WebElement lastNameInput;
	
	@FindBy(id = "address")
	private WebElement addressInput;
	
	@FindBy(id = "city")
	private WebElement cityInput;
	
	@FindBy(id = "telephone")
	private WebElement telephoneInput;
	
	@FindBy(css = ".btn.btn-default")
	private WebElement addOwnerButton;
	
	public AddOwnerPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(pDriver, this);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 *  Send keys methods
	 */
	public void sendKeysFirstName(String firstName) {
		firstNameInput.sendKeys(firstName);
	}
	
	public void sendKeysLastName(String lastName) {
		lastNameInput.sendKeys(lastName);
	}
	
	public void sendKeysAddress(String address) {
		addressInput.sendKeys(address);
	}
	
	public void sendKeysCity(String city) {
		cityInput.sendKeys(city);
	}
	
	public void sendKeysTelephone(String telephone) {
		telephoneInput.sendKeys(telephone);
	}

	public OwnerInformationPage clickOnAddOwnerButton() {
		addOwnerButton.click();
		return new OwnerInformationPage(pDriver);
	}
}
