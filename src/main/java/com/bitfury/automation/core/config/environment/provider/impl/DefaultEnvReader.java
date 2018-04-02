package com.bitfury.automation.core.config.environment.provider.impl;

import com.bitfury.automation.core.config.IPropsReader;
import com.bitfury.automation.core.config.environment.bean.EnvConfig;
import com.bitfury.automation.core.config.environment.provider.IEnvProvider;
import com.bitfury.automation.core.exception.EnvironmentNotProvidedException;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by FOG on 01.04.2018.
 * <p>
 * Default implementation on environment configuration provider.
 * Based on reading property files from resources folder.
 * Program arguments will always override properties from resource files (to support run on CI)
 *
 * @see com.bitfury.automation.core.config.IPropsReader
 * @see com.bitfury.automation.core.config.environment.provider.IEnvProvider
 */
public class DefaultEnvReader implements IEnvProvider, IPropsReader {

    private static final String DEFAULT_ENV_NAME = "local";
    private static final String DEFAULT_ENV_CONFIG_PATH = "env/";
    private static final String PROPERTIES = ".properties";

    //map here in case we will have complicated env with few different parts in future
    private Map<String, EnvConfig> envConfigs;

    public DefaultEnvReader() {
        envConfigs = new HashMap<>();
    }

    @Override
    public EnvConfig loadEnvironment(String envName) {
        return loadEnvironment(envName, DEFAULT_ENV_CONFIG_PATH);
    }

    @Override
    public EnvConfig loadEnvironment(String envName, String path) {
        String filePath = path + envName + PROPERTIES;
        InputStream propsStream = getPropsStream(filePath);
        Properties properties = getProperties(propsStream);
        EnvConfig config = new EnvConfig(envName, properties);
        envConfigs.put(envName, config);
        return config;
    }

    @Override
    public EnvConfig loadEnvironment() {
        //in basic common case you have a simple single instance of environment
        String envName = System.getProperty("env", DEFAULT_ENV_NAME);
        String configPath = System.getProperty("config", DEFAULT_ENV_CONFIG_PATH);
        return loadEnvironment(envName, configPath);
    }

    @Override
    public Map<String, EnvConfig> getEnvironmentConfigs() {
        return envConfigs;
    }

}
