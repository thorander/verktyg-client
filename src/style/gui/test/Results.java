package style.gui.test;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import style.gui.test.create.CreateNodes;

/**
 * Created by Sofia on 2017-05-29.
 */
public class Results {
    private BorderPane root;
    private HBox hbox;
    private Label headline;
    private ComboBox selectTest;
    private Button button;


    public Results(){
        root = CreateNodes.createBorderPane();
        hbox = new HBox(15);

        headline = CreateNodes.createHeader("Results");
        selectTest = CreateNodes.createComboBox("Select test");
        selectTest.setMaxHeight(15);
        button = CreateNodes.createButton("Show results");

        hbox.setPadding(new Insets(15,0,15,0));
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(headline,selectTest,button);

        root.setTop(hbox);
        root.setMaxHeight(600);
        root.setMaxWidth(700);
    }

    public BorderPane getResults(){
        return root;
    }
}
