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

            line = reader.readLine();
            int nullCounter = 0;
            while ( (line = reader.readLine()) != null )
            {

                String[] lineBreakdown = line.split(",");

                String countryCode = lineBreakdown[4];

                if(countryCode.equals("NULL")){
                    countryCode = countryCode + nullCounter;
                    nullCounter++;
                }
                                                                                             //To fix later
                CountryEntity country = new CountryEntity( countryCode , lineBreakdown[0].substring(0,Math.min(29, lineBreakdown[0].length())),new ContinentEntity(lineBreakdown[5]));

                double latitude = Double.parseDouble(lineBreakdown[2]);

                double longitude = Double.parseDouble(lineBreakdown[3]);
                                                    //to fix later
                CityDAO.createCity( lineBreakdown[1].substring(0,Math.min(29, lineBreakdown[1].length())), true, latitude, longitude, country );
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
