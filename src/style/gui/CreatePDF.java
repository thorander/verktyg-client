package style.gui;

import core.Main;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import style.gui.test.create.CreateNodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sofia on 2017-05-26.
 */
public class CreatePDF {
    private GridPane grid;
    private ComboBox student, test;
    private Button button;
    private Label headline;
    private String selectedTest, selectedTestId;
    private List<String> testList, userList;

    public CreatePDF() {
        grid = CreateNodes.createGrid();
        grid.setHgap(15);
        grid.setVgap(15);

        headline = CreateNodes.createHeader("Create PDF");
        test = CreateNodes.createComboBox("Select test");
        test.setMaxWidth(350);
        student = CreateNodes.createComboBox("Select student");
        button = CreateNodes.createButton("Create");

        grid.add(headline, 1, 1);
        grid.add(test, 1, 2);
        grid.add(student, 1, 3);
        grid.add(button, 1, 4);

        grid.setMaxHeight(300);
        grid.setMaxWidth(400);

        test.setOnAction(e->{
            selectedTest = test.getSelectionModel().getSelectedItem().toString();

            for(int i = 0; i < testList.size(); i++){
             if(testList.get(i).equals(selectedTest)){
                 i++;
                 selectedTestId = testList.get(i);
                 Main.getConnection().write("GETUSERSFORPDF#" + selectedTestId);
                 break;
             }
            }
        });

    }
    public void getUTest(String testData){
        testList = new ArrayList<String>(Arrays.asList(testData.split("@")));
        for(int i = 0; i < testList.size(); i++){
            test.getItems().add(testList.get(i));
            i++;
        }}

    public GridPane getCreatePDF() {
        return grid;
    }

    public void addUser(String persons){
        userList = new ArrayList<String>(Arrays.asList(persons.split("@")));
        for(int i = 0; i < userList.size(); i++){
            student.getItems().add(userList.get(i));
            i++;
        }
    }
}
