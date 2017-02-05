package com.kvendingoldo.pingbus.service.database;

import com.kvendingoldo.pingbus.service.datatype.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Controller {

    private String URL;
    private String username;
    private String password;
    private Connection connection;
    private Statement statement;

    Controller(){
        loadConfiguration();
    }

    private void loadConfiguration() {

        Properties property = new Properties();

        try {
            InputStream is = getClass().getResourceAsStream("/app.properties" );
            property.load(is);

            URL = property.getProperty("db.url.fixed");
            username = property.getProperty("db.username");
            password = property.getProperty("db.password");

        } catch (IOException e) {
            System.err.println("Property doesn't exist");
        }
    }

    public void SetConnection(){

        try {
            connection = DriverManager.getConnection(URL, username, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getDataFromDB(){

        return null;
    }

    public void putDataToDB(SQLexpression expression) {

        try {
            statement.execute(expression.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDataInDB(SQLexpression expression){
        try {
            statement.executeUpdate(expression.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean CheckConnection() throws SQLException {

        if (connection.isClosed()) {
            return false;
        }else {
            return true;
        }

    }
}