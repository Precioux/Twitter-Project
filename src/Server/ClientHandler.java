package Server;
import Services.AuthenticationService;
import Services.impl.*;
import Tools.AccountChecker;
import Tools.JSONtool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import entity.Error;
import entity.Request;
import entity.Response;

import java.io.*;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

/**
 * AP-Project-Phase 2
 * @author Samin Mahdipour
 * @since 12.22.2021
 * @version 2.0
 * This class defines clientHandler
 */
public class ClientHandler implements Runnable {
    boolean logFlag=false;
    final BufferedReader bufferedReader;
    final PrintStream printStream;
    final Socket socket;
    AuthenticationServiceImp authenticationServiceImp=new AuthenticationServiceImp();
    CommandPerserServiceImp commandPerserService=new CommandPerserServiceImp();
    ConnectionServiceImp connectionService=null;
    JSONtool jsoNtool=new JSONtool();
    public ClientHandler(Socket socket, BufferedReader bufferedReader, PrintStream printStream)
    {
        this.bufferedReader=bufferedReader;
        this.printStream=printStream;
        this.socket=socket;
        connectionService =  new ConnectionServiceImp(socket,bufferedReader,printStream);
    }
    public void run()
    {
        String response="";
        while (true) {
            try {
                System.out.println("Got to clientHandler");
                String str = bufferedReader.readLine();
                System.out.println("clh rqst: "+str);
                System.out.println("clienthandler reads: "+str);
                Request clientRequest=toRequest(str);
                System.out.println("client sent "+clientRequest.method+" "+clientRequest.ParameterValue);
                if (clientRequest.method.equals("exit")) {
                    System.out.println("Client " + this.socket + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.socket.close();
                    System.out.println("Connection closed");
                    break;
                }
                else {
                    if(clientRequest.method.equals("logIn") && logFlag==false)
                    {
                        response="";
                        Response response1=new Response();
                        System.out.println("clh: "+clientRequest.ParameterValue);
                        int rslt=authenticationServiceImp.begin(1,clientRequest.ParameterValue);
                        System.out.println("clh :"+rslt);
                        if(rslt==0) {
                            response1.addResult("LoggedIn successfully!");
                            response1.setTik();
                            logFlag=true;
                            response += jsoNtool.toJSON(response1);
                        }
                        else
                        {
                            if(rslt==1 || rslt==2)
                            {
                                response="";
                                System.out.println("Wrong result: "+rslt);
                                Response response2=new Response();
                                response2.addResult("LogIn Failed!");
                                Error error=new Error();
                                error.errorSearch(rslt);
                                response2.addError(error);
                                response += jsoNtool.toJSON(response2);
                            }
                        }
                        System.out.println("ClientHandler final res: "+response);
                    }
                    else
                    {
                        if(clientRequest.method.equals("signUp") && logFlag==false)
                        {
                            response="";
                            Response response1=new Response();
                            System.out.println("clh: "+clientRequest.ParameterValue);
                            int rslt=authenticationServiceImp.begin(2,clientRequest.ParameterValue);
                            System.out.println("clh :"+rslt);
                            if(rslt==0)
                            {
                                response1.addResult("Your Account Successfully created!");
                                response1.setTik();
                                logFlag=true;
                                response+=jsoNtool.toJSON(response1);
                            }
                            else
                            {
                                if(rslt==3 || rslt==4)
                                {
                                    response="";
                                    System.out.println("Wrong result: "+rslt);
                                    Response response2=new Response();
                                    response2.addResult("SignUp Failed!");
                                    Error error=new Error();
                                    error.errorSearch(rslt);
                                    response2.addError(error);
                                    response += jsoNtool.toJSON(response2);
                                }
                            }
                            System.out.println("ClientHandler final res: "+response);
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (AuthenticationService.InvalidChoiceException e) {
                e.printStackTrace();
            } catch (AccountChecker.BioException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (AccountChecker.IdException e) {
                e.printStackTrace();
            }

            connectionService.sendToC(response);
        }
        try {
            printStream.close();
            bufferedReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Request toRequest(String json)
    {
        Request request = null;
        try {
            Gson gson=new Gson();
            request = gson.fromJson(json, Request.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return request;
    }
}
