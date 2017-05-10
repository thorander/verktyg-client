package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import style.GUI.CreateTest;
import style.GUI.Login;
import style.GUI.Register;

/**
 * Created by Sofia on 2017-05-09.
 */
public class NavigationBar {

    public static Label role;
    private static Label edit;
    private static Label create;
    private static Label statistics;
    private static Label gradeTest;
    private static Label register;
    private static Label login;


    public static HBox navBackgroundImage() {
        Image image = new Image("Images/headerImage.jpg");
        ImageView headerBackground = new ImageView();
        headerBackground.setImage(image);
        headerBackground.setStyle("-fx-opacity: 0.2");
        HBox headerImg = new HBox();
        headerImg.getChildren().addAll(headerBackground);
        return headerImg;
    }

    public static HBox headline() {
        Label headline = new Label("Testverktyg");
        HBox headlineBox = new HBox();
        headline.setId("navHeadline");
        headlineBox.getChildren().addAll(headline);
        headlineBox.setAlignment(Pos.TOP_CENTER);
        return headlineBox;

    }


    public static HBox navAdmin() {
        setupNavbarLabels();
        HBox header = new HBox();
        header.setId("navHeader");
        header.setAlignment(Pos.CENTER);
        header.getChildren().addAll(role, edit, create, statistics, gradeTest, register, login);

        return header;
    }

    private static void setupNavbarLabels() {
        edit = createLabel("Redigera");
        create = createLabel("Skapa test");
        statistics = createLabel("Statistik");
        gradeTest = createLabel("Rätta prov");
        register = createLabel("Registrera");
        login = createLabel("Logga in");
        role = createLabel("");

        login.setOnMouseClicked(e -> {
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(Login.setup()));
            loginStage.show();
        });

        register.setOnMouseClicked(e -> {
            Stage registerStage = new Stage();
            Register r = new Register(Main.getConnection());
            registerStage.setScene(r.getScene());
            registerStage.show();
        });

        create.setOnMouseClicked(e -> {
            Stage createStage = new Stage();
            CreateTest cT = new CreateTest(Main.getConnection());
            createStage.setScene(new Scene(cT.getRoot()));
            createStage.show();
        });


    }

    //Gives the label an id and title
    private static Label createLabel(String title) {
        Label label = new Label(title);
        label.setId("navLabel");
        return label;
    }
}
