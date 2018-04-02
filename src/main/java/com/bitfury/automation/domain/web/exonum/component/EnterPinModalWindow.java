package com.bitfury.automation.domain.web.exonum.component;

import com.bitfury.automation.domain.web.exonum.AbstractExonumComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by FOG on 02.04.2018.
 *
 * Modal component that occurs after on BallotSignedPage
 */
@FindBy(xpath = "//div[@class='modal-body']")
public class EnterPinModalWindow extends AbstractExonumComponent {

    @FindBy(xpath = "//div[@class='keyboard-buttons']//*[@ng-click='keyboardButtonClick()']")
    private List<WebElement> keyBoardButtons;

    @FindBy(xpath = "//div[@class='decrypt-desc']/*")
    private WebElement decryptDescriptionElement;

    @FindBy(xpath = "//div[@ng-click='submitSign()']")
    private WebElement signBallotButton;

    @FindBy(xpath = "//div[@data-dismiss='modal']")
    private WebElement cancelButton;

    @FindBy(id = "signModal")
    private WebElement modalBaseElement;

    public EnterPinModalWindow(){
        super();
    }

    public EnterPinModalWindow(RemoteWebDriver driver){
        super(driver);
    }

    public void clickOnKeyboardButton(int number){
        for (WebElement element : keyBoardButtons){
            WebElement el = element.findElement(By.xpath("div[@class='keyboard-button-digit']"));
            if (el.getText().contains(String.valueOf(number))){
                element.click();
                return;
            }
        }
    }

    public void enterPin(int ... code){
        for (int number : code){
            clickOnKeyboardButton(number);
        }
    }

    public List<WebElement> getKeyBoardButtons() {
        return keyBoardButtons;
    }

    public WebElement getDecryptDescriptionElement() {
        return decryptDescriptionElement;
    }

    public WebElement getSignBallotButton() {
        return signBallotButton;
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }

    public WebElement getModalBaseElement(){
        return modalBaseElement;
    }

    public void signBallot(){
        clickOn(signBallotButton);
    }
}
