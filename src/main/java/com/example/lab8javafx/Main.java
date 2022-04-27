package com.example.lab8javafx;

import com.example.lab8javafx.DAO.ContinentDAO;
import com.example.lab8javafx.DAO.CountryDAO;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        CountryDAO countryDAO = new CountryDAO();
        ContinentDAO continentDAO = new ContinentDAO();

        countryDAO.emptyTable();
        continentDAO.emptyTable();

        continentDAO.createContinent("Europa");
        continentDAO.createContinent("Asia");
        continentDAO.createContinent("Africa");

        countryDAO.createCountry("ro","Romania",continentDAO.findByName("Europa"));
        countryDAO.createCountry("ja","Japonia",continentDAO.findByName("Asia"));
        countryDAO.createCountry("ni","Nigeria",continentDAO.findByName("Africa"));


        System.out.println(continentDAO.findByName("Europa"));
        System.out.println(continentDAO.findByName("Asia"));
        System.out.println(continentDAO.findByName("Africa"));

        System.out.println(countryDAO.findByName("Romania"));
        System.out.println(countryDAO.findByName("Japonia"));
        System.out.println(countryDAO.findByName("Nigeria"));

        System.out.println(countryDAO.findById(1));
        System.out.println(countryDAO.findById(2));
        System.out.println(countryDAO.findById(3));

//        countryDAO.emptyTable();
//        continentDAO.emptyTable();


    }

}
