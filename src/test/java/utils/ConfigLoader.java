package utils;

import java.util.Properties;

public class ConfigLoader {
    private  final Properties properties;
    private  static ConfigLoader configLoader;

    private ConfigLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }
    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }
    public String getBaseUrl(){
        String prop = properties.getProperty("baseUrl");
        if(prop != null) return prop;
        else throw new RuntimeException("property baseUrl is not found in config.properties file");
    }
    public String getUserNAme(){
        String prop = properties.getProperty("userName");
        if(prop != null) return prop;
        else throw new RuntimeException("property userName is not found in config.properties file");
    }
    public String getPassword(){
        String prop = properties.getProperty("password");
        if(prop != null) return prop;
        else throw new RuntimeException("property password is not found in config.properties file");
    }
}