package style.gui.test.create;

import core.Main;
import javafx.application.Application;
import javafx.application.Platform;
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
import style.gui.components.CustomComboBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the page Statistics
 */
public class Statistic{
    private GridPane grid;
    private CustomComboBox test,group;
    private Label completedTests, passingTests, averagePoint;
    ObservableList<PieChart.Data> gradeChart;
    private PieChart chart;
    private HBox hbox, hboxCombobox;


    public Statistic (){

        setup();
    }

    private void setup(){
        grid = CreateNodes.createGrid();
        grid.setHgap(15);
        grid.setVgap(5);

        test = CreateNodes.createCustomComboBox("Select test");
        test.setPrefWidth(300);

        group = CreateNodes.createCustomComboBox("Select group");
        group.setPrefWidth(300);

        hboxCombobox = new HBox(15);
        hboxCombobox.getChildren().addAll(test,group);
        hboxCombobox.setPadding(new Insets(0,0,0,15));

        completedTests =CreateNodes.createLabel2("");
        passingTests = CreateNodes.createLabel2("");
        averagePoint = CreateNodes.createLabel2("");
        hbox = new HBox(50);
        hbox.setPadding(new Insets(0,0,0,70));
        hbox.getChildren().addAll(completedTests,passingTests,averagePoint);



        gradeChart = FXCollections.observableArrayList(
                        new PieChart.Data("No chosen test or group", 1));
        chart = new PieChart(gradeChart);
        chart.setTitle("Nothing chosen...");
        chart.setPadding(new Insets(0,0,0,20));



        grid.add(hboxCombobox, 0,0);
        GridPane.setColumnSpan(hboxCombobox,4);
        grid.add(chart,0,3);
        GridPane.setColumnSpan(chart,4);
        grid.add(hbox,0,4);
        GridPane.setColumnSpan(hbox,4);
        grid.setAlignment(Pos.CENTER);


        grid.setMaxWidth(700);
        grid.setMaxHeight(700);

        test.setOnAction(e -> {
            checkIfStats();
        });

        group.setOnAction(e -> {
            checkIfStats();
        });

    }

    public GridPane getGrid(){
        return grid;
    }

    public void addTest(String testName, String testId){
        Platform.runLater(() -> {
            test.addItem(testName, Integer.parseInt(testId));
        });
    }

    public void addGroup(String groupName, String groupId){
        Platform.runLater(() -> {
            group.addItem(groupName, Integer.parseInt(groupId));
        });
    }

    public void updateStats(String nrTests, String nrPassedTests, String averageScore,
                            String nrIG, String nrG, String nrVG){
        Platform.runLater(() -> {
            completedTests.setText("Completed tests: " + nrTests);
            passingTests.setText("Passed tests: " + nrPassedTests);
            averagePoint.setText("Average score: " + averageScore);
            gradeChart.clear();
            gradeChart.add(new PieChart.Data("IG", Integer.parseInt(nrIG)));
            gradeChart.add(new PieChart.Data("G", Integer.parseInt(nrG)));
            gradeChart.add(new PieChart.Data("VG", Integer.parseInt(nrVG)));
        });
    }

    private void checkIfStats(){
        if(test.getValue() != null && group.getValue() != null){
            Main.getConnection().write("GETSTATISTICS#" + test.getSelectedId() + "#" + group.getSelectedId());
            chart.setTitle(test.getValue() + " by " + group.getValue());
        }
    }

    public void clearMenus() {
        group.clear();
        test.clear();
    }
}

