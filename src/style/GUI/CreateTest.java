package style.GUI;/**
 * Created by Matilda on 2017-05-09.
 */

import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class CreateTest extends Application {

    private GridPane root = new GridPane();

    // Textfield
    private TextField title = new TextField();
    private TextField answer = new TextField();

    // Textarea
    private TextArea question = new TextArea();
    private TextArea description = new TextArea();

    // Buttons
    private Button save = new Button("Save Quiz");

    // Combobox
    private ComboBox answerChoice = new ComboBox();
    private ComboBox dayCounter = new ComboBox();
    private ComboBox timeCounter = new ComboBox();


    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        root.setVgap(5);
        root.setHgap(5);

        // Answer combobox
        answerChoice.getItems().addAll(
                "Option 1",
                "Option 2",
                "Option 3",
                "Option 4"
        );

        // Day combobox
        dayCounter.getItems().addAll(
                "Option 1",
                "Option 2",
                "Option 3",
                "Option 4"
        );

        //Time combobox
        timeCounter.getItems().addAll(
                "Option 1",
                "Option 2",
                "Option 3",
                "Option 4"
        );

        //Title field
        title.setPromptText("Title");
        title.setPrefColumnCount(30);
        root.setConstraints(title, 0, 1);
        root.getChildren().add(title);

        // Description field
        description.setPromptText("Description");
        description.setPrefColumnCount(15);
        //description.setPrefHeight(100);
        root.setConstraints(description, 0, 2);
        root.getChildren().add(description);

        // Day combobox
        dayCounter.setPromptText("Days");
        dayCounter.setEditable(true);
        GridPane.setConstraints(dayCounter, 0, 3);
        root.getChildren().add(dayCounter);

        // Time combobox
        timeCounter.setPromptText("Minutes");
        timeCounter.setEditable(true);
        GridPane.setConstraints(timeCounter, 1, 3);
        root.getChildren().add(timeCounter);

        // Question field
        question.setPrefColumnCount(15);
        question.setPromptText("Question");
        GridPane.setConstraints(question, 0, 4);
        root.getChildren().add(question);

        // Answer combobox
        answerChoice.setPromptText("Svarsalternativ");
        answerChoice.setEditable(false);
        GridPane.setConstraints(answerChoice, 0, 5);
        root.getChildren().add(answerChoice);

        // Answer field
        answer.setPrefColumnCount(15);
        answer.setPromptText("Answer");
        GridPane.setConstraints(answer, 0, 6);
        //grid.getChildren().add();

        // Save button
        GridPane.setConstraints(save, 0, 7);
        root.getChildren().add(save);

        primaryStage.setTitle("Quiz Creator");
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);

        // Add CSS
        scene.getStylesheets().add("style/Stylesheet.css");
        primaryStage.show();

        //Day Combobox action
        dayCounter.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                System.out.println(ov);
                System.out.println(t);
                System.out.println(t1);
            }
        });

        //Time Combobox action
        timeCounter.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                System.out.println(ov);
                System.out.println(t);
                System.out.println(t1);
            }
        });

        //Answer Combobox action
        answerChoice.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                System.out.println(ov);
                System.out.println(t);
                System.out.println(t1);
            }
        });

        //Savebutton action
        save.setOnAction((ActionEvent event) -> {
            try {
                title.getText();
                description.getText();
                question.getText();
                answer.getText();

           /* title.clear();
            description.clear();
            question.clear();
            answer.clear();
            */
            } catch(Exception e) {
                e.printStackTrace();
            }
        });

    }
}
