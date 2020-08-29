package com.wellsfergo.bdd.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import net.thucydides.core.annotations.Steps;

public class saidefsteps {

    @Steps
    com.wellsfergo.bdd.steps.serenity.GoogleSearchSteps demo;

    @Given("user navigates to google home page")
    public void userNavigatesToThePage()
    {
        demo.userNavigatestoThegooglePage();
    }

    @And("user inputs the search string '(.*)'")
    public void userInputsTheSearchString(String searchKey)
    {
        demo.entersTheSearchInput(searchKey);
    }

    @And("User clicks the search button")
    public void userClicksTheSearchButton()
    {
        demo.click_Search();
    }
}
