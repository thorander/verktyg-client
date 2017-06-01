package style.gui.test.take;

import core.Main;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import style.gui.components.CustomComboBox;
import style.gui.test.create.CreateNodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sofia on 2017-05-23.
 */
public class ShareTest {
    private GridPane grid;
    private CustomComboBox test, studentBox, group;
    private Button button;
    private RadioButton groups, students;
    private final ToggleGroup select;
    private ArrayList listGroup, listStudent;
    private Label radioButtonLabel, label;
    private Boolean studentOrGroup;




    public ShareTest() {

        grid = CreateNodes.createGrid();
        grid.setHgap(5);
        grid.setVgap(15);


        groups = new RadioButton("Group");
        students = new RadioButton("Student");
        button = CreateNodes.createButton("Share test");
        button.setMinWidth(290);
        button.setMaxWidth(310);

        radioButtonLabel = CreateNodes.createLabel2("Choose a recipient");
        label = CreateNodes.createHeader("Share test");

        test = CreateNodes.createCustomComboBox("Choose test");
        test.setMinWidth(290);
        test.setMaxWidth(310);

        studentBox = CreateNodes.createCustomComboBox("Choose a recipient");
        studentBox.setMinWidth(290);
        studentBox.setMaxWidth(310);

        group = CreateNodes.createCustomComboBox("Choose group");
        group.setMinWidth(290);
        group.setMaxWidth(310);


        select = new ToggleGroup();
        groups.setToggleGroup(select);
        students.setToggleGroup(select);


        groups.setOnAction(e -> {
            grid.getChildren().remove(studentBox);
            grid.add(group, 1, 5);
            group.clear();
            Main.getConnection().write("GETGROUPSFORSHARE#");
            studentOrGroup = true;
        });

        students.setOnAction(e -> {
            grid.getChildren().removeAll(group, studentBox);
            grid.add(studentBox, 1, 5);
            studentBox.clear();
            studentBox.setPromptText("Select student");
            Main.getConnection().write("GETSTUDENTS#");
            studentOrGroup = false;
        });

        grid.add(label, 1, 1);
        grid.add(radioButtonLabel, 1, 2);
        grid.add(groups, 1, 3);
        grid.add(students, 2, 3);
        grid.add(test, 1, 4);
        GridPane.setColumnSpan(test, 2);
        grid.add(studentBox, 1, 5);
        GridPane.setColumnSpan(studentBox, 2);
        GridPane.setColumnSpan(group, 2);
        grid.add(button, 1, 6);
        GridPane.setColumnSpan(button, 2);

        grid.setMaxWidth(400);
        grid.setMaxHeight(400);

        button.setOnAction(e -> {
            if(studentOrGroup){
                Main.getConnection().write("SHARETOGROUP#" + test.getSelectedId() + "#" + group.getSelectedId());
            } else {
               Main.getConnection().write("SHARETOSTUDENT#" + test.getSelectedId() + "#" + studentBox.getSelectedId());
            }
        });

    }
    public void addInfo(String testData){
        List<String> myList = new ArrayList<String>(Arrays.asList(testData.split("@")));
        if(myList.size() > 1){
            for(int i = 0; i < myList.size(); i++){
             test.addItem(myList.get(i++), Integer.parseInt(myList.get(i)));
            }
        }
    }

        public void addStudents(String testData){
            List<String> myList = new ArrayList<String>(Arrays.asList(testData.split("@")));
            for (int i = 0; i < myList.size(); i++) {
                studentBox.addItem(myList.get(i++), Integer.parseInt(myList.get(i)));
            System.out.println(testData);
        }
    }
    public GridPane getShareTest(){
        return grid;
    }

    public void addGroups(String testData) {
        List<String> myList = new ArrayList<String>(Arrays.asList(testData.split("#")));
        System.out.println("" + myList.size());
        for (int i = 1; i < myList.size(); i++) {
            group.addItem(myList.get(i++), Integer.parseInt(myList.get(i)));
            System.out.println(testData);
        }
    }
}
