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
import com.google.gson.stream.JsonReader;
import entity.Account;
import entity.Response;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
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
                System.out.println("logFlag: "+logFlag);
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
                    System.out.println("1-SignIn\n2-SignUp");
                    int s=scanner.nextInt();
                    if(s==1)
                    {
                    String request=commandPerserService.logIn();
                    System.out.println("req: "+request);
                    String response=connectionService.send(request);
                    System.out.println("res: "+response);
                    consoleView.print(response);
                    Gson gson=new Gson();
                    Response response1=gson.fromJson(response,Response.class);
                    if(response1.validity)
                        logFlag=true;
                    }
                    else
                    {
                        if(s==2)
                        {
                            String request=commandPerserService.SignUp();
                            System.out.println("req: "+request);
                            String response=connectionService.send(request);
                            System.out.println("res: "+response);
                            consoleView.print(response);
                            Gson gson=new Gson();
                            Response response1=gson.fromJson(response,Response.class);
                            if(response1.validity)
                                logFlag=true;
                        }

                        }
                    }
                }
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            noSuchAlgorithmException.printStackTrace();
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
