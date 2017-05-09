package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Created by Sofia on 2017-05-09.
 */
public class NavigationBar {

public static HBox navBackgroundImage(){
    Image image = new Image("Images/headerImage.jpg");
    ImageView headerBackground = new ImageView();
    headerBackground.setImage(image);
    headerBackground.setStyle("-fx-opacity: 0.2");

    HBox headerImg = new HBox();
    headerImg.getChildren().addAll( headerBackground);
    return headerImg;
}

public static HBox headline(){
    Label headline = new Label("Testverktyg");
    HBox headlineBox = new HBox();
    headlineBox.setId("navHeadline");
    headlineBox.getChildren().addAll(headline );
    return headlineBox;

}


    public static HBox navAdmin() {

       Label  edit =createLabel("Redigera");
       Label create=createLabel("Skapa test");
       Label statistics=createLabel("Statistik");
       Label gradeTest=createLabel("Rätta prov");
       Label register=createLabel("registrera");

        HBox header = new HBox();
        header.setId("navHeader");
        header.setAlignment(Pos.CENTER);
        header.getChildren().addAll( edit,create,statistics,gradeTest,register);

        return header;

    }
    //Gives the label an id and title
    private static Label createLabel(String title){
        Label label = new Label(title);
        label.setId("navLabel");
        return label;
    }
}
