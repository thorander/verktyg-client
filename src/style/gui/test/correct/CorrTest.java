package style.gui.test.correct;

import core.Main;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import style.gui.components.DateTimePicker;
import style.gui.components.EditableLabel;
import style.gui.test.create.CQuestion;
import style.gui.CreateNodes;

import java.util.List;

/**
 * The component which holds data about a test while correcting it.
 */
public class CorrTest extends BorderPane {

        private int id;
        private Label testName;
        private Button doneCorrecting;
        private GridPane buttonBox;
        private VBox qBox;
        private final int HEIGHT = 600, WIDTH = 500;
        private TextArea commentArea;

        private Alert alert;

        public CorrTest(int id, String testName){
            this.id = id;
            this.testName = CreateNodes.createHeader(testName);
            setId("loginStyle");
            System.out.println(id);
            System.out.println(testName+"");

            commentArea = new TextArea();
            commentArea.setMaxWidth(300);
            commentArea.setMaxHeight(100);
            commentArea.setWrapText(true);

            doneCorrecting = CreateNodes.createButton("Save");

            doneCorrecting.setOnAction(e -> {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Din rättning är nu sparad!");
                alert.showAndWait();

                String send = "CORRECT#" + id + "#" + commentArea.getText() + "#";
                for(Node o : qBox.getChildren()){
                    CorrQuestion temp = ((CorrQuestion)o);
                    send += temp.getUpdateQuestion();
                }

                Main.getConnection().write(send);
            });

            buttonBox = new GridPane();
            buttonBox.add(commentArea, 0, 0);
            buttonBox.add(doneCorrecting, 0, 1);
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
            Platform.runLater(() -> {
                qBox.setMargin(q, new Insets(25, 0, 0, 0));
                qBox.getChildren().add(q);

            });
        }
}
