package lib.webpages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class OwnersPage extends Page{

	public OwnersPage(WebDriver driver) {
		super(driver);
		
		// TODO Auto-generated constructor stub
	}

	public OwnerInformationPage findOwner(String firstName, String lastname, String telephone) {
		ArrayList<WebElement> listOfElem = (ArrayList<WebElement>) pDriver.findElements
				(By.xpath("id('vets')/tbody/tr"));
		for (WebElement webElement : listOfElem) {
			ArrayList<WebElement> tdList = (ArrayList<WebElement>) webElement.findElements(By.tagName("td"));
			boolean equalsName = tdList.get(0).getText().equals(firstName + " " + lastname);
			boolean telephoneB = tdList.get(3).getText().equals(telephone);
			System.out.println("equals b i tele b" + equalsName + telephoneB);
			if(equalsName & telephoneB) {
				tdList.get(0).click();
				return PageFactory.initElements(pDriver, OwnerInformationPage.class);
				
			}
			
		}
		 return PageFactory.initElements(pDriver, OwnerInformationPage.class);
	}
}
