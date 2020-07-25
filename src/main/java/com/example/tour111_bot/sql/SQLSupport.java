package com.example.tour111_bot.sql;

import com.example.tour111_bot.restModel.City;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLSupport {

    private Connection connection;

    public SQLSupport() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:65367;database=Tour;integratedSecurity=true;");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public String getInfoByCityName(String city) {
        String info;
        try {
            ResultSet set = connection.createStatement().executeQuery("select info from City where name_of_city='" + city + "'");
            if(set.next()) {
                info =set.getString("info");
                return info;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean updateInfoOfCity(City city) {
        try {
            ResultSet set = connection.createStatement()
                    .executeQuery("select * from City where name_of_city='" + city.getName_of_city() + "'");
            if(set.next()) {
                connection.createStatement()
                        .executeUpdate("update City " +
                                "set info='" + city.getInfo() + "' " +
                                "where name_of_city='" + city.getName_of_city() + "'");
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addCity(City city) {
        try {
            connection.createStatement().execute("insert into City values ('" + city.getName_of_city() + "', '" + city.getInfo() + "')");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCity(String city) {
        try {
            ResultSet set = connection.createStatement().executeQuery("select * from City where name_of_city='" + city + "'");
            if(set.next()) {
                connection.createStatement().execute("delete from City where name_of_city='" + city + "'");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
