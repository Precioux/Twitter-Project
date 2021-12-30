package Services.impl;

import Services.ConsoleViewService;
import Tools.JSONtool;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConsoleViewServiceImp implements ConsoleViewService {
    static JSONtool jsonTool=new JSONtool();
    static ObjectMapper mapper=new ObjectMapper();
    public void print( String Response)
    {

        System.out.println("ConsolseView: "+Response);

    }
}
