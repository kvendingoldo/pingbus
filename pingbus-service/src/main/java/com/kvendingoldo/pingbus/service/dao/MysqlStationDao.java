package com.kvendingoldo.pingbus.service.dao;

import com.kvendingoldo.pingbus.service.entity.Station;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Alexander Sharov
 * @email kvendingoldo@yandex.ru
 * @since 24.02.17
 */

@Deprecated
public class MysqlStationDao implements StationDao {

    private final Connection connection;

    public MysqlStationDao() {
        connection = null;
    }

    public void create(Station station) {
    }

    public void update(Station station) {
    }

    public void delete(Station station) {
    }

    public Station get(int id) {
        return null;
    }

    public Station get(LocalTime tta) {
        return null;
    }

    public Station getNearest(){
        return null;
    }

    public List<Station> getAll() {
        return null;
    }
}
