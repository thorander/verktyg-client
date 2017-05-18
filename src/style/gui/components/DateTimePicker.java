package style.gui.components;

import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import style.gui.test.create.CTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Matilda on 2017-05-16.
 */
public class DateTimePicker extends VBox {

    private DatePicker openDate;
    private DatePicker closeDate;
    //private Spinner<Integer> spinner = new Spinner<Integer>();
    private String open = "";
    private String close = "";
    private String time = "";
    private String currentDate = null;


    public DateTimePicker() {
        super();
        openDate = new DatePicker();
        closeDate = new DatePicker();
        Do();
    }

    private void Do() {
        openDate.setMaxWidth(130);
        closeDate.setMaxWidth(130);
        //spinner.setMaxWidth(80);
       // spinner.setEditable(true);
        getChildren().addAll(openDate, closeDate);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        Date date = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = format1.format(date);

        openDate.setOnAction(event -> {
            String myDate = openDate.getValue().toString();

            if (currentDate.compareTo(myDate)<1) {
                CTest.createButton.setDisable(false);
                setOpenDate(myDate);
            } else if (currentDate.compareTo(myDate)>-1)
            CTest.createButton.setDisable(true);
        });

        closeDate.setOnAction(event -> {
            String myDate = closeDate.getValue().toString();

            if (currentDate.compareTo(myDate)<1) {
                CTest.createButton.setDisable(false);
                setCloseDate(myDate);
            } else if (currentDate.compareTo(myDate)>-1)
                CTest.createButton.setDisable(true);
        });

        /*
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 120);
        spinner.setValueFactory(valueFactory);

        spinner.valueProperty().addListener((obs, oldValue, newValue) ->
                time = newValue +"");
                */
    }

    public void setOpenDate(String date) {
        open = date;
        //System.out.println(open);
    }

    public String getOpenDate() {
        return open;
    }

    public void setCloseDate(String date) {
        close = date;
        //System.out.println(close);
    }

    public String getCloseDate() {
        return close;
    }

}

