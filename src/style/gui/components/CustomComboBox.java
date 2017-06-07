package style.gui.components;

import javafx.scene.control.ComboBox;

import java.util.ArrayList;

/**
 * Custom component which also stores the database id of something put in this combobox
 */
public class CustomComboBox<T> extends ComboBox<T> {

    ArrayList<Integer> ids;

    public CustomComboBox(){
        super();
        ids = new ArrayList<>();
    }

    public void addItem(T item, int id){
        getItems().add(item);
        this.ids.add(id);
    }

    public int getSelectedId(){
        return ids.get(getItems().indexOf(getValue()));
    }

    public void clear(){
        getItems().clear();
        ids.clear();
    }
}
