package style.gui.components;

import core.Main;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import style.gui.test.create.CTest;
import style.gui.test.create.CreateNodes;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Matilda on 2017-05-16.
 */
public class DateTimePicker extends HBox {

    private DatePicker openDate;
    private DatePicker closeDate;
    private String open;
    private String close;
    private String currentDate;
    private Slider slider;
    private Label label, openLabel, closeLabel;
    private String time;
    private VBox dateBox;
    private VBox timeBox;
    private CustomToolTip sliderTip;


    public DateTimePicker() {
        super();
        openDate = new DatePicker();
        closeDate = new DatePicker();
        open = "";
        close = "";
        currentDate = null;
        slider = new Slider(0, 300, 0);
        slider.setOrientation(Orientation.VERTICAL);

        sliderTip = new CustomToolTip("Set a time limit for this test in minutes.\n0 means no time limit.");
        CustomToolTip.install(slider, sliderTip);

        dateBox = new VBox();
        timeBox = new VBox();

        label  = CreateNodes.createLabelTest("Time");
        openLabel = CreateNodes.createLabelTest("Opening date");
        closeLabel = CreateNodes.createLabelTest("Close date");
        time = "0";
        Date();
        Time();
    }

    private void Date() {
        openDate.setMaxWidth(130);
        openDate.setPromptText("Start date");
        openDate.setValue(LocalDate.now());
        closeDate.setMaxWidth(130);
        closeDate.setPromptText("End date");
        closeDate.setValue(LocalDate.now().plusWeeks(1));
        Region space = new Region();
        VBox.setVgrow(space, Priority.ALWAYS);
        dateBox.getChildren().addAll(openLabel,openDate, space, closeLabel, closeDate);
        VBox.setMargin(closeDate, new Insets(0, 0, 10, 0));
        VBox.setMargin(closeLabel, new Insets(5, 0, 5, 0));
        VBox.setMargin(openLabel, new Insets(0, 0, 5, 0));

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

        slider.valueProperty().addListener(
                (observable, oldvalue, newvalue) ->
                {
                    int i = 15*(Math.round(Float.parseFloat(newvalue + "")/15));
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
        timeBox.setMinWidth(100);
        timeBox.setAlignment(Pos.CENTER);
        timeBox.getChildren().addAll(label, slider);
        HBox.setMargin(slider, new Insets(0, 0, 5, 0));
        HBox.setMargin(dateBox, new Insets(0, 10, 0, 0));
        getChildren().addAll(dateBox, timeBox);
        HBox.setMargin(timeBox, new Insets(0, 0, 0, 5));

    }

    public void setOpenDate(String date) {
        open = date;
        //System.out.println(open);
    }

    public String getOpenDate() {
        return openDate.getValue().toString();
    }

    public void setCloseDate(String date) {
        close = date;
        //System.out.println(close);
    }

    public String getCloseDate() {
        return closeDate.getValue().toString();
    }

    public void setTime(Integer timeValue) {
        time = timeValue.toString();
        //System.out.println(time);
    }

    public String getTime() {
        return time;
    }

}

