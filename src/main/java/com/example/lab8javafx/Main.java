package com.example.lab8javafx;

import com.example.lab8javafx.DAO.CityDAO;
import com.example.lab8javafx.DAO.ContinentDAO;
import com.example.lab8javafx.DAO.CountryDAO;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        CityDAO cityDAO = new CityDAO();
        CountryDAO countryDAO = new CountryDAO();
        ContinentDAO continentDAO = new ContinentDAO();

        cityDAO.emptyTable();
        countryDAO.emptyTable();
        continentDAO.emptyTable();

        continentDAO.createContinent("Europa");
        continentDAO.createContinent("Asia");
        continentDAO.createContinent("Africa");
        continentDAO.createContinent("Australia");
        continentDAO.createContinent("America de Nord");
        continentDAO.createContinent("America de Sud");

        countryDAO.createCountry("ja","Japonia",continentDAO.findByName("Asia"));
        countryDAO.createCountry("ro","Romania",continentDAO.findByName("Europa"));
        countryDAO.createCountry("ni","Nigeria",continentDAO.findByName("Africa"));

        cityDAO.createCity("Abuja",true,9.066667,7.483333,countryDAO.findByName("Nigeria"));
        cityDAO.createCity("Iasi",false,47.151726,27.587914,countryDAO.findByName("Romania"));
        cityDAO.createCity("Tokyo",true,35.652832,139.839478,countryDAO.findByName("Japonia"));



//        System.out.println(continentDAO.findByName("Europa"));
//        System.out.println(continentDAO.findByName("Asia"));
//        System.out.println(continentDAO.findByName("Africa"));
//
//        System.out.println(countryDAO.findByName("Romania"));
//        System.out.println(countryDAO.findByName("Japonia"));
//        System.out.println(countryDAO.findByName("Nigeria"));
//
//        System.out.println(countryDAO.findById(1));
//        System.out.println(countryDAO.findById(2));
//        System.out.println(countryDAO.findById(3));



    }

}
