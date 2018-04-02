package com.bitfury.automation.domain.web;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by FOG on 01.04.2018.
 *
 * Abstract page as a base for any type of interaction with pages via selenium.
 * It's also a specific holder and initializer for Webdriver
 */
public abstract class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        waitUntilPageLoaded();
        PageFactory.initElements(driver, this);
    }

    public AbstractPage() {
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected void waitUntilPageLoaded(){
        //doing nothing by default
    }

    protected void clickOn(WebElement el){
        try {
            el.click();
        } catch (WebDriverException e){
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", el);
        }
    }
}
