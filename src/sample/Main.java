package sample;

import Network.Connection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import style.GUI.Login;
import style.GUI.Register;

//Brilliant Budding Blossoms

public class Main extends Application {
    private GridPane grid;
    private Scene scene;
    private HBox header;
    private HBox backgroundImage;
    private HBox headline;
    private static Connection c;

    public static String loggedInPerson = "";
    public static String loggedInRole = "admin";
    public static String loggedInId = "";

    @Override
    public void start(Stage primaryStage){

        c = new Connection("localhost", 4436);
        c.start();

        primaryStage.setOnCloseRequest(e -> {
           System.exit(0);
        });

        //Kommentera bort den del du vill testa
        testMainScreen(primaryStage);

/*        testLoginScreen(primaryStage);*/

        /*testRegisterScreen(primaryStage);*/


    }

    public void testMainScreen(Stage primaryStage){
        createGrid(primaryStage);
    }

    public void testLoginScreen(Stage primaryStage){

        Login l = new Login(c);
        Scene scene = new Scene(l.getRoot(), 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            c.write("end");
            System.exit(0);

        });

    }

    public void testRegisterScreen(Stage primaryStage){
        Register r = new Register(c);
        Scene scene = r.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void createGrid(Stage primaryStage){
       /* Register reg = new Register();
        reg.setUp();*/
        grid = new GridPane();



      backgroundImage = NavigationBar.navBackgroundImage();
        GridPane.setConstraints( backgroundImage, 0, 2);
        GridPane.isFillWidth(backgroundImage);

       headline = NavigationBar.headline();
        GridPane.setConstraints( headline, 0, 1);


        header = NavigationBar.navAdmin();
        header.setPadding(new Insets(5, 5, 5, 5));
       GridPane.setConstraints( header, 0, 0);





        grid.getChildren().addAll( backgroundImage, header, headline);
        scene = new Scene(grid, 900,600);
        //scene = reg.getScene();
        scene.getStylesheets().add(getClass().getResource("../style/Stylesheet.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("BBB");
        primaryStage.show();


    }

    //Method change xxx of label when hovered
    private void searchActions(Label label){
            label.hoverProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue) {

            } else {

            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static Connection getConnection(){
        return c;
    }

}
