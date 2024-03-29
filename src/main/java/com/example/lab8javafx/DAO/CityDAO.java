package com.example.lab8javafx.DAO;

import com.example.lab8javafx.DataBase.DataBase;
import com.example.lab8javafx.DataBase.Singleton;
import com.example.lab8javafx.Entities.CityEntity;
import com.example.lab8javafx.Entities.CountryEntity;

import java.sql.SQLException;
import java.util.List;

public class CityDAO {

    public static void createCity(String name, boolean capital, double latitude, double longitude, CountryEntity country) throws SQLException {
        DataBase dataBase = Singleton.getInstance();

        CityEntity city = new CityEntity(country, name, capital, latitude, longitude);
        dataBase.addCity(city);
    }

    public static void addCity(CityEntity city) throws SQLException
    {
        DataBase dataBase = Singleton.getInstance();
        dataBase.addCity(city);
    }

    public static CityEntity findById(long id) throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        return dataBase.getCityById(id);
    }

    public static CityEntity findByName(String name) throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        return dataBase.getCityByName(name);
    }

    public static void emptyTable() throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        dataBase.emptyTableCities();
    }

    public static List<CityEntity> getAll() throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        return dataBase.getCities();
    }
}
