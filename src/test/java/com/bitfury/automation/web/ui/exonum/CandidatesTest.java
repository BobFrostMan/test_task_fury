package com.bitfury.automation.web.ui.exonum;

import com.bitfury.automation.domain.web.exonum.checker.CandidatesChecker;
import com.bitfury.automation.domain.web.exonum.component.CandidatesModalWindow;
import com.bitfury.automation.domain.web.exonum.page.CandidatesPage;
import com.bitfury.automation.domain.web.exonum.page.ElectionsPage;
import com.bitfury.automation.domain.web.exonum.page.WelcomePage;
import com.bitfury.automation.web.ui.exonum.base.ExonumTestBase;
import com.bitfury.automation.web.ui.exonum.dataprovider.CandidatesProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by FOG on 01.04.2018.
 */
public class CandidatesTest extends ExonumTestBase {

    private CandidatesChecker checker;

    public CandidatesTest(){
        super();
        checker = new CandidatesChecker();
    }

    @Test
    public void checkEstonianCandidatesCount() {
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.getElectionButton().click();

        ElectionsPage electionsPage = new ElectionsPage(driver);
        electionsPage.selectElections("Estonian Presidential Election");
        electionsPage.getVoteInElectionButton().click();

        CandidatesPage candidatesPage = new CandidatesPage(driver);

        int expected = 4;
        int actual = candidatesPage.getCandidatesList().size();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProviderClass = CandidatesProvider.class, dataProvider = "get")
    public void checkCandidatesInformation(String election, String candidateName) {
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.getElectionButton().click();

        ElectionsPage electionsPage = new ElectionsPage(driver);
        electionsPage.selectElections(election);
        electionsPage.getVoteInElectionButton().click();

        CandidatesPage candidatesPage = new CandidatesPage(driver);
        candidatesPage.selectCandidate(candidateName);
        checker.checkCandidate(candidatesPage, candidateName);
    }

    @Test(dataProviderClass = CandidatesProvider.class, dataProvider = "get")
    public void checkCandidatePreVoteInformation(String election, String candidateName) {
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.getElectionButton().click();

        ElectionsPage electionsPage = new ElectionsPage(driver);
        electionsPage.selectElections(election);
        electionsPage.getVoteInElectionButton().click();

        CandidatesPage candidatesPage = new CandidatesPage(driver);
        candidatesPage.selectCandidate(candidateName);
        candidatesPage.vote();

        CandidatesModalWindow modal = candidatesPage.getModalWindow();
        checker.checkModalWindowData(modal, candidateName);
    }

}
