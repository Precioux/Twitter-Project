package Client;
/**
 * This class defines client
 * @author Samin Mahdipour
 * @version 2.0
 * @since 12.7.2021
 * */
import Services.impl.CommandPerserServiceImp;
import Services.impl.ConnectionServiceImp;
import Services.impl.ConsoleViewServiceImp;
import com.google.gson.Gson;
import entity.Response;

import java.io.*;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class Client {
    static boolean logFlag=false;
    static Scanner scanner=new Scanner(System.in);
    static CommandPerserServiceImp commandPerserService=new CommandPerserServiceImp();
    static ConsoleViewServiceImp consoleView=new ConsoleViewServiceImp();
    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket(getHost(), getPort());

            BufferedReader bufferedReaderFromB=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream printStream=new PrintStream(socket.getOutputStream());
            ConnectionServiceImp connectionService=new ConnectionServiceImp(socket,bufferedReaderFromB,printStream);
            String clientRequest,serverResponse;

            while (true)
            {
                if(logFlag) {
                    System.out.println("\nAvailable choices: logOut, exit");
                    clientRequest = scanner.next();
                    if (clientRequest.equals("exit")) {
                        System.out.println("Closing this connection : " + socket);
                        socket.close();
                        System.out.println("Connection closed");
                        break;
                    }
                    printStream.println(clientRequest);
                    serverResponse = bufferedReaderFromB.readLine();
                    System.out.println("Server : " + serverResponse);
                }
                else
                {
                    System.out.println("Log in first!");
                    String request=CommandPerserServiceImp.logIn();
                    String response=connectionService.send(request);
                    consoleView.print(response);
                    Gson gson=new Gson();
                    Response response1=gson.fromJson(response,Response.class);
                    if(response1.validity)
                        logFlag=true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int getPort()
    {
        String port="";
        try {
            InputStream inputStream=new FileInputStream("./Config/propertyClient.properties");
            Properties properties=new Properties();
            properties.load(inputStream);
            port+= properties.getProperty("port");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(port);
    }
    public static String getHost()
    {
        String Host="";
        try {
            InputStream inputStream=new FileInputStream("./Config/propertyClient.properties");
            Properties properties=new Properties();
            properties.load(inputStream);
            Host+= properties.getProperty("host");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Host;
    }

}
