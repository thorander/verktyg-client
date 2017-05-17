package style.gui.test.create;

import core.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import style.gui.components.EditableLabel;

/**
 * Created by Markus on 2017-05-11.
 */
public class CTest {

    private static BorderPane root;
    private static Button addQuestion;
    private static Button createButton;
    private static HBox buttonBox;
    private static VBox qBox;
    private static final int HEIGHT = 500, WIDTH = 500;

    public static BorderPane getCreateTest(){
        root = new BorderPane();
        root.setId("loginStyle");
        addQuestion = new Button("Add question");
        createButton = new Button("Create");
        createButton.setId("button");
        addQuestion.setId("button");

        HBox testHeader = new HBox();
        EditableLabel testTitle = new EditableLabel("New test");
        testTitle.setId("navHeadline");
        testHeader.getChildren().add(testTitle);
        testHeader.setStyle("-fx-border-color: gray; -fx-border-width: 0px 0px 2px 0px");
        root.setTop(testHeader);
        root.setAlignment(testTitle, Pos.BASELINE_CENTER);
        buttonBox = new HBox();
        buttonBox.getChildren().addAll(addQuestion, createButton);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.setMargin(createButton, new Insets(15, 0, 0, 50));
        root.setBottom(buttonBox);

        qBox = new VBox();
        ScrollPane sp = new ScrollPane();
        sp.setId("scrollpane");
        sp.setContent(qBox);
        root.setCenter(sp);
        root.setMaxWidth(WIDTH);
        root.setMinWidth(WIDTH);
        root.setMinHeight(HEIGHT);
        root.setMaxHeight(HEIGHT);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        DropShadow drop = new DropShadow(50, Color.GRAY);
        root.setEffect(drop);
        root.setPadding(new Insets(50, 25, 50, 25));

        addQuestion.setOnAction(e -> {
            qBox.getChildren().add(new CQuestion());
            sp.setVvalue(qBox.getHeight());
        });

        createButton.setOnAction(e -> {
            String command = "CREATETEST#" + testTitle.getText();
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

    public static void removeQuestion(Node n){
        qBox.getChildren().remove(n);
    }

}
