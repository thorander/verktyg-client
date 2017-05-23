package style.gui.test.take;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TAnswer extends HBox {

    protected Label title;
    protected int id;
    protected boolean bCorrect;
    protected int answerOrder;

    public TAnswer(){
        title = new Label("");
        id = 0;
        bCorrect = false;
        this.setPadding(new Insets(5, 10, 0, 0));
    }

    public TAnswer(String text, int id){
        this();
        title.setText(text);
        this.id = id;
    }

    public TAnswer(String text, int id, boolean bCorrect){
        this();
        title.setText(text);
        this.id = id;
        this.bCorrect = bCorrect;
    }

    public String getText(){
        return title.getText();
    }

    public void setText(String text){
        title.setText(text);
    }

    public int getAnswerId(){
        return id;
    }

    public boolean isbCorrect() {
        return bCorrect;
    }

    public int getAnswerOrder(){
        return answerOrder;
    }

    public void setAnswerId(int id){
        this.id = id;
    }
}
