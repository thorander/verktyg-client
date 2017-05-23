package style.gui.test.take;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TAnswerBox extends VBox {

    private TOrderAnswer dragging;
    private TOrderAnswer target;

    public TAnswerBox(){

    }

    private void setDraggable(TOrderAnswer answer){
        Label handler = answer.getDragLabel();
        handler.setOnDragDetected(e -> {answer.startFullDrag();
            dragging = answer;});
        System.out.println("Setting draggable");

        answer.setOnMouseDragEntered(e -> {target = answer; answer.setStyle("-fx-border-width: 1px; -fx-border-color: darkorange; -fx-background-color: #ffc56b;");});
        answer.setOnMouseDragExited(e -> {target = null; answer.setStyle("");});

        answer.setOnMouseDragReleased(e -> {
            if(target != null){
                int answerId = getChildren().indexOf(dragging);
                int targetId = getChildren().indexOf(target);
                String targetText = ((TAnswer)getChildren().get(targetId)).getText();
                String draggedText = ((TAnswer)getChildren().get(answerId)).getText();
                int targetSwitchId = ((TAnswer)getChildren().get(targetId)).getAnswerId();
                int draggedSwitchId = ((TAnswer)getChildren().get(answerId)).getAnswerId();
                ((TAnswer)getChildren().get(answerId)).setText(targetText);
                ((TAnswer)getChildren().get(targetId)).setText(draggedText);
                ((TAnswer)getChildren().get(answerId)).setAnswerId(targetSwitchId);
                ((TAnswer)getChildren().get(targetId)).setAnswerId(draggedSwitchId);
            }
        });
    }

    public void addAnswer(TAnswer t){
        if(t instanceof TOrderAnswer){
            setDraggable((TOrderAnswer) t);
        }

        getChildren().add(t);
    }


}
