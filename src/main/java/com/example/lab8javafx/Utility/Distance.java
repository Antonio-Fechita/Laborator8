package com.example.lab8javafx.Utility;

import com.example.lab8javafx.Entities.CityEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Distance {

    public static double distance(CityEntity firstCity, CityEntity secondCity) {

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        double lon1 = Math.toRadians(firstCity.getLongitude());
        double lon2 = Math.toRadians(secondCity.getLongitude());
        double lat1 = Math.toRadians(firstCity.getLatitude());
        double lat2 = Math.toRadians(secondCity.getLatitude());

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        double result = c * r;
        return BigDecimal.valueOf(result).setScale(3, RoundingMode.HALF_UP).doubleValue();
    }
}
