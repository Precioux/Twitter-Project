package Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public interface ConnectionService {

    /**
     * send to client
     * @param response data
     */
    public void sendToC(String response);

    /**
     * send
     * @param request data
     * @return data
     */
    public String send(String request);
}
