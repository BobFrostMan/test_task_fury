package com.bitfury.automation.core.config.settings.provider.impl;

import com.bitfury.automation.core.config.IPropsReader;
import com.bitfury.automation.core.config.settings.bean.Settings;
import com.bitfury.automation.core.config.settings.provider.ISettingsProvider;

import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * Created by FOG on 01.04.2018.
 * <p>
 * Default settings provider implementation based on reading properties from resource files
 *
 * @see com.bitfury.automation.core.config.settings.provider.ISettingsProvider
 * @see com.bitfury.automation.core.config.IPropsReader
 */
public class DefaultSettingsProvider implements ISettingsProvider, IPropsReader {

    private static final String DEFAULT_SETTINGS_PATH = "config/settings.properties";

    private String settingsPath;
    private Settings settings;

    public DefaultSettingsProvider() {
        settings = new Settings();
        settingsPath = DEFAULT_SETTINGS_PATH;
    }

    public DefaultSettingsProvider(String settingsPath) {
        settings = new Settings();
        this.settingsPath = settingsPath;
    }

    @Override
    public Settings getAll() {
        InputStream inputStream = getPropsStream(settingsPath);
        Properties properties = getProperties(inputStream);
        settings.setProperties(properties);
        return settings;
    }

    @Override
    public Settings get(String type) {
        return getByType(type);
    }

    /**
     * Returns Settings object that contains properties with specified prefix
     *
     * @param type - string prefix to get properties
     * @return - new Settings Object
     */
    public Settings getByType(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Parameter 'type' shouldn't be null or empty!");
        }
        Properties res = new Properties();
        Set<String> keys = settings.getProperties().stringPropertyNames();
        for (String key : keys) {
            if (key.startsWith(type)) {
                String value = System.getProperty(key, settings.getProperties().getProperty(key));
                res.put(key, value);
            }
        }
        return new Settings(res);
    }
}
