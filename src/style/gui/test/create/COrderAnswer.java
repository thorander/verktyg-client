package style.gui.test.create;

import javafx.scene.control.Label;

public class COrderAnswer extends CAnswer {

    private Label moveLabel;

    public COrderAnswer(CQuestion question){
        moveLabel = new Label("≡");
        this.getChildren().addAll(answer, moveLabel, delete);
    }

    @Override
    public String toString() {
        return "#ANSWER#" + answer.getText() + "#true";
    }
}
