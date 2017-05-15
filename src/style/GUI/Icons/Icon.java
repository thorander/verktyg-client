package style.GUI.Icons;

import javafx.beans.value.WritableValue;
import javafx.css.StyleableProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public abstract class Icon extends Label {

    private ImageView removeImageView = new ImageView();
    private Image removeImageOrange, removeImage;
    private final int SIZE = 25;

    public Icon(){
        this("/Images/removeOrange.png", "/Images/remove.png");
    }


    public Icon(String img_path_1, String img_path_2){
            super();
            removeImageOrange = new Image(img_path_1);
            removeImage = new Image(img_path_2);
            removeImageView = new ImageView(removeImage);
            ((StyleableProperty<Node>)(WritableValue<Node>)graphicProperty()).applyStyle(null, removeImageView);
            removeImageView.setFitHeight(SIZE);
            removeImageView.setFitWidth(SIZE);
            setOnMouseEntered(event -> {
                removeImageView.setImage(removeImageOrange);
            });

            setOnMouseExited(event -> {
                removeImageView.setImage(removeImage);
            });
    }
}
