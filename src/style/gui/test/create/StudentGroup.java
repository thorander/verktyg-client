package style.gui.test.create;

import core.Main;
import core.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
    private GridPane grid;


    private Label groupNameLabel, headline, availableStudents, includedStudents;
    private TextField groupname;
    private Button createGroup, includeButton, excludeButton;

    private ListView<User> userView, groupView;

    public StudentGroup(){
        createGroupGrid();
    }


    public GridPane createGroupGrid() {
        grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(5);
        grid.setId("loginStyle");

        groupNameLabel = CreateNodes.createLabel2("Group name:");
        groupname = CreateNodes.createText();
        createGroup = CreateNodes.createButton("Create group");
        includeButton = new Button("->");
        excludeButton = new Button("<-");
        availableStudents = CreateNodes.createLabel("Available students");
        includedStudents = CreateNodes.createLabel("Included students");

        headline = CreateNodes.createHeader("Create Group");

        userView = new ListView<>();
        groupView = new ListView<>();

        userView.getItems().add(new User("Markus Gustafsson", 2));

        userView.setMaxWidth(200);
        groupView.setMaxWidth(200);

        createGroup.setOnAction(e -> {

            String s = "CREATEGROUP#" + groupname.getText();
            for(User u : groupView.getItems()){
                s += "#" + u.getId();
            }
            Main.getConnection().write(s);
        });

        includeButton.setOnAction(e -> {
            groupView.getItems().add(userView.getSelectionModel().getSelectedItem());
            userView.getItems().remove(userView.getSelectionModel().getSelectedItem());
        });

        excludeButton.setOnAction(e -> {
            userView.getItems().add(groupView.getSelectionModel().getSelectedItem());
            groupView.getItems().remove(groupView.getSelectionModel().getSelectedItem());
        });

        grid.add(headline,0,0);
        grid.add(groupNameLabel, 0,1);
        grid.add(groupname,1,1);
        grid.add(availableStudents, 0, 2);
        grid.add(includedStudents, 2, 2);
        grid.add(userView,0,3);
        grid.add(groupView, 2, 3);
        grid.add(includeButton, 1, 4);
        grid.add(excludeButton, 1, 5);
        grid.add(createGroup,0,6);
        GridPane.setColumnSpan(createGroup,3);
        GridPane.setColumnSpan(groupname, 2);
        GridPane.setRowSpan(userView, 3);
        GridPane.setRowSpan(groupView, 3);

        DropShadow drop = new DropShadow(50, Color.GRAY);
        grid.setEffect(drop);
        grid.setMaxWidth(500);
        grid.setMaxHeight(500);
        return grid;

    }

    public void addUser(String name, String id){
        try{
            User user = new User(name, Integer.parseInt(id));
            userView.getItems().add(user);
        } catch (Exception e){
            System.out.println("Couldn't add user");
        }
    }


}
