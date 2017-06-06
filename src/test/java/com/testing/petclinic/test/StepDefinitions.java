package com.testing.petclinic.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.Sleeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import com.testing.petclinic.lib.config.ConfigVariables;
import com.testing.petclinic.lib.config.TestConfig;
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

@ContextConfiguration(classes={ ConfigVariables.class, TestConfig.class })
public class StepDefinitions {

	WebDriver driver;

	String browser;

	String url;

	Owner newOwner;
	Pet pet;
	String visitDescription;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");


	private HomePage homePage;
	private FindOwnersPage findOwnersPage;
	private AddOwnerPage addOwnerPage;
	private OwnerInformationPage ownerInformationPage;
	private AddNewPetPage addNewPetPage;
	private AddVisitPage addVisitPage;
	private OwnersPage ownersPage;

	@Autowired
	ConfigVariables configVariables;
	
	@Autowired
	TestConfig testConfig;
	@Before
	public void setUp() {
		
		url = configVariables.getUrl();
		browser = configVariables.getBrowser();
		driver = testConfig.getDriver().openBrowser(browser);
		driver.get(url);

	}

	@Given("^I have a new owner (.+) (.+) (.+) (.+) (\\d+)$")
	public void i_have_a_new_owner(String firstname, String lastname,
			String address, String city, String telephone) throws Throwable {
	
		newOwner = new Owner(firstname, lastname, address, city, telephone);

	}

	@Given("^Owner has a pet (.*) (.*)$")
	public void owner_has_a_pet(String petname, String brithdate)
			throws Throwable {

		Date date = sdf.parse(brithdate);

		pet = new Pet(petname, date);

	}

	@Given("^I have a (.*) about the visit$")
	public void i_have_a_description_about_the_visit(String description)
			throws Throwable {
	
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

		findOwnersPage = ownerInformationPage.openFindOwnersPage();
		ownerInformationPage = findOwnersPage.searchOneOwner(newOwner.getLastname());
		
		Owner owner1 = ownerInformationPage.getOwnerInformation();
		Assert.assertEquals("Name should be equal", newOwner.getFirstname() + " "
				+ newOwner.getLastname(), owner1.getFirstname() + " "+owner1.getLastname());
		Assert.assertEquals("Address should be equal", newOwner.getAddress(), 
				owner1.getAddress());
		Assert.assertEquals("City should be equal", newOwner.getCity(), 
				owner1.getCity());
		Assert.assertEquals("Telephone should be equal", newOwner.getTelephone(),
				owner1.getTelephone());
	}

	@Then("^New pet is in the owners list$")
	public void new_pet_is_in_the_owner_s_list() throws Throwable {
		Pet pet1 = ownerInformationPage.getPetInformation();
		
		Assert.assertEquals("Name should be equal", pet1.getName(), pet.getName());
		Assert.assertEquals("Birth date should be equal", sdf1.format(pet1.getBirtDate()), sdf1.format(pet.getBirtDate()));
	}

	@Then("^New visit is in the visits list$")
	public void new_visit_is_in_the_visits_list() throws Throwable {
		String desc = ownerInformationPage.getFirstPetVisitDescription();
		
		Assert.assertEquals("Visist description should be equal", visitDescription, desc);
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}

}
