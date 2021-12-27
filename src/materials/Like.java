package materials;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
/**
 * This class defines like
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public class Like extends TweetMaterial{
    /**
     *
     * @param str data
     */
    public void makeDDU(String str)
    {
       File f=new File("./Data/likes/" + publisher.ID + "/" + str+"/ddu");
       try {
               FileWriter fileWriter = new FileWriter(f);
               fileWriter.write(toString());
               fileWriter.close();

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
        return date+"  Liked by "+publisher.ID+" "+data;
    }
}
