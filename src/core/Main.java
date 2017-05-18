package core;

import javafx.application.Application;
import javafx.stage.Stage;
import network.Connection;
import javafx.scene.control.Label;
import style.gui.GUI;
import style.gui.Register;

//Brilliant Budding Blossoms
//BBB
public class Main extends Application{


    public static String loggedInPerson;
    public static String loggedInRole;
    public static String loggedInId;

    public static Connection c;
    public static GUI window;

    public static void main(String[] args){
        c = new Connection("localhost", 4436);
        c.start();
        launch(args);
    }


    public static Connection getConnection(){
        return c;
    }

    public static GUI getGUI(){return window;}

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = new GUI(primaryStage);
    }
}
