package com.example.lab8javafx.Mercator;

public class SphericalMercator extends Mercator {
    public SphericalMercator(double width, double height) {
        super(width, height);
    }

    @Override
    double xAxisProjection(double input) {
        return Math.toRadians(input) * RADIUS_MAJOR;
    }

    @Override
    double yAxisProjection(double input) {
        return Math.log(Math.tan(Math.PI / 4 + Math.toRadians(input) / 2)) * RADIUS_MAJOR;
    }
}
