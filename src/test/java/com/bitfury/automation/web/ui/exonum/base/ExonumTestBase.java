package com.bitfury.automation.web.ui.exonum.base;

import com.bitfury.automation.core.exception.PreparationFailedException;
import com.bitfury.automation.web.ui.WebUITestBase;
import org.testng.annotations.*;

/**
 * Created by FOG on 01.04.2018.
 *
 * Specific test base that oriented to use Settings to configure and prepare tests to work with
 * Exonum based UI applications
 */
public class ExonumTestBase extends WebUITestBase {

    protected String voteUrl;

    @BeforeClass
    @Parameters("browser")
    protected void prepare(@Optional String browser) throws PreparationFailedException {
        voteUrl = envConfig.getValue("exonum.base.url") + envConfig.getValue("exonum.voting.url");
        initDriver(browser);
    }

    @BeforeMethod
    protected void beforeTestMethod(){
        driver.get(voteUrl);
    }

    @AfterMethod
    protected void afterTesMethod(){
        driver.get(voteUrl);
    }
}
