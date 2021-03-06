package com.testing.petclinic.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
	glue = "com.testing.petclinic.test",
	plugin = {"pretty", "json:target/cucumber.json"}
		)
public class RunCukesTest {

}
