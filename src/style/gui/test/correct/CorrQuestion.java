package style.gui.test.correct;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import style.gui.test.create.CreateNodes;

/**
 * Created by Markus on 2017-05-28.
 */
public class CorrQuestion extends GridPane {

    private int id, maxPoints;
    private VBox answerBox;
    private TextField givenPoints;
    private Label maxPointsLabel, questionLabel, addCommentLabel;
    private TextArea commentArea;

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

        GridPane.setMargin(commentArea, new Insets(10, 0, 0, 0));

        add(questionLabel, 0, 0);
        add(givenPoints, 1, 0);
        add(maxPointsLabel, 2, 0);
        add(answerBox, 0, 1);
        add(addCommentLabel, 0, 2);

        addCommentLabel.setOnMouseClicked(e -> {
            getChildren().remove(addCommentLabel);
            add(commentArea, 0, 2);
        });

        questionLabel.setMinWidth(300);
        questionLabel.setMaxWidth(300);
        questionLabel.setWrapText(true);
    }

    public void addAnswer(CorrAnswer answer){
        answerBox.getChildren().add(answer);
    }
}
