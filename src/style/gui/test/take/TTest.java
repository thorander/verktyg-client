package style.gui.test.take;

import core.Main;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
    private int time, currentQuestion, countTime, countdownSeconds, minutes, countDownMinutes;

    private Label timer, timerUp, questionCounter;

    private Button next, previous, start, turnIn;

    private ArrayList<TQuestion> questions;


    public TTest(String title, String description, int time, int id) {
        this.setTitle(title);
        this.setDescription(description);
        this.setTime(time);
        this.id = id;
        setup();
    }

    private void setup(){


        questions = new ArrayList<>();
        next = CreateNodes.createButton("Next");
        previous = CreateNodes.createButton("Previous");
        start = CreateNodes.createButton("Start");
        turnIn = CreateNodes.createButton("Turn in");
        questionCounter = CreateNodes.createLabel("Question: " + getQuestions());

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

        Thread thread = new Thread(new Runnable () {

            public void run() {
                boolean onTime = false;
                countTime = 0;
                int min = getTime();
                int sec =  getTime()*60;

                if(getTime() != 0){
                    onTime = true;
                }
                countdownSeconds = sec;

                countDownMinutes =  min;

                while(true){
                    try{
                        Thread.sleep(1000);
                        countTime++;

                        if(onTime) {
                            countdownSeconds--;


                            if(countdownSeconds == 0){

                                turnIn();
                            }
                        }

                    }catch (InterruptedException e) {}
                    Platform.runLater(()->{
                        timer.setText("Timer: " + " Minutes: "+countdownSeconds/60+ " Seconds: "+ countdownSeconds%60);
                    });
                }
            }
        });
        thread.start();
    }

    public void start(){

        setTop(timer);
        //setTop(questionCounter);
        setBottom(new HBox(previous, next, turnIn));
        countDownTimer();
        nextQuestion();
        questions.stream().forEach(q -> q.shuffleAnswers());
    }

    public void turnIn(){

        Main.getConnection().write("ADDTAKENTEST"
                + "#" + id
                + "#" + countTime);


        questions.stream().forEach(e -> {
            String question = "ADDUSERQUESTION#" + e.getQId();
            for(Node n : e.getAnswerBox().getChildren()){
                switch(e.getType()){
                    case "One choice":
                        TOneChoiceAnswer choiceAnswer = ((TOneChoiceAnswer)n);
                        if(choiceAnswer.isChecked()){
                            question += "#" + choiceAnswer.getAnswerId() + "#" + (e.getAnswerBox().getChildren().indexOf(n) + 1) + "#" + choiceAnswer.getText() + "#false";
                        }
                        break;
                    case "Multiple choice":
                        TMultipleChoiceAnswer multipleChoiceAnswer = ((TMultipleChoiceAnswer)n);
                        if(multipleChoiceAnswer.isChecked()){
                            question += "#" + multipleChoiceAnswer.getAnswerId() + "#" + (e.getAnswerBox().getChildren().indexOf(n) + 1) + "#" + multipleChoiceAnswer.getText() + "#true";
                        } else {
                            question += "#" + multipleChoiceAnswer.getAnswerId() + "#" + (e.getAnswerBox().getChildren().indexOf(n) + 1) + "#" + multipleChoiceAnswer.getText() + "#false";

                        }
                        break;
                    case "Order":
                        TOrderAnswer orderAnswer = ((TOrderAnswer)n);
                        question += "#" + orderAnswer.getAnswerId() + "#" + (e.getAnswerBox().getChildren().indexOf(n) + 1) + "#" + orderAnswer.getText() + "#false";

                        break;
                    case "Open question":
                        TOpenAnswer openAnswer = ((TOpenAnswer)n);
                        question += "#" + openAnswer.getAnswerId() + "#" + (e.getAnswerBox().getChildren().indexOf(n) + 1) + "#" + openAnswer.getText() + "#false";
                        break;
                }
            }
            Main.getConnection().write(question);
        });

        Main.getConnection().write("PERSISTTAKENTEST#");

        Main.getGUI().FrontPageScreen();
    }
}
