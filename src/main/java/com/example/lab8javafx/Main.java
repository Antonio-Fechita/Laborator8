package com.example.lab8javafx;

import com.example.lab8javafx.generators.RandomCityGenerator;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException
    {
        //RealWorldGetter.importRealCities();
        RandomCityGenerator.generateRandom();

        /*
        CityDAO.emptyTable();
        CountryDAO.emptyTable();
        ContinentDAO.emptyTable();

        ContinentDAO.createContinent("Europa");
        ContinentDAO.createContinent("Asia");
        ContinentDAO.createContinent("Africa");
        ContinentDAO.createContinent("Australia");
        ContinentDAO.createContinent("America de Nord");
        ContinentDAO.createContinent("America de Sud");

        CountryDAO.createCountry("ja","Japonia",ContinentDAO.findByName("Asia"));
        CountryDAO.createCountry("ro","Romania",ContinentDAO.findByName("Europa"));
        CountryDAO.createCountry("ni","Nigeria",ContinentDAO.findByName("Africa"));

        CityDAO.createCity("Abuja",true,9.066667,7.483333,CountryDAO.findByName("Nigeria"));
        CityDAO.createCity("Iasi",false,47.151726,27.587914,CountryDAO.findByName("Romania"));
        CityDAO.createCity("Tokyo",true,35.652832,139.839478,CountryDAO.findByName("Japonia"));
        */



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
