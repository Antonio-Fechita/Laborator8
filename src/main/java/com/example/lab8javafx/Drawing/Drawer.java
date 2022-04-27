package com.example.lab8javafx.Drawing;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Drawer {


    public void DrawStage(Stage stage){
        Canvas canvas = new Canvas(500,500);
        Pane layout = new Pane();
        layout.getChildren().add(canvas);
        Scene scene = new Scene(layout,500,500);
        stage.setScene(scene);
    }
}
