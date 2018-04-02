package com.bitfury.automation;

import com.bitfury.automation.core.config.environment.bean.EnvConfig;
import com.bitfury.automation.core.config.environment.provider.IEnvProvider;
import com.bitfury.automation.core.config.environment.provider.impl.DefaultEnvReader;
import com.bitfury.automation.core.config.settings.bean.Settings;
import com.bitfury.automation.core.config.settings.provider.ISettingsProvider;
import com.bitfury.automation.core.config.settings.provider.impl.DefaultSettingsProvider;
import com.bitfury.automation.core.exception.PreparationFailedException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by FOG on 12.02.2018.
 * <p>
 * Simple test base can be used for the most basic preparations
 * (configuration providing, checks environment is up and running, ect.)
 */
public class TestBase {

    protected IEnvProvider envProvider;
    protected ISettingsProvider settingsProvider;
    protected EnvConfig envConfig;
    protected Settings settings;

    public TestBase() {
        envProvider = new DefaultEnvReader();
        settingsProvider = new DefaultSettingsProvider();
    }

    @BeforeSuite
    public void setUp() throws PreparationFailedException {

    }

    @AfterSuite
    public void cleanUp() {

    }

}
