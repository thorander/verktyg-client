package style.gui;

import core.Main;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import network.Connection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import style.gui.test.create.CreateNodes;

import java.io.UncheckedIOException;


public class Register {

    private BorderPane border;
    private Label registerLabel;
    private Label name;
    private Label surname;
    private Label uName;
    private Label uPass;
    private TextField firstName;
    private TextField lastName;
    private TextField userName;
    private ComboBox role;
    private PasswordField password;
    private Button register;

    public Register(){
        setUp();
    }

    public BorderPane setUp() {

        border = new BorderPane();

        // Labels
        registerLabel = CreateNodes.createLabel("Register");
        registerLabel.setId("headline");
        name = CreateNodes.createLabel("Firstname");
        surname = CreateNodes.createLabel("Lastname");
        uName = CreateNodes.createLabel("Username");
        uPass = CreateNodes.createLabel("Password");

        // Textfields
        firstName = CreateNodes.createText();
        firstName.setPromptText("Firstname");
        lastName = CreateNodes.createText();
        lastName.setPromptText("Lastname");
        userName = CreateNodes.createText();
        userName.setPromptText("Email");
        password = new PasswordField();
        password.setId("input");

        password.setPromptText("Password");

        // Combobox
        role = new ComboBox();
        role.setId("combobox1");

        role.getItems().addAll(
                "admin",
                "teacher",
                "student"
        );
        role.getSelectionModel().selectFirst();


        // Buttons
        register = new Button("Sign up");
        register.setId("button");
        register.setMinWidth(200);

        HBox hbox = new HBox();
        role.setMinWidth(100);
        HBox.setMargin(role, new Insets(0, 75, 0, 0));
        hbox.getChildren().addAll(role,register);
        hbox.setPadding(new Insets(15,0,15,0));
        register.setAlignment(Pos.BOTTOM_LEFT);

        register.setOnAction(e -> Main.getConnection().write("REGISTER#" + firstName.getText() + "#" + lastName.getText() + "#" + userName.getText() + "#" + password.getText() + "#" + role.getValue()));

        // Layout
        VBox labels = new VBox();
        labels.getChildren().addAll(registerLabel, name, firstName, surname, lastName, uName, userName, uPass, password, hbox);
        labels.setPadding(new Insets(15, 15, 15, 15));
        VBox.setMargin(role, new Insets(15, 10, 10, 0));
        register.setAlignment(Pos.CENTER);

        border.setCenter(labels);

        border.setId("loginStyle");
        border.setMaxWidth(400);
        border.setMaxHeight(300);
        DropShadow drop = new DropShadow(50, Color.GRAY);
        border.setEffect(drop);

        return border;
    }

    public Node getBorder(){
        return border;
    }
}
