package style.gui;

import core.Main;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import style.gui.components.CustomComboBox;
import style.gui.test.correct.CorrAnswer;
import style.gui.test.create.CreateNodes;

/**
 * Created by Markus on 2017-06-07.
 */
public class Result extends BorderPane {

    private GridPane header;
    private ScrollPane scrollPane;
    private VBox questionBox, answerBox;

    private CustomComboBox testBox;

    private Label titleLabel, commentLabel, gradeLabel, pointsLabel, chooseTest, timeSpentLabel;

    public Result(){
        header = new GridPane();
        questionBox = new VBox();
        answerBox = new VBox();
        scrollPane = new ScrollPane();
        chooseTest = CreateNodes.createLabel2("Choose test:");
        testBox = CreateNodes.createCustomComboBox("Pick test");
        testBox.setOnAction(e -> {
           Main.getConnection().write("UTESTFORRESULTPAGE#" + testBox.getSelectedId());
            System.out.println("Sending for a new test");
        });
        setTop(new HBox(chooseTest, testBox));
        setId("loginStyle");

        setMaxWidth(500);
        setMaxHeight(600);
        setPadding(new Insets(25, 25, 25, 25));
        DropShadow drop = new DropShadow(50, Color.GRAY);
        setEffect(drop);
    }

    public void newTest(String title, String points, String maxPoints, String grade, String comment, String timeSpent){
        header.getChildren().clear();
        questionBox.getChildren().clear();
        titleLabel = CreateNodes.createHeader(title);
        commentLabel = CreateNodes.createLabelTest(comment);
        gradeLabel = CreateNodes.createLabel2("Grade: " + grade);
        pointsLabel = CreateNodes.createLabel2("Points: " + points + "/" + maxPoints);
        timeSpentLabel = CreateNodes.createLabel2("Time: " + Integer.parseInt(timeSpent)/60 + ":" + Integer.parseInt(timeSpent)%60);

        questionBox.setSpacing(25);
        placeNodes();
    }

    private void placeNodes() {
        header.add(titleLabel, 0, 0);
        GridPane.setColumnSpan(titleLabel, 3);
        header.add(gradeLabel, 0, 1);
        header.add(pointsLabel, 1, 1);
        header.add(timeSpentLabel, 2, 1);
        header.add(commentLabel, 0, 2);
        GridPane.setColumnSpan(commentLabel, 3);
        GridPane.setMargin(commentLabel, new Insets(10, 0, 25, 0));
        commentLabel.setMaxWidth(300);
        commentLabel.setWrapText(true);

        scrollPane.setContent(new VBox(header, questionBox));
        setCenter(scrollPane);
    }


    public void addQuestion(String qTitle, String qPoints, String qMaxPoints, String comment){
        VBox question = new VBox();
        Label qPointsLabel = CreateNodes.createLabel(qPoints + "/" + qMaxPoints);
        Label qTitleLabel = CreateNodes.createLabel2(qTitle);
        Label commentLabel = CreateNodes.createLabel(comment);
        answerBox = new VBox();
        question.getChildren().addAll(new HBox(qTitleLabel, qPointsLabel), answerBox, commentLabel);
        questionBox.getChildren().add(question);
    }

    public void addAnswer(CorrAnswer a){
        answerBox.getChildren().add(a);
    }

    public void addTestToPick(String title, int id){
        testBox.addItem(title, id);
    }

    public void clearBox(){
        testBox.clear();
    }
}
