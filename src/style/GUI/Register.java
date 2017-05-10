package style.GUI;

import Network.Connection;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private ComboBox role;
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
        password.setId("passwordField");

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
        register.setId("sign");

        register.setOnAction(e -> {
            c.write("REGISTER#" + firstName.getText() + "#" + lastName.getText() + "#" + userName.getText() + "#" + password.getText() + "#" + role.getValue());
        });

        // Layout
        VBox labels = new VBox();
        labels.getChildren().addAll(name, firstName, surname, lastName, uName, userName, uPass, password, role, register);
        labels.setPadding(new Insets(15, 15, 15, 15));

       /* VBox text = new VBox();
        text.getChildren().addAll( surname, uName, uPass);*/

        scene = new Scene(border, 350, 300);
        border.setCenter(labels);


    }
    private static Label createLabel(String title){
        Label label = new Label(title);
        label.setId("label");
        return label;
    }

    //Gives the label an id and title
    private static TextField createText(){
        TextField textField = new TextField();
        textField.setId("textField");
        return textField;
    }

    public Scene getScene() {
        return scene;
    }
}
