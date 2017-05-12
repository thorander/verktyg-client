package style.GUI.components;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

/**
 * Created by Markus on 2017-05-11.
 */
public class EditableLabel extends VBox {

    private Label label;
    private TextField textField;
    private String string;

    public EditableLabel(){
        super();
        label = new Label();
        textField = new TextField();
        string = "";
        setup();
    }

    public EditableLabel(String string){
        this();
        this.string = string;
        textField.setText(string);
        label.setText(string);
    }

    private void setup(){
        getChildren().add(label);
        label.setOnMouseClicked(e->{
            getChildren().remove(label);
            getChildren().add(textField);
            textField.requestFocus();
        });

        textField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                string = textField.getText();
                label.setText(string);
                getChildren().remove(textField);
            }
        });

        textField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue){}
                else
                {
                    string = textField.getText();
                    label.setText(string);
                    getChildren().remove(textField);
                    getChildren().add(label);
                }
            }
        });
    }

    public void setWidth(int width){
        label.setMaxWidth(width);
        label.setMinWidth(width);
        textField.setMaxWidth(width);
        textField.setMinWidth(width);
    }

    public void setWrapText(boolean bool){
        label.setWrapText(bool);
    }

    public String getText(){
        return string;
    }




}
