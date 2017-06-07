package style.gui.test.create;

import core.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.converter.NumberStringConverter;
import style.gui.components.CustomComboBox;
import style.gui.test.take.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A factory class for creating nodes and test data
 */
public class CreateNodes {


    //Gives the label an id and title
    public static Label createLabel(String title) {
        Label label = new Label(title);
        label.setId("label");
        return label;
    }
    //Gives the label an id and title
    public static Label createLabel2(String title) {
        Label label = new Label(title);
        label.setId("labelType2");
        return label;
    }
    public static Label createLabelTest(String title){
        Label label = new Label(title);
        label.setId("labelTest");
        return label;
    }
    //Gives the TextField an id and title
   public static TextField createText() {
       TextField textField = new TextField();
       textField.setId("input");
       return textField;
   }
    //Gives the TextField an id and title
    public static TextField createTextPromt(String promptText) {
        TextField textField = new TextField();
        textField.setId("input");
        textField.setPromptText(promptText);
        return textField;
    }
    //Gives the TextField an id and title
    public static Button createButton(String title) {
       Button button = new Button(title);
        button.setId("button");
        return button;
    }
    public static Label createHeader(String title) {
        Label label = new Label(title);
        label.setId("headline");
        return label;
    }
    public static ComboBox createComboBox(String promptText){
        ComboBox cb = new ComboBox();
        cb.setPromptText(promptText);
        cb.setId("combobox");
        return cb;
    }
    public static GridPane createGrid(){
        GridPane grid= new GridPane();
        grid.setId("loginStyle");
        DropShadow drop = new DropShadow(50, Color.GRAY);
        grid.setEffect(drop);
        return grid;

    }
    public static BorderPane createBorderPane(){
        BorderPane root= new BorderPane();
        root.setId("loginStyle");
        DropShadow drop = new DropShadow(50, Color.GRAY);
        root.setEffect(drop);
        return root;

    }

    public static TextField createNumberCorrectingField(int maxPoints){
        TextField numberField = createText();
        numberField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        numberField.setMaxWidth(32);
        numberField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    numberField.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if(!numberField.getText().equals("") && Integer.parseInt(numberField.getText()) > maxPoints){
                    numberField.setText(maxPoints + "");
                }
            }
        });
        return numberField;
    }

    public static TTest getTestTest(){
        TTest test = new TTest("Testtest", "A test where we test the testing", 0, 5);

        TQuestion multipleChoiceQuestion = new TQuestion("Hur många barn får jag när jag blir stor?", "Multiple choice", 1);
        multipleChoiceQuestion.addAnswer(new TMultipleChoiceAnswer("Två", 2, true));
        multipleChoiceQuestion.addAnswer(new TMultipleChoiceAnswer("Tre", 3, false));

        TQuestion oneChoiceQuestion = new TQuestion("Seven of...", "One choice", 2);
        oneChoiceQuestion.addAnswer(new TOneChoiceAnswer("Five", 4, false, oneChoiceQuestion.getGroup()));
        oneChoiceQuestion.addAnswer(new TOneChoiceAnswer("Two", 5, false, oneChoiceQuestion.getGroup()));
        oneChoiceQuestion.addAnswer(new TOneChoiceAnswer("Nine", 6, true, oneChoiceQuestion.getGroup()));

        TQuestion orderQuestion = new TQuestion("Rangordna efter ålder (äldst högst upp): ", "Order", 3);
        orderQuestion.addAnswer(new TOrderAnswer("Rasmus", 7));
        orderQuestion.addAnswer(new TOrderAnswer("Sofia", 7));
        orderQuestion.addAnswer(new TOrderAnswer("Markus", 7));

        TQuestion openQuestion = new TQuestion("Skriv en dikt: ", "Open question",4);
        openQuestion.addAnswer(new TOpenAnswer("Vad ska stå här?", 1));

        test.addQuestion(multipleChoiceQuestion);
        test.addQuestion(oneChoiceQuestion);
        test.addQuestion(orderQuestion);
        test.addQuestion(openQuestion);
        return test;
    }

    public static CustomComboBox createCustomComboBox(String s) {
        CustomComboBox cb = new CustomComboBox();
        cb.setPromptText(s);
        cb.setId("combobox");
        return cb;
    }

}
