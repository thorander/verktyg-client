package style.gui.test.take;

import core.Main;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import style.gui.test.create.CreateNodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sofia on 2017-05-23.
 */
public class ShareTest {
    private GridPane grid;
    private ComboBox test, group;
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

        test = CreateNodes.createComboBox("Choose test");
        test.setMinWidth(290);
        test.setMaxWidth(310);

        group = CreateNodes.createComboBox("Choose a recipient");
        group.setMinWidth(290);
        group.setMaxWidth(310);


        select = new ToggleGroup();
        groups.setToggleGroup(select);
        students.setToggleGroup(select);


        groups.setOnAction(e -> {
            group.setPromptText("Select group");
            studentOrGroup = true;
        });
        students.setOnAction(e -> {
            group.setPromptText("Select student");
            Main.getConnection().write("GETSTUDENTS#");
        });

        grid.add(label, 1, 1);
        grid.add(radioButtonLabel, 1, 2);
        grid.add(groups, 1, 3);
        grid.add(students, 2, 3);
        grid.add(test, 1, 4);
        GridPane.setColumnSpan(test, 2);
        grid.add(group, 1, 5);
        GridPane.setColumnSpan(group, 2);
        grid.add(button, 1, 6);
        GridPane.setColumnSpan(button, 2);

        grid.setMaxWidth(400);
        grid.setMaxHeight(400);

    }
    public void addInfo(String testData){
        List<String> myList = new ArrayList<String>(Arrays.asList(testData.split("@")));
        for(int i = 0; i < myList.size(); i++){
         test.getItems().add(myList.get(i));
         i++;
        }}

        public void addStudents(String testData){
            List<String> myList = new ArrayList<String>(Arrays.asList(testData.split("@")));
            System.out.println("" + myList.size());
            for (int i = 0; i < myList.size(); i++) {
                group.getItems().add(myList.get(i));
                System.out.println("" + myList.get(i));
                i++;
            System.out.println(testData);
        }
    }
    public GridPane getShareTest(){
        return grid;
    }
}
