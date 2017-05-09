package style.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by phili on 2017-05-09.
 */
public class login extends Application {


    Label welcome;
    Label userName;
    Label password;
    TextField user;
    TextField pass;
    Button login;



    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane grid = new GridPane();

        welcome = new Label("Welcome");
        userName = new Label("Username");
        password = new Label("Password");
        user = new TextField();
        user.setPromptText("Username");
        pass = new TextField();
        pass.setPromptText("Password");
        login = new Button("Login");

        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(welcome,0,0);
        grid.add(userName,0,2);
        grid.add(user,1,2);
        grid.add(password,0,4);
        grid.add(pass,1,4);
        grid.add(login,1,6);

        Scene scene = new Scene(grid, 350, 300);
        primaryStage.setScene(scene);

        primaryStage.setScene(scene);
        primaryStage.show();

}

    public static void main(String[] args) {
        launch(args);
    }
}