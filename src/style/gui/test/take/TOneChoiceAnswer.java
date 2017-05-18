package style.gui.test.take;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class TOneChoiceAnswer extends TAnswer {

    private RadioButton correct;

    public TOneChoiceAnswer(String text, int id, boolean correct, ToggleGroup group){
        super(text, id);
        this.correct = new RadioButton();
        this.correct.setSelected(correct);
        this.correct.setToggleGroup(group);
        getChildren().addAll(title, this.correct);
    }

}
