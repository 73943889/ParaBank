package com.parabank.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigHandler {
    private static final String file = "src/test/resources/testdata.properties";

    public static void setTestData(String username, String pass) {
        Properties prop = new Properties();
        try (FileOutputStream output = new FileOutputStream(file)) {
            prop.setProperty("test.username", username);
            prop.setProperty("test.password", pass);
            prop.store(output,"Datos guardados satisfactoriamente");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
