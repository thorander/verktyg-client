package style.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import style.gui.test.create.CreateNodes;

/**
 * Created by Sofia on 2017-05-26.
 */
public class CopyTest {
    private GridPane grid;
    private ComboBox selectTest;
    private Label copyTest;
    private Button button;

    public CopyTest(){

        grid = CreateNodes.createGrid();
        grid.setHgap(20);
        grid.setVgap(20);


        copyTest = CreateNodes.createHeader("Copy Test");
        selectTest = CreateNodes.createComboBox("Select test");
        button = CreateNodes.createButton("Copy");

        grid.add(copyTest,1,1);
        grid.add(selectTest,1,2);
        grid.add(button,1,3);
        grid.setAlignment(Pos.CENTER);
        grid.setMaxWidth(350);
        grid.setMaxHeight(250);
    }

    public GridPane getCopyTest(){
        return grid;
    }

}
