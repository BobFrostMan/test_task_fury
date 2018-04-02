package com.bitfury.automation.domain.web.exonum.page;

import com.bitfury.automation.domain.web.exonum.AbstractExonumPage;
import com.bitfury.automation.domain.web.exonum.component.CandidatesModalWindow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by FOG on 01.04.2018.
 */
public class CandidatesPage extends AbstractExonumPage {

    @FindBy(xpath = "//table[@class='app-list']/tbody/tr")
    private List<WebElement> candidatesList;

    @FindBy(xpath = "//div[@class='buttons-wrapper']/div[@ng-click='chooseCandidate()']")
    private WebElement voteInElectionButton;

    @FindBy(css = "div.list-option-description.ng-binding")
    private WebElement description;

    @FindBy(css = "a.list-option-link")
    private WebElement officialPageLink;

    public CandidatesModalWindow getModalWindow() {
        return candidatesModalWindow;
    }

    private CandidatesModalWindow candidatesModalWindow;

    public CandidatesPage() {
    }

    public CandidatesPage(RemoteWebDriver driver) {
        super(driver);
        candidatesModalWindow = new CandidatesModalWindow(driver);
    }

    public void selectCandidate(String candidateName){
        for (WebElement election : candidatesList){
            if (hasGivenText(election, candidateName)){
                checkIfUnchecked(election);
                return;
            }
        }
    }

    public String getCandidateDescription() {
        return description.getText();
    }

    public WebElement getVoteInElectionButton() {
        return voteInElectionButton;
    }

    public List<WebElement> getCandidatesList() {
        return candidatesList;
    }

    public String getOfficialPageLink() {
        return officialPageLink.getAttribute("href");
    }

    protected void waitUntilPageLoaded() {
        By pageTitleLocator = By.xpath("//div[@class='toolbar-title']/span");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        pageTitleLocator, "Candidates of Election"
                )
        );
    }

    public void vote(){
        scrollToElement(voteInElectionButton);
        voteInElectionButton.click();
        waitUntilElementVisible(candidatesModalWindow.getCandidateName());
    }
}
