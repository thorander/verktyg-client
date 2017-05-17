package style.gui.test.create;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import style.gui.icons.RemoveIcon;

public abstract class CAnswer extends HBox {

    protected TextField answer;
    protected RemoveIcon delete;

    private CQuestion question;

    public CAnswer(){
        answer = new TextField();
        answer.setPromptText("Answer...");
        delete = new RemoveIcon();
        delete.setOnMouseClicked(e -> {
            question.removeAnswer(this);
        });
        this.setPadding(new Insets(5, 0, 0, 0));
    }

    public CAnswer(CQuestion question){
        this();
        this.question = question;
    }

    public abstract String toString();
}
