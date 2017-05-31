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
import style.gui.components.CustomComboBox;
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
  private ComboBox test;
  private CustomComboBox user;
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
    private List<String> list = new ArrayList<>();

  public CorrectTest(){
      root = CreateNodes.createBorderPane();
      root.setMaxWidth(800);
      root.setMaxHeight(550);

      createHeader();
      createFooter();

  }

   /* public CorrectTest(int i, String s) {
        this.id = i;
        this.testname = s;

        testList = Arrays.asList(testname);
        idList = Arrays.asList(id);
        userList = Arrays.asList(users);
        System.out.println("list: "+testList);
    }

    public CorrectTest(String e) {
        this.users = e;
        userList = Arrays.asList(users);
        System.out.println("user: " + userList);
    }*/
    
  private void createHeader(){

      headline = CreateNodes.createHeader("Correct test");
      test = CreateNodes.createComboBox("Select test");
      user = CreateNodes.createCustomComboBox("Select student");

      test.setOnAction(e -> {
          user.clear();
          Main.getConnection().write("SENDTESTNAME#"+test.getValue());
          Main.getConnection().write("GETUSERLIST#");
      });

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
            //System.out.println(test.getValue());
            Main.getConnection().write("SENDTESTNAME#"+test.getValue());
            Main.getConnection().write("SENDUSERID#"+user.getSelectedId());

      });
      BorderPane.setAlignment(grade, Pos.BOTTOM_RIGHT);
     BorderPane.setMargin(grade, new Insets(15,20,15,15));

  }

  public BorderPane getCorrectTestContent(){
      return root;
  }

  public void setTestList(int i, String s) {
      this.id = i;
      this.testname = s;
      test.getItems().add(testname);
  }

    public void setTestUser(String s) {
        this.users = s;
        user.getItems().add(users);
    }

    public void addUser(String username, int id){
      user.addItem(username, id);
    }

}
