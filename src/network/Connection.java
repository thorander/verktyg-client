package network;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import core.Main;
import style.gui.GUI;
import style.gui.FrontPage;
import style.gui.GUI;
import style.gui.test.create.StudentGroup;
import style.gui.test.take.TAnswer;
import style.gui.test.take.TQuestion;
import style.gui.test.take.TTest;
import style.gui.test.take.TTestSelect;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Connection extends Thread{

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private String ipadress;
    private int port;

    private TTest takeTest;

    private StudentGroup studentGroup;

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
                Main.getGUI().loginScreen();
                break;
            case "ALLTESTS":
              Main.getGUI().addTestsToShare(split[1]);
              break;

            case "GETSTUDENTS":
                System.out.println("test connection");
                Main.getGUI().addStudentsToShare(split[1]);
                System.out.println(" " + split[1]);
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
            case "USERSFORGROUP":
                for(int i = 1; i < split.length; i++){
                    StudentGroup.addUsers(split[i]);
                }
                break;
            case "GETGROUPS":
                for(int i = 1; i < split.length; i++){
                    StudentGroup.addGroups(split[i]);
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

        }
    }


    public void write(String message){
        out.println(message);
    }



}
