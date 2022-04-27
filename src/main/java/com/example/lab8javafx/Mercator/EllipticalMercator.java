package com.example.lab8javafx.Mercator;

class EllipticalMercator extends Mercator {
    @Override
    double yAxisProjection(double input) {

        input = Math.min(Math.max(input, -89.5), 89.5);
        double earthDimensionalRateNormalized = 1.0 - Math.pow(RADIUS_MINOR / RADIUS_MAJOR, 2);

        double inputOnEarthProj = Math.sqrt(earthDimensionalRateNormalized) *
                Math.sin( Math.toRadians(input));

        inputOnEarthProj = Math.pow(((1.0 - inputOnEarthProj) / (1.0+inputOnEarthProj)),
                0.5 * Math.sqrt(earthDimensionalRateNormalized));

        double inputOnEarthProjNormalized =
                Math.tan(0.5 * ((Math.PI * 0.5) - Math.toRadians(input))) / inputOnEarthProj;

        return getHeight()-(((((-1) * RADIUS_MAJOR * Math.log(inputOnEarthProjNormalized)) + 34619289.3718562600016593933)/69238578.7437126) * getHeight());
    }

    @Override
    double xAxisProjection(double input) {
        return (((RADIUS_MAJOR * Math.toRadians(input)) + 20037508.342789244055747986)/40075016.68557849)*getWidth();
    }


    public EllipticalMercator(double width, double height) {
        super(width, height);
    }

    public static void main(String[] args) {
        Mercator mercator = new EllipticalMercator(500,500);
        System.out.println(mercator.xAxisProjection(0));
        System.out.println(mercator.yAxisProjection(-90));
    }
}
