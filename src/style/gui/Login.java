package style.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import core.Main;

public class Login {

    private static GridPane grid;
    private static Label welcome;
    private static Label userName;
    private static Label password;
    private static TextField user;
    private static PasswordField pass;
    private static Button login;

    public static GridPane setup(){
        grid = new GridPane();
        welcome = createLabel("Welcome");
        userName = createLabel("Username");
        password= createLabel("Password");
        user = createText();
        user.setPromptText("Username");
        pass = new PasswordField();
        pass.setId("input");
        pass.setPromptText("Password");
        login = new Button("Login");
        login.setId("button");

        login.setOnAction(e -> {
           String uName = user.getText();
           String uPass = pass.getText();
            Main.getConnection().write("LOGIN#" + uName + "#" + uPass);
        });

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(5, 10, 5, 10));
        grid.add(userName,0,2);
        grid.add(user,0,3);
        grid.add(password,0,4);
        grid.add(pass,0,5);
        grid.add(login,0,6);
        grid.setId("loginStyle");
        grid.setMaxWidth(300);
        grid.setMaxHeight(300);
        DropShadow drop = new DropShadow(50, Color.GRAY);
        grid.setEffect(drop);
        return grid;
    }
    //Gives the label an id and title
    private static Label createLabel(String title){
        Label label = new Label(title);
        label.setId("label");
        return label;
    }

    //Gives the label an id and title
    private static TextField createText(){
        TextField textField = new TextField();
        textField.setId("input");
        return textField;
    }

    public GridPane getRoot(){
        return grid;
    }
}