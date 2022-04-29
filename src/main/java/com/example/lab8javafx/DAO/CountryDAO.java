package com.example.lab8javafx.DAO;

import com.example.lab8javafx.DataBase.DataBase;
import com.example.lab8javafx.DataBase.Singleton;
import com.example.lab8javafx.Entities.ContinentEntity;
import com.example.lab8javafx.Entities.CountryEntity;

import java.sql.SQLException;
import java.util.List;

public class CountryDAO {


    public void createCountry(String code, String name, ContinentEntity continent) throws SQLException {
        DataBase dataBase = Singleton.getInstance();

        CountryEntity country = new CountryEntity(code,name,continent);
        dataBase.addCountry(country);
    }

    public CountryEntity findById(long id) throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        return dataBase.getCountryById(id);
    }

    public CountryEntity findByName(String name) throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        return dataBase.getCountryByName(name);
    }

    public void emptyTable() throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        dataBase.emptyTableCountries();
    }

    public List<CountryEntity> getAll() throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        return dataBase.getCountries();
    }

}
