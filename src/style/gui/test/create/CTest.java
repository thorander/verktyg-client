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
import style.gui.components.DateTimePicker;
import style.gui.components.EditableLabel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Markus on 2017-05-11.
 */
public class CTest {

    private BorderPane root;
    public Button addQuestion,createButton;
    private HBox buttonBox;
    private VBox qBox;
    private CheckBox selfCorrecting, showResult;
    private final int HEIGHT = 500, WIDTH = 800;

    public BorderPane getCreateTest(){
        root = new BorderPane();
        root.setId("loginStyle");
        addQuestion = new Button("Add question");
        createButton = new Button("Create");
        createButton.setId("button");
        addQuestion.setId("button");
        selfCorrecting = new CheckBox("Self-Correcting");
        showResult = new CheckBox("show result");

        HBox testHeader = new HBox();
        EditableLabel testTitle = new EditableLabel("New test");
        testTitle.setId("headline");
        EditableLabel testDescription = new EditableLabel("Description");
        testDescription.setWrapText(true);
        testDescription.setId("description");
        DateTimePicker timePicker = new DateTimePicker();
        VBox titleBox = new VBox(testTitle, testDescription);
        Region spacingRegion = new Region();
        HBox.setHgrow(spacingRegion, Priority.ALWAYS);
        titleBox.setMaxWidth(300);
        testHeader.getChildren().addAll(titleBox, spacingRegion, timePicker, selfCorrecting,showResult);
        testHeader.setStyle("-fx-border-color: gray; -fx-border-width: 0px 0px 2px 0px; -fx-padding: 5px;");
        root.setTop(testHeader);
        root.setAlignment(testTitle, Pos.BASELINE_CENTER);
        buttonBox = new HBox();
        buttonBox.getChildren().addAll(addQuestion, createButton);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.setMargin(createButton, new Insets(15, 0, 0, 50));
        root.setBottom(buttonBox);

        qBox = new VBox();
        ScrollPane sp = new ScrollPane();
        sp.setContent(qBox);
        root.setCenter(sp);
        root.setMaxWidth(WIDTH);
        root.setMinWidth(WIDTH);
        root.setMinHeight(HEIGHT);
        root.setMaxHeight(HEIGHT);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        DropShadow drop = new DropShadow(50, Color.GRAY);
        root.setEffect(drop);
        root.setPadding(new Insets(50, 50, 50, 50));

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
        });
        return root;
    }

    public void removeQuestion(Node n){
        qBox.getChildren().remove(n);
    }

    private int getMaxPoints(){
        int sum = 0;
        for(Node n : qBox.getChildren()){
            sum += ((CQuestion)n).getPoints();
        }
        return sum;
    }

}
