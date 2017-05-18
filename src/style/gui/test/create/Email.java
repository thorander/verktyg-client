package style.gui.test.create;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

/**
 * Created by Sofia on 2017-05-18.
 */
public class Email {
    private GridPane grid;
    private ComboBox type, users,test;
    private Button send;


    private void Email(){
        grid = new GridPane();
        grid.setId("loginStyle");

        type = new ComboBox();
        type.setId("combobox1");

        users = new ComboBox();
        users.setId("combobox1");

        test = new ComboBox();
        test.setId("combobox1");

        send = CreateNodes.createButton("Send");

        grid.add(type, 1,1);
        grid.add(users,1,2);
        grid.add(test,1,3);
        grid.add(send,1,4);

    }
    public GridPane getEmailContent(){
        return grid;
    }
}
