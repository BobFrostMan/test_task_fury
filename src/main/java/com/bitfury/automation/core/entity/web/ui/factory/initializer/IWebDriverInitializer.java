package com.bitfury.automation.core.entity.web.ui.factory.initializer;

import com.bitfury.automation.core.config.settings.bean.Settings;
import com.bitfury.automation.core.exception.PreparationFailedException;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by FOG on 01.04.2018.
 * WebDriver initializer interface for pretty WebDriverFactory implementation
 */
public interface IWebDriverInitializer {

    /**
     * Returns fully prepared and configured RemoteWebDriver object
     * Preparations and configurations oriented on local webdriver creation
     *
     * @param settings - settings that will be used to configure driver
     * @return configured webdriver instance
     * @throws PreparationFailedException if something goes wrong
     */
    RemoteWebDriver initWebDriver(Settings settings) throws PreparationFailedException;


    /**
     * Returns fully prepared and configured RemoteWebDriver object, created for SeleniumGrid
     *
     * @param settings - settings that will be used to configure driver
     * @return configured webdriver instance
     * @throws PreparationFailedException when preparations failed
     */
    RemoteWebDriver initDriverWithGrid(Settings settings) throws PreparationFailedException;
}
