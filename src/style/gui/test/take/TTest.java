package style.gui.test.take;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import style.gui.test.create.CreateNodes;

import java.util.*;

public class TTest extends BorderPane {

    private Label title;
    private String description;
    private int id;
    private int time, currentQuestion;
    private Label timer, timerUp;

    private Button next, previous, start, turnIn;

    private ArrayList<TQuestion> questions;


    public TTest(String title, String description, int time, int id){
        this.setTitle(title);
        this.setDescription(description);
        this.setTime(time);
        setup();
    }

    private void setup(){


        questions = new ArrayList<>();
        next = CreateNodes.createButton("Next");
        previous = CreateNodes.createButton("Previous");
        start = CreateNodes.createButton("Start");
        turnIn = CreateNodes.createButton("Turn in");

        timer = CreateNodes.createLabel("");


        currentQuestion = -1;
        setBottom(new HBox(start));
        next.setOnAction(e -> nextQuestion());
        previous.setOnAction(e -> previousQuestion());
        start.setOnAction(e -> start());
        turnIn.setOnAction(e -> turnIn());
        setCenter(title);
        HBox.setMargin(next, new Insets(0, 0, 0, 40));
        setMaxWidth(600);
        setMaxHeight(500);
        setId("loginStyle");
        setPadding(new Insets(10, 10, 10, 10));
        DropShadow shadow = new DropShadow(50, Color.GRAY);
        setEffect(shadow);
    }

    public String getTitle() {
        return title.getText();
    }

    public void setTitle(String title) {
        this.title = CreateNodes.createLabel(title);
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

    public void nextQuestion(){
        System.out.println("Clicked next");
        if(currentQuestion < questions.size() -1){
            TQuestion q = questions.get(++currentQuestion);
            setCenter(q);
            System.out.println(q.getTitle());
        }
    }

    public void previousQuestion(){
        System.out.println("Clicked previous");
        if(currentQuestion > 0){
            --currentQuestion;
            setCenter(questions.get(currentQuestion));
        } else if (currentQuestion == 0){
            currentQuestion--;
            setCenter(title);
        }
    }

    private void countDownTimer() {


        final int minutes =  getTime();
        final int seconds = getTime();
        final int up = 0;


        Thread thread = new Thread(new Runnable () {

            public void run() {
                int countdownSeconds;
                countdownSeconds = minutes;
                int countUpSeconds = up;

                for (int i = countdownSeconds ; i >= 0; i--) {

                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e) {}
                    Platform.runLater(()->{
                        timer.setText("Minutes: " + minutes );
                    });

                    System.out.println(i);
                }
                /*for (int i = countUpSeconds ; i >= 0; i++) {

                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e) {}
                    Platform.runLater(()->{
                        timerUp.setText("Minutes: " + minutes);
                    });

                    System.out.println(i);
                }*/
            }
        });
        thread.start();
    }

    public void start(){

        setTop(timer);

        setBottom(new HBox(previous, next, turnIn));
        countDownTimer();
        nextQuestion();
    }

    public void turnIn(){
        System.out.println("Here will be the code for turning in a test");
    }
}
