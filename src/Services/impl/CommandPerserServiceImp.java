package Services.impl;

import Services.CommandPerserService;
import Tools.JSONtool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import entity.Account;
import entity.Request;
import requestsFormats.Comment;
import requestsFormats.LogIn;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Scanner;

public class CommandPerserServiceImp implements CommandPerserService {
    static Scanner scanner=new Scanner(System.in);
    static Scanner scanner1=new Scanner(System.in).useDelimiter("\n");
    static JSONtool jsonTool=new JSONtool();
    public static String follow()
    {
        System.out.println("Enter ID of user you intend to follow: ");
        String fid=scanner.next();
        Request request=new Request("follow","following "+fid,fid);
        return jsonTool.toJSON(request);
    }
    public static String comment()
    {
        System.out.println("Enter tweet you intend to comment: ");
        String tweet=scanner1.next();
        System.out.println("Enter your comment");
        String comment=scanner1.next();
        Comment comment1=new Comment(tweet,comment);
        Request request=new Request("comment","to comment",jsonTool.toJSON(comment1));
        return jsonTool.toJSON(request);
    }
    public static String retweet()
    {
        System.out.println("Enter tweet you intend to retweet: ");
        String tweet=scanner1.next();
        Request request=new Request("retweet","to retweet",tweet);
        return jsonTool.toJSON(request);
    }
    public static String like()
    {
        System.out.println("Enter tweet you intend to like: ");
        String tweet=scanner1.next();
        Request request=new Request("like","to like",tweet);
        return jsonTool.toJSON(request);
    }
    public static String remove()
    {
        System.out.println("Enter tweet you intend to remove: ");
        String tweet=scanner1.next();
        Request request=new Request("remove","removing",tweet);
        return jsonTool.toJSON(request);
    }
    public static String tweet()
    {
        System.out.println("Enter tweet: ");

        String tweet=scanner1.next();
        Request request=new Request("tweet","tweeting",tweet);
        return jsonTool.toJSON(request);
    }
    public static String logOut(){
        Request request=new Request("logOut","loggingOut","");
        return jsonTool.toJSON(request);
    }
    public static String logIn() throws JsonProcessingException {
        System.out.println("ID: ");
        String id=scanner.next();
        System.out.println("Password: ");
        String Password=scanner.next();
        LogIn login=new LogIn(id,Password);
        Request request=new Request("logIn","logging In",jsonTool.toJSON(login));
        return jsonTool.toJSON(request);
    }
   public static String SignUp() throws NoSuchAlgorithmException {
       Account account=new Account();
       System.out.println("First Name: ");
       String fname = scanner.next();
       account.addFirstName(fname);
       System.out.println("Last Name: ");
       String lname = scanner.next();
       account.addLastName(lname);
       System.out.println("ID: ");
       String id=scanner.next();
       account.ID=id;
       System.out.println("Birthdate:  (yyyy-mm-dd)" );
       int y,d,m;
       y=scanner.nextInt();
       m=scanner.nextInt();
       d=scanner.nextInt();
       LocalDate bd= LocalDate.of(y,m,d);
       account.addBdate(bd);
       System.out.println("Password: ");
       String password= scanner.next();
       account.addPassword(toHash(password));
       LocalDate l=LocalDate.now();
       account.addjDate(l);
       System.out.println("Bio: (256 characters only!)");
       Scanner input=new Scanner(System.in).useDelimiter("\n");
       String bio=input.next();
       account.addBio(bio);
       Gson gson=new Gson();
       Request request=new Request("signUp","to Sign up",gson.toJson(account));
       System.out.println("CPS 2 : "+gson.toJson(request));
  return gson.toJson(request);
   }
    /**
     *
     * @param password data
     * @return Hash of password
     * @throws NoSuchAlgorithmException for checking algorithm
     */
    public static String toHash(String password) throws NoSuchAlgorithmException {
        return toHexString(getSHA(password));
    }

    /**
     *
     * @param input string
     * @return bytes
     * @throws NoSuchAlgorithmException for checking algorithm
     */
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     *
     * @param hash bytes
     * @return string
     */
    public static String toHexString(byte[] hash)
    {

        BigInteger number = new BigInteger(1, hash);

        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

}
