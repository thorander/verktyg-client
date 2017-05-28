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

    public CorrAnswer(String text, boolean correct, boolean answered){

        if(correct){
            correctIcon = new CorrectIcon();
        } else {
            correctIcon = new IncorrectIcon();
        }
            getChildren().add(correctIcon);

        Label answerLabel = CreateNodes.createLabel2(text);

        getChildren().add(answerLabel);

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
