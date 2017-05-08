package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;


import java.awt.*;

public class Main extends Application {
    private GridPane grid;
    private Scene scene;
    private Label edit;
    private Label create;
    private Label statistics;
    private Label gradeTest;
    private Label register;





    @Override
    public void start(Stage primaryStage){
        createGrid(primaryStage);

    }

    private void createGrid(Stage primaryStage){

        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));

        edit =createLabel("Redigera");
        create=createLabel("Skapa test");
        statistics=createLabel("Statistik");
        gradeTest=createLabel("Rätta prov");
        register=createLabel("registrera");



        HBox header = new HBox();
        header.getChildren().addAll(edit,create,statistics,gradeTest,register);

        GridPane.setConstraints( header, 20, 0);


        grid.getChildren().addAll( header);
        scene = new Scene(grid, 900,600);
        scene.getStylesheets().add(getClass().getResource("../style/Stylesheet.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("BBB");
        primaryStage.show();


    }
    //Gives the label an id and title
        private Label createLabel(String title){
        Label label = new Label(title);
        label.setId("navLabel");
        return label;
    }

    //Method change xxx of label when hovered
    private void searchActions(Label label){
            label.hoverProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue) {

            } else {

            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
