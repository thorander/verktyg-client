package style.gui;

import core.Main;
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


public class Register {

    private static BorderPane border;
    private static Label registerLabel;
    private static Label name;
    private static Label surname;
    private static Label uName;
    private static Label uPass;
    private static TextField firstName;
    private static TextField lastName;
    private static TextField userName;
    private static ComboBox role;
    private static PasswordField password;
    private static Button register;
    private static Connection c;

    public Register(Connection c){
        this.c = c;
        setUp();
    }

    public static BorderPane setUp() {

        border = new BorderPane();

        // Labels
        registerLabel = createLabel("Register");
        registerLabel.setId("headline");
        name = createLabel("Firstname");
        surname = createLabel("Lastname");
        uName = createLabel("Username");
        uPass = createLabel("Password");

        // Textfields
        firstName = createText();
        firstName.setPromptText("Firstname");
        lastName = createText();
        lastName.setPromptText("Lastname");
        userName = createText();
        userName.setPromptText("Username");
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
        hbox.setMargin(role, new Insets(0, 75, 0, 0));
        hbox.getChildren().addAll(role,register);
        hbox.setPadding(new Insets(15,0,15,0));
        register.setAlignment(Pos.BOTTOM_LEFT);

        register.setOnAction(e -> {

                Main.getConnection().write("REGISTER#" + firstName.getText() + "#" + lastName.getText() + "#" + userName.getText() + "#" + password.getText() + "#" + role.getValue());

        });

        // Layout
        VBox labels = new VBox();
        labels.getChildren().addAll(registerLabel, name, firstName, surname, lastName, uName, userName, uPass, password, hbox);
        labels.setPadding(new Insets(15, 15, 15, 15));
        labels.setMargin(role, new Insets(15, 10, 10, 0));
        register.setAlignment(Pos.CENTER);

        border.setCenter(labels);

        border.setId("loginStyle");
        border.setMaxWidth(400);
        border.setMaxHeight(300);
        DropShadow drop = new DropShadow(50, Color.GRAY);
        border.setEffect(drop);

        return border;
    }

    private static Label createLabel(String title) {
        Label label = new Label(title);
        label.setId("label");
        label.setPadding(new Insets(10, 5, 2, 5));
        return label;
    }

    //Gives the label an id and title
    private static TextField createText() {
        TextField textField = new TextField();
        textField.setId("input");
        return textField;
    }
}
