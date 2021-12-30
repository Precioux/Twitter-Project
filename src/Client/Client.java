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
import entity.Request;
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
            Gson gson=new Gson();
            BufferedReader bufferedReaderFromB=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream printStream=new PrintStream(socket.getOutputStream());
            ConnectionServiceImp connectionService=new ConnectionServiceImp(socket,bufferedReaderFromB,printStream);
            String clientRequest,serverResponse;

            while (true)
            {
                System.out.println("logFlag: "+logFlag);
                if(logFlag) {
                    System.out.println("\nAvailable choices: tweet, removeTweet, comment, like, socialize, logOut, exit");
                    clientRequest = scanner.next();
                    if (clientRequest.equals("exit")) {
                        System.out.println("Closing this connection : " + socket);
                        socket.close();
                        System.out.println("Connection closed");
                        break;
                    }
                    else
                    {
                        switch (clientRequest)
                        {
                            case "logOut":
                            {
                                String request=commandPerserService.logOut();
                                String response=connectionService.send(request);
                                consoleView.print(response);
                                logFlag=false;
                                break;
                            }
                            case "tweet":
                            {
                                String request=commandPerserService.tweet();
                                String response=connectionService.send(request);
                                consoleView.print(response);
                                break;
                            }
                            case "removeTweet":
                            {
                                String request=commandPerserService.remove();
                                String response=connectionService.send(request);
                                consoleView.print(response);
                                break;
                            }
                            case "like":
                            {
                                String request=commandPerserService.like();
                                String response=connectionService.send(request);
                                consoleView.print(response);
                                break;
                            }
                            case "retweet":
                            {
                                String request=commandPerserService.retweet();
                                String response=connectionService.send(request);
                                consoleView.print(response);
                                break;
                            }
                            case "comment":
                            {
                                String request=commandPerserService.comment();
                                String response=connectionService.send(request);
                                consoleView.print(response);
                                break;
                            }
                            case "socialize":
                            {
                                System.out.println("follow, unfollow, (view) profile(in order to like,ret or comment)");
                                String c=scanner.next();
                                switch (c)
                                {
                                    case "follow":
                                    {

                                        String request=commandPerserService.follow();
                                        String response=connectionService.send(request);
                                        consoleView.print(response);
                                        break;
                                    }
                                    case "unfollow":
                                    {

                                        String request=commandPerserService.unfollow();
                                        String response=connectionService.send(request);
                                        consoleView.print(response);
                                        break;
                                    }
                                    case "profile":
                                    {
                                        String request=commandPerserService.profile();
                                        String response=connectionService.send(request);
                                        consoleView.print(response);
                                        Response response1=gson.fromJson(response,Response.class);
                                        if(response1.validity)
                                        {
                                            System.out.println("Profile is "+true);
                                            Request previousRequest=gson.fromJson(request,Request.class);
                                            System.out.println("reacting to "+previousRequest.ParameterValue);
                                            String requestAction=commandPerserService.action(previousRequest.ParameterValue);
                                            if(!request.equals(null)) {
                                                System.out.println("sending request : "+requestAction);
                                                String responseAction = connectionService.send(requestAction);
                                                consoleView.print(responseAction);
                                            }
                                            else break;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
//                    printStream.println(clientRequest);
//                    serverResponse = bufferedReaderFromB.readLine();
//                    System.out.println("Server : " + serverResponse);
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
                   // Gson gson=new Gson();
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
                         //   Gson gson=new Gson();
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
