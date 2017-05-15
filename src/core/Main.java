package core;

import network.Connection;
import javafx.scene.control.Label;
import style.gui.GUI;
import style.gui.Register;

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
