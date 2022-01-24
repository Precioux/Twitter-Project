package entity;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class defines Tweet
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public class Tweet extends TweetEntity {
    /**
     *
     * @param str data
     * @throws IOException check
     */
    public void makeDDU(String str) throws IOException {
        path=str;
        File ddu=new File(path+"\\ddu");
        File like=new File(path+"\\likes");
        FileWriter fw=null;
        FileWriter ff=null;
        try {
            fw=new FileWriter(ddu);
            ff=new FileWriter(like);
            fw.write(date+"\n");
            fw.write(publisher.ID+"\n");
            fw.write(data);
            ff.write("0");
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            fw.close();
            ff.close();
        }
    }

}
