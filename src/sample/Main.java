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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import style.GUI.Login;
import style.GUI.Register;

import sun.rmi.runtime.Log;


import java.awt.*;

//Brilliant Budding Blossoms
//BBB
public class Main extends Application {
    private GridPane grid;
    private Scene scene;
    private HBox header;
    private HBox backgroundImage;
    private HBox headline;
    private static Connection c;

    private StackPane userView;
    private GridPane test;

    public static String loggedInPerson = "";
    public static String loggedInRole = "admin";
    public static String loggedInId = "";

    @Override
    public void start(Stage primaryStage){

        c = new Connection("localhost", 4436);
        c.start();

        primaryStage.setOnCloseRequest(e -> {
           System.exit(0);
        });

        createGrid(primaryStage);
    }

    private void createGrid(Stage primaryStage){
       /* Register reg = new Register();
        reg.setUp();*/

        grid = new GridPane();
        userView = new StackPane();

        test = Login.setup();
      backgroundImage = NavigationBar.navBackgroundImage();
      userView.getChildren().addAll(backgroundImage, test);
      test.toFront();
        GridPane.setConstraints( userView, 0, 1);
        GridPane.isFillWidth(userView);




       headline = NavigationBar.headline();
        GridPane.setConstraints( headline, 0, 1);


        header = NavigationBar.navAdmin();
        header.setPadding(new Insets(5, 5, 5, 5));
       GridPane.setConstraints( header, 0, 0);



        grid.getChildren().addAll( userView, header, headline);
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

    public static Connection getConnection(){
        return c;
    }

}
