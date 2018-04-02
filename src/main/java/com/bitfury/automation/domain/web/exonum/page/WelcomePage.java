package com.bitfury.automation.domain.web.exonum.page;

import com.bitfury.automation.domain.web.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

/**
 * Created by FOG on 01.04.2018.
 */
public class WelcomePage extends AbstractPage {

    @FindBy(xpath = "//div[@ng-click=\"electionWizardLink()\"]")
    private WebElement electionButton;

    @FindBy(xpath = "//div[@ui-sref=\"monitor\"]")
    private WebElement monitorButton;

    public WelcomePage(){
    }

    public WelcomePage(RemoteWebDriver driver){
        super(driver);
    }

    public WebElement getElectionButton() {
        return electionButton;
    }

    public WebElement getMonitorButton() {
        return monitorButton;
    }
}
