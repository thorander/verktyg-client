package style.GUI.Icons;


import javafx.beans.value.WritableValue;
import javafx.css.StyleableProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Markus on 2017-05-11.
 */
public class RemoveIcon extends Label {

    private ImageView removeImageView = new ImageView("/Images/remove.png");
    private Image removeImageOrange, removeImage;
    private final int SIZE = 25;

    public RemoveIcon(){
        super();
        removeImageOrange = new Image("/Images/removeOrange.png");
        removeImage = new Image("/Images/remove.png");
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
