package style.gui.components;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

/**
 * Created by Matilda on 2017-05-16.
 */
public class DateTimePicker extends VBox {

    private DatePicker datePicker;
    private String string;
    private Spinner<Integer> spinner;


    public DateTimePicker() {
        super();
        datePicker = new DatePicker();
        string = "";
        spinner = new Spinner<Integer>();
        Run();
    }

    private void Run() {
        datePicker.setMaxWidth(130);
        spinner.setMaxWidth(70);
        spinner.setEditable(true);
        getChildren().addAll(datePicker, spinner);

        datePicker.setOnAction(event -> {
            LocalDate date = datePicker.getValue();
            string = date + "";
            System.out.println(string);

        });

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 120);
        spinner.setValueFactory(valueFactory);

        System.out.println(spinner.getValue());


    }

}
