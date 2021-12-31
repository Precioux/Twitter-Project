package Services.impl;
import Services.ConnectionService;
import java.io.*;
import java.net.Socket;
import java.util.Properties;

/**
 * this class defines connection service
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.30.2021
 */
public class ConnectionServiceImp implements ConnectionService {
    Socket socket;
    BufferedReader bufferedReader;
    PrintStream printStream;

    /**
     * this is constructor
     * @param socket data
     * @param bufferedReader data
     * @param printStream data
     */
    public ConnectionServiceImp(Socket socket, BufferedReader bufferedReader, PrintStream printStream) {
        this.socket = socket;
        this.bufferedReader = bufferedReader;
        this.printStream = printStream;
    }

    /**
     * send to client
     * @param response data
     */
    public void sendToC(String response)
    {
        printStream.println(response);
    }

    /**
     * send
     * @param request data
     * @return response
     */
    public String send(String request)
    {
        String response="";
        try {
            printStream.println(request);
            response+= bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
