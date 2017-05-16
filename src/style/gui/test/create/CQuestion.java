package style.gui.test.create;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import style.gui.icons.RemoveIcon;
import style.gui.components.EditableLabel;

import java.util.ArrayList;

/**
 * Created by Markus on 2017-05-11.
 */
public class CQuestion extends HBox {

    private GridPane question;
    private VBox answerList;
    private HBox questionBox;
    private HBox shortQuestion;
    EditableLabel titleLabel;
    ComboBox chooseType;

    public CQuestion(){
        question = new GridPane();
        questionBox = new HBox();
        shortQuestion = new HBox();
        shortQuestion.setId("questionBox");
        Label addAnswer = new Label("+");
        addAnswer.setId("icon");
        Label shorten = new Label("-");
        Label expand = new Label("+");
        titleLabel = new EditableLabel("Enter your question-title here...");
        RemoveIcon remove = new RemoveIcon();
        question.setMargin(remove, new Insets(0, 5, 0, 5));
        titleLabel.setMaxWidth(350);
        titleLabel.setWrapText(true);
        Label shortQTitle = new Label(titleLabel.getText());
        shortQTitle.setMaxWidth(350);
        shortQuestion.setAlignment(Pos.BOTTOM_CENTER);
        TextField titleField = new TextField();
        titleField.setMinWidth(350);
        chooseType = new ComboBox();
        shortQuestion.setMargin(expand, new Insets(0, 0, 0, 50));
        question.setMargin(shorten, new Insets(0, 0, 0, 5));
        question.setMargin(chooseType, new Insets(0, 0, 15, 0));
        titleLabel.setId("headline");
        shorten.setId("icon");
        shorten.setMaxSize(50, 50);
        expand.setId("icon");
        expand.setMaxSize(50, 50);
        chooseType.getItems().addAll("One choice", "Multiple choice", "Order", "Open question");
        chooseType.setPromptText("(Choose question type)");

        answerList = new VBox();

        question.add(titleLabel, 0, 0);
        question.add(shorten, 2, 0);
        question.add(remove, 1, 0);
        question.add(chooseType, 0, 1);
        question.add(answerList, 0, 2);
        question.add(addAnswer, 0, 3);

        shortQTitle.setId("headline");

        shortQuestion.getChildren().addAll(shortQTitle, expand);

        addAnswer.setOnMouseClicked(e -> {
            answerList.getChildren().add(new CAnswer(this));
            answerList.requestLayout();
        });
        shorten.setOnMouseClicked(e->{
            questionBox.getChildren().remove(question);
            shortQTitle.setText(titleLabel.getText());
            questionBox.getChildren().add(shortQuestion);
        });
        expand.setOnMouseClicked(e->{
            questionBox.getChildren().remove(shortQuestion);
            questionBox.getChildren().add(question);
        });

        remove.setOnMouseClicked(e->{
            CTest.removeQuestion(this);
        });
        questionBox.getChildren().add(question);
        this.getChildren().add(questionBox);
    }

    public void removeAnswer(HBox answer){
        answerList.getChildren().remove(answer);
    }

    public String getRepresentation(){
        String s = "QUESTION#" + titleLabel.getText() + "#" + chooseType.getValue();
        for(Node n : answerList.getChildren()){
            s += ((CAnswer)n).toString();
        }
        return(s);
    }

}
