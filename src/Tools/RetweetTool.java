package Tools;
import entity.Retweet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
/**
 * This class defines Retweet Tool
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.7.2021
 * */
public class RetweetTool extends Tool{
    /**
     *
     * @param str address
     * @return code
     */
    private String giveT(String str) {

        Scanner scan = new Scanner(str).useDelimiter("-");
        String p = scan.next(); //y
        p += scan.next(); //m
        String e = scan.next(); //d
        Scanner sca = new Scanner(e).useDelimiter("T");
        p += sca.next();
        String ee = sca.next();
        Scanner scann = new Scanner(ee).useDelimiter(":");
        p += scann.next();
        p += scann.next();

        return p;
    }

    /**
     *
     * @param likedTweet data
     */
    public void add(String likedTweet) {
        try {
            LocalDateTime n = LocalDateTime.now();
            LocalTime localTime=LocalTime.now();
            String strl = n.toString();
            String str= giveT(strl);
            String Name = "./Data/retweets/" + account.ID + "/" + str;
            Path path1 = Paths.get(Name);
            Files.createDirectories(path1);
            Retweet retweeT = new Retweet();
            retweeT.addPublisher(account);
            LocalDate now = LocalDate.now();
            retweeT.addDate(now,localTime);
            retweeT.addData(likedTweet);
            retweeT.makeDDU(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
