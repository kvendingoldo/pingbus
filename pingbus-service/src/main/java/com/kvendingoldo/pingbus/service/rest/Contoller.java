package com.kvendingoldo.pingbus.service.rest;

import java.sql.Connection;
import java.util.List;

import com.kvendingoldo.pingbus.service.dao.PostgresStationDao;
import com.kvendingoldo.pingbus.service.dao.StationDao;
import com.kvendingoldo.pingbus.service.dao.factory.DaoFactory;
import com.kvendingoldo.pingbus.service.entity.Station;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Alexander Sharov
 * @email kvendingoldo@yandex.ru
 * @since 22.02.17
 */

@RestController
public class Contoller {

    private DaoFactory daoFactory = new DaoFactory();
    private StationDao dao;

    private static final Logger userLogger = LogManager.getLogger();

    @RequestMapping(value = "/station/all", method = RequestMethod.GET)
    public List<Station> getAllStation() {

        userLogger.info("try GET /station/all");

        Connection con = daoFactory.getConnection();
        dao = daoFactory.getStationDao("postgres");

        return dao.getAll();
    }

    @Deprecated
    @RequestMapping(value = "/station/{id}", method = RequestMethod.GET)
    public Station getStationById(@PathVariable("id") int id) {

        userLogger.info(String.format("try GET /station/%d", id));

        Connection con = daoFactory.getConnection();
        dao = daoFactory.getStationDao("postgres");

        return dao.get(id);
    }

    @RequestMapping(value = "/station/nearest", method = RequestMethod.GET)
    public Station getNearestStation() {

        userLogger.info(String.format("try GET /station/nearest"));

        Connection con = daoFactory.getConnection();
        dao = daoFactory.getStationDao("postgres");

        return dao.getNearest();
    }

    @RequestMapping(value = "/station/{id}/set_rating?={rating}", method = RequestMethod.POST)
    public void setRating(@PathVariable("id") int id, @PathVariable("rating") int rating) {

        userLogger.info(String.format("try POST /station/%d/set_rating?=%d", id, rating));

        Connection con = daoFactory.getConnection();
        dao = daoFactory.getStationDao("postgres");
        Station station = dao.get(id);
        station.setRating(rating);
    }

    @RequestMapping(value = "/station/{id}/add_rating", method = RequestMethod.POST)
    public void addRating(@PathVariable("id") int id) {

        userLogger.info(String.format("try POST /station/%d/add_rating", id));

        Connection con = daoFactory.getConnection();
        dao = daoFactory.getStationDao("postgres");
        Station station = dao.get(id);
        station.setRating(station.getRating() + 1);
    }
}
