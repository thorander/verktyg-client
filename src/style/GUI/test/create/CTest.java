package style.GUI.test.create;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import style.GUI.components.EditableLabel;

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

        EditableLabel testTitle = new EditableLabel("New test");
        testTitle.setId("navHeadline");
        root.setTop(testTitle);
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
            CQuestion q = new CQuestion();
            qBox.getChildren().add(q.getQuestion());
            sp.setVvalue(qBox.getHeight());
        });
        return root;
    }

    public static void removeQuestion(Node n){
        qBox.getChildren().remove(n);
    }

}
