package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
    public static Properties propertyLoader(String filePath){
        Properties props = new Properties();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            try {
                props.load(reader);
                reader.close();
            } catch (IOException e){
                e.printStackTrace();
                throw  new RuntimeException("failed to load properties from file " + filePath);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw  new RuntimeException("properties file not found at " + filePath);
        }
        return props;
    }
}