package entity;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class defines Tweet material
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public class TweetEntity {
    public Account publisher=new Account();
    protected String path="";
    protected String data="";
    protected String date="";
    public class OFException extends Exception {};

    /**
     *
     * @param publisher data
     */
    public void addPublisher(Account publisher)
    {
        this.publisher=publisher;
    }

    /**
     * @param data adder
     */
    public void addData(String data)
    {
        try {
            if(data.length()<=256)
                this.data += data;
            else throw new Tweet.OFException();
        }
        catch (Tweet.OFException e)
        {
            System.out.println("More than 256 characters!");
        }
    }
    /**
     *
     * @param date adder
     * @param time data
     */
    public void addDate(LocalDate date, LocalTime time)
    {
        this.date=date+" "+time;
    }
    /**
     *
     * @return data
     */
    public String toString()
    {
        return publisher.ID+"\n"+data+"\n"+date;
    }

    /**
     *
     * @param str data
     * @throws IOException check
     */
    protected void makeDDU(String str) throws IOException {}
}
