package Network;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import sample.Main;
import sample.NavigationBar;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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
                Platform.runLater(()->{NavigationBar.role.setText(Main.loggedInRole);});

                break;
            case "ERROR":
                Platform.runLater( () -> {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setContentText(split[1]);
                    error.show();
                });
                break;
        }
    }

    public void write(String message){
        out.println(message);
    }



}
