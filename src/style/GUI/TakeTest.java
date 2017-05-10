package style.GUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Created by phili on 2017-05-10.
 */
public class TakeTest {

    private GridPane grid;

    private TextField answer;
    private Label question;
    private CheckBox correct;
    private Scene scene;

    public  GridPane setup(){

        grid = new GridPane();
        answer = new TextField();
        question = new Label();
        correct = new CheckBox();


        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(5, 10, 5, 10));
        grid.add(question,0,1);
        grid.add(answer,0,3);
        grid.add(correct,0,3);
        scene = new Scene(grid,450,400);

        return grid;

    }

    public GridPane getGrid() {
        return grid;
    }

    public void setGrid(GridPane grid) {
        this.grid = grid;
    }

    public TextField getAnswer() {
        return answer;
    }

    public void setAnswer(TextField answer) {
        this.answer = answer;
    }

    public Label getQuestion() {
        return question;
    }

    public void setQuestion(Label question) {
        this.question = question;
    }

    public CheckBox getCorrect() {
        return correct;
    }

    public void setCorrect(CheckBox correct) {
        this.correct = correct;
    }

}
