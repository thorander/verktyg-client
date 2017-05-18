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
import javafx.scene.layout.HBox;
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
    private static Button takeTest;// = new Button("View test");
    private static HBox hbox;

    public static Node getTestChooser(){
        Main.getConnection().write("GETAVAILABLETESTS#");
        root = new Pane();
        tests.setId("combobox1");
        tests.setMinSize(150,20);
        root.setId("loginStyle");
        root.setMaxWidth(315);
        root.setMaxHeight(200);
        Label chooseTestLabel = CreateNodes.createLabel2("Pick your test:");

        takeTest = CreateNodes.createButton("View test");

        hbox = new HBox(15);
        hbox.getChildren().addAll(chooseTestLabel, tests);
        hbox.setPadding(new Insets(50,0,0,15));
        DropShadow drop = new DropShadow(50, Color.GRAY);
        root.setEffect(drop);
        root.setPadding(new Insets(50, 25, 50, 30));
        takeTest.relocate(60, 120);
        root.getChildren().addAll(hbox, takeTest);

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
