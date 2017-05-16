package style.gui.test.create;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * Created by Markus on 2017-05-16.
 */
public class COneChoiceAnswer extends CAnswer {

    private static ToggleGroup radioGroup = new ToggleGroup();
    private RadioButton correct = new RadioButton();

    public COneChoiceAnswer(CQuestion question){
        super(question);
        correct.setToggleGroup(radioGroup);
        this.getChildren().addAll(answer, correct, delete);
    }

    @Override
    public String toString() {
        return "#ANSWER#" + answer.getText() + "#" + correct.isSelected();
    }
}
