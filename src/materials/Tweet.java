package materials;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
/**
 * This class defines Tweet
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public class Tweet extends TweetMaterial{
    /**
     *
     * @param str data
     * @throws IOException check
     */
    public void makeDDU(String str) throws IOException {
        path=str;
        File ddu=new File(path+"\\ddu");
        FileWriter fw=null;
        try {
            fw=new FileWriter(ddu);
            fw.write(date+"\n");
            fw.write(publisher.ID+"\n");
            fw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            fw.close();
        }
    }

}
