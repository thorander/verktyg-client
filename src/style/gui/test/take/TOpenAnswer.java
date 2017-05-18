package style.gui.test.take;

import javafx.scene.control.TextArea;

public class TOpenAnswer extends TAnswer {

    private TextArea answerBox;

    public TOpenAnswer(String text, int id){
        super(text, id);
        answerBox = new TextArea();
        getChildren().add(answerBox);
    }


}
