package style.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import style.gui.test.create.CreateNodes;

/**
 * Created by Sofia on 2017-05-24.
 */
public class CorrectTest {
  private BorderPane root;
  private ComboBox test, user;
  private GridPane grid;
  private HBox top,bottom;
  private Label headline;
  private Button grade;

  public CorrectTest(){
      root = CreateNodes.createBorderPane();
      root.setMaxWidth(800);
      root.setMaxHeight(550);

      createHeader();
      createFooter();









  }
  private void createHeader(){

      headline = CreateNodes.createHeader("Correct test");
      test = CreateNodes.createComboBox("Select test");
      user = CreateNodes.createComboBox("Select student");

      top = new HBox(20);
      top.getChildren().addAll(headline, test,user);
      top.setPadding(new Insets(15,0,15,0));
      top.setAlignment(Pos.CENTER);

      root.setTop(top);
  }
  private void createFooter(){
      grade = CreateNodes.createButton("Grade");
      root.setBottom(grade);
      BorderPane.setAlignment(grade, Pos.BOTTOM_RIGHT);
     BorderPane.setMargin(grade, new Insets(15,20,15,15));

  }

  public BorderPane getCorrectTestContent(){
      return root;
  }

}
