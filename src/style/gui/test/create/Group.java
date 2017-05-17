package style.gui.test.create;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Created by Sofia on 2017-05-16.
 */
public  class Group {
    private static GridPane grid;


    private static void createGroupGrid() {
        grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(5);
    }
}
