package sample;

import Network.Connection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import style.GUI.GUI;
import style.GUI.Login;
import style.GUI.Register;

import sun.rmi.runtime.Log;


import java.awt.*;

//Brilliant Budding Blossoms
//BBB
public class Main {

    public static String loggedInPerson = "";
    public static String loggedInRole = "admin";
    public static String loggedInId = "";

    public static Connection c;

    //Method change xxx of label when hovered
    private void searchActions(Label label){
            label.hoverProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue) {

            } else {

            }
        });
    }

    public static void main(String[] args){
        c = new Connection("localhost", 4436);
        c.start();

        GUI mainWindow = new GUI(args);
    }


    public static Connection getConnection(){
        return c;
    }

}
