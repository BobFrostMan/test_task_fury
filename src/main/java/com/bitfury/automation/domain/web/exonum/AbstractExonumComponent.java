package com.bitfury.automation.domain.web.exonum;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by FOG on 01.04.2018.
 *
 * Abstract component that can encapsulate some specific functions
 * for Exonum-based applications UI components
 */
public class AbstractExonumComponent {

    protected WebDriver driver;

    public AbstractExonumComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AbstractExonumComponent() {
    }

    public WebDriver getDriver() {
        return driver;
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
