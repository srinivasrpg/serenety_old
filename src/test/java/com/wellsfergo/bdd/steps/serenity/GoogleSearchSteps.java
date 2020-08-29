package com.wellsfergo.bdd.steps.serenity;

import com.wellsfergo.bdd.pages.GoogleHomePage;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class GoogleSearchSteps {

    GoogleHomePage googleHomePage;

    @Step
    public void entersTheSearchInput(String keyword) {

        googleHomePage.userInputsthesearchString(keyword);
    }

    @Step
    public void click_Search() {
        googleHomePage.userClicksTheSearch();
    }

    @Step
    public void userNavigatestoThegooglePage() {
        googleHomePage.open();
    }

   }