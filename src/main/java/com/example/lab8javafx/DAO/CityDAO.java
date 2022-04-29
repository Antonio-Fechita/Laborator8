package com.example.lab8javafx.DAO;

import com.example.lab8javafx.DataBase.DataBase;
import com.example.lab8javafx.DataBase.Singleton;
import com.example.lab8javafx.Entities.CityEntity;
import com.example.lab8javafx.Entities.CountryEntity;

import java.sql.SQLException;
import java.util.List;

public class CityDAO {

    public void createCity(String name, boolean capital, double latitude, double longitude, CountryEntity country) throws SQLException {
        DataBase dataBase = Singleton.getInstance();

        CityEntity city = new CityEntity(country, name, capital, latitude, longitude);
        dataBase.addCity(city);
    }

    public CityEntity findById(long id) throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        return dataBase.getCityById(id);
    }

    public CityEntity findByName(String name) throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        return dataBase.getCityByName(name);
    }

    public void emptyTable() throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        dataBase.emptyTableCities();
    }

    public List<CityEntity> getAll() throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        return dataBase.getCities();
    }
}
