package style.gui.test.create;

import core.Main;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import network.Connection;
import sun.util.resources.cldr.en.CalendarData_en_GY;

import java.util.List;

/**
 * Created by Sofia on 2017-05-16.
 */
public  class StudentGroup {
    private static GridPane grid;
    private static ObservableList names;
    private static ObservableList data;
    private static List users;
    private static List savedUssers;

    private static Label groupNameLabel, headline;
    private static TextField groupname;
    private static Button createGroup;

    private static String gname;

    private static String group;

    public StudentGroup(String gn){
        //this.setGroupName(gn);
        this.group = gn;
        createGroupGrid();
    }


    public static GridPane createGroupGrid() {
        grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(5);
        grid.setId("loginStyle");

        groupNameLabel = CreateNodes.createLabel2("Group name:");
        groupname = CreateNodes.createText();
        createGroup = CreateNodes.createButton("Create group");

        createGroup.setOnAction(e -> {
            gname = groupname.getText();
            //String members = "ADDUSER#" + names;
            //Main.getConnection().write("ADDUSER#" + users);
            Main.getConnection().write("CREATEGROUP#" + gname/* + "#" + members */);

        });

        headline = CreateNodes.createHeader("Create Group");

        names = FXCollections.observableArrayList("ADDGROUP#" + group);
        data = FXCollections.observableArrayList();



        final ListView listView = new ListView(names);
        listView.setPrefSize(200, 250);
        listView.setEditable(true);



        /*names.addAll(
                "Adam", "Alex", "Alfred", "Albert",
                "Brenda", "Connie", "Derek", "Donny",
                "Lynne", "Myrtle", "Rose", "Rudolph",
                "Tony", "Trudy", "Williams", "Zach"
        );*/

        for (int i = 0; i < 30; i++) {
            data.add("");
        }

        listView.setItems(data);
        listView.setCellFactory(ComboBoxListCell.forListView(names));

        grid.add(headline,0,0);
        grid.add(groupNameLabel, 0,1);
        grid.add(groupname,1,1);
        grid.add(listView,0,2);
        grid.add(createGroup,0,3);
        GridPane.setColumnSpan(listView,4);
        GridPane.setColumnSpan(createGroup,3);

        DropShadow drop = new DropShadow(50, Color.GRAY);
        grid.setEffect(drop);
        grid.setMaxWidth(500);
        grid.setMaxHeight(400);
        return grid;

    }

    public void setGroupName(String gn) {
        //data.add(gn);
        this.group = gn;
        System.out.println(group);
    }

    public List getGroupName(){
        return data;
    }


}
