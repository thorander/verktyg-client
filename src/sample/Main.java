package sample;

import Network.Connection;
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
import style.GUI.Login;


import java.awt.*;

//Brilliant Budding Blossoms

public class Main extends Application {
    private GridPane grid;
    private Scene scene;
    HBox header;
    HBox backgroundImage;
    HBox headline;
    private Connection c;

    @Override
    public void start(Stage primaryStage){
/*        createGrid(primaryStage);*/
        c = new Connection("localhost", 4436);
        c.start();
        Login l = new Login(c);
        Scene scene = new Scene(l.getRoot(), 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            c.write("end");
            System.exit(0);
        });

    }

    private void createGrid(Stage primaryStage){

        grid = new GridPane();



      backgroundImage = NavigationBar.navBackgroundImage();
        GridPane.setConstraints( backgroundImage, 0, 1);
        GridPane.isFillWidth(backgroundImage);

        headline = NavigationBar.headline();
        GridPane.setConstraints( backgroundImage, 0, 2);


        header = NavigationBar.navAdmin();
        header.setPadding(new Insets(5, 5, 5, 5));
       GridPane.setConstraints( header, 0, 0);





        grid.getChildren().addAll( backgroundImage, header);
        scene = new Scene(grid, 900,600);
        scene.getStylesheets().add(getClass().getResource("../style/Stylesheet.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("BBB");
        primaryStage.show();


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
