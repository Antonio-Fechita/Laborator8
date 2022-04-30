package com.example.lab8javafx.DAO;

import com.example.lab8javafx.DataBase.DataBase;
import com.example.lab8javafx.DataBase.Singleton;
import com.example.lab8javafx.Entities.ContinentEntity;
import com.example.lab8javafx.Entities.CountryEntity;

import java.sql.SQLException;
import java.util.List;

public class CountryDAO {


    public static void createCountry(String code, String name, ContinentEntity continent) throws SQLException {
        DataBase dataBase = Singleton.getInstance();

        CountryEntity country = new CountryEntity(code,name,continent);
        dataBase.addCountry(country);
    }

    public static CountryEntity findById(long id) throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        return dataBase.getCountryById(id);
    }

    public static CountryEntity findByName(String name) throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        return dataBase.getCountryByName(name);
    }

    public static void emptyTable() throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        dataBase.emptyTableCountries();
    }

    public static List<CountryEntity> getAll() throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        return dataBase.getCountries();
    }

}
