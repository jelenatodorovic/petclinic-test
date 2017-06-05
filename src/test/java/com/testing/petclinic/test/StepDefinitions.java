package com.testing.petclinic.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.http.conn.routing.RouteInfo.TunnelType;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.Sleeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import com.testing.petclinic.lib.config.ConfigVariables;
import com.testing.petclinic.lib.model.Owner;
import com.testing.petclinic.lib.model.Pet;
import com.testing.petclinic.lib.utilities.Driver;
import com.testing.petclinic.lib.webpages.AddNewPetPage;
import com.testing.petclinic.lib.webpages.AddOwnerPage;
import com.testing.petclinic.lib.webpages.AddVisitPage;
import com.testing.petclinic.lib.webpages.FindOwnersPage;
import com.testing.petclinic.lib.webpages.HomePage;
import com.testing.petclinic.lib.webpages.OwnerInformationPage;
import com.testing.petclinic.lib.webpages.OwnersPage;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {

	WebDriver driver;

	String browser;

	String url;// = "localhost:8080";

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
	private ConfigVariables configVariables;

	@Before
	public void setUp() {
		url = "localhost:8080";
		
		driver = Driver.openBrowser("chrome");
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
		Sleeper.SYSTEM_SLEEPER.sleep(new Duration(3, TimeUnit.SECONDS));
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
		//ownersPage = findOwnersPage.clickOnFindEmptyOwnerButton();
//		ownerInformationPage = ownersPage.findOwner(newOwner.getFirstname(), newOwner.getLastname(), newOwner.getTelephone());
		ownerInformationPage = findOwnersPage.searchOneOwner(newOwner.getLastname());
		
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
	
	@After
	public void tearDown() {
		driver.quit();
	}

}
