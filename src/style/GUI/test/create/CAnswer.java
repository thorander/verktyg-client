package style.GUI.test.create;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        answer.setPromptText("Type the answer here...");
        correct = new CheckBox();
        delete = new ImageView("/Images/remove.png");
        deleteLabel = new Label("", delete);

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
