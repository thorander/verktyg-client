package style.gui.test.take;

import core.Main;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

/**
 * Created by Markus on 2017-05-17.
 */
public class TTestSelect {

    private static BorderPane root;
    private static ComboBox<String> tests = new ComboBox<>();

    public static Node getTestChooser(){
        Main.getConnection().write("GETAVAILABLETESTS#");
        root = new BorderPane();
        root.setId("loginStyle");
        root.setCenter(tests);
        root.setMaxWidth(300);
        root.setMaxHeight(200);
        Label chooseTestLabel = new Label("Pick your test");
        DropShadow drop = new DropShadow(50, Color.GRAY);
        root.setTop(chooseTestLabel);
        root.setEffect(drop);
        root.setPadding(new Insets(50, 25, 50, 25));
        return root;
    }

    public static void addOption(String test){
        tests.getItems().add(test);
    }

    public static void selectFirst(){
        tests.getSelectionModel().selectFirst();
    }
}
