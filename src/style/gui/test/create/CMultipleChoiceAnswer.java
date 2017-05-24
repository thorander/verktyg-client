package style.gui.test.create;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import style.gui.components.CustomToolTip;

/**
 * Created by Markus on 2017-05-16.
 */
public class CMultipleChoiceAnswer extends CAnswer {

    private CheckBox correct;
    CustomToolTip correctTip;

    public CMultipleChoiceAnswer(CQuestion question, int order){
        super(question, order);

        correct = new CheckBox();
        correct.setPadding(new Insets(0, 0, 0, 5));

        correctTip = new CustomToolTip("Whether this answer is correct or not. \n Checked means it is true.");
        Tooltip.install(correct, correctTip);

        this.getChildren().addAll(answer, correct, delete);
    }
    @Override
    public String toString() {
        return "#ANSWER#" + answer.getText() + "#" + correct.isSelected() + "#" + order;
    }
}
