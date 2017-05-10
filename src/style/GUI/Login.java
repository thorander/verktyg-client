package style.GUI;

import Network.Connection;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login {

    private static GridPane grid;
    private static Label welcome;
    private static Label userName;
    private static Label password;
    private static TextField user;
    private static PasswordField pass;
    private static Button login;

    private static Connection connection;

    public Login(Connection connection){
        this.connection = connection;
        setup();
    }

    public static GridPane setup(){
        grid = new GridPane();
        welcome = createLabel("Welcome");
        userName = createLabel("Username");
        password= createLabel("Password");
        user = new TextField();
        user.setPromptText("Username");
        pass = new PasswordField();
        pass.setPromptText("Password");
        login = new Button("Login");
        login.setId("buttontest");

        login.setOnAction(e -> {
           String uName = user.getText();
           String uPass = pass.getText();
           connection.write("LOGIN#" + uName + "#" + uPass);
        });

        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(5, 10, 5, 10));
        grid.add(userName,0,2);
        grid.add(user,0,3);
        grid.add(password,0,4);
        grid.add(pass,0,5);
        grid.add(login,0,6);
        grid.setId("loginStyle");
        grid.setMaxWidth(500);
        grid.setMaxHeight(300);
        return grid;
    }
    //Gives the label an id and title
    private static Label createLabel(String title){
        Label label = new Label(title);
        label.setId("label");
        return label;
    }

    //Gives the label an id and title
    private static TextField createText(String title){
        TextField textField = new TextField(title);
        textField.setId("textField");
        return textField;
    }

    public GridPane getRoot(){
        return grid;
    }
}