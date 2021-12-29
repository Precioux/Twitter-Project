package Services.impl;

import Services.CommandPerserService;
import Tools.JSONtool;
import com.fasterxml.jackson.core.JsonProcessingException;
import entity.Request;
import requestsFormats.LogIn;

import java.util.Scanner;

public class CommandPerserServiceImp implements CommandPerserService {
    static Scanner scanner=new Scanner(System.in);
    static JSONtool jsonTool=new JSONtool();
    public static String logIn() throws JsonProcessingException {
        System.out.println("ID: ");
        String id=scanner.next();
        System.out.println("Password: ");
        String Password=scanner.next();
        LogIn login=new LogIn(id,Password);
        Request request=new Request("logIn","logging In",jsonTool.toJSON(login));
        return jsonTool.toJSON(request);
    }



}
