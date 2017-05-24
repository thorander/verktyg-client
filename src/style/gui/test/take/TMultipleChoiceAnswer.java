package style.gui.test.take;

import javafx.scene.control.CheckBox;

/**
 * Created by Markus on 2017-05-18.
 */
public class TMultipleChoiceAnswer extends TAnswer {

    private CheckBox correct;

    public TMultipleChoiceAnswer(String text, int id, boolean correct){
        super(text, id);
        this.correct = new CheckBox();
        this.correct.setSelected(correct);
        getChildren().addAll(title, this.correct);
    }

    public boolean isChecked(){
        return correct.isSelected();
    }
}
