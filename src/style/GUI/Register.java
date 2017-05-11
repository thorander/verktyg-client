package style.GUI;

import Network.Connection;
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
        registerLabel.setId("navHeadline");
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

        role.getItems().addAll(
                "admin",
                "teacher",
                "student"
        );
        role.setPromptText("Role");

        // Buttons
        register = new Button("Sign up");
        register.setId("button");

        register.setOnAction(e -> {
            c.write("REGISTER#" + firstName.getText() + "#" + lastName.getText() + "#" + userName.getText() + "#" + password.getText() + "#" + role.getValue());
        });

        // Layout
        VBox labels = new VBox();
        labels.getChildren().addAll(registerLabel, name, firstName, surname, lastName, uName, userName, uPass, password, role, register);
        labels.setPadding(new Insets(15, 15, 15, 15));
        labels.setMargin(role, new Insets(10, 0, 10, 0));
        register.setAlignment(Pos.CENTER);

        border.setCenter(labels);

        border.setId("loginStyle");
        border.setMaxWidth(300);
        border.setMaxHeight(300);
        DropShadow drop = new DropShadow(50, Color.GRAY);
        border.setEffect(drop);

        return border;
    }
    private static Label createLabel(String title){
        Label label = new Label(title);
        label.setId("label");
        label.setPadding(new Insets(10, 5, 2, 5));
        return label;
    }

    //Gives the label an id and title
    private static TextField createText(){
        TextField textField = new TextField();
        textField.setId("input");
        return textField;
    }
}
