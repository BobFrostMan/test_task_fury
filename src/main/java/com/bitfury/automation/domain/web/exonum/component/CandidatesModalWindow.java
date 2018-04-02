package com.bitfury.automation.domain.web.exonum.component;

import com.bitfury.automation.domain.web.exonum.AbstractExonumComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

/**
 * Created by FOG on 01.04.2018.
 * Modal window that occurs on Candidates page by click on 'Vote in election'
 */
@FindBy(xpath = "//div[@class='modal-body']")
public class CandidatesModalWindow extends AbstractExonumComponent {

    @FindBy(xpath = "//div[@class='button' and @data-dismiss='modal']")
    private WebElement cancelButton;

    @FindBy(xpath = "//div[@ng-click='submitCandidate()']")
    private WebElement yesButton;

    @FindBy(css = "div.confirm-choise-block-subtitle")
    private WebElement confirmationSubtitle;

    @FindBy(css = "div.confirm-choise-block-name")
    private WebElement candidateName;

    public CandidatesModalWindow(){
        super();
    }

    public CandidatesModalWindow(RemoteWebDriver driver){
        super(driver);
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }

    public WebElement getYesButton() {
        return yesButton;
    }

    public void clickYesButton(){
        yesButton.click();
        driver.switchTo().defaultContent();
    }

    public WebElement getConfirmationSubtitle() {
        return confirmationSubtitle;
    }

    public WebElement getCandidateName() {
        return candidateName;
    }
}
