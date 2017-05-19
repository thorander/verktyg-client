package style.gui.test.create;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import style.gui.test.take.*;

/**
 * Created by Sofia on 2017-05-17.
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

    public static TTest getTestTest(){
        TTest test = new TTest("Testtest", "A test where we test the testing", 0);

        TQuestion multipleChoiceQuestion = new TQuestion("Hur många barn får jag när jag blir stor?", "Multiple choice");
        multipleChoiceQuestion.addAnswer(new TMultipleChoiceAnswer("Två", 2, true));
        multipleChoiceQuestion.addAnswer(new TMultipleChoiceAnswer("Tre", 3, false));

        TQuestion oneChoiceQuestion = new TQuestion("Seven of...", "One choice");
        oneChoiceQuestion.addAnswer(new TOneChoiceAnswer("Five", 4, false, oneChoiceQuestion.getGroup()));
        oneChoiceQuestion.addAnswer(new TOneChoiceAnswer("Two", 5, false, oneChoiceQuestion.getGroup()));
        oneChoiceQuestion.addAnswer(new TOneChoiceAnswer("Nine", 6, true, oneChoiceQuestion.getGroup()));

        TQuestion orderQuestion = new TQuestion("Rangordna efter ålder (äldst högst upp): ", "Order");
        orderQuestion.addAnswer(new TOrderAnswer("Rasmus", 7));
        orderQuestion.addAnswer(new TOrderAnswer("Sofia", 7));
        orderQuestion.addAnswer(new TOrderAnswer("Markus", 7));

        TQuestion openQuestion = new TQuestion("Skriv en dikt: ", "Open");
        openQuestion.addAnswer(new TOpenAnswer("Vad ska stå här?", 1));

        test.addQuestion(multipleChoiceQuestion);
        test.addQuestion(oneChoiceQuestion);
        test.addQuestion(orderQuestion);
        test.addQuestion(openQuestion);
        return test;
    }
}
