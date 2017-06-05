package lib.webpages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OwnerInformationPage extends Page{

	@FindBy(xpath = "html/body/div[1]/div/a[2]")
	private WebElement addNewpet;
	
	@FindBy(xpath = "//table[contains(@class,'table-condensed')]//tbody//td[2]")
	private WebElement addVisit;
	
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
	
	public ArrayList<String> getOwnerInformation() {
		ArrayList<WebElement> ownerInfo = (ArrayList<WebElement>) pDriver.findElements(By.xpath("//table[1]//tr/td"));
		ArrayList<String> list = new ArrayList<String>();
		for (WebElement webElement : ownerInfo) {
			list.add(webElement.getText());
		}
		return list;
		
	}
}
