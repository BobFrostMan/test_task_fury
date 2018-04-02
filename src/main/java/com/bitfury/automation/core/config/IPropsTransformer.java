package com.bitfury.automation.core.config;

/**
 * Created by FOG on 01.04.2018.
 *
 * Interface with default implementation for types convertions.
 * Quite useful when you are going to use property files
 */
public interface IPropsTransformer {

    /**
     * Basic method that should somehow return String representation of value for given key
     *
     * @param key - property key
     * @return - property value for given key
     */
    public String getValue(String key);

    public default String get(String name){
        return getValue(name);
    }

    public default int getInt(String name){
        try {
            return Integer.parseInt(getValue(name));
        } catch (NumberFormatException e){
            return -1;
        }
    }

    public default float getFloat(String name){
        try {
            return Float.valueOf(getValue(name));
        } catch (NumberFormatException e){
            return -1;
        }
    }

    public default double getDouble(String name){
        try {
            return Double.valueOf(getValue(name));
        } catch (NumberFormatException e){
            return -1;
        }
    }

    public default boolean getBool(String name){
        return Boolean.valueOf(getValue(name));
    }

}
