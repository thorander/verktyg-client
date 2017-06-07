package style.gui.components;

import core.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import style.gui.GUI;

/**
 * The class which represents the Navbar
 */
public class NavigationBar {

    private static Label role,takeTest,edit,logout, create, statistics,gradeTest, register, login, result,
            copyTest,createPDF, group, shareTest;

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
        header.getChildren().addAll(create, statistics, gradeTest, register, createPDF, group, shareTest, takeTest, result, logout);

        return header;
    }
    public static HBox navStudent() {
        setupNavbarLabels();
        HBox header = new HBox();
        header.setId("navHeader");
        header.setAlignment(Pos.CENTER);
        header.getChildren().addAll(takeTest,result,logout);

        return header;
    }
    public static HBox navTeacher() {
        setupNavbarLabels();
        HBox header = new HBox();
        header.setId("navHeader");
        header.setAlignment(Pos.CENTER);
        header.getChildren().addAll(create, statistics, gradeTest, logout);

        return header;
    }

    private static void setupNavbarLabels() {
        edit = createLabel("Edit");
        create = createLabel("Create test");
        statistics = createLabel("Statistics");
        gradeTest = createLabel("Grade test");
        register = createLabel("Register");
        login = createLabel("Log in");
        logout = createLabel("Log out");
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

        group.setOnMouseClicked(e-> { //Hämtar alla användare som ska listas i group-klassen
            Main.getGUI().groupScreen();
            Main.getConnection().write("GETUSERSFORGROUP#");
        });
        statistics.setOnMouseClicked(e-> Main.getGUI().stastisticContent());
        takeTest.setOnMouseClicked(e -> Main.getGUI().takeTest());
        shareTest.setOnMouseClicked(e -> Main.getGUI().shareTestContent());

        gradeTest.setOnMouseClicked(e-> { //Hämtar alla prover som ska listas när man vill rätta något prov som studenten gjort
            Main.getGUI().correctTestContent();
            Main.getGUI().getCorrectTest().clear();
            Main.getConnection().write("GETTESTLIST#");
        });
        logout.setOnMouseClicked(e->{
            Main.getGUI().loginScreen();
            Main.getGUI().clearNavbar();
        });
        copyTest.setOnMouseClicked(e->Main.getGUI().copyTestContent());
        createPDF.setOnMouseClicked(e-> Main.getGUI().createPDFContent());
        result.setOnMouseClicked(e->{
            Main.getGUI().result();
            Main.getConnection().write("UTESTSFORRESULTPAGE#");
        });

    }

    //Gives the label an id and title
    private static Label createLabel(String title) {
        Label label = new Label(title);
        label.setId("navLabel");
        return label;
    }
}
