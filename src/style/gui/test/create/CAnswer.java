package style.gui.test.create;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Created by Markus on 2017-05-11.
 */
public class CAnswer extends HBox {

    public TextField answer;
    private CheckBox correct;
    private ImageView delete;
    private Label deleteLabel;

    private CQuestion question;

    public CAnswer(CQuestion question){
        answer = new TextField();
        answer.setPromptText("Answer...");
        correct = new CheckBox();
        delete = new ImageView("/Images/remove.png");
        deleteLabel = new Label("", delete);

        correct.setPadding(new Insets(0, 0, 0, 5));

        Tooltip correctTip = new Tooltip("Whether this answer is correct or not. \n Checked means it is true.");
        Tooltip.install(correct, correctTip);
        this.setPadding(new Insets(5, 0, 0, 0));

        delete.setFitHeight(25);
        delete.setFitWidth(25);
        this.question = question;
        deleteLabel.setOnMouseClicked(e -> {
            question.removeAnswer(this);
        });
        deleteLabel.setOnMouseEntered(e -> {
            delete.setImage(new Image("/Images/removeOrange.png"));
        });
        deleteLabel.setOnMouseExited(e -> {
            delete.setImage(new Image("/Images/remove.png"));
        });

        getChildren().addAll(answer, correct, deleteLabel);
    }

    public String toString(){
        return "#ANSWER#" + answer.getText() + "#" + correct.isSelected();
    }
}
