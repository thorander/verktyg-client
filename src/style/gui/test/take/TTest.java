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
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import style.gui.CreateNodes;

import java.util.*;

public class TTest extends BorderPane {

    private Label title;
    private String description;
    private int id;
    private int time,questionCounter, currentQuestion, countTime, countdownSeconds;
    private HBox show;
    private Label timer, numberOfQuestion;

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

        show = new HBox(50);
        questions = new ArrayList<>();
        next = CreateNodes.createButton("Next");
        previous = CreateNodes.createButton("Previous");
        start = CreateNodes.createButton("Start");
        turnIn = CreateNodes.createButton("Turn in");

        numberOfQuestion = CreateNodes.createLabel("" );
        questionCounter = 1;

        timer = CreateNodes.createLabel("");


        currentQuestion = -1;
        setBottom(new HBox(start));
        next.setOnAction(e -> nextQuestion());
        previous.setOnAction(e -> previousQuestion());
        start.setOnAction(e -> start());
        turnIn.setOnAction(e -> turnIn());
        setCenter(title);
        show.setMinWidth(300);
        HBox.setHgrow(timer, Priority.ALWAYS);
        show.getChildren().addAll(timer, numberOfQuestion);
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
            numberOfQuestion.setText("Question: " + (currentQuestion+2) + " of " + getQuestions().size());
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
            numberOfQuestion.setText("Question: " + (currentQuestion+1) + " of " + getQuestions().size());
        }
    }

    private void countDownTimer() {

        Thread thread = new Thread(new Runnable () {

            public void run() {
                boolean onTime = false;
                countTime = 0;
                int sec =  getTime()*60;

                if(getTime() != 0){
                    onTime = true;
                }
                countdownSeconds = sec;

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
                        timer.setText(countdownSeconds/60+ ":"+ countdownSeconds%60);
                    });
                }
            }
        });
        thread.start();
    }

    public void start(){


        setTop(show);

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
                            question += "#" + choiceAnswer.getAnswerId() + "#" + (e.getAnswerBox().getChildren().indexOf(n) + 1) + "#" + choiceAnswer.getText() + "#true";
                        } else {
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
                        question += "#" + openAnswer.getAnswerId() + "#" + (e.getAnswerBox().getChildren().indexOf(n) + 1) + "#" + openAnswer.getOpenWriting() + "#false";
                        break;
                }
            }
            Main.getConnection().write(question);
        });

        Main.getConnection().write("PERSISTTAKENTEST#");

        Main.getGUI().FrontPageScreen();
    }
}
