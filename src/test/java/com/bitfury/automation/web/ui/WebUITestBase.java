package com.bitfury.automation.web.ui;

import com.bitfury.automation.TestBase;
import com.bitfury.automation.core.entity.web.ui.factory.WebDriverFactory;
import com.bitfury.automation.core.exception.PreparationFailedException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by FOG on 01.04.2018.
 *
 * Test base for tests based on web ui test automation framework (selenium webdriver in this case)
 */
public class WebUITestBase extends TestBase {

    private static final String DRIVER_SETTINGS_KEY = "driver";

    protected RemoteWebDriver driver;
    protected WebDriverFactory factory;

    public WebUITestBase(){
        super();
        envConfig = envProvider.loadEnvironment();
        settings = settingsProvider.getAll();
        factory = new WebDriverFactory();
    }

    @BeforeSuite
    public void setUp() throws PreparationFailedException {
        super.setUp();
    }

    protected void initDriver(String browser) throws PreparationFailedException {
        if (browser == null || browser.isEmpty()){
            browser = settings.get("browser");
        }
        driver = factory.createDriver(browser, settingsProvider.get(DRIVER_SETTINGS_KEY));
    }

    @AfterSuite
    public void cleanUp(){
        if (driver != null){
            driver.quit();
        }
    }

}
