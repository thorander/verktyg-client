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

public class TTestSelect {

    private TTest test;
    private Pane root;
    private ComboBox<String> tests = new ComboBox<>();
    private Button takeTest = new Button("View test");

    public TTestSelect(){
        getTestChooser();
    }

    public void getTestChooser(){
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
    }

    public void addOption(String test){
        tests.getItems().add(test);
    }

    public void clearOptions(){
        tests.getItems().clear();
    }

    public void selectFirst(){
        tests.getSelectionModel().selectFirst();
    }

    public void setTest(TTest t){
        test = t;
    }

    public TTest getTest(){
        return test;
    }

    public Node getGraphics(){
        return root;
    }
}
