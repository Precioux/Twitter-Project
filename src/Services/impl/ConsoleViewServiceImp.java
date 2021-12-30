package Services.impl;

import Services.ConsoleViewService;
import Tools.JSONtool;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import entity.Response;

public class ConsoleViewServiceImp implements ConsoleViewService {
    static JSONtool jsonTool=new JSONtool();
    static ObjectMapper mapper=new ObjectMapper();
    public void print( String SResponse)
    {
        Gson gson=new Gson();
        entity.Response response=gson.fromJson(SResponse,Response.class);
        if(response.Count==1)
           System.out.println("ConsolseView: "+SResponse);
        else
        {
            if(response.Count==2)
            {
                if(response.Results.get(0).equals("profile loaded SuccessFully!"))
                {
                    System.out.println("Console view: ");
                    String p=response.Results.get(1);
                    System.out.println("{\"validity\":true,\"error\":\"No Error Found\",\"Count\":2,\"Results\":");
                    System.out.println("[\n"+p+"\n]\n}");
                }
            }
        }

    }
}
