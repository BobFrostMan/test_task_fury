package com.bitfury.automation.core.entity.web.ui.factory.initializer.impl;

import com.bitfury.automation.core.entity.web.ui.factory.initializer.AbstractWebDriverInitializer;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by FOG on 01.04.2018.
 * <p>
 * Class-with preparaion functions for ChromeDriver initialization and creation
 */
public class ChromeInitializer extends AbstractWebDriverInitializer {

    @Override
    protected DesiredCapabilities getCapabilities() {
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        dc.setBrowserName(BrowserType.CHROME);
        return dc;
    }

}
