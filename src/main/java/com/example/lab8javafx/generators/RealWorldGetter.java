package com.example.lab8javafx.generators;

import com.example.lab8javafx.DAO.CityDAO;
import com.example.lab8javafx.Entities.ContinentEntity;
import com.example.lab8javafx.Entities.CountryEntity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class RealWorldGetter
{
    public static void importRealCities()
    {
        String csvPath = "src/main/java/com/example/lab8javafx/generators/csv_capitals.csv";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvPath));

            String line;

            while ( (line = reader.readLine()) != null )
            {
                String[] lineBreakdown = line.split(",");

                CountryEntity country = new CountryEntity( lineBreakdown[4] , lineBreakdown[0],new ContinentEntity(lineBreakdown[5]));

                double latitude = Double.parseDouble(lineBreakdown[2]);

                double longitude = Double.parseDouble(lineBreakdown[3]);

                CityDAO.createCity( lineBreakdown[1], true, latitude, longitude, country );
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
