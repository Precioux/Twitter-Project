package entity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
/**
 * This class defines like
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.7.2021
 * */
public class Like extends TweetEntity {
    /**
     *
     * @param str data
     */
    public void makeDDU(String str)
    {
       File f=new File("./Data/likes/" + publisher.ID + "/" + str+"/ddu");
        File ff=new File("./Data/likes/" + publisher.ID + "/" + str+"/ddg");
       try {
               FileWriter fileWriter = new FileWriter(f);
               fileWriter.write(toString());
               fileWriter.close();
           FileWriter fileWriter2 = new FileWriter(ff);
           fileWriter2.write(date+"\n"+publisher.ID+"\nLiked\n"+data);
           fileWriter2.close();

       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }

    }

    /**
     *
     * @return data
     */
    public String toString()
    {
        return date+"  Liked by "+publisher.ID+" [ "+data+" ]";
    }
}
