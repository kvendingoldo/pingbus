package com.kvendingoldo.pingbus.service.utils;

import com.kvendingoldo.pingbus.service.config.Config;
import com.kvendingoldo.pingbus.service.dao.StationDao;
import com.kvendingoldo.pingbus.service.dao.factory.DaoFactory;
import com.kvendingoldo.pingbus.service.entity.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;
import java.util.*;

/**
 * @author Alexander Sharov
 * @email kvendingoldo@yandex.ru
 * @since 12.02.17
 */

public class TableRecalculating {

    private Config config = new Config();

    public List<Station> getUpdatedData() {

        StationDao stationDao = new DaoFactory().getStationDao(config.getDbName());

        List<Station> stations = stationDao.getAll();

        sort(stations);

        for (int i = 0; i < stations.size(); i++) {

            List<LocalTime> collect_times = new ArrayList<>();

            collect_times.add(stations.get(i).getTTA());

            userLogger.debug("FOR COMPARE: " + stations.get(i).getTTA().toString());

            int time1 = stations.get(i).getTTA().getHour() * 60 + stations.get(i).getTTA().getMinute();
            stations.remove(i);

            for (int j = 0; j < stations.size(); j++) {

                int time2 = stations.get(j).getTTA().getHour() * 60 + stations.get(j).getTTA().getMinute();
                int diff = Math.abs(time1 - time2);

                if (diff < 5) {
                    userLogger.debug("FOUND (+): " + stations.get(j).getTTA().toString());
                    collect_times.add(stations.get(j).getTTA());
                    stations.remove(j);
                } else {
                    userLogger.debug("FOUND (-):" + stations.get(j).getTTA().toString());
                }
            }

            if (collect_times.size() > 1) {
                LocalTime time = reduceTimes(collect_times);
                stations.remove(i);
                stations.add(i, new Station(time, 0));
            } else {
                stations.add(i, new Station(collect_times.get(0), 0));
            }
        }

        sort(stations);

        return stations;
    }

    public void recalculatingDbData() {

        List<Station> stations = getUpdatedData();

        System.out.println("===========");
        for (int i = 0; i < stations.size(); i++) {
            System.out.println(stations.get(i).getTTA());
        }

        // delete all data from db

        // load new data to db

    }

    private static final Logger userLogger = LogManager.getLogger();

    private void sort(List<Station> arr) {
        for (int i = arr.size() - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {

                int time1 = arr.get(j).getTTA().getHour() * 60 + arr.get(j).getTTA().getMinute();
                int time2 = arr.get(j + 1).getTTA().getHour() * 60 + arr.get(j + 1).getTTA().getMinute();

                if (time1 > time2) {
                    Station tmpStation = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, tmpStation);
                }
            }
        }
    }

    private LocalTime reduceTimes(List<LocalTime> excess_times) {

        userLogger.debug("Reduce function was running");

        int hour = excess_times.get(0).getHour();
        int minutes = 0;

        for (int i = 0; i < excess_times.size(); i++) {
            minutes += excess_times.get(i).getMinute();
        }

        minutes = minutes / excess_times.size();

        return LocalTime.of(hour, minutes);
    }

}