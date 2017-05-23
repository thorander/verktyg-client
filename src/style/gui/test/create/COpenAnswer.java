package style.gui.test.create;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Created by Markus on 2017-05-16.
 */
public class COpenAnswer extends CAnswer {

    private Label answerLabel;

    public COpenAnswer(CQuestion question){
        super(question);
        answerLabel = new Label("There will be an open text area to answer this question.");
        this.getChildren().add(answerLabel);
    }

    @Override
    public String toString() {
        return "#ANSWER#" + "OPENQUESTION#true#1";
    }
}
