package com.example.lab8javafx.Drawing;

import com.example.lab8javafx.DAO.CityDAO;
import com.example.lab8javafx.Entities.CityEntity;
import com.example.lab8javafx.Mercator.Projection;
import com.example.lab8javafx.Utility.Distance;
import com.example.lab8javafx.Utility.Pair;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Drawer extends Application {

    private List<CityEntity> cities;
    private Map<Circle, Pair<CityEntity, Text>> circleCityMap = new HashMap<>();
    private int numberOfSelectedCircles = 0;
    private Circle firstSelectedCircle;
    private Circle secondSelectedCircle;
    private Line distanceLine = new Line();
    private Text distanceText = new Text();
    private Pane layout = new Pane();

    public void setCities(List<CityEntity> cities) {
        this.cities = cities;
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        setCities(new CityDAO().getAll());

        double height = 993;
        double width = 1280;
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Mercator-projection.jpg/1280px-Mercator-projection.jpg"));
        imageView.setOnMouseClicked(mouseEvent -> onMapClicked());
        layout.getChildren().add(imageView);
        layout.getChildren().add(distanceLine);
        layout.getChildren().add(distanceText);
        drawCircles(width, height);


        Scene scene = new Scene(layout, width, height);
        stage.setTitle("Cities map");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void drawCircles(double width, double height) {
        Projection projection = new Projection(width, height);

        for (CityEntity city : cities) {
            System.out.println(city.getName());

            projection.mapToPixelValues(city.getLatitude(), city.getLongitude());

            double xProjection = projection.getXProjection();
            double yProjection = projection.getYProjection();

            System.out.println("x: " + xProjection);
            System.out.println("y: " + yProjection);

            Circle circle = new Circle();
            circle.setCenterX(xProjection);
            circle.setCenterY(yProjection);
            circle.setFill(Color.RED);
            circle.setRadius(7);
            circle.setOnMouseEntered(mouseEvent -> onCircleEntered(circle));
            circle.setOnMouseExited(mouseEvent -> onCircleExited(circle));
            circle.setOnMouseClicked(mouseEvent -> onCircleClicked(circle));
            getCityLabelText(city, circle);

            layout.getChildren().add(circle);
        }
    }

    private void getCityLabelText(CityEntity city, Circle circle) {
        Text label = new Text(city.getName() + "\nlatitude: " + city.getLatitude() + "\n longitude: " + city.getLongitude());
        label.setFont(Font.font(null, FontWeight.BOLD, 15));
        label.setFill(Color.WHITE);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setX(circle.getCenterX() - label.getLayoutBounds().getWidth() / 2);
        label.setY(circle.getCenterY() - label.getLayoutBounds().getHeight());
        label.setVisible(false);
        circleCityMap.put(circle, new Pair<>(city, label));
        layout.getChildren().add(label);
    }

    private void onCircleEntered(Circle circle) {
        Text label = circleCityMap.get(circle).getSecond();
        label.setVisible(true);
        label.toFront();

        if (!circle.equals(firstSelectedCircle) && !circle.equals(secondSelectedCircle)) {
            circle.setFill(Color.GREEN);
            circle.setRadius(10);
        }
    }

    private void onCircleExited(Circle circle) {
        Text label = circleCityMap.get(circle).getSecond();
        label.setVisible(false);

        if (!circle.equals(firstSelectedCircle) && !circle.equals(secondSelectedCircle)) {
            circle.setFill(Color.RED);
            circle.setRadius(7);
        }
    }

    private void onCircleClicked(Circle circle) {
        if (numberOfSelectedCircles == 0) {
            firstSelectedCircle = circle;
            drawSelectedCircle(circle);
            numberOfSelectedCircles++;
        } else if (numberOfSelectedCircles == 1) {
            secondSelectedCircle = circle;
            drawSelectedCircle(circle);
            numberOfSelectedCircles++;
            drawDistanceLine();

        } else if (numberOfSelectedCircles == 2) {
            drawUnselectedCircle(firstSelectedCircle);
            drawUnselectedCircle(secondSelectedCircle);
            firstSelectedCircle = circle;
            drawSelectedCircle(circle);
            numberOfSelectedCircles = 1;
            secondSelectedCircle=null;
            distanceLine.setVisible(false);
            distanceText.setVisible(false);
        }
    }

    private void drawDistanceLine(){
        distanceLine.setStartX(firstSelectedCircle.getCenterX());
        distanceLine.setStartY(firstSelectedCircle.getCenterY());

        distanceLine.setEndX(secondSelectedCircle.getCenterX());
        distanceLine.setEndY(secondSelectedCircle.getCenterY());

        distanceLine.setStrokeWidth(5);
        distanceLine.setStroke(Color.CYAN);
        distanceLine.setVisible(true);
        distanceLine.toFront();
        firstSelectedCircle.toFront();
        secondSelectedCircle.toFront();

        CityEntity firstCity = circleCityMap.get(firstSelectedCircle).getFirst();
        CityEntity secondCity = circleCityMap.get(secondSelectedCircle).getFirst();
        distanceText.setText(Distance.distance(firstCity, secondCity) + " KM");
        distanceText.setX((distanceLine.getStartX() + distanceLine.getEndX())/2 + 15);
        distanceText.setY((distanceLine.getStartY() + distanceLine.getEndY())/2 + 15);
        distanceText.setFont(Font.font(null, FontWeight.BOLD, 25));
        distanceText.setFill(Color.AQUAMARINE);
        distanceText.setVisible(true);
        distanceText.toFront();
    }

    private void drawSelectedCircle(Circle circle) {
        circle.setRadius(10);
        circle.setFill(Color.YELLOW);
    }

    private void drawUnselectedCircle(Circle circle) {
        circle.setRadius(7);
        circle.setFill(Color.RED);
    }

    private void onMapClicked(){
        if(firstSelectedCircle!=null)
        drawUnselectedCircle(firstSelectedCircle);
        if(secondSelectedCircle!=null)
        drawUnselectedCircle(secondSelectedCircle);
        numberOfSelectedCircles=0;

        firstSelectedCircle=null;
        secondSelectedCircle=null;
        distanceLine.setVisible(false);
        distanceText.setVisible(false);
    }


    public static void main(String[] args) {
        launch();
    }
}