package style.GUI;/**
 * Created by Matilda on 2017-05-09.
 */

import Network.Connection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;



public class CreateTest {

    private static GridPane root;
    private static TextField title; // Textfield
    private static TextField answer1;
    private static TextField answer2;
    private static TextField answer3;
    private static  TextField answer4;
    private static TextField answer5;
    private static TextArea description; // Textarea
    private static TextArea question;
    private static Button save; // Buttons
    private static ComboBox answerChoice; // Combobox
    private static Spinner<Integer> timeCounter; // Spinner
    private static Label timeLabel; // Label
    private static Connection connection; // Connection


    public CreateTest(Connection connection){
        this.connection = connection;
        Quiz();
    }


    public static GridPane Quiz() {

        root = new GridPane();
        title = new TextField();
        answer1 = new TextField();
        answer2 = new TextField();
        answer3 = new TextField();
        answer4 = new TextField();
        answer5 = new TextField();
        description = new TextArea();
        question = new TextArea();
        save = new Button("Save Quiz");
        answerChoice = new ComboBox();
        timeCounter = new Spinner<Integer>();
        timeLabel = new Label();

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

        // Time label
        timeLabel.setText("Minuter: ");
        GridPane.setConstraints(timeLabel, 0, 3);
        root.getChildren().add(timeLabel);

        // Time spinner
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 120);
        timeCounter.setValueFactory(valueFactory);
        timeCounter.setEditable(true);
        timeCounter.setMaxWidth(80);
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


        //Scene scene = new Scene(root, 900, 800);


        //Answer Combobox action
        answerChoice.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                System.out.println(ov);
                System.out.println(t);
                System.out.println(t1);

                try {
                    if(t1.equals("Option 1")) {
                        root.getRowConstraints().clear();
                        root.getChildren().addAll(answer1, answer2);
                    } else if(t1.equals("Option 2")) {
                        root.getChildren().addAll(answer3, answer4, answer5);
                    }

                } catch(Exception e) {
                    System.out.println("Something went wrong: "+e);
                }
            }
        });

        // Save button action
        save.setOnAction((ActionEvent event) -> {
            try {
                String uTitle = title.getText();
                String uDescription = description.getText();
                String uQuestion = question.getText();
                Integer uTime = timeCounter.getValue();
                String uAnswerChoice = answerChoice.getValue().toString();
                String uAnswer1 = answer1.getText();

                connection.write("CREATEQUIZ#" + uTitle + "#" + uDescription + "#" + uQuestion + "#" + uTime + "#" + uAnswerChoice + "#" + uAnswer1);
            } catch(Exception e) {
                System.out.println("Something went wrong: "+e);
            }
        });

        return root;
    }

    public GridPane getRoot(){
        return root;
    }

}
