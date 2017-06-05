package com.testing.petclinic.lib.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewPetPage extends Page{

	@FindBy(id = "name")
	private WebElement nameInput;
	
	@FindBy(id = "birthDate")
	private WebElement birthDateInput;
	
	@FindBy(xpath = "//button[contains(@class,'btn btn-default')]")
	private WebElement updatePetButton;
	
	public AddNewPetPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void sendKeysToNameInput(String name) {
		nameInput.sendKeys(name);
	}
	
	public void sendKeysToBirtDateInput(String birthDate) {
		birthDateInput.sendKeys(birthDate);
	}
	
	public OwnerInformationPage clickOnUpdatePetButton() {
		updatePetButton.click();
		return PageFactory.initElements(pDriver, OwnerInformationPage.class);
	}
}
