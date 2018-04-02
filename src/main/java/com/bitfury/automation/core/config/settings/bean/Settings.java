package com.bitfury.automation.core.config.settings.bean;

import com.bitfury.automation.core.config.IPropsTransformer;

import java.util.Properties;

/**
 * Created by FOG on 02.04.2018.
 * <p>
 * Settings bean that represents general framework run settings and modes
 */
public class Settings implements IPropsTransformer {

    private Properties properties;

    public Settings() {
        properties = new Properties();
    }

    public Settings(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String getValue(String key) {
        return System.getProperty(key, properties.getProperty(key));
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
