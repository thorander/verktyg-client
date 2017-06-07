package network;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import core.Main;
import style.gui.CorrectTest;
import style.gui.GUI;
import style.gui.FrontPage;
import style.gui.GUI;
import style.gui.test.correct.CorrAnswer;
import style.gui.test.correct.CorrQuestion;
import style.gui.test.correct.CorrTest;
import style.gui.test.create.StudentGroup;
import style.gui.test.take.TAnswer;
import style.gui.test.take.TQuestion;
import style.gui.test.take.TTest;
import style.gui.test.take.TTestSelect;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Connection class - represents the connection with the server.
 */
public class Connection extends Thread{

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private String ipadress;
    private int port;

    private TTest takeTest;

    private CorrTest test;
    private CorrectTest correctTest;

    public Connection(String ipadress, int port){
        this.ipadress = ipadress;
        this.port = port;
    }

    @Override
    public void run(){
        try{
            socket = new Socket(ipadress, port);
            out = new PrintWriter(new OutputStreamWriter(
                    socket.getOutputStream(), StandardCharsets.UTF_8), true);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), "UTF8"));
        } catch (IOException e){
            System.out.println(e);
        }

        while(true){
            try {
                handleInput(in.readLine());
            } catch (IOException e) {
                System.exit(0);
            }
        }
    }

    private void handleInput(String input){
        String[] split = input.split("#");
        switch(split[0]){
            case "LOGIN":
                Main.loggedInPerson = split[1];
                Main.loggedInRole = split[2];
                Main.loggedInId = split[3];
                Main.getGUI().FrontPageScreen();


                if(split[2].equalsIgnoreCase("admin")){
                    Main.getGUI().loginAdmin();
                } else if (split[2].equalsIgnoreCase("teacher")){
                    Main.getGUI().loginTeacher();
                } else if (split[2].equalsIgnoreCase("student")){
                    Main.getGUI().loginStudent();
                }
                break;
            case "REGSUCCESS":
                Main.getGUI().showPopupMessage("Registration successful", "success");
                Main.getGUI().FrontPageScreen();
                break;
            case "SUCCESS":
                Main.getGUI().showPopupMessage(split[1], "success");
                break;
            case "ALLTESTS":
              Main.getGUI().addTestsToShare(split[1]);
              break;
            case "GETUSERSFORPDF":
                System.out.println("test connection");
                Main.getGUI().addStudentsToPDF(split[1]);
                break;

            case "GETSTUDENTSTOSHARE":
                Main.getGUI().addStudentsToShare(split[1]);
                break;
            case "GETGROUPSTOSHARE":
                Main.getGUI().addGroupsToShare(input);
                break;
            case "GETUTEST": // Hämtar test
               Main.getGUI().addUTestToPDF(split[1]);
                break;
            case "ERROR":
                Platform.runLater( () -> Main.getGUI().showPopupMessage(split[1], "error"));
                break;
            case "AVAILABLETESTS":
                Platform.runLater(() -> {
                    Main.getGUI().getTestSelectScreen().clearOptions();
                    for(int i = 1; i < split.length; i++){
                        Main.getGUI().getTestSelectScreen().addOption(split[i++]);
                        Main.getGUI().getTestSelectScreen().addId(split[i]);
                    }
                    Main.getGUI().getTestSelectScreen().selectFirst();
                });
                break;
            case "STUDENTSFORGROUP": // Get/set students in the group-class
                String[] users = split[1].split("@");
                System.out.println(input);
                for(int i = 0; i < users.length; ){
                    Main.getGUI().getStudentGroup().addUser(users[i++], users[i++]);
                }
                break;
            case "TAKETEST":
                takeTest = new TTest(split[1], split[2], Integer.parseInt(split[3]), Integer.parseInt(split[4]));
                break;
            case "ADDTQUESTION":
                TQuestion tQuestion = new TQuestion(split[1], split[2], Integer.parseInt(split[3]));
                for(int i = 5; i < split.length; i++){
                    TAnswer tAnswer = new TAnswer(split[i++], Integer.parseInt(split[i++]), false);
                    i++;
                    i++;
                    tQuestion.addAnswer(tAnswer);
                }
                takeTest.addQuestion(tQuestion);
                break;
            case "SHOWTEST":
                Main.getGUI().setMainContent(takeTest);
                break;
            case "GETTESTUSER":
                for(int i = 1; i < split.length;){
                    Main.getGUI().getCorrectTest().addUser(split[i++], Integer.parseInt(split[i++]));
                }
                break;
            case "UTEST": // Saves all questions, several optitions questions etc. so they show up while you correct the tests
                    test = new CorrTest(Integer.parseInt(split[1]), split[2]);
                    Main.getGUI().setMainContent(test);

                    CorrQuestion c = null;
                    for(int i = 3; i < split.length;) {
                        if (split[i].equals("UQUESTION")) {
                            i++;
                            c = new CorrQuestion(Integer.parseInt(split[i++]), split[i++], Integer.parseInt(split[i++]));
                            test.addCorrQuestion(c);
                        } else if (split[i].equals("ANSWER")) {
                            i++;
                            CorrAnswer canswer = new CorrAnswer(split[i++], split[i++], Boolean.parseBoolean(split[i++]), Boolean.parseBoolean(split[i++]), split[i++]);
                            c.addAnswer(canswer);
                        }
                    }
                System.out.println(input);
                break;
            case "GETTESTLIST": // Get all tests
                correctTest = Main.getGUI().getCorrectTest();
                for(int i = 1; i < split.length;) {
                    correctTest.setTestList(Integer.parseInt(split[i++]), split[i++]);
                }
                break;
            case "asdasd":
                correctTest = Main.getGUI().getCorrectTest();
                for(int i = 1; i < split.length;) {
                    correctTest.setTestUser(split[i++]);
                }
                break;
            case "ADDTESTS":
                    System.out.println(input);
                for(int i = 1; i < split.length;){
                    Main.getGUI().getStatistic().addTest(split[i++], split[i++]);
                }
            case "ADDGROUPS":
                for(int i = 1; i < split.length;){
                    Main.getGUI().getStatistic().addGroup(split[i++], split[i++]);
                }
                break;
            case "UPDATESTATS":
                Main.getGUI().getStatistic().updateStats(split[1],split[2],split[3],split[4],split[5],split[6]);
                break;
            case "COMBOBOX":
                Main.getGUI().getCombo();


                break;
            case "UTESTSFORRESULTPAGE":
                System.out.println("UTESTS FOR RESULT PAGE");
                for(int i = 1; i < split.length;){
                    Main.getGUI().addTestToResult(split[i++], Integer.parseInt(split[i++]));
                }
                break;
            case "UTESTFORRESULTPAGE":
                Main.getGUI().newResultTest(split[1], split[2], split[3], split[4], split[5]);
                System.out.println(input);
                for(int i = 6; i < split.length;) {
                    if (split[i].equals("UQUESTION")) {
                        i++;
                        Main.getGUI().addQuestionToResult(split[i++], split[i++], split[i++]);
                    } else if (split[i].equals("ANSWER")) {
                        i++;
                        CorrAnswer canswer = new CorrAnswer(split[i++], split[i++], Boolean.parseBoolean(split[i++]), Boolean.parseBoolean(split[i++]), split[i++]);
                        Main.getGUI().addAnswerToResult(canswer);
                    }
                }
                break;
        }
    }


    public void write(String message){
        out.println(message);
    }



}
