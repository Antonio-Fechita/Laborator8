package com.example.lab8javafx.Mercator;

public class Projection {

    private double width;
    private double height;
    private double XProjection;
    private double YProjection;

    public Projection(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void mapToPixelValues(double latitude, double longitude) {
        int FE = 180; // false easting
        double radius = getWidth() / (2 * Math.PI);

        double latRad = Math.toRadians(latitude);
        double lonRad = Math.toRadians(longitude + FE);

        XProjection = lonRad * radius;

        double yFromEquator = radius * Math.log(Math.tan(Math.PI / 4 + latRad / 2));
        YProjection = getHeight() / 2 - yFromEquator;

    }

    public double getXProjection() {
        return XProjection;
    }

    public double getYProjection() {
        return YProjection;
    }
}
