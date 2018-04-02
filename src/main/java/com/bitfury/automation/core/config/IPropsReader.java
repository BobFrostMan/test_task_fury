package com.bitfury.automation.core.config;

import com.bitfury.automation.core.exception.EnvironmentNotProvidedException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by FOG on 01.04.2018.
 * <p>
 * PropertyReader interface. default implementation works with resource files
 */
public interface IPropsReader {

    /**
     * Provides input stream to read properties.
     *
     * @param filePath - filepath or uri to read content as stream.
     *                 Default implementation reads properties from resource files, so the path should be relative
     *                 and starts from resources folder
     * @return input stream object
     * @throws EnvironmentNotProvidedException in case if stream cannot be opened
     */
    default InputStream getPropsStream(String filePath) {
        InputStream propsStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (propsStream == null) {
            throw new EnvironmentNotProvidedException("Cannot access property file by path '" + filePath + "'");
        }
        return propsStream;
    }

    /**
     * Reads Properties object from given input stream
     *
     * @param propsStream - stream to read properties
     * @return set of properties represented as Properties object
     * @throws EnvironmentNotProvidedException in case if stream cannot be accessed or managed properly
     */
    default Properties getProperties(InputStream propsStream) {
        Properties prop = new Properties();
        try {
            prop.load(propsStream);
            return prop;
        } catch (IOException e) {
            throw new EnvironmentNotProvidedException("Cannot access properties stream. Message: " + e.getMessage(), e);
        } finally {
            if (propsStream != null) {
                try {
                    propsStream.close();
                } catch (IOException e) {
                    throw new EnvironmentNotProvidedException("Failed to close stream while load property process. Message: " + e.getMessage(), e);
                }
            }
        }
    }
}
