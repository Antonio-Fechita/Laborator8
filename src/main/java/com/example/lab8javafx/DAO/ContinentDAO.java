package com.example.lab8javafx.DAO;

import com.example.lab8javafx.DataBase.DataBase;
import com.example.lab8javafx.Entities.ContinentEntity;
import com.example.lab8javafx.DataBase.Singleton;

import java.sql.SQLException;
import java.util.List;

public class ContinentDAO {

    public static void createContinent(String name) throws SQLException {
        DataBase dataBase = Singleton.getInstance();

        ContinentEntity continent = new ContinentEntity(name);
        dataBase.addContinent(continent);
    }

    public static ContinentEntity findById(long id) throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        return dataBase.getContinentById(id);
    }

    public static ContinentEntity findByName(String name) throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        return dataBase.getContinentByName(name);
    }

    public static void emptyTable() throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        dataBase.emptyTableContinents();
    }

    public static List<ContinentEntity> getAll() throws SQLException {
        DataBase dataBase = Singleton.getInstance();
        return dataBase.getContinents();
    }
}
