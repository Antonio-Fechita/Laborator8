package com.example.lab8javafx.generators;

import com.example.lab8javafx.DAO.CityDAO;
import com.example.lab8javafx.Entities.CityEntity;
import com.example.lab8javafx.Entities.ContinentEntity;
import com.example.lab8javafx.Entities.CountryEntity;
import com.example.lab8javafx.graphtheory.CliqueFinder;
import com.example.lab8javafx.graphtheory.Edge;
import com.example.lab8javafx.graphtheory.Node;
import com.github.javafaker.Faker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RandomCityGenerator
{
    public static final int CITY_NUMBER = 6000;
    public static final int COUNTRY_NUMBER = 200;
    public static final int CONTINENT_NUMBER = 5;
    public static final float SISTERHOOD_PROBABILITY = 0.02f;

    static ArrayList<ContinentEntity> continents = new ArrayList<>();

    static ArrayList<CountryEntity> countries = new ArrayList<>();

    static volatile ArrayList<CityEntity> cities = new ArrayList<>();

    public static void generateRandom()
    {
        Faker faker = new Faker();

        Random rand = new Random();

        for (int continentIndex = 0; continentIndex < CONTINENT_NUMBER;continentIndex++)
        {
            String continentName = faker.witcher().location();

            ContinentEntity newContinent = new ContinentEntity( continentName );

            continents.add( newContinent );
        }

        for (int countryIndex = 0; countryIndex < COUNTRY_NUMBER; countryIndex++)
        {
            String fakeCountryName = faker.elderScrolls().region();

            int randContinentIndex = rand.nextInt(CONTINENT_NUMBER);

            CountryEntity newCountry = new CountryEntity("c_" + (countryIndex + 1), "Country_" + countryIndex, continents.get(randContinentIndex));

            countries.add( newCountry );

            CityEntity newCapital = new CityEntity( newCountry, "CapitalCity_" + countryIndex, true, 0, 0);

            cities.add(newCapital);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        int neededIterations = CITY_NUMBER / RunnableCityCreator.CITIES_PER_THREAD;

        int generatedCityIndex = COUNTRY_NUMBER;

        for (int threadIteration = 0; threadIteration < neededIterations; threadIteration++)
        {
            Runnable runnable = new RunnableCityCreator(countries, generatedCityIndex);

            generatedCityIndex += RunnableCityCreator.CITIES_PER_THREAD;

            executorService.execute(runnable);
        }

        executorService.shutdown();

        while (!executorService.isTerminated()){}

        System.out.println(cities.size());

        HashSet<String> cityNames = new HashSet<>();

        boolean duplicatesFound = false;

        for (CityEntity city: cities)
        {
            System.out.println(city.getName() + " - " + city.getCountry().getName() + " - " + city.getCountry().getContinent().getName());

            if (cityNames.contains( city.getName() ))
            {
                duplicatesFound = true;
            }

            cityNames.add( city.getName() );
        }

        if (duplicatesFound == false)
            System.out.println( "No naming duplicates were found!" );

        int sisterEdges = (int) ((cities.size() * 100) * SISTERHOOD_PROBABILITY);

        HashSet<String> edges = new HashSet<>();

        while (edges.size() < sisterEdges)
        {
            int nodeIndexA, nodeIndexB;

            nodeIndexA = rand.nextInt(cities.size());
            nodeIndexB = rand.nextInt(cities.size());

            if (nodeIndexA == nodeIndexB)
                continue;

            String possibleEdgeName = Edge.getIdentifierFromNodes( cities.get(nodeIndexA), cities.get(nodeIndexB) );

            if (edges.contains(possibleEdgeName))
                continue;

            edges.add(possibleEdgeName);

            cities.get( nodeIndexA ).addSister( cities.get( nodeIndexB ) );

            cities.get( nodeIndexB ).addSister( cities.get( nodeIndexA ) );
        }

        System.out.println("Edges generated " + edges.size());

        ArrayList<Node> nodes = new ArrayList<>();

        nodes.addAll( cities );

        CliqueFinder.findCliques( nodes );

        for (CityEntity city: cities)
        {
            try {
                CityDAO.addCity(city);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addCity(CityEntity city)
    {
        cities.add(city);
    }
}
