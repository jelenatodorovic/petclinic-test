Feature: Critical happy path

Scenario Outline: End to end test
Given I have a new owner <firstname> <lastname> <address> <city> <telephone>
And Owner has a pet <petname> <birthdate>
And I have a <description> about the visit
When I add new owner
And I add new pet
And I add description for visit
Then New owner is in the list
And New pet is in the owners list
And New visit is in the visits list

Examples:
|firstname|lastname   |address  |city    |telephone|petname|birthdate  |description  |
|Mary     |Todorov7   |road 	  |London  |07548972 | Suzi  |2017/01/01 |description1 |



