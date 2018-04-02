package com.bitfury.automation.core.entity.web.ui.factory.initializer;

import com.bitfury.automation.core.config.settings.bean.Settings;
import com.bitfury.automation.core.exception.NotSupportedYetException;
import com.bitfury.automation.core.exception.PreparationFailedException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by FOG on 01.04.2018.
 * <p>
 * Basic WebDriver initializer. Currently works for SeleniumGrid only!
 */
public abstract class AbstractWebDriverInitializer implements IWebDriverInitializer {

    protected static final String HUB_ENDPOINT_KEY = "driver.hub.endpoint";

    protected static final String TIMEOUT_ELEM_WAIT_KEY = "driver.timeout.elemwait";
    protected static final String TIMEOUT_PAGE_LOAD_KEY = "driver.timeout.pageload";
    protected static final String TIMEOUT_SCRIPT_LOAD_KEY = "driver.timeout.scriptload";

    public RemoteWebDriver initWebDriver(Settings settings) throws PreparationFailedException {
        throw new NotSupportedYetException("Driver initialization currently supported only with selenium grid!");
    }

    public RemoteWebDriver initDriverWithGrid(Settings settings) throws PreparationFailedException {
        checkIsPossible(settings);
        DesiredCapabilities dc = getCapabilities();
        RemoteWebDriver driver = createDriverForGrid(dc, settings.get(HUB_ENDPOINT_KEY));
        return configDriver(driver, settings);
    }

    protected RemoteWebDriver configDriver(RemoteWebDriver driver, Settings settings) {
        driver.manage().timeouts().implicitlyWait(settings.getInt(TIMEOUT_ELEM_WAIT_KEY), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(settings.getInt(TIMEOUT_PAGE_LOAD_KEY), TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(settings.getInt(TIMEOUT_SCRIPT_LOAD_KEY), TimeUnit.SECONDS);
        return driver;
    }

    private void checkIsPossible(Settings settings) {
        if (settings == null || settings.getProperties() == null || settings.getProperties().size() == 0) {
            throw new IllegalArgumentException("Cannot prepare driver with '" + this.getClass().getName() + "'. Preparation settings cannot be null or empty!");
        }
    }

    protected abstract DesiredCapabilities getCapabilities();

    protected RemoteWebDriver createDriverForGrid(DesiredCapabilities capabilities, String url) throws PreparationFailedException {
        try {
            return new RemoteWebDriver(new URL(url), capabilities);
        } catch (MalformedURLException e) {
            throw new PreparationFailedException("Failed to create web driver object using " + getClass().getSimpleName() + "! Message: " + e.getMessage(), e);
        }
    }

}
