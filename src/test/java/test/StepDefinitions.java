package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import lib.config.ConfigVariables;
import lib.model.Owner;
import lib.model.Pet;
import lib.webpages.AddNewPetPage;
import lib.webpages.AddOwnerPage;
import lib.webpages.AddVisitPage;
import lib.webpages.FindOwnersPage;
import lib.webpages.HomePage;
import lib.webpages.OwnerInformationPage;
import lib.webpages.OwnersPage;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {

	WebDriver driver;

	String browser;

	String url = "localhost:8080";
	String geckoDriverLocation = "C:\\geckodriver-v0.16.1-win64\\geckodriver.exe";

	String chromeDriverLocation = "C:\\chromedriver26\\chromedriver.exe";

	Owner newOwner;
	Pet pet;
	String visitDescription;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");

	private HomePage homePage;
	private FindOwnersPage findOwnersPage;
	private AddOwnerPage addOwnerPage;
	private OwnerInformationPage ownerInformationPage;
	private AddNewPetPage addNewPetPage;
	private AddVisitPage addVisitPage;
	private OwnersPage ownersPage;

	@Autowired
	private ConfigVariables config;

	@Before
	public void setUp() {
		// url = config.getUrl();
		// browser = config.getBrowser();
		System.out.println(url);

		 System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
		 driver = new ChromeDriver();

//		System.setProperty("webdriver.gecko.driver", geckoDriverLocation);
//		ProfilesIni profile = new ProfilesIni();
//		FirefoxProfile firebugProfile = profile.getProfile("selenium");
//		driver = new FirefoxDriver(firebugProfile);
		 
		driver.get(url);
		

	}

	@Given("^I have a new owner (.*) (.*) (.*) (.*) (\\d+)$")
	public void i_have_a_new_owner(String firstname, String lastname,
			String address, String city, String telephone) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		newOwner = new Owner(firstname, lastname, address, city, telephone);

	}

	@Given("^Owner has a pet (.*) (.*)$")
	public void owner_has_a_pet(String petname, String brithdate)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		Date date = sdf.parse(brithdate);

		pet = new Pet(petname, date);

	}

	@Given("^I have a (.*) about the visit$")
	public void i_have_a_description_about_the_visit(String description)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		visitDescription = description;

	}

	@When("^I add new owner$")
	public void i_add_new_owner() throws Throwable {
		
		HomePage homePage = new HomePage(driver);
		findOwnersPage = homePage.openFindOwnersPage();
		addOwnerPage = findOwnersPage.clickOnAddOwnerButton();
		addOwnerPage.sendKeysFirstName(newOwner.getFirstname());
		addOwnerPage.sendKeysLastName(newOwner.getLastname());
		addOwnerPage.sendKeysAddress(newOwner.getAddress());
		addOwnerPage.sendKeysCity(newOwner.getCity());
		addOwnerPage.sendKeysTelephone(newOwner.getTelephone());
		ownerInformationPage = addOwnerPage.clickOnAddOwnerButton();
	}

	@When("^I add new pet$")
	public void i_add_new_pet() throws Throwable {
		addNewPetPage = ownerInformationPage.clickOnAddNewPet();
		addNewPetPage.sendKeysToNameInput(pet.getName());
		addNewPetPage.sendKeysToBirtDateInput(sdf.format(pet.getBirtDate()));
		ownerInformationPage = addNewPetPage.clickOnUpdatePetButton();
	}

	@When("^I add description for visit$")
	public void i_add_description_for_visit() throws Throwable {
		addVisitPage = ownerInformationPage.clickOnAddVisit();
		addVisitPage.sendKeysToDescriptionInput(visitDescription);
		ownerInformationPage = addVisitPage.clickOnAddVisitButton();
	}

	@Then("^New owner is in the list$")
	public void new_owner_is_in_the_list() throws Throwable {
		// homePage = new HomePage(driver);
		// findOwnersPage = homePage.openFindOwnersPage();

		findOwnersPage = ownerInformationPage.openFindOwnersPage();
		ownersPage = findOwnersPage.clickOnFindEmptyOwnerButton();
		ownerInformationPage = ownersPage.findOwner(newOwner.getFirstname(), newOwner.getLastname(), newOwner.getTelephone());
//		ownerInformationPage = findOwnersPage.searchOneOwner(newOwner
//				.getLastname());
		
		ArrayList<String> list = ownerInformationPage.getOwnerInformation();
		Assert.assertEquals("Name should be equal", list.get(0), newOwner.getFirstname() + " "
				+ newOwner.getLastname());
		Assert.assertEquals("Address should be equal", list.get(1), newOwner.getAddress()
				);
		Assert.assertEquals("City should be equal", list.get(2), newOwner.getCity()
				);
		Assert.assertEquals("Telephone should be equal", list.get(3), newOwner.getTelephone()
				);
	}

	@Then("^New pet is in the owners list$")
	public void new_pet_is_in_the_owner_s_list() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^New visit is in the visits list$")
	public void new_visit_is_in_the_visits_list() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

}
