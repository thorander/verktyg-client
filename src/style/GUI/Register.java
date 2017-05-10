package style.GUI;

import Network.Connection;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;




public class Register {
    private BorderPane border;
    private Scene scene;
    private Label name;
    private Label surname;
    private Label uName;
    private Label uPass;
    private TextField firstName;
    private TextField lastName;
    private TextField userName;
    private PasswordField password;
    private Button register;
    private Connection c;

    public Register(Connection c){
        this.c = c;
        setUp();
    }

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
        password = new PasswordField();

        password.setPromptText("Password");

        // Buttons
        register = new Button("Sign up");

        register.setOnAction(e -> {
            c.write("REGISTER#" + firstName.getText() + "#" + lastName.getText() + "#" + userName.getText() + "#" + password.getText());
        });

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
