package Tools;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;

public class JSONtool {
    public String toJSON(Object object)
    {
        String value="";
        Gson gson=new Gson();
        try {
            value += gson.toJson(object);
            //     System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}