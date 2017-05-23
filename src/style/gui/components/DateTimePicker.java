package style.gui.components;

import core.Main;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import style.gui.test.create.CTest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Matilda on 2017-05-16.
 */
public class DateTimePicker extends VBox {

    private DatePicker openDate;
    private DatePicker closeDate;
    private String open;
    private String close;
    private String currentDate;
    private Slider slider;
    private Label label;
    private String time;


    public DateTimePicker() {
        super();
        openDate = new DatePicker();
        closeDate = new DatePicker();
        open = "";
        close = "";
        currentDate = null;
        slider = new Slider(1, 120, 5);
        label  = new Label("Minuter");
        time = "";
        Date();
        Time();
    }

    private void Date() {
        openDate.setMaxWidth(130);
        openDate.setPromptText("Startdatum");
        closeDate.setMaxWidth(130);
        closeDate.setPromptText("Slutdatum");
        getChildren().addAll(openDate, closeDate);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        Date date = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = format1.format(date);

        openDate.setOnAction(event -> {
            String myDate = openDate.getValue().toString();

            if (currentDate.compareTo(myDate)<1) {
                Main.getGUI().getCreateTest().createButton.setDisable(false);
                setOpenDate(myDate);
            } else if (currentDate.compareTo(myDate)>-1)
                Main.getGUI().getCreateTest().createButton.setDisable(true);
        });

        closeDate.setOnAction(event -> {
            String myDate = closeDate.getValue().toString();

            if (currentDate.compareTo(myDate)<1) {
                Main.getGUI().getCreateTest().createButton.setDisable(false);
                setCloseDate(myDate);
            } else if (currentDate.compareTo(myDate)>-1)
                Main.getGUI().getCreateTest().createButton.setDisable(true);
        });

    }

    private void Time() {

        slider.setMaxWidth(150);
        slider.valueProperty().addListener(
                (observable, oldvalue, newvalue) ->
                {
                    int i = newvalue.intValue();
                    label.setText(Integer.toString(i)); //Kan använda textfield om man vill
                    setTime(i);
                } );

        /*Annan metod som också fungerar
        slider.setMaxWidth(130);

        label.textProperty().bind(
                Bindings.format(
                        "%.2f",
                        slider.valueProperty()
                )
        );*/

        getChildren().addAll(label, slider);

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

    public void setTime(Integer timeValue) {
        time = timeValue.toString();
        //System.out.println(time);
    }

    public String getTime() {
        return time;
    }

}

