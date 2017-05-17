package network;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import core.Main;
import style.gui.GUI;
import style.gui.FrontPage;
import style.gui.GUI;
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
                GUI.FrontPageScreen();


                if(split[2].equalsIgnoreCase("admin")){
                    GUI.loginAdmin();
                } else if (split[2].equalsIgnoreCase("teacher")){
                    GUI.loginTeacher();
                } else if (split[2].equalsIgnoreCase("student")){
                    GUI.loginStudent();
                }
                break;
            case "REGSUCCESS":
                GUI.showPopupMessage("Registration successful", "success");
                GUI.loginScreen();
                break;

            case "ERROR":
                Platform.runLater( () -> {
                    GUI.showPopupMessage(split[1], "error");
                });
                break;
            case "AVAILABLETESTS":
                Platform.runLater(() -> {
                    TTestSelect.clearOptions();
                    for(int i = 1; i < split.length; i++){
                        TTestSelect.addOption(split[i]);
                    }
                    TTestSelect.selectFirst();
                });
                break;
        }
    }


    public void write(String message){
        out.println(message);
    }



}
