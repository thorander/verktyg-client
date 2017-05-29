package core;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Markus on 2017-05-29.
 */
public class User {

    private SimpleStringProperty name;
    private SimpleIntegerProperty id;

    public User(String name, int id){
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
    }

    public SimpleStringProperty getNameProperty(){
        return name;
    }

    public String getName(){
        return name.get();
    }

    public SimpleIntegerProperty getIdProperty(){
        return id;
    }

    public int getId(){
        return id.get();
    }

    public void setId(int id){
        this.id.set(id);
    }

    public void setName(String name){
        this.name.set(name);
    }

    public String toString(){
        return name.get();
    }
}
