package com.kvendingoldo.pingbus.service.dao.factory;

import com.kvendingoldo.pingbus.service.config.Config;
import com.kvendingoldo.pingbus.service.dao.MysqlStationDao;
import com.kvendingoldo.pingbus.service.dao.PostgresStationDao;
import com.kvendingoldo.pingbus.service.dao.StationDao;
import com.kvendingoldo.pingbus.service.exceptions.database.UnknownDatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Alexander Sharov
 * @email kvendingoldo@yandex.ru
 * @since 12.02.17
 */

public class DaoFactory {

    public DaoFactory() {
    }

    public Connection getConnection() {
        Config config = new Config();
        config.load();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(config.getDbUrl(), config.getDbUsername(), config.getDbPassword());
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQLException message:" + ex.getMessage());
        }

        return connection;
    }

    public StationDao getStationDao(String dbName) {
        if (dbName.equals("mysql"))
            return new MysqlStationDao();
        else if (dbName.equals("postgres"))
            return new PostgresStationDao(getConnection());
        else {
            throw new UnknownDatabaseException("Unknown database: " + dbName);
        }
    }
}