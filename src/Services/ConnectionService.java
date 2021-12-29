package Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public interface ConnectionService {


    public void sendToC(String response);

    public String send(String request);
}
