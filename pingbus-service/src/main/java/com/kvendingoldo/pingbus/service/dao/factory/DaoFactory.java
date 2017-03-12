package com.kvendingoldo.pingbus.service.dao.factory;

import com.kvendingoldo.pingbus.service.config.Config;
import com.kvendingoldo.pingbus.service.dao.MysqlStationDao;
import com.kvendingoldo.pingbus.service.dao.PostgresStationDao;
import com.kvendingoldo.pingbus.service.dao.StationDao;
import com.kvendingoldo.pingbus.service.exceptions.database.UnknownDatabaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Alexander Sharov
 * @email kvendingoldo@yandex.ru
 * @since 12.02.17
 */

public class DaoFactory {

    private static final Logger LOGGER = LogManager.getLogger();

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
            LOGGER.error("SQLException message:" + ex.getMessage());
        }

        return connection;
    }

    public StationDao getStationDao(String dbName) {

        StationDao stationDao = null;

        try {
            if (dbName.equals("mysql"))
                stationDao = new MysqlStationDao();
            else if (dbName.equals("postgres"))
                stationDao = new PostgresStationDao(getConnection());
            else
                throw new UnknownDatabaseException("Unknown database: " + dbName);
        } catch (NullPointerException ex) {
            LOGGER.error("SQLException message:" + ex.getMessage());
        }

        return stationDao;
    }
}