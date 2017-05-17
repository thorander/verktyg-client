package style.gui.test.create;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

/**
 * Created by Sofia on 2017-05-16.
 */
public  class StudentGroup {
    private static GridPane grid;
    private static ObservableList names,data;
    private static Label groupNameLabel;
    private static TextField groupname;
    private static Button createGroup;


    public static GridPane createGroupGrid() {
        grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(5);
        grid.setId("loginStyle");

        groupNameLabel = CreateNodes.createLabel("Group name:");
        groupname = CreateNodes.createText();
        createGroup = CreateNodes.createButton("Create group");


        names = FXCollections.observableArrayList();
        data = FXCollections.observableArrayList();

        final ListView listView = new ListView(data);
        listView.setPrefSize(200, 250);
        listView.setEditable(true);

        names.addAll(
                "Adam", "Alex", "Alfred", "Albert",
                "Brenda", "Connie", "Derek", "Donny",
                "Lynne", "Myrtle", "Rose", "Rudolph",
                "Tony", "Trudy", "Williams", "Zach"
        );
        for (int i = 0; i < 30; i++) {
            data.add("");
        }

        listView.setItems(data);
        listView.setCellFactory(ComboBoxListCell.forListView(names));

        grid.add(groupNameLabel, 0,0);
        grid.add(groupname,1,0);
        grid.add(listView,0,1);
        grid.add(createGroup,0,2);
        GridPane.setColumnSpan(listView,3);
        GridPane.setColumnSpan(createGroup,2);

        DropShadow drop = new DropShadow(50, Color.GRAY);
        grid.setEffect(drop);
        grid.setMaxWidth(500);
        grid.setMaxHeight(400);
        return grid;
    }

}
