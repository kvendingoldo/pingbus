package com.kvendingoldo.pingbus.service.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Alexander Sharov
 * @email kvendingoldo@yandex.ru
 * @since 24.02.17
 */

public class Config {

    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private String dbName;
    private String timezone;

    public Config() {
    }

    public Config(String action) {
        if (action.equals("load")) {
            load();
        }
    }

    public void reload() {
        load();
    }

    public void load() {
        Properties property = new Properties();

        try (InputStream stream = getClass().getResourceAsStream("/app.properties")) {
            property.load(stream);

            dbName = property.getProperty("db.basic.table");
            dbUrl = property.getProperty("db.url");
            dbUsername = property.getProperty("db.username");
            dbPassword = property.getProperty("db.password");
            timezone = property.getProperty("timezone");

        } catch (FileNotFoundException e) {
            System.err.println("[ERROR] Config file doesn't exist!");
        } catch (IOException e) {
            System.err.println("[ERROR]");
        }
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbTableName() {
        return dbName;
    }

    public String getCurrentTimeZone(){
        return timezone;
    }
}
