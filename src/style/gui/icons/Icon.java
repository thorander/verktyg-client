package style.gui.icons;

import javafx.beans.value.WritableValue;
import javafx.css.StyleableProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public abstract class Icon extends Label {

    private ImageView imageView = new ImageView();
    private Image orangeImage, image;
    private int size = 25;

    public Icon(){
        super();
        imageView = new ImageView();
        ((StyleableProperty<Node>)(WritableValue<Node>)graphicProperty()).applyStyle(null, imageView);
        setSize(size);
    }

    public Icon(String img_path){
        this();
        image = new Image(img_path);
        imageView.setImage(image);
    }


    public Icon(String img_path_1, String img_path_2){
            this();
            orangeImage = new Image(img_path_1);
            image = new Image(img_path_2);
            imageView.setImage(image);
            ((StyleableProperty<Node>)(WritableValue<Node>)graphicProperty()).applyStyle(null, imageView);
            setOnMouseEntered(event -> {
                imageView.setImage(orangeImage);
            });

            setOnMouseExited(event -> {
                imageView.setImage(image);
            });
    }

    public void setSize(int size){
        this.size = size;
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);
    }
}
