package style.gui.test.take;

import javafx.scene.control.Label;

/**
 * Created by Markus on 2017-05-17.
 */
public class TOrderAnswer extends TAnswer {

    private Label dragLabel;

    public TOrderAnswer(String text, int id){
        super(text, id);
        dragLabel = new Label("≡");
        dragLabel.setId("icon");
        getChildren().addAll(dragLabel, title);
    }

    public Label getDragLabel(){
        return dragLabel;
    }


}
