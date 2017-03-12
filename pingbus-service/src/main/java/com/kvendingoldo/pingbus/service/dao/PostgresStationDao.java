package com.kvendingoldo.pingbus.service.dao;

import com.kvendingoldo.pingbus.service.config.Config;
import com.kvendingoldo.pingbus.service.entity.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Sharov
 * @email kvendingoldo@yandex.ru
 * @since 12.02.17
 */

public class PostgresStationDao implements StationDao {

    private final Connection connection;
    private Config config = new Config();
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm:ss");

    private static final Logger LOGGER = LogManager.getLogger();

    public PostgresStationDao(Connection connection) {
        this.connection = connection;
        config.load();
    }

    public PostgresStationDao() {
        connection = null;
    }

    @Override
    public void create(Station station) {

        String sql = "INSERT INTO " + config.getDbTableName() + " (TTA, RATING) VALUES (?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, station.getTTA().toString());
            statement.setInt(2, station.getRating());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            LOGGER.error("SQLException message:" + ex.getMessage());
            LOGGER.error("SQLException SQL state:" + ex.getSQLState());
        }
    }

    @Override
    public void update(Station station) {
    }

    @Override
    public void delete(Station station) {

        String sql = "DELETE FROM " + config.getDbTableName() + " WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, station.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOGGER.error("SQLException message:" + ex.getMessage());
            LOGGER.error("SQLException SQL state:" + ex.getSQLState());
        }
    }

    @Override
    public Station get(LocalTime tta) {

        String sql = "SELECT * FROM " + config.getDbTableName() + " WHERE tta = ?;";
        Station station = new Station();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tta.toString());
            ResultSet rs = statement.executeQuery();
            rs.next();

            station.setId(rs.getInt("id"));
            station.setTTA(LocalTime.parse(rs.getString("TTA"), dtf));
            station.setRating(rs.getInt("rating"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOGGER.error("SQLException message:" + ex.getMessage());
            LOGGER.error("SQLException SQL state:" + ex.getSQLState());
        }

        return station;
    }

    @Override
    public Station get(int id) {

        String sql = "SELECT * FROM " + config.getDbTableName() + " WHERE id = ?;";
        Station station = new Station();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();

            station.setId(rs.getInt("id"));
            station.setTTA(LocalTime.parse(rs.getString("TTA"), dtf));
            station.setRating(rs.getInt("rating"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOGGER.error("SQLException message:" + ex.getMessage());
            LOGGER.error("SQLException SQL state:" + ex.getSQLState());
        }

        return station;
    }

    @Override
    public Station getNearest(){

        ZoneId timezone = ZoneId.of(config.getCurrentTimeZone());
        LocalTime currentTime = LocalTime.now(timezone);

        String sql = "SELECT *\n" +
                "FROM " + config.getDbTableName() + "\n" +
                "WHERE ((EXTRACT(MINUTE FROM tta) + EXTRACT(HOUR FROM tta) * 60) -\n" +
                "       (EXTRACT(MINUTE FROM TIME '" + currentTime.format(dtf) + "') + EXTRACT(HOUR FROM TIME '" + currentTime.format(dtf) +"') * 60)) BETWEEN 0 AND 5;\n";

        Station station = new Station();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            rs.next();

            station.setId(rs.getInt("id"));
            station.setTTA(LocalTime.parse(rs.getString("TTA"), dtf));
            station.setRating(rs.getInt("rating"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOGGER.error("SQLException message:" + ex.getMessage());
            LOGGER.error("SQLException SQL state:" + ex.getSQLState());
        }

        return station;
    }

    @Override
    public List<Station> getAll() {

        String sql = "SELECT DISTINCT * FROM " + config.getDbTableName();
        List<Station> list = new ArrayList<Station>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Station station = new Station();
                station.setId(rs.getInt("id"));
                station.setTTA(LocalTime.parse(rs.getString("TTA"), dtf));
                station.setRating(rs.getInt("rating"));
                list.add(station);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOGGER.error("SQLException message:" + ex.getMessage());
            LOGGER.error("SQLException SQL state:" + ex.getSQLState());
        }

        return list;
    }
}