package style.gui.test.create;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.scene.Group;
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

        headline = createLabel("Test:");
        testName = createLabel(inputTest);
        completedTests =createLabel("Completed tests: " + completedTestsInt);
        passingTests = createLabel("Passing tests: " + passingTestsInt);
        averagePoint = createLabel("Avreage points" + averagePointInt);

        ObservableList<PieChart.Data> gradeChart =
                FXCollections.observableArrayList(
                        new PieChart.Data("IG", 4),
                        new PieChart.Data("G",10),
                        new PieChart.Data("VG",9));
        final PieChart chart = new PieChart(gradeChart);
        chart.setTitle("Utfall för " + inputTest);



        grid.add(test, 0,0);
        grid.add(group, 1,0);
        GridPane.setColumnSpan(group,4);
        grid.add(chart,0,3);
        GridPane.setColumnSpan(chart,4);
        grid.add(completedTests,0,4);
        grid.add(passingTests,1,4);
        grid.add(averagePoint,2,4);
        grid.setId("loginStyle");
        DropShadow drop = new DropShadow(50, Color.GRAY);
        grid.setEffect(drop);
        grid.setMaxWidth(700);
        grid.setMaxHeight(700);

    }
    //Gives the label an id and title
    private static Label createLabel(String title){
        Label label = new Label(title);
        label.setId("labelType2");
        return label;
    }


    public static GridPane getGrid(){
        setup();
        return grid;
    }
}

