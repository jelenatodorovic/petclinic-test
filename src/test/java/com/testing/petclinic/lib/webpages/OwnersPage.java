package com.testing.petclinic.lib.webpages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class OwnersPage extends Page{

	public OwnersPage(WebDriver driver) {
		super(driver);

	}

	public OwnerInformationPage findOwner(String firstName, String lastname, String telephone) {
		ArrayList<WebElement> listOfElem = (ArrayList<WebElement>) pDriver.findElements
				(By.xpath("id('vets')/tbody/tr"));
		for (WebElement webElement : listOfElem) {
			ArrayList<WebElement> listTd = (ArrayList<WebElement>) webElement.findElements(By.tagName("td"));
			System.out.println(listTd.get(0).getText());
			boolean equalsName = listTd.get(0).getText().equals(firstName + " " + lastname);
			
			if(equalsName) {
				listTd.get(0).click();
				return PageFactory.initElements(pDriver, OwnerInformationPage.class);
				
			}
			
		}
		 
		return null;
	}
}
