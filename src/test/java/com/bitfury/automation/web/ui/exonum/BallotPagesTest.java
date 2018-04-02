package com.bitfury.automation.web.ui.exonum;

import com.bitfury.automation.core.entity.web.api.rest.guerilla.bean.Email;
import com.bitfury.automation.core.entity.web.api.rest.guerilla.bean.Session;
import com.bitfury.automation.core.entity.web.api.rest.guerilla.bean.UserSession;
import com.bitfury.automation.core.entity.web.api.rest.guerilla.impl.GuerrillaClient;
import com.bitfury.automation.domain.web.exonum.checker.BallotChecker;
import com.bitfury.automation.domain.web.exonum.component.CandidatesModalWindow;
import com.bitfury.automation.domain.web.exonum.component.EnterPinModalWindow;
import com.bitfury.automation.domain.web.exonum.page.*;
import com.bitfury.automation.web.ui.exonum.base.ExonumTestBase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

/**
 * Created by FOG on 02.04.2018.
 */
public class BallotPagesTest extends ExonumTestBase {

    private BallotChecker checker;
    private GuerrillaClient guerrillaClient;
    private UserSession userSession;

    public BallotPagesTest(){
        super();
        checker = new BallotChecker();
        guerrillaClient = new GuerrillaClient();
        userSession = guerrillaClient.createTempMailbox("some_user"+ new Random(10).nextInt());
    }

    @Test
    public void checkBallotReceiptPage() {
        String candidateName = "Siim Kallas";
        String elections = "Estonian Presidential Election";

        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.getElectionButton().click();

        ElectionsPage electionsPage = new ElectionsPage(driver);
        electionsPage.selectElections(elections);
        electionsPage.getVoteInElectionButton().click();

        CandidatesPage candidatesPage = new CandidatesPage(driver);
        candidatesPage.selectCandidate(candidateName);
        candidatesPage.vote();

        CandidatesModalWindow candidatesModalWindow = candidatesPage.getModalWindow();
        candidatesModalWindow.clickYesButton();

        BallotReceiptPage ballotReceiptPage = new BallotReceiptPage(driver);
        checker.checkBallotPageContent(ballotReceiptPage);
    }

    @Test
    public void checkEnterPinModal() {
        String candidateName = "Eiki Nestor";
        String elections = "Estonian Presidential Election";

        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.getElectionButton().click();

        ElectionsPage electionsPage = new ElectionsPage(driver);
        electionsPage.selectElections(elections);
        electionsPage.getVoteInElectionButton().click();

        CandidatesPage candidatesPage = new CandidatesPage(driver);
        candidatesPage.selectCandidate(candidateName);
        candidatesPage.vote();

        CandidatesModalWindow candidatesModalWindow = candidatesPage.getModalWindow();
        candidatesModalWindow.clickYesButton();

        BallotReceiptPage ballotReceiptPage = new BallotReceiptPage(driver);
        ballotReceiptPage.clickOnSignIn();

        EnterPinModalWindow enterPinModalWindow = new EnterPinModalWindow(driver);
        checker.checkEnterPinModalContent(enterPinModalWindow);
    }

    @Test
    public void checkSignBallotContent() {
        String candidateName = "Mailis Reps";
        String elections = "Estonian Presidential Election";

        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.getElectionButton().click();

        ElectionsPage electionsPage = new ElectionsPage(driver);
        electionsPage.selectElections(elections);
        electionsPage.getVoteInElectionButton().click();

        CandidatesPage candidatesPage = new CandidatesPage(driver);
        candidatesPage.selectCandidate(candidateName);
        candidatesPage.vote();

        CandidatesModalWindow candidatesModalWindow = candidatesPage.getModalWindow();
        candidatesModalWindow.clickYesButton();

        BallotReceiptPage ballotReceiptPage = new BallotReceiptPage(driver);
        ballotReceiptPage.clickOnSignIn();

        EnterPinModalWindow enterPinModalWindow = new EnterPinModalWindow(driver);
        enterPinModalWindow.enterPin(1, 2, 3, 4);
        enterPinModalWindow.signBallot();

        BallotSignedPage ballotSignedPage = new BallotSignedPage(driver);
        checker.checkBallotSignedContent(ballotSignedPage);
    }

    @Test
    public void checkSignBallotEmail() {
        String candidateName = "Allar Jõks";
        String elections = "Estonian Presidential Election";
        String email = userSession.getEmailAddr();

        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.getElectionButton().click();

        ElectionsPage electionsPage = new ElectionsPage(driver);
        electionsPage.selectElections(elections);
        electionsPage.getVoteInElectionButton().click();

        CandidatesPage candidatesPage = new CandidatesPage(driver);
        candidatesPage.selectCandidate(candidateName);
        candidatesPage.vote();

        CandidatesModalWindow candidatesModalWindow = candidatesPage.getModalWindow();
        candidatesModalWindow.clickYesButton();

        BallotReceiptPage ballotReceiptPage = new BallotReceiptPage(driver);
        String hash = ballotReceiptPage.getSHA256HashValue();
        String memo = ballotReceiptPage.getMemoValue();
        ballotReceiptPage.clickOnSignIn();

        EnterPinModalWindow enterPinModalWindow = new EnterPinModalWindow(driver);
        enterPinModalWindow.enterPin(2, 3, 4, 5);
        enterPinModalWindow.signBallot();

        BallotSignedPage ballotSignedPage = new BallotSignedPage(driver);
        ballotSignedPage.getEmailInput().sendKeys(email);
        ballotSignedPage.clickOnSendEmail();
        checker.checkBallotSuccessfullyPostedEmail(guerrillaClient, userSession, hash, memo);
    }

    @AfterClass
    public void cleanUp(){
        super.cleanUp();
        guerrillaClient.forgetMe(userSession);
    }

}
