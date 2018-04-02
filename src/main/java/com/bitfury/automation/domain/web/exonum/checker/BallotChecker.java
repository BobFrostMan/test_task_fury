package com.bitfury.automation.domain.web.exonum.checker;

import com.bitfury.automation.core.entity.web.api.rest.guerilla.bean.Email;
import com.bitfury.automation.core.entity.web.api.rest.guerilla.bean.UserSession;
import com.bitfury.automation.core.entity.web.api.rest.guerilla.impl.GuerrillaClient;
import com.bitfury.automation.core.utils.logging.Logger;
import com.bitfury.automation.domain.web.exonum.component.EnterPinModalWindow;
import com.bitfury.automation.domain.web.exonum.page.BallotReceiptPage;
import com.bitfury.automation.domain.web.exonum.page.BallotSignedPage;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by FOG on 02.04.2018.
 * <p>
 * Checker commonly used to check functionality and information on Ballot-related pages
 */
public class BallotChecker extends AbstractExonumChecker {

    public void checkBallotSignedContent(BallotSignedPage page) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(page.getEmailLabel().getText().contains("E-mail"));
        checkButtonEnabled(softAssert, page.getSubmitBallotButtonInput(), page);
        checkButtonEnabled(softAssert, page.getDiscardBallotButtonInput(), page);
        softAssert.assertEquals(page.getPageTitle().getText(), "Ballot has been signed");
        softAssert.assertAll();
    }

    public void checkEnterPinModalContent(EnterPinModalWindow enterPinModalWindow) {
        SoftAssert softAssert = new SoftAssert();
        String desc = enterPinModalWindow.getDecryptDescriptionElement().getText();
        String text = "PIN2 should be unique";
        softAssert.assertTrue(desc.contains(text), "Window should contains '" + text + "'");
        softAssert.assertAll();
    }

    public void checkBallotPageContent(BallotReceiptPage page) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getDecryptButton().getText(), "DECRYPT");
        softAssert.assertEquals(page.getDiscardButton().getText(), "DISCARD");
        softAssert.assertEquals(page.getSignButton().getText(), "SIGN");

        checkIsDisplayed(softAssert, page.getContentHeader(), page);
        checkIsDisplayed(softAssert, page.getHashSHA256Title(), page);

        checkIsNotEmpty(softAssert, page.getSHA256HashValue());
        checkIsNotEmpty(softAssert, page.getMemoValue());

        checkButtonEnabled(softAssert, page.getSaveMemoWordsButton(), page);
        checkButtonEnabled(softAssert, page.getDecryptButton(), page);
        checkButtonEnabled(softAssert, page.getDiscardButton(), page);
        checkButtonEnabled(softAssert, page.getSignButton(), page);

        softAssert.assertAll();
    }

    public void checkBallotSuccessfullyPostedEmail(GuerrillaClient client, UserSession session, String hash, String memo){
        String expectedSubject = "Voter, your ballot has been successfully posted on public bulletin board";
        Email email = waitForEmailToBeReceived(client, session, expectedSubject);
        checkEmailContent(email, expectedSubject, hash, memo);
    }

    private Email getEmailBySubject(GuerrillaClient client, UserSession session, String subject){
        List<Email> emailList = client.checkEmail(session, 0);
        for(Email email : emailList) {
            if (email.getMailSubject().equals(subject)){
                return email;
            }
        }
        return null;
    }

    private Email waitForEmailToBeReceived(GuerrillaClient client, UserSession session, String subject){
        int attempts = 10;
        int intervalInSec = 30;
        for (int i = 0; i < attempts; i++){
            Email email = getEmailBySubject(client, session, subject);
            if (email != null){
                return email;
            }
            try {
                Logger.info("Email with subject '" + subject +"' wasn't received. Yet...");
                TimeUnit.SECONDS.sleep(intervalInSec);
            } catch (InterruptedException e) {
                Logger.error("InterruptedException occured. Message: " + e.getMessage(), e);
            }
        }
        return null;
    }

    private void checkEmailContent(Email email, String subject, String hash, String memo){
        Assert.assertNotNull(email, "Email with subject '"+subject+"' wasn't received!");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(email.getMailExcerpt().contains("HASH HEXADECIMAL: " + hash));
        softAssert.assertTrue(email.getMailExcerpt().contains("MNEMONIC CODE PLAINTEXT: " + memo));
        softAssert.assertAll();
    }

}
