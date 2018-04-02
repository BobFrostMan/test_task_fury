package com.bitfury.automation.core.config.settings.provider;

import com.bitfury.automation.core.config.settings.bean.Settings;

/**
 * Created by FOG on 01.04.2018.
 * <p>
 * Interface for providing settings to run test automation framework.
 */
public interface ISettingsProvider {

    /**
     * Provides Settings object which contains all settings properties
     *
     * @return Settings object
     */
    Settings getAll();

    /**
     * Provides Settings object with properties of specified type
     *
     * @param type - type of settings
     * @return Settings object
     */
    Settings get(String type);

}
