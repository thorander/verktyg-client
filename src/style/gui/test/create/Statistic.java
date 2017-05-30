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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Statistic{
    private GridPane grid;
    private ComboBox test,group;
    private Label headline, testName,completedTests, passingTests, averagePoint;
    private String inputTest;
    private int completedTestsInt, passingTestsInt, averagePointInt;
    private HBox hbox, hboxCombobox;
    private List<String> testList, userList;


    public Statistic (){

        setup();
    }

    private void setup(){
        grid = CreateNodes.createGrid();
        grid.setHgap(15);
        grid.setVgap(5);

        test = CreateNodes.createComboBox("Select test");
        test.setPrefWidth(300);

        group = CreateNodes.createComboBox("Select group");
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


/*
        ObservableList<PieChart.Data> gradeChart =
                FXCollections.observableArrayList(
                        new PieChart.Data("IG", 4),
                        new PieChart.Data("G",10),
                        new PieChart.Data("VG",9));
        final PieChart chart = new PieChart(gradeChart);
        chart.setTitle("Results for " + inputTest);
        chart.setPadding(new Insets(0,0,0,20));*/



        grid.add(hboxCombobox, 0,0);
        GridPane.setColumnSpan(hboxCombobox,4);
        grid.setAlignment(Pos.TOP_CENTER);

        //grid.add(chart,0,3);
        //GridPane.setColumnSpan(chart,4);
        //grid.add(hbox,0,4);
        //GridPane.setColumnSpan(hbox,4);


        grid.setMaxWidth(700);
        grid.setMaxHeight(700);

    }
    public void getUTest(String testData){
        System.out.println("test statistik");
        testList = new ArrayList<String>(Arrays.asList(testData.split("@")));
        for(int i = 0; i < testList.size(); i++){
            test.getItems().add(testList.get(i));
            i++;
        }}

    public GridPane getGrid(){
        return grid;
    }
}

