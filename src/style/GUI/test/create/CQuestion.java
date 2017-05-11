package style.GUI.test.create;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import style.GUI.Icons.RemoveIcon;

/**
 * Created by Markus on 2017-05-11.
 */
public class CQuestion {

    private GridPane question;
    private VBox answerList;
    private HBox questionBox;
    private HBox shortQuestion;

    public HBox getQuestion(){
        question = new GridPane();
        questionBox = new HBox();
        shortQuestion = new HBox();
        shortQuestion.setId("questionBox");
        Label addAnswer = new Label("+");
        addAnswer.setId("icon");
        Label shorten = new Label("-");
        Label expand = new Label("+");
        Label titleLabel = new Label("Enter your question-title here...");
        RemoveIcon remove = new RemoveIcon();
        question.setMargin(remove, new Insets(0, 5, 0, 5));
        titleLabel.setMaxWidth(350);
        titleLabel.setWrapText(true);
        Label shortQTitle = new Label(titleLabel.getText());
        shortQTitle.setMaxWidth(350);
        shortQuestion.setAlignment(Pos.BOTTOM_CENTER);
        TextField titleField = new TextField();
        titleField.setMinWidth(350);
        ComboBox chooseType = new ComboBox();
        shortQuestion.setMargin(expand, new Insets(0, 0, 0, 50));
        question.setMargin(shorten, new Insets(0, 0, 0, 5));
        titleLabel.setId("headline");
        shorten.setId("icon");
        shorten.setMaxSize(50, 50);
        expand.setId("icon");
        expand.setMaxSize(50, 50);
        chooseType.getItems().addAll("One choice", "Multiple choice", "Order", "Open question");

        answerList = new VBox();

        question.add(titleLabel, 0, 0);
        question.add(shorten, 2, 0);
        question.add(remove, 1, 0);
        question.add(chooseType, 0, 1);
        question.add(answerList, 0, 2);
        question.add(addAnswer, 0, 3);

        shortQTitle.setId("headline");

        shortQuestion.getChildren().addAll(shortQTitle, expand);

        titleLabel.setOnMouseClicked(e -> {
            question.getChildren().remove(titleLabel);
            question.add(titleField, 0, 0);
            titleField.requestFocus();
        });
        titleField.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.ENTER){
                titleLabel.setText(titleField.getText());
                question.getChildren().remove(titleField);
                question.add(titleLabel, 0, 0);
            }
        });
        addAnswer.setOnMouseClicked(e -> {
            CAnswer c = new CAnswer(this);
            answerList.getChildren().add(c.getAnswerRepresentation());
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
            CTest.removeQuestion(questionBox);
        });
        questionBox.getChildren().add(question);
        return questionBox;
    }

    public void removeAnswer(HBox answer){
        answerList.getChildren().remove(answer);
    }

}
