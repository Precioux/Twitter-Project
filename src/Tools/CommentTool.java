package Tools;
import entity.Comment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
/**
 * This class defines coomment Tool
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public class CommentTool extends Tool{
    /**
     *
     * @param str addres
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
     * @param Tweet data
     */
    public void add(String Tweet,String com) {
        try {
            LocalDateTime n = LocalDateTime.now();
            String strl = n.toString();
            String str= giveT(strl);
            String Name = "./Data/comments/" + account.ID + "/" + str;
            Path path1 = Paths.get(Name);
            Files.createDirectories(path1);
            Comment comment = new Comment();
            comment.addPublisher(account);
            LocalDate now = LocalDate.now();
            comment.addDate(now);
            comment.addData(Tweet);
            comment.makeDDU(str,com);
       //     System.out.println("Commented Successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
