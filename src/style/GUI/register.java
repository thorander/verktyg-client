package style.GUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Created by phili on 2017-05-09.
 */


public class register {
    BorderPane border;
    Scene scene;
    Label name;
    Label surname;
    Label uName;
    Label uPass;
    TextField firstName;
    TextField lastName;
    TextField userName;
    TextField password;
    Button register;

    public void setUp() {

        border = new BorderPane();

        // Labels
        name = new Label("Firstname");
        surname = new Label("Lastname");
        uName = new Label("Username");
        uPass = new Label("Password");

        // Textfields
        firstName = new TextField();
        firstName.setPromptText("Firstname");
        lastName = new TextField();
        lastName.setPromptText("Lastname");
        userName = new TextField();
        userName.setPromptText("Username");
        password = new TextField();
        password.setPromptText("Password");

        // Buttons
        register = new Button("Sign up");

        // Layout
        VBox labels = new VBox();
        labels.getChildren().addAll(name, firstName, surname, lastName, uName, userName, uPass, password, register);
        labels.setPadding(new Insets(15, 15, 15, 15));

       /* VBox text = new VBox();
        text.getChildren().addAll( surname, uName, uPass);*/

        scene = new Scene(border, 350, 300);
        border.setCenter(labels);


    }

    public Scene getScene() {
        return scene;
    }
}
