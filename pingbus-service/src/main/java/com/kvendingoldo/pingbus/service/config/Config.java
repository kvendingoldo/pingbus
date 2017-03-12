package com.kvendingoldo.pingbus.service.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private String dbTableName;
    private String timezone;

    private static final Logger LOGGER = LogManager.getLogger();

    public Config() {
    }

    public Config(String action) {
        try {
            if (action.equals("load")) {
                load();
            }
        } catch (NullPointerException ex) {
            LOGGER.error("[ERROR] config action doesn't choose");
        }
    }

    public void reload() {
        load();
    }

    public void load() {
        Properties property = new Properties();

        try (InputStream stream = getClass().getResourceAsStream("/app.properties")) {
            property.load(stream);

            dbName = property.getProperty("db.name");
            dbTableName = property.getProperty("db.basic.table");
            dbUrl = property.getProperty("db.url");
            dbUsername = property.getProperty("db.username");
            dbPassword = property.getProperty("db.password");
            timezone = property.getProperty("timezone");

        } catch (FileNotFoundException ex) {
            LOGGER.error("[ERROR] Config file doesn't exist!");
        } catch (IOException ex) {
            LOGGER.error("[ERROR]");
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
        return dbTableName;
    }

    public String getCurrentTimeZone(){
        return timezone;
    }

    public String getDbName() {
        return dbName;
    }
}
