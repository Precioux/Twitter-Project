package Server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.util.Scanner;
import static java.lang.System.*;

/**
 * AP-Project-Phase 2
 * @author Samin Mahdipour
 * @since 12.22.2021
 * @version 2.0
 * This class defines Server
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1000);

        while (true)
        {
            Socket socket=null;
            try {
                socket=serverSocket.accept();
                out.println("A new client joined");

                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream printStream=new PrintStream(socket.getOutputStream());

                out.println("attaching client handler");
                Thread thread=new ClientHandler(socket,bufferedReader,printStream);
                thread.start();
            }
            catch (Exception e){
                socket.close();
                e.printStackTrace();
            }
        }
    }

}
