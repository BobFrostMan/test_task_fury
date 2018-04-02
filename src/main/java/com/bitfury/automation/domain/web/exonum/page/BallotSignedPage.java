package com.bitfury.automation.domain.web.exonum.page;

import com.bitfury.automation.domain.web.exonum.AbstractExonumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

/**
 * Created by FOG on 02.04.2018.
 */
public class BallotSignedPage extends AbstractExonumPage {

    @FindBy(xpath = "//div[@class='toolbar-title']/span")
    private WebElement pageTitle;

    @FindBy(xpath = "//div[@class='input-wrapper-prefix']")
    private WebElement emailLabel;

    @FindBy(xpath = "//div[@class='input-wrapper-prefix']/following-sibling::input")
    private WebElement emailInput;

    @FindBy(xpath = "//div[contains(text(), 'SUBMIT BALLOT')]")
    private WebElement submitBallotButtonInput;

    @FindBy(xpath = "//div[contains(text(), 'DISCARD BALLOT')]")
    private WebElement discardBallotButtonInput;

    public BallotSignedPage(){
        super();
    }

    public BallotSignedPage(RemoteWebDriver driver){
        super(driver);
    }

    public WebElement getEmailInput() {
        return emailInput;
    }

    public WebElement getSubmitBallotButtonInput() {
        return submitBallotButtonInput;
    }

    public WebElement getDiscardBallotButtonInput() {
        return discardBallotButtonInput;
    }

    public WebElement getEmailLabel() {
        return emailLabel;
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public void clickOnSendEmail() {
        scrollToElement(submitBallotButtonInput);
        clickOn(submitBallotButtonInput);
    }
}
