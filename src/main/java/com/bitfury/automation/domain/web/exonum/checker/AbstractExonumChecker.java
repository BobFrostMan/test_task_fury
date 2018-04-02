package com.bitfury.automation.domain.web.exonum.checker;

import com.bitfury.automation.domain.web.exonum.AbstractExonumPage;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

/**
 * Created by FOG on 01.04.2018.
 *
 * Abstract Checker class that encapsulates sets of hard and soft asserts for another more specific checkers.
 * Checkers can be used to encapsulate complex checks on the test layer
 */
public abstract class AbstractExonumChecker {

    protected void checkButtonEnabled(SoftAssert softAssert, WebElement element, AbstractExonumPage page) {
        page.scrollToElement(element);
        softAssert.assertTrue(element.isEnabled() && element.isDisplayed());
    }

    protected void checkIsNotEmpty(SoftAssert softAssert, String text) {
        softAssert.assertTrue(text != null && !text.isEmpty(), "Text is null or empty!");
    }

    protected void checkIsDisplayed(SoftAssert softAssert, WebElement element, AbstractExonumPage page) {
        page.scrollToElement(element);
        softAssert.assertTrue(element.isDisplayed());
    }
}
