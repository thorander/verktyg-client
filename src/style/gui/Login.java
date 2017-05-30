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
import style.gui.test.create.CreateNodes;

public class Login {

    private GridPane grid;
    private Label welcome;
    private Label logIn;
    private Label userName;
    private Label password;
    private TextField user;
    private PasswordField pass;
    private Button login;
    private Label signUp;

    public Login(){
        setup();
    }

    public GridPane setup(){
        grid = new GridPane();
        logIn = CreateNodes.createHeader("Log in");
        welcome = CreateNodes.createLabel("Welcome");
        userName = CreateNodes.createLabel("Username:");
        password= CreateNodes.createLabel("Password:");
        signUp = CreateNodes.createLabel("Register");
        user = CreateNodes.createText();
        user.setPromptText("Username");
        pass = new PasswordField();
        pass.setId("input");
        pass.setPromptText("Password");
        login = new Button("Log in");
        login.setMinWidth(200);
        login.setId("button");

        signUp.setOnMouseClicked(e-> Main.getGUI().registerScreen());
        login.setOnAction(e -> {
           String uName = user.getText();
           String uPass = pass.getText();
           Main.getConnection().write("LOGIN#" + uName + "#" + uPass);
        });

        user.setText("mackan@bbb.se");
        pass.setText("1234");

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 5, 10));
        grid.add(logIn,0,0);
        grid.add(userName,0,2);
        grid.add(user,0,3);
        grid.add(password,0,4);
        grid.add(pass,0,5);
        grid.add(login,0,6);
        grid.add(signUp,0,7);
        grid.setId("loginStyle");
        grid.setMaxWidth(300);
        grid.setMaxHeight(300);
        DropShadow drop = new DropShadow(50, Color.GRAY);
        grid.setEffect(drop);
        return grid;
    }

    public GridPane getRoot(){
        return grid;
    }
}