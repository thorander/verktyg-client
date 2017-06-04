package style.gui.test.create;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.text.TextAlignment;
import style.gui.components.CustomToolTip;

/**
 * Order answer on creation page
 */
public class COrderAnswer extends CAnswer {

    private Label moveLabel;
    CustomToolTip orderTip;

    public COrderAnswer(CQuestion question, int order){
        super(question, order);
        moveLabel = new Label("≡");
        moveLabel.setId("icon");
        moveLabel.setStyle("-fx-font-size: 20px;");

        orderTip = new CustomToolTip("Click and drag to swap answers");
        Tooltip.install(moveLabel, orderTip);
        this.setMargin(moveLabel, new Insets(0, 5, 0, 5));
        this.getChildren().addAll(answer, moveLabel, delete);
    }

    @Override
    public String toString() {
        return "#ANSWER#" + answer.getText() + "#true" + "#" + order;
    }

    public Label getMoveLabel(){
        return moveLabel;
    }
}
