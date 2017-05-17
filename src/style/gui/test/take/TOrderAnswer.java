package style.gui.test.take;

import javafx.scene.control.Label;

/**
 * Created by Markus on 2017-05-17.
 */
public class TOrderAnswer extends TAnswer {

    private Label dragLabel;

    public TOrderAnswer(String answer, int id){
        super();
        dragLabel = new Label("≡");
        dragLabel.setId("icon");
        this.title.setText(answer);
        this.id = id;
        getChildren().addAll(dragLabel, title);
    }

    public Label getDragLabel(){
        return dragLabel;
    }


}
