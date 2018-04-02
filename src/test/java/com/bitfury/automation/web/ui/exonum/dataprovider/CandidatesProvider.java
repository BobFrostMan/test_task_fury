package com.bitfury.automation.web.ui.exonum.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * Created by FOG on 02.04.2018.
 *
 * Simple implementation of non-external resource based data provider
 */
public class CandidatesProvider {

    @DataProvider
    public static Object[][] get() {
        return new Object[][]{
                {"Estonian Presidential Election", "Allar Jõks"},
                {"Estonian Presidential Election", "Siim Kallas"},
                {"Estonian Presidential Election", "Eiki Nestor"},
                {"Estonian Presidential Election", "Mailis Reps"}
        };
    }
}
