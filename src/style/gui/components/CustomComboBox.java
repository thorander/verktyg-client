package style.gui.components;

import javafx.scene.control.ComboBox;

import java.util.ArrayList;

/**
 * Created by Markus on 2017-05-30.
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
