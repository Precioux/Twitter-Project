package Server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Scanner;
import static java.lang.System.*;

/**
 * AP-Project-Phase 3
 * @author Samin Mahdipour
 * @since 12.22.2021
 * @version 3.0
 * This class defines Server
 */
public class Server {
    /**
     * main
     * @param args data
     * @throws IOException exception
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(getPort());

        while (true)
        {
            Socket socket=null;
            try {
                socket=serverSocket.accept();
                out.println("A new client joined");

                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream printStream=new PrintStream(socket.getOutputStream());

                out.println("attaching client handler");
                ClientHandler clientHandler=new ClientHandler(socket,bufferedReader,printStream);
                Thread thread=new Thread(clientHandler);
                thread.start();
            }
            catch (Exception e){
                 socket.close();
                e.printStackTrace();
            }
        }
    }

    /**
     * get port
     * @return port
     */
    public static int getPort()
    {
        String port="";
        try {
            InputStream inputStream=new FileInputStream("./Config/propertyServer.properties");
            Properties properties=new Properties();
            properties.load(inputStream);
            port= properties.getProperty("port");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(port);
    }
}
