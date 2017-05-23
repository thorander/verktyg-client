package style.gui.test.take;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import style.gui.test.create.CreateNodes;

/**
 * Created by Markus on 2017-05-17.
 */
public class TQuestion extends HBox {

    private BorderPane box;
    private TAnswerBox answers;
    private Label question;
    private String type;
    private ToggleGroup group;
    private int id;

    public TQuestion(String question, String type, int id){
        answers = new TAnswerBox();
        this.question = CreateNodes.createHeader(question);
        this.type = type;
        box = new BorderPane();
        group = new ToggleGroup();
        setupGraphics();
    }

    public void setupGraphics(){
        box.setMaxWidth(500);
        box.setMaxHeight(500);
        box.setStyle("-fx-border-width: 2px;");

        box.setPadding(new Insets(10, 10, 10, 10));
        box.setTop(question);
        box.setCenter(answers);

        getChildren().add(box);
    }

    public void addAnswer(TAnswer answer){
        switch(type){
            case "Multiple choice":
                TMultipleChoiceAnswer mcanswer = new TMultipleChoiceAnswer(answer.getText(), answer.getAnswerId(), answer.isbCorrect());
                answers.addAnswer(mcanswer);
                break;
            case "One choice":
                TOneChoiceAnswer ocanswer = new TOneChoiceAnswer(answer.getText(), answer.getAnswerId(), answer.isbCorrect(), group);
                answers.addAnswer(ocanswer);
                break;
            case "Order":
                TOrderAnswer oanswer = new TOrderAnswer(answer.getText(), answer.getAnswerId());
                answers.addAnswer(oanswer);
                break;
            case "Open":
                TOpenAnswer openAnswer = new TOpenAnswer(answer.getText(), answer.getAnswerId());
                answers.addAnswer(openAnswer);
                break;
        }
    }

    public ToggleGroup getGroup(){
        return group;
    }

    public String getTitle() {return question.getText();}

    public void setQId(int id){
        this.id = id;
    }

    public int getQId(){
        return id;
    }
}
