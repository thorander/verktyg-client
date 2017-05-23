package style.gui.components;

import core.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import style.gui.GUI;

public class NavigationBar {

    private static Label role,takeTest,edit, create, statistics,gradeTest, register, login, result, copyTest,createPDF, group, shareTest;

    public static HBox navBackgroundImage() {
        Image image = new Image("images/headerImage.jpg");
        ImageView headerBackground = new ImageView();
        headerBackground.setFitWidth(1000);
        headerBackground.setFitHeight(700);
        headerBackground.setImage(image);
        headerBackground.setStyle("-fx-opacity: 0.3;");

        HBox headerImg = new HBox();
        headerImg.getChildren().addAll(headerBackground);
        return headerImg;
    }

    public static HBox headline() {
        Label headline = new Label("Digital Test Tool");
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
        header.getChildren().addAll(role, edit, create, statistics, gradeTest, register, login, copyTest,group, shareTest,createPDF, takeTest);

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

        login.setOnMouseClicked(e -> Main.getGUI().loginScreen());
        register.setOnMouseClicked(e -> Main.getGUI().registerScreen());
        create.setOnMouseClicked(e -> Main.getGUI().createTestScreen());
        group.setOnMouseClicked(e-> Main.getGUI().groupScreen());
        statistics.setOnMouseClicked(e-> Main.getGUI().stastisticContent());
        takeTest.setOnMouseClicked(e -> Main.getGUI().takeTest());
        shareTest.setOnMouseClicked(e -> Main.getGUI().shareTestContent());

    }

    //Gives the label an id and title
    private static Label createLabel(String title) {
        Label label = new Label(title);
        label.setId("navLabel");
        return label;
    }
}
