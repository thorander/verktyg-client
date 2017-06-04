package style.gui.test.create;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import style.gui.components.CustomToolTip;

/**
 * One choice answer on creation page
 */
public class COneChoiceAnswer extends CAnswer {

    private static ToggleGroup radioGroup = new ToggleGroup();
    private RadioButton correct;
    private CustomToolTip correctTip;

    public COneChoiceAnswer(CQuestion question, int order){
        super(question, order);
        correct = new RadioButton();
        correct.setToggleGroup(radioGroup);
        correct.setPadding(new Insets(5, 0, 0, 5));

        correctTip = new CustomToolTip("Whether this answer is correct or not. \n Checked means it is true.");
        Tooltip.install(correct, correctTip);
        this.getChildren().addAll(answer, correct, delete);
    }

    @Override
    public String toString() {
        return "#ANSWER#" + answer.getText() + "#" + correct.isSelected() + "#" + order;
    }
}
