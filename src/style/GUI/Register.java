package style.GUI;

import Network.Connection;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;




public class Register {

    private static GridPane grid;
    private static Scene scene;
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
    }

    public static GridPane setUp() {

        grid = new GridPane();

        // Labels
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
        labels.getChildren().addAll(name, firstName, surname, lastName, uName, userName, uPass, password, role, register);
        labels.setPadding(new Insets(15, 15, 15, 15));
        grid.add(firstName,1,0);
       grid.add(name,1,1);
        grid.add(lastName,1,2);
        grid.add(surname,1,1);

        return grid;


    }
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

    public Scene getScene() {
        return scene;
    }
}
