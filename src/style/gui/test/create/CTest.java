package style.gui.test.create;

import core.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import style.gui.CreateNodes;
import style.gui.components.DateTimePicker;
import style.gui.components.EditableLabel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A class which represents a test
 * while creating a test.
 */
public class CTest {

    private BorderPane root;
    private Button addQuestion,createButton;
    private HBox buttonBox, testHeader;
    private VBox qBox, titleBox;
    private CheckBox selfCorrecting, showResult;
    private EditableLabel testTitle, testDescription;
    private ScrollPane sp;
    private DateTimePicker timePicker;
    private final int HEIGHT = 500, WIDTH = 800;

    public BorderPane getCreateTest(){
        root = CreateNodes.createBorderPane();
        addQuestion = CreateNodes.createButton("Add question");
        createButton = CreateNodes.createButton("Create");
        selfCorrecting = new CheckBox("Self-Correcting");
        showResult = new CheckBox("Show result");
        testHeader = new HBox();
        testTitle = new EditableLabel("New test");
        testDescription = new EditableLabel("Description");
        timePicker = new DateTimePicker();
        titleBox = new VBox(testTitle, testDescription);
        qBox = new VBox();
        sp = new ScrollPane();

        style();
        actions();

        sp.setContent(new VBox(testHeader, qBox));
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        root.setPadding(new Insets(50, 50, 50, 50));
        root.setMaxWidth(WIDTH);
        root.setMinWidth(WIDTH);
        root.setMinHeight(HEIGHT);
        root.setMaxHeight(HEIGHT);
        root.setAlignment(testTitle, Pos.BASELINE_CENTER);

        root.setCenter(sp);
        root.setBottom(buttonBox);

        return root;
    }

    //Group together all styling code
    private void style(){
        testTitle.setId("headline");
        testDescription.setWrapText(true);
        testDescription.setId("description");

        Region spacingRegion = new Region();
        HBox.setHgrow(spacingRegion, Priority.ALWAYS);

        titleBox.setMaxWidth(300);

        testHeader.getChildren().addAll(titleBox, spacingRegion, timePicker, new VBox(selfCorrecting,showResult));
        testHeader.setStyle("-fx-border-color: gray; -fx-border-width: 0px 0px 2px 0px; -fx-padding: 5px;");

        buttonBox = new HBox();
        buttonBox.getChildren().addAll(addQuestion, createButton);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);

        HBox.setMargin(createButton, new Insets(15, 0, 0, 50));

    }

    private void actions(){
        addQuestion.setOnAction(e -> {
            qBox.getChildren().add(new CQuestion());
            sp.setVvalue(qBox.getHeight());
        });

        createButton.setOnAction(e -> {
            String command = "CREATETEST#" + testTitle.getText()
                    + "#" + testDescription.getText()
                    + "#" + timePicker.getOpenDate()
                    + "#" + timePicker.getCloseDate()
                    + "#" + timePicker.getTime()
                    + "#" + selfCorrecting.isSelected()
                    + "#" + showResult.isSelected()
                    + "#" + getMaxPoints();
            Main.getConnection().write(command);
            qBox.getChildren().forEach(q -> {
                if(q instanceof CQuestion){
                    Main.getConnection().write(((CQuestion)q).getRepresentation());
                }
            });
            Main.getConnection().write("PERSISTTEST#");
            Main.getGUI().FrontPageScreen();
        });
    }

    public void removeQuestion(Node n){
        qBox.getChildren().remove(n);
    }

    //Loops through all questions and sums up the max points which is then returned
    private int getMaxPoints(){
        int sum = 0;
        for(Node n : qBox.getChildren()){
            sum += ((CQuestion)n).getPoints();
        }
        return sum;
    }

    public Button getCreateButton(){
        return createButton;
    }

}
