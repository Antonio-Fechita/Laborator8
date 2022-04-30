package com.example.lab8javafx.generators;

import com.example.lab8javafx.Entities.CityEntity;
import com.example.lab8javafx.Entities.CountryEntity;

import java.util.ArrayList;
import java.util.Random;

public class RunnableCityCreator implements Runnable
{
    public static final int CITIES_PER_THREAD = 250;
    ArrayList<CountryEntity> countries;
    int generatedCityIndex;

    public RunnableCityCreator (ArrayList<CountryEntity> countries, int generatedCityIndex)
    {
        this.countries = countries;
        this.generatedCityIndex = generatedCityIndex;
    }

    @Override
    public void run()
    {
        Random rand = new Random();

        for (int generatedCity = 0; generatedCity < CITIES_PER_THREAD; generatedCity++)
        {
            int randCountryIndex = rand.nextInt(countries.size());

            CityEntity newCity = new CityEntity( countries.get(randCountryIndex), "City_" + (generatedCity + generatedCityIndex), false, 0, 0 );

            RandomCityGenerator.addCity(newCity);
        }
    }
}
