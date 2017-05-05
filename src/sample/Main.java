package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;


import java.awt.*;

public class Main extends Application {
    private GridPane grid;
    private Scene scene;
    private Button sendMessage;
    private ImageView image;


    @Override
    public void start(Stage primaryStage){
        createGrid(primaryStage);
        searchActions();
    }

    private void createGrid(Stage primaryStage){

        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        image =new ImageView("images/2.png");
        sendMessage = new Button("", image);
        sendMessage.setContentDisplay(ContentDisplay.BOTTOM);
        sendMessage.setStyle("-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color;");

        HBox header = new HBox(8);
        header.getChildren().addAll(sendMessage);


        GridPane.setConstraints( header, 1, 0);
        grid.getChildren().addAll( header);
        scene = new Scene(grid, 900,600);
        scene.getStylesheets().add("style/Stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("BBB");
        primaryStage.show();

    }
    private void searchActions(){
        sendMessage.hoverProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue) {
                image.setStyle("-fx-opacity: 0.5;-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color;");
                sendMessage.setText("Mejl");
                sendMessage.setContentDisplay(ContentDisplay.CENTER);
            } else {
                image.setStyle("-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color;");
                sendMessage.setText("");
            }
        });

    }
    public static void main(String[] args) {
        launch(args);
    }
}
