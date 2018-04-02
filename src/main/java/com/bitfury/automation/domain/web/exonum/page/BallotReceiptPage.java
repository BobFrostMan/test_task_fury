package com.bitfury.automation.domain.web.exonum.page;

import com.bitfury.automation.domain.web.exonum.AbstractExonumPage;
import com.bitfury.automation.domain.web.exonum.component.EnterPinModalWindow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

/**
 * Created by FOG on 01.04.2018.
 */
public class BallotReceiptPage extends AbstractExonumPage {

    @FindBy(xpath = "//div/*[contains(text(), 'Ballot Receipt')]")
    private WebElement contentHeader;

    @FindBy(xpath = "//div[contains(text(), 'Ballot reciept 3-word memo')]")
    private WebElement wordMemoTitle;

    @FindBy(xpath = "//*[contains(text(), 'Ballot  SHA256 hash')]")
    private WebElement hashSHA256Title;

    @FindBy(xpath = "//ballot-actions//div[@class='buttons-wrapper']//div[@class='button']")
    private WebElement saveMemoWordsButton;

    @FindBy(xpath = "//ballot-actions//div[@ng-click='electionWizardReset()']")
    private WebElement discardButton;

    @FindBy(xpath = "//ballot-actions//div[@ng-click='decryptModal()']")
    private WebElement decryptButton;

    @FindBy(xpath = "//ballot-actions//div[@ng-click='signModal()']")
    private WebElement signButton;

    private EnterPinModalWindow enterPinModalWindow;

    public BallotReceiptPage() {
        super();
    }

    public BallotReceiptPage(RemoteWebDriver driver) {
        super(driver);
        enterPinModalWindow = new EnterPinModalWindow(driver);
    }

    public String getSHA256HashValue() {
        return getValueForTitle(hashSHA256Title);
    }

    public String getMemoValue() {
        return getValueForTitle(wordMemoTitle);
    }

    private String getValueForTitle(WebElement titleElement) {
        WebElement valueHolderElem = titleElement.findElement(By.xpath("following-sibling::*"));
        return valueHolderElem.getText();
    }

    public void clickOnSignIn() {
        scrollToElement(signButton);
        clickOn(signButton);
        waitUntilElementVisible(enterPinModalWindow.getModalBaseElement());
    }

    public WebElement getContentHeader() {
        return contentHeader;
    }

    public WebElement getWordMemoTitle() {
        return wordMemoTitle;
    }

    public WebElement getHashSHA256Title() {
        return hashSHA256Title;
    }

    public WebElement getSaveMemoWordsButton() {
        return saveMemoWordsButton;
    }

    public WebElement getDiscardButton() {
        return discardButton;
    }

    public WebElement getDecryptButton() {
        return decryptButton;
    }

    public WebElement getSignButton() {
        return signButton;
    }
}
