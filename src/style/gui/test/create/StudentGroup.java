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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sofia on 2017-05-16.
 */
public  class StudentGroup {
    private static GridPane grid;

    private static ObservableList names;
    private static ObservableList data;
    private static ObservableList groups;


    private static Label groupNameLabel, headline;
    private static TextField groupname;
    private static Button createGroup;

    private static String gname;
    private static String uname;
    private static String group;
    private static String user;
    private static ArrayList<String> checkUsers;

    public StudentGroup(String gn){
        this.user = gn;
        System.out.println(user);
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

        headline = CreateNodes.createHeader("Create Group");

        names = FXCollections.observableArrayList();
        data = FXCollections.observableArrayList();
        groups = FXCollections.observableArrayList();



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

        createGroup.setOnAction(e -> {

            try {
                gname = groupname.getText();

                checkUsers = new ArrayList<String>(listView.getItems());

                //.equals("[, , , , , , , , , , , , , , , , , , , , , , , , , , , , , ]")

                for(String s : checkUsers){
                    if(s != ""){
                        Main.getConnection().write("CREATEGROUP#" + gname + "#" + listView.getItems());
                        break;
                    } else
                        Main.getConnection().write("CREATEGROUP#" + gname + "#" + "null");
                    break;
                }



            } catch(Exception ex) {
                ex.printStackTrace();
            };

        });

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
        this.group = gn;
        System.out.println(group);
    }

    public List getGroupName(){
        return data;
    }

    public static void addUsers(String name){
        names.add(name);
    }

    public static void addGroups(String name){
        groups.add(name);
    }


}
