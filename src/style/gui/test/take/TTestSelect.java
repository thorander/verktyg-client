package style.gui.test.take;

import core.Main;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import style.gui.test.create.CreateNodes;

/**
 * Created by Markus on 2017-05-17.
 */
public class TTestSelect {

    private static TTest test;
    private static Pane root;
    private static ComboBox<String> tests = new ComboBox<>();
    private static Button takeTest = new Button("View test");

    public static Node getTestChooser(){
        Main.getConnection().write("GETAVAILABLETESTS#");
        root = new Pane();
        root.setId("loginStyle");
        root.setMaxWidth(300);
        root.setMaxHeight(200);
        Label chooseTestLabel = CreateNodes.createLabel("Pick your test:");
        DropShadow drop = new DropShadow(50, Color.GRAY);
        root.setEffect(drop);
        root.setPadding(new Insets(50, 25, 50, 25));
        takeTest.relocate(100, 150);
        chooseTestLabel.relocate(50, 50);
        tests.relocate(50, 100);
        root.getChildren().addAll(chooseTestLabel, tests, takeTest);
        return root;
    }

    public static void addOption(String test){
        tests.getItems().add(test);
    }

    public static void clearOptions(){
        tests.getItems().clear();
    }

    public static void selectFirst(){
        tests.getSelectionModel().selectFirst();
    }

    public static void setTest(TTest t){
        test = t;
    }

    public static TTest getTest(){
        return test;
    }
}
