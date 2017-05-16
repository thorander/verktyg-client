package style.gui.test.create;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.scene.Group;

/**
 * Created by Sofia on 2017-05-11.
 */
public abstract class Statistic extends Application{
    private  GridPane grid;
    private  ComboBox test,group;
    private  Label headline, testName;
    String inputTest;


    public Statistic (){

        grid= new GridPane();

        test = new ComboBox();
        test.setPromptText("Select test");

        group = new ComboBox();
        test.setPromptText("Select group");

        headline = new Label("Test:");
        testName = new Label(inputTest);

        ObservableList<PieChart.Data> gradeChart =
                FXCollections.observableArrayList(
                        new PieChart.Data("IG", 4),
                        new PieChart.Data("G",10),
                        new PieChart.Data("VG",9));
        final PieChart chart = new PieChart(gradeChart);
        chart.setTitle("Utfall för " + inputTest);



        grid.add(test, 0,0);
        grid.add(test, 0,1);
        grid.add(headline, 0,2);
        grid.add(testName, 1,2);
        grid.add(chart,0,3);
    }

    public GridPane getGrid(){
        return this.grid;
    }
}

