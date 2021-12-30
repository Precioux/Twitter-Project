package Tools;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;

/**
 * this class is jsonTool
 * @author SaminMahdipour
 * @since 12.28.2021
 * @version 3.0
 */
public class JSONtool {
    /**
     *
     * @param object data
     * @return string
     */
    public String toJSON(Object object)
    {
        String value="";
        Gson gson=new Gson();
        try {
            value += gson.toJson(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}