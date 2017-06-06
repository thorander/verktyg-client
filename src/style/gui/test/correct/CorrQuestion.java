package style.gui.test.correct;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import style.gui.test.create.CreateNodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Markus on 2017-05-28.
 */
public class CorrQuestion extends GridPane {

    private int id, maxPoints;
    private VBox answerBox;
    private TextField givenPoints;
    private Label maxPointsLabel, questionLabel, addCommentLabel;
    private TextArea commentArea;

    private static String write;
    private static String commentText = "";
    private static List<String> list;
    private static String newCommentText;

    public CorrQuestion(int id, String questionText, int maxPoints){
        this.id = id;
        this.maxPoints = maxPoints;
        answerBox = new VBox();
        givenPoints = CreateNodes.createNumberCorrectingField(maxPoints);
        maxPointsLabel = CreateNodes.createHeader("/" + maxPoints);
        questionLabel = CreateNodes.createLabelTest(questionText);
        addCommentLabel = new Label("+");
        addCommentLabel.setId("icon");
        commentArea = new TextArea();
        commentArea.setMaxWidth(300);
        commentArea.setMaxHeight(100);
        commentArea.setWrapText(true);

        System.out.println(id);
        System.out.println(questionText+"");
        System.out.println(maxPoints);

        GridPane.setMargin(commentArea, new Insets(10, 0, 0, 0));

        add(questionLabel, 0, 0);
        add(givenPoints, 1, 0);
        add(maxPointsLabel, 2, 0);
        add(answerBox, 0, 1);
        add(addCommentLabel, 0, 2);

        commentArea.textProperty().addListener((obs,old,niu)->{
            commentText = "#"+niu;
        });

        list = new ArrayList<>();

        addCommentLabel.setOnMouseClicked(e -> {
            getChildren().remove(addCommentLabel);
            add(commentArea, 0, 2);
            list.add(commentText);
        });

        questionLabel.setMinWidth(300);
        questionLabel.setMaxWidth(300);
        questionLabel.setWrapText(true);
    }

    public void addAnswer(CorrAnswer answer){
        Platform.runLater(() -> answerBox.getChildren().add(answer));
    }

    public static String sendId(String s) {
        System.out.println(s);
        return "nothing";
    }

    public static String sendCorrect(String s) {
        list.add(commentText);

        StringBuilder builder = new StringBuilder();
        for (String value : list) {
            builder.append(value);
        }
        newCommentText = builder.toString();
        String send = s+newCommentText;
        write = CreateNodes.sendCorrect(send);

        return "nothing";
    }

}
