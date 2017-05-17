package style.gui.test.take;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Created by Markus on 2017-05-17.
 */
public abstract class TAnswer extends HBox {

    protected Label title;
    protected int id;

    public TAnswer(){
        title = new Label("");
        id = 0;
    }
}
