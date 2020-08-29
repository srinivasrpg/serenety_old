package com.wellsfergo.bdd.steps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.cucumber.datatable.DataTable;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.wellsfergo.bdd.service.Utils.commons.getWebDriverWait;
import static com.wellsfergo.bdd.service.glossary.BDDGlossary.getGlossaryTerm;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class VisitorSteps {

  @Managed private WebDriver webdriver;

  @Given("User navigates to the page (.*).*$")
  public void navigateToThePage(String pageName) {
    String navigationURL = getGlossaryTerm(pageName);
    webdriver.navigate().to(navigationURL);
  }

  @And("User clicks on (.*)")
  public void userClicksOnTheElement(String element) {
    String elementSelector = getGlossaryTerm(element);
    WebElement targetElement = webdriver.findElement(cssSelector(elementSelector));
    getWebDriverWait(webdriver, 30).until(visibilityOfElementLocated(cssSelector(elementSelector)));
    targetElement.click();
  }

  @And("^the User can see the (.*) is (displayed|ready|visible|clickable|invisible)$")
  public void userCanSeeTheElementDisplayed(String element, String condition) {
    String elementSelector = getGlossaryTerm(element);
    switch (condition) {
      case "clickable":
        getWebDriverWait(webdriver, 30).until(elementToBeClickable(cssSelector(elementSelector)));
        break;
      case "invisible":
        getWebDriverWait(webdriver, 30)
            .until(invisibilityOfElementLocated(cssSelector(elementSelector)));
        break;
      default:
        getWebDriverWait(webdriver, 30)
            .until(visibilityOfElementLocated(cssSelector(elementSelector)));
    }
  }

  @And("User enters the values into the fields of Registration Form:$")
  public void userClicksOnTheElement(DataTable dataTable) {
    dataTable
        .asMaps()
        .forEach(
            formItem -> {
              String elementSelector = getGlossaryTerm(formItem.get("FormFieldSelector"));
              WebElement fieldElement = webdriver.findElement(cssSelector(elementSelector));
              fieldElement.sendKeys(formItem.get("Value"));
            });
  }
}
