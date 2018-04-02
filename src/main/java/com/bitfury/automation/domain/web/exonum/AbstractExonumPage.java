package com.bitfury.automation.domain.web.exonum;

import com.bitfury.automation.domain.web.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by FOG on 01.04.2018.
 *
 * Abstract class to aggregate useful methods for work with Exonum-Base web applications
 */
public abstract class AbstractExonumPage extends AbstractPage {

    protected static String SCROLL_INTO_VIEW_SCRIPT = "arguments[0].scrollIntoView(true);";

    public AbstractExonumPage(){
        super();
    }

    public AbstractExonumPage(RemoteWebDriver driver){
        super(driver);
    }

    protected boolean isActiveChecker(WebElement checker){
        return checker != null && checker.getAttribute("class").contains("active");
    }

    protected boolean hasGivenText(WebElement el, String text){
        List<WebElement> row = el.findElements(By.xpath("*[contains(text(), '" + text + "')]"));
        return row != null && !row.isEmpty();
    }

    protected void checkIfUnchecked(WebElement election){
        WebElement checker = election.findElement(By.xpath("td/div"));
        if (!isActiveChecker(checker)){
            checker.click();
        }
    }

    protected void waitUntilElementVisible(WebElement el) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(el));
    }

    public void scrollToElement(WebElement element){
        ((JavascriptExecutor) driver).executeScript(SCROLL_INTO_VIEW_SCRIPT, element);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Override
    protected void waitUntilPageLoaded(){
        //do nothing by default
    }
}
