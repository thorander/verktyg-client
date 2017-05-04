package sample;

import Network.Connection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = new GridPane();
        //Hejsan
        Label welcome = new Label("Welcome! Please, log in:");
        root.add(welcome, 0, 0);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        Connection c = new Connection("localhost", 4436);
        c.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
