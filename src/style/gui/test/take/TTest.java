package style.gui.test.take;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import style.gui.test.create.CreateNodes;

import java.util.ArrayList;

public class TTest extends BorderPane {

    private String title;
    private String description;
    private int time, currentQuestion;
    private Button next, previous;

    private ArrayList<TQuestion> questions;

    public TTest(String title, String description, int time){
        this.setTitle(title);
        this.setDescription(description);
        this.setTime(time);
        setup();
    }

    private void setup(){
        questions = new ArrayList<>();
        next = CreateNodes.createButton("Next");
        previous = CreateNodes.createButton("Previous");
        currentQuestion = 0;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public ArrayList<TQuestion> getQuestions(){return questions;}

    public void addQuestion(TQuestion q){questions.add(q);}
}
