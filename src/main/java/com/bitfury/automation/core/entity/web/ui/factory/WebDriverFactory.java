package com.bitfury.automation.core.entity.web.ui.factory;

import com.bitfury.automation.core.config.settings.bean.Settings;
import com.bitfury.automation.core.entity.web.ui.factory.initializer.IWebDriverInitializer;
import com.bitfury.automation.core.entity.web.ui.factory.initializer.impl.ChromeInitializer;
import com.bitfury.automation.core.exception.NotSupportedYetException;
import com.bitfury.automation.core.exception.PreparationFailedException;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by FOG on 01.04.2018.
 *
 * Factory dedicated to create WebDriver objects of different types.
 * Mode can be used to create drivers in specific way, as it is required for test automation framework run
 */
public class WebDriverFactory {

    private static final String DEFAULT_INIT_MODE = "grid";

    private String mode;
    private Map<String, IWebDriverInitializer> initMap;

    public WebDriverFactory() {
        initMap = new HashMap<>();
        mode = DEFAULT_INIT_MODE;
        initMap.put(BrowserType.CHROME, new ChromeInitializer());
    }

    public WebDriverFactory(String mode) {
        initMap = new HashMap<>();
        this.mode = mode;
        initMap.put(BrowserType.CHROME, new ChromeInitializer());
    }

    public RemoteWebDriver createDriver(String browserName, Settings settings) throws PreparationFailedException {
        if (browserName == null || browserName.isEmpty()) {
            throw new IllegalArgumentException("Parameter 'name' can't be null or empty!");
        }
        IWebDriverInitializer initializer = initMap.get(browserName.toLowerCase());
        if (initializer == null) {
            throw new NotSupportedYetException("WebDriver '" + browserName + "' creation is not supported by framework yet!");
        }

        switch (mode) {
            case DEFAULT_INIT_MODE:
                return initializer.initDriverWithGrid(settings);
            default:
                throw new NotSupportedYetException("Creation mode '" + mode + "' is not supported yet!");
        }
    }
}
