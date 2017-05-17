package style.gui;

import core.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import style.gui.components.NavigationBar;

import javax.swing.*;

/**
 * Created by phili on 2017-05-16.
 */
public class FrontPage {
    private static GridPane grid;
    private static Label welcome;
    private static Label info;
    private static ImageView icon;
    private static ImageView hat;
    private static Label pic;
    private static Label hatLabel;

    public static GridPane setup() {

        grid = new GridPane();
        icon = new ImageView("images/back2school.png");
        pic = new Label("", icon);
        hat = new ImageView("images/hat.png");
        hatLabel = new Label("", hat);


        welcome = createLabel("Welcome "+Main.loggedInPerson+" to your digital test tool");
        info = createLabel("Here you can take tests that your teacher has created\nfor you to test your skills in different subjects\n");

        grid.add(welcome, 0, 2,4,1);
        grid.add(info,0,3, 4, 1);
        grid.add(icon,4,0);
        grid.add(hat,0,0);
        icon.setId("frontIcon");
        hat.setId("frontHat");


        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(5, 10, 5, 10));

        grid.setMaxWidth(800);
        grid.setMaxHeight(900);

        return grid;
    }

    //Gives the label an id and title
    private static Label createLabel(String title) {
        Label label = new Label(title);
        label.setId("frontPage");
        return label;
    }


    public static GridPane getRoot() {
        return grid;
    }
}
