package Network;

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
        Scanner scin = new Scanner(System.in);

        while(true){
            if(scin.hasNextLine()){
                out.println(scin.nextLine());
            }
        }
    }
}
