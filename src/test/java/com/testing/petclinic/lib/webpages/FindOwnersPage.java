package com.testing.petclinic.lib.webpages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class FindOwnersPage extends Page{

	@FindBy(xpath = "id('lastName')//input")
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
	
	public AddOwnerPage clickOnAddOwnerButton() {
		addOwnerButton.click();
		return PageFactory.initElements(pDriver, AddOwnerPage.class);
	}
	
	public OwnerInformationPage searchOneOwner(String lastname) {
		try {
			Sleeper.SYSTEM_SLEEPER.sleep(new Duration(3, TimeUnit.SECONDS));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sendKeysLastnameInput(lastname);
	
		return clickOnFindOwnerButton();
	}
	public void searchNonExistingOwner(String s) {
		sendKeysLastnameInput(s);
		clickOnFindOwnerButton();
	}
	public OwnerInformationPage clickOnFindOwnerButton() {
		
		findOwnerButton.click();
		return PageFactory.initElements(pDriver, OwnerInformationPage.class);
	}
	
	public OwnersPage clickOnFindEmptyOwnerButton() {
		
		findOwnerButton.click();
		return PageFactory.initElements(pDriver, OwnersPage.class);
	}
	public void sendKeysLastnameInput(String sInput) {
		lastnameInput.sendKeys(sInput);
	}
}
