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
import style.gui.components.CustomToolTip;
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
    private COrderAnswer dragging, target;

    public CQuestion(){
        question = new GridPane();
        questionBox = new HBox();
        shortQuestion = new HBox();
        shortQuestion.setId("questionBox");
        Label addAnswer = new Label("+");
        addAnswer.setId("icon");
        CustomToolTip addAnswerTip = new CustomToolTip("Click here to add a new answer");
        Tooltip.install(addAnswer, addAnswerTip);
        Label shorten = new Label("-");
        Label expand = new Label("+");
        titleLabel = new EditableLabel("Enter your question-title here...");
        titleLabel.setStyle("-fx-font-size: 12px");
        RemoveIcon remove = new RemoveIcon();
        question.setMargin(remove, new Insets(0, 5, 0, 5));
        titleLabel.setMaxWidth(600);
        titleLabel.setMinWidth(600);
        titleLabel.setWrapText(true);
        Label shortQTitle = new Label(titleLabel.getText());
        shortQTitle.setMaxWidth(600);
        shortQuestion.setAlignment(Pos.BOTTOM_CENTER);
        TextField titleField = new TextField();
        titleField.setMinWidth(600);
        chooseType = new ComboBox();
        shortQuestion.setMargin(expand, new Insets(0, 0, 0, 50));
        question.setMargin(shorten, new Insets(0, 0, 0, 5));
        question.setMargin(chooseType, new Insets(0, 0, 15, 0));
        titleLabel.setId("headline");
        shorten.setId("icon");
        shorten.setMaxSize(50, 50);
        CustomToolTip shortenTip = new CustomToolTip("Minimize this question");
        Tooltip.install(shorten, shortenTip);
        expand.setId("icon");
        expand.setMaxSize(50, 50);
        CustomToolTip expandTip = new CustomToolTip("Expand this question");
        Tooltip.install(expand, expandTip);
        chooseType.getItems().addAll("One choice", "Multiple choice", "Order", "Open question");
        chooseType.getSelectionModel().selectFirst();

        answerList = new VBox();

        question.add(titleLabel, 0, 0);
        question.add(shorten, 2, 0);
        question.add(remove, 1, 0);
        question.add(chooseType, 0, 1);
        question.add(answerList, 0, 2);
        question.add(addAnswer, 0, 3);

        shortQTitle.setId("headline");

        shortQuestion.getChildren().addAll(shortQTitle, expand);

        chooseType.setOnAction(e -> {
            if(chooseType.getValue().equals("Open question")){
                addAnswer.setVisible(false);
                answerList.getChildren().clear();
                answerList.getChildren().add(new COpenAnswer(this));
            } else {
                addAnswer.setVisible(true);
                answerList.getChildren().clear();
            }
        });

        addAnswer.setOnMouseClicked(e -> {
            CAnswer answer;
            switch((String)chooseType.getValue()){
                case "One choice":
                    answer = new COneChoiceAnswer(this);
                    answerList.getChildren().add(answer);
                    answerList.setMargin(answer, new Insets(0, 0, 5, 0));
                    break;
                case "Multiple choice":
                    answer = new CMultipleChoiceAnswer(this);
                    answerList.getChildren().add(answer);
                    answerList.setMargin(answer, new Insets(0, 0, 5, 0));
                    break;
                case "Order":
                    answer = new COrderAnswer(this);
                    setDraggable((COrderAnswer)answer);
                    answerList.getChildren().add(answer);
                    answerList.setMargin(answer, new Insets(0, 0, 5, 0));
                    break;
            }
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

    private void setDraggable(COrderAnswer answer){
        Label handler = answer.getMoveLabel();
        handler.setOnDragDetected(e -> {answer.startFullDrag();
            dragging = answer;});
        System.out.println("Setting draggable");

        answer.setOnMouseDragEntered(e -> {target = answer; answer.setStyle("-fx-border-width: 1px; -fx-border-color: darkorange; -fx-background-color: #ffc56b;");});
        answer.setOnMouseDragExited(e -> {target = null; answer.setStyle("");});

        answer.setOnMouseDragReleased(e -> {
            if(target != null){
                int answerId = answerList.getChildren().indexOf(dragging);
                int targetId = answerList.getChildren().indexOf(target);
                String targetText = ((CAnswer)answerList.getChildren().get(targetId)).getText();
                String draggedText = ((CAnswer)answerList.getChildren().get(answerId)).getText();
                ((CAnswer)answerList.getChildren().get(answerId)).setText(targetText);
                ((CAnswer)answerList.getChildren().get(targetId)).setText(draggedText);
            }
            System.out.println("Dropped");
        });
    }

    public void removeAnswer(HBox answer){
        answerList.getChildren().remove(answer);
    }

    public String getRepresentation(){
        String s = "QUESTION#" + titleLabel.getText() + "#" + chooseType.getValue();
        for(Node n : answerList.getChildren()){
            s += n.toString();
        }
        return(s);
    }

}
