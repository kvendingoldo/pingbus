package com.kvendingoldo.pingbus.service.dao.factory;

import com.kvendingoldo.pingbus.service.dao.StationDao;
import com.kvendingoldo.pingbus.service.exceptions.database.UnknownDatabaseException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Alexander Sharov
 * @email kvendingoldo@yandex.ru
 * @since 24.02.17
 */

public class DaoFactoryTest {

    public StationDao getStationDao(String dbName){
        DaoFactory factory = new DaoFactory();
        return factory.getStationDao(dbName);
    }

    @Test
    public void getStationDaoPostgres() {
        Assert.assertTrue(getStationDao("postgres").getClass().getName().equals("com.kvendingoldo.pingbus.service.dao.PostgresStationDao"));
    }

    @Test
    public void getStationDaoMysql() {
        Assert.assertTrue(getStationDao("mysql").getClass().getName().equals("com.kvendingoldo.pingbus.service.dao.MysqlStationDao"));
    }

    @Test
    public void getStationDaoNone() {
        try {
            getStationDao("None").getClass().getName();
        } catch (UnknownDatabaseException ex){
            Assert.assertTrue(ex.getMessage().equals("Unknown database: None"));
        }
    }
}