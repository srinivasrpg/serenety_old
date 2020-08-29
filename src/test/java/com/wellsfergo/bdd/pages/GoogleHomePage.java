package com.wellsfergo.bdd.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.pages.WebElementFacade;
import java.util.stream.Collectors;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;

import java.util.List;

@DefaultUrl("http://google.com")
public class GoogleHomePage extends PageObject {

    @FindBy(css=".gLFyf.gsfi[name='q']")
    private WebElementFacade searchinputBox;

    @FindBy(xpath="(//input[@type='submit'])[3]")
    private WebElementFacade searchButton;

   public void userInputsthesearchString(String searchInput)
   {
       searchinputBox.sendKeys(searchInput);
   }

   public void userClicksTheSearch()
   {
       searchButton.submit();
   }
}