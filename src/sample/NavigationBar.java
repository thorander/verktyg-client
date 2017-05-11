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
import style.GUI.GUI;
import style.GUI.Login;
import style.GUI.Register;

/**
 * Created by Sofia on 2017-05-09.
 */
public class NavigationBar {

    private static Label role,takeTest,edit, create, statistics,gradeTest, register, login, result, copyTest,createPDF, group, shareTest;

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
        header.getChildren().addAll(role, edit, create, statistics, gradeTest, register, login, copyTest,group, shareTest,createPDF);

        return header;
    }
    public static HBox navStudent() {
        setupNavbarLabels();
        HBox header = new HBox();
        header.setId("navHeader");
        header.setAlignment(Pos.CENTER);
        header.getChildren().addAll(takeTest,result);

        return header;
    }
    public static HBox navTeacher() {
        setupNavbarLabels();
        HBox header = new HBox();
        header.setId("navHeader");
        header.setAlignment(Pos.CENTER);
        header.getChildren().addAll(edit,create,statistics,gradeTest,copyTest);

        return header;
    }

    private static void setupNavbarLabels() {
        edit = createLabel("Edit");
        create = createLabel("Create test");
        statistics = createLabel("statistics");
        gradeTest = createLabel("Grade test");
        register = createLabel("Rregister");
        login = createLabel("Log in");
        takeTest= createLabel("Take test");
        result = createLabel("Result");
        copyTest= createLabel("Copy test");
        createPDF = createLabel("CreatePDF");
        group = createLabel("Group");
        shareTest = createLabel("Share Test");
        role = createLabel("");

        login.setOnMouseClicked(e -> {
            GUI.loginScreen();
        });

        register.setOnMouseClicked(e -> {
            GUI.registerScreen();
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
