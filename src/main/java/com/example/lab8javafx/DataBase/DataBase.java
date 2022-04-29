package com.example.lab8javafx.DataBase;

import com.example.lab8javafx.Entities.CityEntity;
import com.example.lab8javafx.Entities.ContinentEntity;
import com.example.lab8javafx.Entities.CountryEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    static Connection connection;

    public DataBase() throws SQLException {
        String address = "jdbc:postgresql://localhost:5432/JavaLab8";
        connection = DriverManager.getConnection(address,"postgres","Zeus1234");
    }

    public void emptyTableCities() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("truncate table cities cascade");
    }

    public void emptyTableCountries() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("truncate table countries cascade");
    }

    public void emptyTableContinents() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("truncate table continents cascade");
    }

    public List<CountryEntity> getCountries() throws SQLException {
        List<CountryEntity> countries = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet countriesSet = statement.executeQuery("select * from countries");
        while (countriesSet.next()){
            ContinentEntity continent = getContinentById(countriesSet.getInt("continent_id"));
            String countryCode = countriesSet.getString("code");
            String countryName = countriesSet.getString("name");
            countries.add(new CountryEntity(countryCode,countryName,continent));
        }


        return countries;
    }

    public List<CityEntity> getCities() throws SQLException {
        List<CityEntity> cities = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet citiesSet = statement.executeQuery("select * from cities");
        while (citiesSet.next()){
            CountryEntity country = getCountryById(citiesSet.getInt("country_id"));
            String name = citiesSet.getString("name");
            boolean capital = citiesSet.getBoolean("capital");
            double latitude = citiesSet.getDouble("latitude");
            double longitude = citiesSet.getDouble("longitude");

            cities.add(new CityEntity(country,name,capital,latitude,longitude));
        }


        return cities;
    }

    public List<ContinentEntity> getContinents() throws SQLException {
        List<ContinentEntity> continents = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet continentsSet = statement.executeQuery("select * from continents");
        while(continentsSet.next()){
            continents.add(new ContinentEntity(continentsSet.getString("name")));
        }

        return continents;
    }



    public long getIdForCountry() throws SQLException {
        Statement statement = connection.createStatement();
        int maxId = 0;
        ResultSet resultSet = statement.executeQuery("select max(id) as \"max\" from countries");
        if(resultSet.next()){
            maxId = resultSet.getInt("max");
        }
        System.out.println("returned id for country: " + (maxId + 1));
        return maxId + 1;
    }

    public long getIdForContinent() throws SQLException {
        Statement statement = connection.createStatement();
        int maxId = 0;
        ResultSet resultSet = statement.executeQuery("select max(id) as \"max\" from continents");
        if(resultSet.next()){
            maxId = resultSet.getInt("max");
        }
        System.out.println("returned id for continent: " + (maxId + 1));
        return maxId + 1;
    }

    public long getIdForCity() throws SQLException {
        Statement statement = connection.createStatement();
        int maxId = 0;
        ResultSet resultSet = statement.executeQuery("select max(id) as \"max\" from cities");
        if(resultSet.next()){
            maxId = resultSet.getInt("max");
        }
        System.out.println("returned id for city: " + (maxId + 1));
        return maxId + 1;
    }


    public void addContinent(ContinentEntity continent) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("insert into continents (id, name) values (" + getIdForContinent() + ", \'" + continent.getName() + "\')");
    }

    public void addCountry(CountryEntity country) throws SQLException {
        ContinentEntity continentOfCountry = country.getContinent();

        if(getContinentByName(continentOfCountry.getName())==null){
            addContinent(continentOfCountry);
        }
        int continentId;
        Statement continentIdStatement = connection.createStatement();
        ResultSet resultSet =  continentIdStatement.executeQuery("select id from continents where name = \'" + continentOfCountry.getName() + "\'");
        resultSet.next();
        continentId = resultSet.getInt("id");

        Statement statement = connection.createStatement();
        statement.execute("insert into countries (id,name,code,continent_id) values (" + getIdForCountry() + ", \'" + country.getName() + "\', \'" + country.getCode() + "\', " + continentId + ")");
    }

    public void addCity(CityEntity city) throws SQLException {
        CountryEntity countryOfCity = city.getCountry();

        if(getCountryByName(countryOfCity.getName())==null){
            addCountry(countryOfCity);
        }
        int countryId;
        Statement countryIdStatement = connection.createStatement();
        ResultSet resultSet =  countryIdStatement.executeQuery("select id from countries where name = \'" + countryOfCity.getName() + "\'");
        resultSet.next();
        countryId = resultSet.getInt("id");

        Statement statement = connection.createStatement();
        int capitalBit;
        if(city.isCapital())
            capitalBit = 1;
        else
            capitalBit = 0;
        statement.execute("insert into cities (id,name,capital,latitude,longitude,country_id) values (" + getIdForCity() + ", \'" + city.getName() + "\', \'" + capitalBit + "\', " + city.getLatitude() + ", " + city.getLongitude() + ", " + countryId + ")");
    }

    public void setCountries(List<CountryEntity> countries) throws SQLException {
        emptyTableCountries();
        countries.forEach(countryEntity -> {
            try {
                addCountry(countryEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }

    public void setCities(List<CityEntity> cities) throws SQLException {
        emptyTableCountries();
        cities.forEach(cityEntity -> {
            try {
                addCity(cityEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }

    public void setContinents(List<ContinentEntity> continents) throws SQLException {
        emptyTableContinents();
        continents.forEach(continentEntity -> {
            try {
                addContinent(continentEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }

    public CountryEntity getCountryById(long id) throws SQLException {
        Statement statement = connection.createStatement();
        CountryEntity country = null;
        ResultSet resultSet = statement.executeQuery("select * from countries where id = " + id);
        if(resultSet.next()){
            ContinentEntity continent = getContinentById(resultSet.getInt("continent_id"));
            String countryCode = resultSet.getString("code");
            String countryName = resultSet.getString("name");
            country = new CountryEntity(countryCode,countryName,continent);
        }
        return country;
    }

    public CityEntity getCityById(long id) throws SQLException {
        Statement statement = connection.createStatement();
        CityEntity city = null;
        ResultSet resultSet = statement.executeQuery("select * from cities where id = " + id);
        if(resultSet.next()){
            CountryEntity country = getCountryById(resultSet.getInt("country_id"));
            String name = resultSet.getString("name");
            boolean capital = resultSet.getBoolean("capital");
            double latitude = resultSet.getDouble("latitude");
            double longitude = resultSet.getDouble("longitude");
            city = new CityEntity(country,name,capital,latitude,longitude);
        }
        return city;
    }

    public CountryEntity getCountryByName(String name) throws SQLException {
        Statement statement = connection.createStatement();
        CountryEntity country = null;
        ResultSet resultSet = statement.executeQuery("select * from countries where name = \'" + name + "\'");
        if(resultSet.next()){
            ContinentEntity continent = getContinentById(resultSet.getInt("continent_id"));
            String countryCode = resultSet.getString("code");
            String countryName = resultSet.getString("name");
            country = new CountryEntity(countryCode,countryName,continent);
        }
        return country;
    }

    public CityEntity getCityByName(String name) throws SQLException {
        Statement statement = connection.createStatement();
        CityEntity city = null;
        ResultSet resultSet = statement.executeQuery("select * from cities where name = \'" + name + "\'");
        if(resultSet.next()){
            CountryEntity country = getCountryById(resultSet.getInt("country_id"));
            boolean capital = resultSet.getBoolean("capital");
            double latitude = resultSet.getDouble("latitude");
            double longitude = resultSet.getDouble("longitude");
            city = new CityEntity(country,name,capital,latitude,longitude);
        }
        return city;
    }

    public ContinentEntity getContinentById(long id) throws SQLException {
        Statement statement = connection.createStatement();
        ContinentEntity continent = null;
        ResultSet resultSet = statement.executeQuery("select * from continents where id = " + id);
        if (resultSet.next()){
            continent = new ContinentEntity(resultSet.getString("name"));
        }
        return continent;
    }

    public ContinentEntity getContinentByName(String name) throws SQLException {
        Statement statement = connection.createStatement();
        ContinentEntity continent = null;
        ResultSet resultSet = statement.executeQuery("select * from continents where name = \'" + name + "\'");
        if (resultSet.next()){
            continent = new ContinentEntity(resultSet.getString("name"));
        }
        return continent;
    }


}
