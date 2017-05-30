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

import java.util.ArrayList;

public class TTestSelect {

    private TTest test;
    private Pane root;
    private ComboBox<String> tests = new ComboBox<>();
    private ArrayList<Integer> testIds = new ArrayList<>();
    private Button takeTest = new Button("View test");
    private HBox hbox;

    public TTestSelect(){
        getTestChooser();
    }

    public void getTestChooser(){
        Main.getConnection().write("GETAVAILABLETESTS#");
        root = new Pane();
        tests.setId("combobox1");
        tests.setMinSize(150,20);
        root.setId("loginStyle");
        root.setMaxWidth(315);
        root.setMaxHeight(200);
        Label chooseTestLabel = CreateNodes.createLabel2("Pick your test:");

        tests.setOnAction(e -> {
            System.out.println(getId(tests.getSelectionModel().getSelectedIndex()));
        });

        takeTest = CreateNodes.createButton("View test");

        takeTest.setOnAction(e -> {Main.getConnection().write("FETCHTESTBYID#" + getId(tests.getSelectionModel().getSelectedIndex()));});

        hbox = new HBox(15);
        hbox.getChildren().addAll(chooseTestLabel, tests);
        hbox.setPadding(new Insets(50,0,0,15));
        DropShadow drop = new DropShadow(50, Color.GRAY);
        root.setEffect(drop);
        root.setPadding(new Insets(50, 25, 50, 30));
        takeTest.relocate(60, 120);
        root.getChildren().addAll(hbox, takeTest);

    }

    public void addOption(String test){
        tests.getItems().add(test);
    }

    public void addId(String id){
        testIds.add(Integer.parseInt(id));
    }

    public Integer getId(int index){
        return testIds.get(index);
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
