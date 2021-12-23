package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class defines Account
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public class Account {
    protected String fname;
    protected String lname;
    protected String ID;
    private String password;
    protected LocalDate birthdate;
    protected LocalDate joindate;
    public String bio;
    /**
     *
     * @param fname data
     */
    public void addFirstName(String fname)
    {
        this.fname=fname;
    }

    /**
     *
     * @param lname data
     */
    public void addLastName(String lname)
    {
        this.lname=lname;
    }

    /**
     *
     * @param ID data
     */
    public void addID(String ID)
    {
        this.ID=ID;
    }

    /**
     *
     * @param birthdate data
     */
    public void addBdate(LocalDate birthdate)
    {
        this.birthdate=birthdate;
    }

    /**
     *
     * @param bio data
     */
    public void addBio(String bio)
    {
        this.bio=bio;
    }

    /**
     *
     * @param password data
     */
    public void addPassword(String password)
    {
        this.password=password;
    }

    /**
     * add join date
     */
    public void addjDate(LocalDate joindate)
    {
        this.joindate=joindate;
    }


    /**
     *
     * @return user data
     */
    public String toString()
    {
        return password+"\n"+fname+"\n"+lname+"\n"+bio+"\n"+birthdate+"\n"+joindate;
    }

    /**
     * Create path
     */
    public void createDirectory()
    {
        try {
            String d = "./Tweets/" + ID;
            String d2 = "./Users/" + ID;
            String d3 = "./comments/" + ID+"/";
            String d4 = "./likes/" + ID+"/";
            String d5 = "./retweets/" + ID+"/";
            Path path4 = Paths.get(d4);
            Files.createDirectories(path4);
            Path path5 = Paths.get(d5);
            Files.createDirectories(path5);
            Path path3 = Paths.get(d3);
            Files.createDirectories(path3);
            Path path1 = Paths.get(d);
            Files.createDirectories(path1);
            Path path2 = Paths.get(d2);
            Files.createDirectories(path2);
            ffmaker();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * make following and follower files
     */
    protected void ffmaker()
    {

        try {
            File followers = new File("./Users/" + ID + "/followers");
            File following = new File("./Users/" + ID + "/following");
            FileWriter fw1=new FileWriter(followers);
            fw1.write("");
            FileWriter fw2=new FileWriter(following);
            fw2.write("");
            fw1.close();
            fw2.close();
            if(!followers.exists() || !following.exists())
                throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
