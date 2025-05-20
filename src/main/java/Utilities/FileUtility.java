package Utilities;

import java.io.*;
import java.util.Properties;

public class FileUtility {

    static Properties properties;
    static InputStream input;

    public static Properties loadProperties(String path) {
        try {
            input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            return properties;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void updateBearerToken(String newToken) {
        String propertyPath = System.getProperty("user.dir") + "/src/main/resources/env.properties";
        Properties props = new Properties();

        try {
            // Load existing properties
            props.load(new java.io.FileInputStream(propertyPath));

            // Set new token
            props.setProperty("bearerToken", newToken);

            // Store back to file
            props.store(new FileOutputStream(propertyPath), null);
            System.out.println("Bearer token updated successfully!");
        } catch (IOException e) {
            System.out.println("Failed to update bearer token: " + e.getMessage());
        }
    }
}