package com.wellsfergo.bdd.service.Utils;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class commons extends PageObject {
    public static WebDriver getCurrentWebDriver()
    {
        return Serenity.getWebdriverManager().getCurrentDriver();
    }

    public static void scrollToAnElement(By selector,WebDriver webDriver)
    {
        try{
            WebElement element=webDriver.findElement(selector);
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);",element);
        }catch(NoSuchElementException e)
        {
            e.printStackTrace();
        }
    }

    public static WebDriverWait getWebDriverWait(WebDriver webDriver,int waitInSeconds)
    {
        return new WebDriverWait(webDriver,waitInSeconds);
    }

}
