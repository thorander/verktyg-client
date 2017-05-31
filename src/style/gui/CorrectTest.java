package style.gui;

import core.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import style.gui.test.correct.CorrAnswer;
import style.gui.test.correct.CorrQuestion;
import style.gui.test.correct.CorrTest;
import style.gui.test.create.CreateNodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private int id;
    private String testname;
    private String users;
    private List<String> testList;
    private List<Integer> idList;
    private List<String> userList;

  public CorrectTest(){
      root = CreateNodes.createBorderPane();
      root.setMaxWidth(800);
      root.setMaxHeight(550);

      createHeader();
      createFooter();

  }

    public CorrectTest(int i, String s) {
        this.id = i;
        this.testname = s;
        //this.users = a;
        testList = Arrays.asList(testname);
        idList = Arrays.asList(id);
        //userList = Arrays.asList(users);
        System.out.println(testList +" been here");
    }
    
  private void createHeader(){

      headline = CreateNodes.createHeader("Correct test");
      test = CreateNodes.createComboBox("Select test");
      user = CreateNodes.createComboBox("Select student");

      test.getItems().add(testList+"");

      top = new HBox(20);
      top.getChildren().addAll(headline, test,user);
      top.setPadding(new Insets(15,0,15,0));
      top.setAlignment(Pos.CENTER);

      root.setTop(top);
  }
  private void createFooter(){
      grade = CreateNodes.createButton("Grade");
      root.setBottom(grade);
      grade.setOnAction(e -> {
            Main.getConnection().write("GETTEST#");
            CorrTest test = new CorrTest(1, "How to do shit.");
            CorrQuestion question = new CorrQuestion(2, "How to peel banana?", 3);
            CorrQuestion question2 = new CorrQuestion(3, "Why in the world??", 5);
            test.addCorrQuestion(question);
            test.addCorrQuestion(question2);
            question.addAnswer(new CorrAnswer("At top", true, true));
            question.addAnswer(new CorrAnswer("At top", true, false));
            question.addAnswer(new CorrAnswer("At bottom", false, false));
            question.addAnswer(new CorrAnswer("At bottom", false, true));
            question2.addAnswer(new CorrAnswer("Because", true, true));
            question2.addAnswer(new CorrAnswer("Därför", true, true));
            question2.addAnswer(new CorrAnswer("Therefore", true, false));
            Main.getGUI().setMainContent(test);
      });
      BorderPane.setAlignment(grade, Pos.BOTTOM_RIGHT);
     BorderPane.setMargin(grade, new Insets(15,20,15,15));

  }

  public BorderPane getCorrectTestContent(){
      return root;
  }

}
