package com.testing.petclinic.lib.webpages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testing.petclinic.lib.model.Owner;
import com.testing.petclinic.lib.model.Pet;

public class OwnerInformationPage extends Page{

	@FindBy(xpath = "html/body/div[1]/div/a[2]")
	private WebElement addNewpet;
	
	@FindBy(xpath = "//table[contains(@class,'table-condensed')]//tbody//td[2]")
	private WebElement addVisit;
	
	@FindBy(xpath = "//table[2]//tr[1]//td/dl/dd[1]")
	private WebElement firstPetName;
	
	@FindBy(xpath = "//table[2]/tbody/tr/td[2]//tr[1]/td[2]")
	private WebElement firstPetVisitDescription;
	
	public OwnerInformationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public AddNewPetPage clickOnAddNewPet() {
		addNewpet.click();
		return PageFactory.initElements(pDriver, AddNewPetPage.class);
	}
	
	public AddVisitPage clickOnAddVisit() {
		addVisit.click();
		return PageFactory.initElements(pDriver, AddVisitPage.class);
	}
	
	public Owner getOwnerInformation() {
		ArrayList<WebElement> ownerInfo = (ArrayList<WebElement>) pDriver.findElements(By.xpath("//table[1]//tr/td"));
		ArrayList<String> list = new ArrayList<String>();
		for (WebElement webElement : ownerInfo) {
			list.add(webElement.getText());
		}
		String [] split = list.get(0).split(" ");
		return new Owner(split[0], split[1], list.get(1), list.get(2), list.get(3));
		
		
	}
	
	public Pet getPetInformation() {
		ArrayList<WebElement> webElemsList = (ArrayList<WebElement>) pDriver.findElements(By.xpath("//table[2]/tbody/tr/td/dl/dd"));
		ArrayList<String > list = new ArrayList<String>();
		for (WebElement elem : webElemsList) {
			list.add(elem.getText());			
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		try {
			return new Pet(list.get(0), sdf.parse(list.get(1)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String getFirstPetVisitDescription() {
		return firstPetVisitDescription.getText();
	}
}
