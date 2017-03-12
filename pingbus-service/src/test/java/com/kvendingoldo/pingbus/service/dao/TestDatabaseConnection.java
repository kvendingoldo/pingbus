package com.kvendingoldo.pingbus.service.dao;

import com.kvendingoldo.pingbus.service.config.Config;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Alexander Sharov
 * @email kvendingoldo@yandex.ru
 * @since 24.02.17
 */

public class TestDatabaseConnection extends Assert {

    private static Config config;

    @BeforeClass
    public static void loadTestConfiguration() {
        config = new Config("load");
    }

    @Test
    public void getConnection() throws Exception {

        Connection connection = DriverManager.getConnection(config.getDbUrl(), config.getDbUsername(), config.getDbPassword());

        Assert.assertNotNull(connection);
        Assert.assertTrue(!connection.isClosed());

        connection.close();
    }
}