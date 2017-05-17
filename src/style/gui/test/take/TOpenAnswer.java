package style.gui.test.take;

import javafx.scene.control.TextArea;

/**
 * Created by Markus on 2017-05-17.
 */
public class TOpenAnswer extends TAnswer {

    private TextArea answerBox;

    public TOpenAnswer(String title, int id){
        super();
        this.title.setText(title);
        this.id = id;
        answerBox = new TextArea();

        getChildren().add(answerBox);
    }


}
