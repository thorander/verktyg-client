package style.gui.test.correct;

import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.HBox;
import style.gui.icons.CorrectIcon;
import style.gui.icons.Icon;
import style.gui.icons.IncorrectIcon;
import style.gui.test.create.CreateNodes;

/**
 * Created by Markus on 2017-05-28.
 */
public class CorrAnswer extends HBox {

    private Icon correctIcon;
    private String type, text, order;
    private boolean correct, answered;

    public CorrAnswer(String type, String text, boolean correct, boolean answered, String order){
        this.type = type;
        this.text = text;
        this.correct = correct;
        this.answered = answered;
        this.order = order;
        setup();
    }

    private void setup(){
        switch(type){
            case "Open question":
                setAnsweredText();
                answered = true;
                setStyle(getStyle() + "-fx-border-width: 2px; -fx-border-color: #d7d7d7;");
                setAnsweredStyle();
                break;
            case "Order":
                text = order + " " + text;
                System.out.println(text);
                setAnsweredText();
                break;
            case "Multiple choice":
                addIcon();
                setAnsweredText();
                setAnsweredStyle();
                break;
            case "One choice":
                addIcon();
                setAnsweredText();
                setAnsweredStyle();
                break;
        }
    }

    private void addIcon(){
        if(correct){
            correctIcon = new CorrectIcon();
        } else {
            correctIcon = new IncorrectIcon();
        }
        getChildren().add(correctIcon);
    }

    private void setAnsweredText(){
        Label answerLabel = CreateNodes.createLabel2(text);
        answerLabel.setMaxWidth(300);
        answerLabel.setWrapText(true);

        getChildren().add(answerLabel);
    }

    private void setAnsweredStyle(){
        if(answered){
            getStyleClass().add("answered");
        } else {
            getStyleClass().add("unanswered");
            ColorAdjust grayScale = new ColorAdjust();
            grayScale.setSaturation(-1);
            grayScale.setContrast(10);
            grayScale.setBrightness(-50);
            setEffect(grayScale);
        }
    }

}
