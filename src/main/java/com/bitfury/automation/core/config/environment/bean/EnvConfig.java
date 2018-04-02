package com.bitfury.automation.core.config.environment.bean;

import com.bitfury.automation.core.config.IPropsTransformer;

import java.util.Map;
import java.util.Properties;

/**
 * Created by FOG on 01.04.2018.
 * <p>
 * Entity that represents environment configurations holder.
 */
public class EnvConfig implements IPropsTransformer {

    private String environmentName;
    private Properties configProperties;

    public EnvConfig(String envName) {
        environmentName = envName;
        configProperties = new Properties();
    }

    public EnvConfig(String envName, Properties defaultProps) {
        environmentName = envName;
        configProperties = defaultProps;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public EnvConfig addProperties(Properties properties) {
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            configProperties.put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public EnvConfig addProperty(String key, String value) {
        configProperties.put(key, value);
        return this;
    }

    public EnvConfig addProperty(String key, Object value) {
        configProperties.put(key, value);
        return this;
    }

    public void setProperties(Properties properties) {
        configProperties = properties;
    }

    public String getValue(String key) {
        String argValue = System.getProperty(key);
        if (argValue != null && !argValue.isEmpty()) {
            return argValue;
        }
        return configProperties.getProperty(key.toLowerCase());
    }

    public String getName() {
        return environmentName;
    }

    public Properties getProperties() {
        return configProperties;
    }

    @Override
    public String toString() {
        return "EnvConfig{" +
                "environmentName='" + environmentName + '\'' +
                ", configProperties=" + configProperties +
                '}';
    }
}
