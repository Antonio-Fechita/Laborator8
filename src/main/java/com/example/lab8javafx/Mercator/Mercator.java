package com.example.lab8javafx.Mercator;

abstract class Mercator {
    final static double RADIUS_MAJOR = 6378137.0;
    final static double RADIUS_MINOR = 6356752.3142;
    private double width;
    private double height;

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Mercator(double width, double height) {
        this.width = width;
        this.height = height;
    }



    abstract double yAxisProjection(double input);
    abstract double xAxisProjection(double input);
}
