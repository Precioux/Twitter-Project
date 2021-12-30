package Services.impl;

import Services.ConnectionService;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class ConnectionServiceImp implements ConnectionService {
    Socket socket;
    BufferedReader bufferedReader;
    PrintStream printStream;

    public ConnectionServiceImp(Socket socket, BufferedReader bufferedReader, PrintStream printStream) {
        this.socket = socket;
        this.bufferedReader = bufferedReader;
        this.printStream = printStream;
    }
    public void sendToC(String response)
    {
        System.out.println("Connection Service SS res: "+response);
        printStream.println(response);
    }
    public String send(String request)
    {
        System.out.println("Connection ServiceCS rqst: "+request);
        String response="";
        try {
            printStream.println(request);
            response+= bufferedReader.readLine();
            System.out.println("ConnectionService CS rspnse: "+response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
