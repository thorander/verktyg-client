package style.gui.components;

import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

/**
 * Created by Matilda on 2017-05-16.
 */
public class DateTimePicker extends VBox {

    private DatePicker datePicker;
    private String string;

    public DateTimePicker(){
        super();
        datePicker = new DatePicker();
        string = "";
        Run();
    }

    private void Run(){
        getChildren().add(datePicker);

        datePicker.setOnAction(event -> {
            LocalDate date = datePicker.getValue();
            string = date+"";
            System.out.println(string);

        });
    }
}
