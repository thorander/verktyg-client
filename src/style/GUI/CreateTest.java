package style.GUI;/**
 * Created by Matilda on 2017-05-09.
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
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
    private TextField answer1 = new TextField();
    private TextField answer2 = new TextField();
    private TextField answer3 = new TextField();
    private TextField answer4 = new TextField();
    private TextField answer5 = new TextField();

    // Textarea
    private TextArea description = new TextArea();
    private TextArea question = new TextArea();

    // Buttons
    private Button save = new Button("Save Quiz");

    // Combobox
    private ComboBox answerChoice = new ComboBox();
    private ComboBox timeCounter = new ComboBox();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        root.setVgap(10);
        root.setHgap(10);
        root.setPadding(new Insets(5, 5, 5, 5));

        // Answer combobox
        answerChoice.getItems().addAll(
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

        // Time combobox
        timeCounter.setPromptText("Minutes");
        timeCounter.setEditable(true);
        GridPane.setConstraints(timeCounter, 0, 3);
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

        // Answer fields
        answer1.setPrefColumnCount(10);
        answer1.setPromptText("Answer one");
        GridPane.setConstraints(answer1, 0, 6);
        //root.getChildren().add(answer1);

        answer2.setPrefColumnCount(10);
        answer2.setPromptText("Answer two");
        GridPane.setConstraints(answer2, 0, 7);
        //root.getChildren().add(answer2);

        answer3.setPrefColumnCount(10);
        answer3.setPromptText("Answer three");
        GridPane.setConstraints(answer3, 0, 8);
        //root.getChildren().add(answer3);

        answer4.setPrefColumnCount(10);
        answer4.setPromptText("Answer four");
        GridPane.setConstraints(answer4, 0, 9);
        //root.getChildren().add(answer4);

        answer5.setPrefColumnCount(10);
        answer5.setPromptText("Answer five");
        GridPane.setConstraints(answer5, 0, 10);
        //root.getChildren().add(answer5);

        // Save button
        GridPane.setConstraints(save, 0, 11);
        root.getChildren().add(save);

        primaryStage.setTitle("Quiz Creator");
        Scene scene = new Scene(root, 900, 800);
        primaryStage.setScene(scene);

        // Add CSS
        scene.getStylesheets().add("style/Stylesheet.css");
        primaryStage.show();

        //Time Combobox action
        timeCounter.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                System.out.println(ov);
                System.out.println(t);
                System.out.println(t1);

                try {
                    //Do something
                    System.out.println(timeCounter.getItems());
                } catch(Exception e) {
                    System.out.println("Something went wrong: "+e);
                }
            }
        });

        //Answer Combobox action
        answerChoice.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                System.out.println(ov);
                System.out.println(t);
                System.out.println(t1);

                try {
                    if(t1.equals("Option 1")) {
                        root.getChildren().addAll(answer1, answer2);
                    } else if(t1.equals("Option 2")) {
                        root.getChildren().addAll(answer3, answer4, answer5);
                    }

                } catch(Exception e) {
                    System.out.println("Something went wrong: "+e);
                }
            }
        });

        //Savebutton action
        save.setOnAction((ActionEvent event) -> {
            try {
                title.getText();
                description.getText();
                timeCounter.getCellFactory();
                question.getText();
                //answer.getText();

           /* title.clear();
            description.clear();
            question.clear();
            answer.clear();
            */

            } catch(Exception e) {
                System.out.println("Something went wrong: "+e);
            }
        });

    }

}
