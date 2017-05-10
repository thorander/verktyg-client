package style.GUI;

import Network.Connection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import sample.NavigationBar;

/**
 * Created by Sofia on 2017-05-10.
 */
public class GUI {

    private static BorderPane borderPane;
    private static Scene scene;
    private static HBox header;
    private static HBox backgroundImage;
    private static HBox headline;
    private static Connection c;

    private static StackPane userView;
    private static GridPane mainContent;


    public static BorderPane offline(){
       BorderPane borderPaneBase = new BorderPane();
        borderPane = new BorderPane();
        userView = new StackPane();

        mainContent = Login.setup();
        mainContent.setAlignment(Pos.CENTER);
        borderPane.setCenter(mainContent);
        borderPane.setMargin(mainContent, new Insets(0, 0, 100, 0));

        headline = NavigationBar.headline();
        borderPane.setTop(headline);

        backgroundImage = NavigationBar.navBackgroundImage();
        borderPaneBase.setTop(backgroundImage);

        userView.getChildren().addAll(backgroundImage, borderPane);
        borderPane.toFront();
        borderPaneBase.setCenter(userView);

        return borderPaneBase;

    }
    public static BorderPane admin(){
        BorderPane borderPaneBase = new BorderPane();
        borderPane = new BorderPane();
        userView = new StackPane();

        headline = NavigationBar.headline();
        borderPane.setTop(headline);

        header = NavigationBar.navAdmin();
        headline = NavigationBar.headline();
        borderPaneBase.setTop(header);

        header.setPadding(new Insets(5, 5, 5, 5));
        borderPaneBase.setTop(header);

        backgroundImage = NavigationBar.navBackgroundImage();
        userView.getChildren().addAll( backgroundImage,borderPane);
        borderPane.toFront();
        borderPaneBase.setCenter(userView);

        return borderPaneBase;
    }
}
