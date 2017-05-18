package style.gui.test.create;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Created by Sofia on 2017-05-17.
 */
public class CreateNodes {
    //Gives the label an id and title
    public static Label createLabel(String title) {
        Label label = new Label(title);
        label.setId("label");
        return label;
    }
    //Gives the label an id and title
    public static Label createLabel2(String title) {
        Label label = new Label(title);
        label.setId("labelType2");
        return label;
    }
    //Gives the TextField an id and title
   public static TextField createText() {
       TextField textField = new TextField();
       textField.setId("input");
       return textField;
   }
    //Gives the TextField an id and title
    public static Button createButton(String title) {
       Button button = new Button(title);
        button.setId("button");
        return button;
    }
    public static Label createHeader(String title) {
        Label label = new Label(title);
        label.setId("headline");
        return label;
    }
}
