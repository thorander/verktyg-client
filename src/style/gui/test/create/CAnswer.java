package style.gui.test.create;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import style.gui.icons.RemoveIcon;

public abstract class CAnswer extends HBox {

    protected TextField answer;
    protected RemoveIcon delete;

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

    public abstract String toString();

    public String getText(){return answer.getText();}

    public void setText(String answer){
        this.answer.setText(answer);
    }
}
