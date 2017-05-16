package style.gui;

import network.Connection;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import style.gui.components.NavigationBar;
import style.gui.test.create.CTest;
import style.gui.test.create.Statistic;

/**
 * Created by Sofia on 2017-05-10.
 */
public class GUI extends Application {

    private static BorderPane borderPaneBase;
    private static BorderPane borderPane;
    private static Scene scene;
    private static HBox header;
    private static HBox backgroundImage;
    private static HBox headline;
    private static Connection c;
    private static Statistic statistic;

    private static StackPane userView;
    private static GridPane mainContent;


    public GUI(String[] args){
        launch(args);
    }

    public GUI(){

    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setOnCloseRequest(e -> {
            System.exit(0);
        });
        createGrid(primaryStage);
    }

    private void createGrid(Stage primaryStage){
        borderPaneBase = new BorderPane();
        borderPane = new BorderPane();
        userView = new StackPane();

        backgroundImage = NavigationBar.navBackgroundImage();
        userView.getChildren().addAll( backgroundImage,borderPane);
        borderPane.toFront();
        borderPaneBase.setCenter(userView);

        headline = NavigationBar.headline();
        borderPane.setTop(headline);

        loginAdmin();
        stastisticContent();

        scene = new Scene(borderPaneBase, 900,600);
        scene.getStylesheets().add(getClass().getResource("../Stylesheet.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("BBB");
        primaryStage.show();
    }

    public static void loginScreen() {
        mainContent = Login.setup();
        mainContent.setAlignment(Pos.CENTER);
        borderPane.setMargin(mainContent, new Insets(0, 0, 100, 0));
        setMainContent(mainContent);
    }
    public static void stastisticContent(){
        mainContent = statistic.getGrid();
        mainContent.setAlignment(Pos.CENTER);
        borderPane.setMargin(mainContent, new Insets(0, 0, 100, 0));
        setMainContent(mainContent);}



    public static void registerScreen(){
        BorderPane p = Register.setUp();
        mainContent.setAlignment(Pos.CENTER);
        borderPane.setMargin(p, new Insets(0, 0, 100, 0));
        setMainContent(p);
    }

    public static void createTestScreen(){
        Node g = CTest.getCreateTest();
        mainContent.setAlignment(Pos.CENTER);
        borderPane.setMargin(g, new Insets(0, 0, 100, 0));
        setMainContent(g);
    }

    public static void loginAdmin(){
        setNavbar(NavigationBar.navAdmin());
    }

    public static void loginTeacher(){
        setNavbar(NavigationBar.navTeacher());
    }

    public static void loginStudent(){
        setNavbar(NavigationBar.navStudent());
    }

    public static void setNavbar(HBox navbar){
        navbar.setPadding(new Insets(5, 5, 5, 5));
        borderPaneBase.setTop(navbar);
    }

    public static void setMainContent(Node content){
        borderPane.setCenter(content);
    }

}
