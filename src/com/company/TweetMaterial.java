package com.company;

import java.io.IOException;
import java.time.LocalDate;
/**
 * This class defines Tweet material
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public class TweetMaterial {
    public Account publisher=new Account();
    protected String path="";
    protected String data="";
    protected LocalDate date;
    public class OFException extends Exception {};

    /**
     *
     * @param publisher data
     */
    protected void addPublisher(Account publisher)
    {
        this.publisher=publisher;
    }

    /**
     * @throws Tweet.OFException checker
     * @param data adder
     */
    protected void addData(String data)
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
     */
    protected void addDate(LocalDate date)
    {
        this.date=date;
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
