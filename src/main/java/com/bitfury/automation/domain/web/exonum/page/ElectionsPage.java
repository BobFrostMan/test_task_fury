package com.bitfury.automation.domain.web.exonum.page;

import com.bitfury.automation.domain.web.exonum.AbstractExonumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by FOG on 01.04.2018.
 */
public class ElectionsPage extends AbstractExonumPage {

    @FindBy(xpath = "//table[@class='app-list']/tbody/tr")
    private List<WebElement> electionsList;

    @FindBy(xpath = "//div[@href='#/elections/candidates']")
    private WebElement voteInElectionButton;

    public ElectionsPage(){}

    public ElectionsPage(RemoteWebDriver driver){
        super(driver);
    }

    public void selectElections(String electionTitle){
        for (WebElement election : electionsList){
            if (hasGivenText(election, electionTitle)){
                checkIfUnchecked(election);
                return;
            }
        }
    }

    public List<WebElement> getElectionsList() {
        return electionsList;
    }

    public WebElement getVoteInElectionButton() {
        return voteInElectionButton;
    }
}
