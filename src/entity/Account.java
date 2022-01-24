package entity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * This class defines Account
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.7.2021
 * */
public class Account {
    public String fname;
    public String lname;
    public String ID;
    public String password;
    public LocalDate birthdate;
    public LocalDate joindate;
    public String bio;
    public String photoPath;
    public String birthString;
    public String joinString;

    /**
     * string of birth
     * @param birthString b
     */
    public void addBirthString(String birthString)
    {
        this.birthString=birthString;
    }

    /**
     * string join
     * @param joinString join
     */
    public void addJoinString(String joinString)
    {
        this.joinString=joinString;
    }
    /**
     * add profile photo
     * @param photoPath path
     */
    public void addPhotoPath(String photoPath)
    {
        this.photoPath=photoPath;
    }
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
     * @param joindate data
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
        return password+"\n"+fname+"\n"+lname+"\n"+bio+"\n"+birthdate+"\n"+joindate+"\n"+photoPath;
    }


    /**
     *
     * @return user data
     */
    public String toStringForOverWrite()
    {
        return password+"\n"+fname+"\n"+lname+"\n"+bio+"\n"+birthString+"\n"+joinString+"\n"+photoPath;
    }

    /**
     * Create path
     */
    public void createDirectory()
    {
        try {
            String d = "./Data/Tweets/" + ID;
            String d2 = "./Data/Users/" + ID;
            String d3 = "./Data/comments/" + ID+"/";
            String d4 = "./Data/likes/" + ID+"/";
            String d5 = "./Data/retweets/" + ID+"/";
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
            File followers = new File("./Data/Users/" + ID + "/followers");
            File following = new File("./Data/Users/" + ID + "/following");
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
