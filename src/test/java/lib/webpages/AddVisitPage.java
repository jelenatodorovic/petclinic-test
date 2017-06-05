package lib.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddVisitPage extends Page {

	@FindBy(id = "description")
	private WebElement descriptionInput;
	
	@FindBy(css = ".btn.btn-default")
	private WebElement addVisitButton;
	
	public AddVisitPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void sendKeysToDescriptionInput(String description) {
		descriptionInput.sendKeys(description);
	}
	
	public OwnerInformationPage clickOnAddVisitButton() {
		addVisitButton.click();
		return PageFactory.initElements(pDriver, OwnerInformationPage.class);
	}
}
