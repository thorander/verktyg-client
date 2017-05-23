package style.gui;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
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
import style.gui.test.create.StudentGroup;
import style.gui.test.take.ShareTest;
import style.gui.test.take.ShareTest;
import style.gui.test.take.TTestSelect;

public class GUI {

    private Stage stage;

    private BorderPane borderPaneBase;
    private BorderPane borderPane;
    private Scene scene;
    private HBox header;
    private HBox backgroundImage;
    private HBox headline;
    private Connection c;
    private Statistic statistic;

    private StackPane userView;
    private GridPane mainContent;

    private FrontPage fp;
    private Login login;
    private Register register;
    private TTestSelect testSelect;
    private CTest createTest;
    private ShareTest shareTest;


    public GUI(Stage primaryStage){
        stage = primaryStage;
        createGrid(stage);
    }

    public GUI(){
    }

    private void createGrid(Stage primaryStage){
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        borderPaneBase = new BorderPane();
        borderPane = new BorderPane();
        userView = new StackPane();

        backgroundImage = NavigationBar.navBackgroundImage();
        userView.getChildren().addAll( backgroundImage,borderPane);
        borderPane.toFront();
        borderPaneBase.setCenter(userView);

        headline = NavigationBar.headline();
        borderPane.setTop(headline);
        fp = new FrontPage();
        login = new Login();
        register = new Register();
        statistic = new Statistic();
        createTest = new CTest();
        shareTest = new ShareTest();



        loginScreen();



        scene = new Scene(borderPaneBase, 1000,700);
        scene.getStylesheets().add(getClass().getResource("../Stylesheet.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("BBB");
        primaryStage.show();
    }

    public void loginScreen() {
        mainContent = login.getRoot();
        mainContent.setAlignment(Pos.CENTER);
        BorderPane.setMargin(mainContent, new Insets(0, 0, 100, 0));
        setMainContent(mainContent);

    }
    public void stastisticContent(){
        mainContent = statistic.getGrid();
        mainContent.setAlignment(Pos.CENTER);
        BorderPane.setMargin(mainContent, new Insets(0, 0, 100, 0));
        setMainContent(mainContent);}

    public void shareTestContent(){
        mainContent = shareTest.getShareTest();
        mainContent.setAlignment(Pos.CENTER);
        BorderPane.setMargin(mainContent, new Insets(0, 0, 100, 0));
        setMainContent(mainContent);}



    public void registerScreen(){
        BorderPane p = (BorderPane)register.getBorder();
        mainContent.setAlignment(Pos.CENTER);
        BorderPane.setMargin(p, new Insets(0, 0, 100, 0));
        setMainContent(p);
    }
    public void FrontPageScreen(){
        mainContent = fp.getRoot();
        BorderPane.setMargin(mainContent, new Insets(0, 0, 100, 0));
        Platform.runLater(() -> setMainContent(mainContent));
    }
    public void groupScreen(){
        mainContent = StudentGroup.createGroupGrid();
        mainContent.setAlignment(Pos.CENTER);
        BorderPane.setMargin(mainContent, new Insets(0, 0, 100, 0));
        setMainContent(mainContent);
    }

    public void createTestScreen(){
        Node g = createTest.getCreateTest();
        mainContent.setAlignment(Pos.CENTER);
        BorderPane.setMargin(g, new Insets(0, 0, 100, 0));
        setMainContent(g);
    }

    public void takeTest(){
        testSelect = new TTestSelect();
        Node g = testSelect.getGraphics();
        mainContent.setAlignment(Pos.CENTER);
        BorderPane.setMargin(g, new Insets(0, 0, 100, 0));
        setMainContent(g);
    }

    public void loginAdmin(){
        setNavbar(NavigationBar.navAdmin());
    }
    public void loginStudent(){
        setNavbar(NavigationBar.navStudent());
    }
    public void loginTeacher(){
        setNavbar(NavigationBar.navTeacher());
    }

    public void setNavbar(HBox navbar){
        navbar.setPadding(new Insets(5, 5, 5, 5));
        Platform.runLater(() -> borderPaneBase.setTop(navbar));
    }

    public void setMainContent(Node content){
        Platform.runLater(() -> borderPane.setCenter(content));
    }

    public Popup createPopup(final String message) {
        final Popup popup = new Popup();
        popup.setAutoFix(true);
        popup.setAutoHide(true);
        popup.setHideOnEscape(true);
        Label label = new Label(message);
        label.setVisible(false);
        label.setOnMouseReleased(e -> popup.hide());
        label.getStyleClass().add("popup");
        popup.getContent().add(label);
        return popup;
    }

    public void showPopupMessage(final String message, String type) {
        final Popup popup = createPopup(message);
        switch(type){
            case "error":
                popup.getContent().get(0).getStyleClass().add("error");
                break;
            case "info":
                popup.getContent().get(0).getStyleClass().add("info");
                break;
            case "success":
                popup.getContent().get(0).getStyleClass().add("success");
                break;
        }

        FadeTransition popupFade = new FadeTransition(Duration.millis(500));
        popupFade.setNode(popup.getContent().get(0));
        popupFade.setFromValue(0.0);
        popupFade.setToValue(1.0);
        popupFade.setCycleCount(1);
        popupFade.setAutoReverse(true);
        popup.setOnShown(e ->  {
                popup.setX(stage.getX() + stage.getWidth()/2 - popup.getWidth()/2);
                popup.setY(stage.getY() + stage.getHeight() - popup.getHeight() - 25);
        });
        Platform.runLater(() -> popup.show(stage));
        popup.getContent().get(0).setVisible(true);
        popupFade.playFromStart();
    }

    public TTestSelect getTestSelectScreen(){
        return testSelect;
    }

    public CTest getCreateTest() {return createTest;}

}
