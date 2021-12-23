package Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.time.LocalDate;
/**
 * AP-Project-Phase 2
 * @author Samin Mahdipour
 * @since 12.22.2021
 * @version 2.0
 * This class defines clientHandler
 */
public class ClientHandler extends Thread {
    final BufferedReader bufferedReader;
    final PrintStream printStream;
    final Socket socket;
    /**
     * Constructor
     * @param socket data
     * @param bufferedReader data
     * @param printStream data
     */
    public ClientHandler(Socket socket, BufferedReader bufferedReader, PrintStream printStream)
    {
        this.bufferedReader=bufferedReader;
        this.printStream=printStream;
        this.socket=socket;
    }

    /**
     * Run method for thread
     */
    public void run()
    {
        String clientRequest="";
        String response="";

        while (true) {
            try {
                printStream.println("Enter your request: ");
                clientRequest = bufferedReader.readLine();
                if (clientRequest.equals("exit")) {
                    System.out.println("Client " + this.socket + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.socket.close();
                    System.out.println("Connection closed");
                    break;
                }
                response="";
                switch (clientRequest) {
                    case "Hey": {
                        response += "Hey back!";
                        printStream.println(response);
                        break;
                    }
                    case "Date": {
                        LocalDate date = LocalDate.now();
                        response += date.toString();
                        printStream.println(response);
                        break;
                    }
                    case "City": {
                        response += "Tehran";
                        printStream.println(response);
                        break;
                    }
                    case "Bye": {
                        response += "Bye Bye";
                        printStream.println(response);
                        break;
                    }
                    default: {
                        printStream.println("Sorry I didn't get that!");
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            printStream.close();
            bufferedReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
