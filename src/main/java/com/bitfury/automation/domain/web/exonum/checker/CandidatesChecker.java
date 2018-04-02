package com.bitfury.automation.domain.web.exonum.checker;

import com.bitfury.automation.core.entity.web.api.rest.wiki.impl.WikiApiClient;
import com.bitfury.automation.domain.web.exonum.component.CandidatesModalWindow;
import com.bitfury.automation.domain.web.exonum.page.CandidatesPage;
import org.testng.asserts.SoftAssert;

/**
 * Created by FOG on 02.04.2018.
 * <p>
 * Checker commonly used to check Candidates related pages and components
 */
public class CandidatesChecker extends AbstractExonumChecker {

    public void checkModalWindowData(CandidatesModalWindow modal, String candidateName) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(modal.getCancelButton().isEnabled());
        softAssert.assertTrue(modal.getYesButton().isEnabled());

        softAssert.assertEquals(modal.getCancelButton().getText(), "CANCEL");
        softAssert.assertEquals(modal.getYesButton().getText(), "YES");

        softAssert.assertEquals(modal.getCandidateName().getText(), candidateName);
        softAssert.assertEquals(modal.getConfirmationSubtitle().getText(), "Your choice is:");
        softAssert.assertAll();
    }

    public void checkCandidate(CandidatesPage candidatesPage, String candidateName) {
        String expected = new WikiApiClient().getShortIntroContent(candidateName);
        String actual = candidatesPage.getCandidateDescription();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual, expected);
        softAssert.assertTrue(isOfficialLinkCorrect(candidatesPage, candidateName));
        softAssert.assertAll();
    }

    private boolean isOfficialLinkCorrect(CandidatesPage page, String candidateName) {
        String candidatesLink = page.getOfficialPageLink();
        String[] candidateNameParts = candidateName.trim().split(" ");

        boolean validLink;
        if (candidateNameParts.length == 2) {
            validLink = candidatesLink.contains(candidateNameParts[0]) && candidatesLink.contains(candidateNameParts[1]);
        } else {
            validLink = candidatesLink.contains(candidateName);
        }
        return validLink && candidatesLink.contains("wikipedia");
    }

}
