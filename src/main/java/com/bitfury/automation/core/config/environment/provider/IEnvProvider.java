package com.bitfury.automation.core.config.environment.provider;

import com.bitfury.automation.core.config.environment.bean.EnvConfig;

import java.util.Map;

/**
 * Created by FOG on 01.04.2018.
 * <p>
 * Interface for providing environment configurations, that will be used for tests run
 */
public interface IEnvProvider {

    /**
     * Loads config properties using default environment name
     * and other configurations(in common cases called 'local')
     *
     * @return environment configurations represented as EnvConfig object
     */
    public EnvConfig loadEnvironment();

    /**
     * Loads config properties for specified environment
     *
     * @param envName - environment which configurations want to be loaded
     * @return environment configurations represented as EnvConfig object
     */
    public EnvConfig loadEnvironment(String envName);


    /**
     * Loads config properties for specified environment
     *
     * @param envName - environment which configurations want to be loaded
     * @param path    - filepath or url to reach environment configurations by given name
     * @return environment configurations represented as EnvConfig object
     */
    public EnvConfig loadEnvironment(String envName, String path);

    /**
     * Return map Environment name = environment config.
     * It sometimes happens, that products have complicated environment which contains from different parts,
     * called differently.
     *
     * @return Return map Environment name = environment config represented as Map<String, EnvConfig>
     */
    public Map<String, EnvConfig> getEnvironmentConfigs();
}
