package style.gui.test.create;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Created by Sofia on 2017-05-11.
 */
public abstract class Statistic extends Application{
    private static GridPane grid;
    private static ComboBox test,group;
    private static Label headline, testName,completedTests, passingTests, averagePoint;
    private static String inputTest;
    private  static int completedTestsInt, passingTestsInt, averagePointInt;
    private static HBox hbox, hboxCombobox;
//test

    public Statistic (){

        setup();
    }

    private static void setup(){
        grid= new GridPane();
        grid.setHgap(15);
        grid.setVgap(5);

        test = new ComboBox();
        test.setPromptText("Select test");
        test.setId("combobox");
        test.setPrefWidth(300);

        group = new ComboBox();
        group.setPromptText("Select group");
        group.setId("combobox");
        group.setPrefWidth(300);

        hboxCombobox = new HBox(15);
        hboxCombobox.getChildren().addAll(test,group);
        hboxCombobox.setPadding(new Insets(0,0,0,15));

        headline = CreateNodes.createLabel2("Test:");
        testName = CreateNodes.createLabel2(inputTest);
        completedTests =CreateNodes.createLabel2("Completed tests: " + completedTestsInt);
        passingTests = CreateNodes.createLabel2("Passing tests: " + passingTestsInt);
        averagePoint = CreateNodes.createLabel2("Avreage points: " + averagePointInt);
        hbox = new HBox(50);
        hbox.setPadding(new Insets(0,0,0,70));
        hbox.getChildren().addAll(completedTests,passingTests,averagePoint);


        ObservableList<PieChart.Data> gradeChart =
                FXCollections.observableArrayList(
                        new PieChart.Data("IG", 4),
                        new PieChart.Data("G",10),
                        new PieChart.Data("VG",9));
        final PieChart chart = new PieChart(gradeChart);
        chart.setTitle("Results for " + inputTest);
        chart.setPadding(new Insets(0,0,0,20));



        grid.add(hboxCombobox, 0,0);
        GridPane.setColumnSpan(hboxCombobox,4);
        grid.add(chart,0,3);
        GridPane.setColumnSpan(chart,4);
        grid.add(hbox,0,4);
        GridPane.setColumnSpan(hbox,4);
        grid.setAlignment(Pos.CENTER);

        grid.setId("loginStyle");
        DropShadow drop = new DropShadow(50, Color.GRAY);
        grid.setEffect(drop);
        grid.setMaxWidth(700);
        grid.setMaxHeight(700);

    }

    public static GridPane getGrid(){
        setup();
        return grid;
    }
}

