package style.gui.components;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import style.gui.CreateNodes;

/**
 * Custom Label which has a text field as well.
 * When label is clicked, switch it out for the text field to edit the content.
 * When it loses focus we switch back to the label with the new content.
 */

public class EditableLabel extends VBox {

    private Label label;
    private TextField textField;
    private String string;
    private CustomToolTip labelTip;

    public EditableLabel() {
        super();
        label = new Label();
        labelTip = new CustomToolTip("Click this label to edit its contents");
        Tooltip.install(label, labelTip);
        textField = CreateNodes.createText();
        string = "";
        setup();
    }

    public EditableLabel(String string) {
        this();
        this.string = string;
        textField.setText(string);
        label.setText(string);
    }

    private void setup() {
        getChildren().add(label);
        label.setOnMouseClicked(e -> {
            getChildren().remove(label);
            getChildren().add(textField);
            textField.requestFocus();
        });

        textField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                string = textField.getText();
                label.setText(string);
                getChildren().remove(textField);
            }
        });

        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                } else {
                    string = textField.getText();
                    label.setText(string);
                    getChildren().remove(textField);
                    getChildren().add(label);
                }
            }
        });
    }

    public void setWidth(int width) {
        label.setMaxWidth(width);
        label.setMinWidth(width);
        textField.setMaxWidth(width);
        textField.setMinWidth(width);
    }

    public void setWrapText(boolean bool) {
        label.setWrapText(bool);
    }

    public String getText() {
        return string;
    }


}
