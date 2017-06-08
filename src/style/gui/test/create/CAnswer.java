package style.gui.test.create;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import style.gui.CreateNodes;
import style.gui.icons.RemoveIcon;

/**
 * A superclass representing an answer on the creation-page
 */
public abstract class CAnswer extends HBox {

    protected TextField answer;
    protected RemoveIcon delete;
    protected int order;

    private CQuestion question;

    public CAnswer(){
        answer = CreateNodes.createTextPromt("Answer..");
        this.setAlignment(Pos.TOP_LEFT);
        delete = new RemoveIcon();

        delete.setOnMouseClicked(e -> {
            question.removeAnswer(this);
        });
    }

    public CAnswer(CQuestion question){
        this();
        this.question = question;
    }

    public CAnswer(CQuestion question, int order){
        this(question);
        this.order = order;
    }

    public abstract String toString();

    public String getText(){return answer.getText();}

    public void setText(String answer){
        this.answer.setText(answer);
    }
}
