package com.kvendingoldo.pingbus.service.dao;

import com.kvendingoldo.pingbus.service.entity.Station;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Alexander Sharov
 * @email kvendingoldo@yandex.ru
 * @since 12.02.17
 */

public interface StationDao {

    void create(Station station);

    void update(Station station);

    void delete(Station station);

    Station get(int id);

    Station get(LocalTime tta);

    Station getNearest();

    List<Station> getAll();
}