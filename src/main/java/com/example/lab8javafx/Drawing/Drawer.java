package com.example.lab8javafx.Drawing;

import com.example.lab8javafx.DAO.CityDAO;
import com.example.lab8javafx.Entities.CityEntity;
import com.example.lab8javafx.Mercator.Projection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Drawer extends Application {

    private List<CityEntity> cities;

    public void setCities(List<CityEntity> cities) {
        this.cities = cities;
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        setCities(new CityDAO().getAll());

        Pane layout = new Pane();
        double height = 993;
        double width = 1280;
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Mercator-projection.jpg/1280px-Mercator-projection.jpg"));
        layout.getChildren().add(imageView);
        drawCircles(layout,width,height);


        Scene scene = new Scene(layout, width, height);
        stage.setTitle("Cities map");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void drawCircles(Pane layout, double width, double height){
        Projection projection = new Projection(width,height);

        for(CityEntity city : cities){
            System.out.println(city.getName());

            projection.mapToPixelValues(city.getLatitude(),city.getLongitude());

            double xProjection = projection.getXProjection();
            double yProjection = projection.getYProjection();

            System.out.println("x: " + xProjection);
            System.out.println("y: " + yProjection);

            Circle circle = new Circle();
            circle.setCenterX(xProjection);
            circle.setCenterY(yProjection);
            circle.setFill(Color.RED);
            circle.setRadius(7);

            layout.getChildren().add(circle);
        }
    }


    public static void main(String[] args) {
        launch();
    }
}