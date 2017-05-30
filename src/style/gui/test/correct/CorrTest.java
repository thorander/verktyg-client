package style.gui.test.correct;

import core.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import style.gui.components.DateTimePicker;
import style.gui.components.EditableLabel;
import style.gui.test.create.CQuestion;
import style.gui.test.create.CreateNodes;

import java.util.List;

/**
 * Created by Markus on 2017-05-28.
 */
public class CorrTest extends BorderPane {

        private int id;
        private Label testName;
        private Button doneCorrecting;
        private HBox buttonBox;
        private VBox qBox;
        private final int HEIGHT = 600, WIDTH = 500;

        public CorrTest(int id, String testName){
            this.id = id;
            this.testName = CreateNodes.createHeader(testName);
            setId("loginStyle");
            System.out.println(id);
            System.out.println(testName+"");

            doneCorrecting = CreateNodes.createButton("Submit");

            buttonBox = new HBox();
            buttonBox.getChildren().addAll(doneCorrecting);
            buttonBox.setAlignment(Pos.BOTTOM_CENTER);
            buttonBox.setMargin(doneCorrecting, new Insets(15, 0, 0, 50));
            setTop(this.testName);
            setBottom(buttonBox);

            qBox = new VBox();
            ScrollPane sp = new ScrollPane();
            sp.setContent(qBox);
            setCenter(sp);
            setMaxWidth(WIDTH);
            setMinWidth(WIDTH);
            setMinHeight(HEIGHT);
            setMaxHeight(HEIGHT);
            sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            DropShadow drop = new DropShadow(50, Color.GRAY);
            setEffect(drop);
            setPadding(new Insets(50, 50, 50, 50));
        }

        public void addCorrQuestion(CorrQuestion q){
            qBox.setMargin(q, new Insets(25, 0, 0, 0));
            qBox.getChildren().add(q);
        }
}
