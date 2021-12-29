package Tools;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
/**
 * This class defines property Tool
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.28.2021
 * */
public class PropertyTool {
    /**
     * add to property
     * @param string data
     * @param num data
     */
    public void add(String string,String num) {
        try {
            File file = new File("./Config/propertyClient.properties");
            InputStream inputStream = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(inputStream);
            properties.setProperty(string,num);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
