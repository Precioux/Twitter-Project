package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
/**
 * This class defines comment
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.7.2021
 * */
public class Comment extends TweetEntity {
    /**
     * @param cmt comment
     * @param str address
     */
    public void makeDDU(String str,String cmt)
    {
        File f=new File("./Data/comments/" + publisher.ID + "/" + str+"/ddu");
        File ff=new File("./Data/comments/" + publisher.ID + "/" + str+"/ddg");
        try {
                FileWriter fileWriter = new FileWriter(f);
                fileWriter.write(toString()+cmt+" >  on  " +data);
                fileWriter.close();
            FileWriter fileWriter2 = new FileWriter(ff);
            fileWriter2.write(date+"\n"+publisher.ID+ "\nCommented\n"+cmt+" on " +data);
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
        return date+"  "+publisher.ID+ " Commented < ";
    }
}
