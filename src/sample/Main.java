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
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;


import java.awt.*;

public class Main extends Application {
    private GridPane grid;
    private Scene scene;
    private Button sendMessage;
    private Button sendMessage1;
    private Button sendMessage2;
    private Button sendMessage3;
    private Button sendMessage4;
    private Button tmp1;
    private ImageView image;


    @Override
    public void start(Stage primaryStage){
        createGrid(primaryStage);

    }

    private void createGrid(Stage primaryStage){

        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));

        image =buttonIcon("images/2.png");
        sendMessage = createButton(image);
        searchActions(sendMessage,"Dela");

        sendMessage1 = createButton(image);
        sendMessage2 = createButton(image);
        sendMessage3 = createButton(image);
        sendMessage4 = createButton(image);



        HBox header = new HBox();
        header.getChildren().addAll(sendMessage, sendMessage1, sendMessage2, sendMessage3, sendMessage4);

        GridPane.setConstraints( header, 20, 0);


        grid.getChildren().addAll( header);
        scene = new Scene(grid, 900,600);
       // scene.getStylesheets().add("../style/Stylesheet.css");
        scene.getStylesheets().add(getClass().getResource("../style/Stylesheet.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("BBB");
        primaryStage.show();


    }
    //Gives the button an id and image
    private Button createButton(ImageView img){
        Button button = new Button("", img);
        button.setId("navButton");
        return button;
    }

    //Gives the ImageView an id
    private ImageView buttonIcon(String path){
        ImageView imgV = new ImageView(path);
        imgV.setId("navImg");
        return imgV;
    }

    //Method add title on button if hovered
    private void searchActions(Button button, String title){
        sendMessage.hoverProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue) {
                button.setText(title);
                button.setContentDisplay(ContentDisplay.CENTER);
            } else {
                button.setText("");
            }
        });

    }
    public static void main(String[] args) {
        launch(args);
    }
}
