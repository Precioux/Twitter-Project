package Services.impl;
import Services.CommandPerserService;
import Tools.JSONtool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import entity.Account;
import entity.Request;
import requestsFormats.Comment;
import requestsFormats.ForOther;
import requestsFormats.LogIn;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Scanner;
/**
 * This class defines command perser service
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.30.2021
 * */
public class CommandPerserServiceImp implements CommandPerserService {
    static Scanner scanner=new Scanner(System.in);
    static Scanner scanner1=new Scanner(System.in).useDelimiter("\n");
    static JSONtool jsonTool=new JSONtool();
    /**
     * timeline request
     * @return request
     */
    public static String timeLine()
    {
        Request request=new Request("timeLine","timeline","");
        return jsonTool.toJSON(request);
    }

    /**
     *
     * @param who data
     * @return request
     */
    public static String action(String who)
    {
        boolean wrong=false;
        String f="";
        System.out.println("If you want to React to any tweet enter tweet's index otherwise enter -1");
        int c=scanner.nextInt();
        String choice="";
        if(c!=-1)
        {
            System.out.println("Comment ,Like ,Retweet?");
            ForOther forOther=null;
            choice+=scanner.next();
            switch (choice)
            {
                case "Comment":
                {
                    System.out.println("Enter your comment ");
                    String comment=scanner1.next();
                    forOther=new ForOther(c,who,2,comment);
                    break;
                }
                case "Like":
                {
                    forOther=new ForOther(c,who,1,"");
                    break;
                }
                case "Retweet":
                {
                    forOther=new ForOther(c,who,3,"");
                    break;
                }
                default:{
                    wrong=true;
                    break;}
            }
            if(!wrong){
            Request request=new Request("action","reacting",jsonTool.toJSON(forOther));
            f+=jsonTool.toJSON(request);}
            else f+="-";
        }
        else f+="-";
        return f;
    }

    /**
     * request for profile
     * @return request
     */
    public static String profile()
    {
        System.out.println("Enter ID of user you intend to view: ");
        String fid=scanner.next();
        Request request=new Request("profile","view "+fid,fid);
        return jsonTool.toJSON(request);
    }

    /**
     * request for unfollow
     * @return request
     */
    public static String unfollow()
    {
        System.out.println("Enter ID of user you intend to unfollow: ");
        String fid=scanner.next();
        Request request=new Request("unfollow","unfollowing "+fid,fid);
        return jsonTool.toJSON(request);
    }

    /**
     * request for follow
     * @return request
     */
    public static String follow()
    {
        System.out.println("Enter ID of user you intend to follow: ");
        String fid=scanner.next();
        Request request=new Request("follow","following "+fid,fid);
        return jsonTool.toJSON(request);
    }

    /**
     * request for comment
     * @return request
     */
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

    /**
     * request for retweet
     * @return request
     */
    public static String retweet()
    {
        System.out.println("Enter tweet you intend to retweet: ");
        String tweet=scanner1.next();
        Request request=new Request("retweet","to retweet",tweet);
        return jsonTool.toJSON(request);
    }

    /**
     * request for like
     * @return request
     */
    public static String like()
    {
        System.out.println("Enter tweet you intend to like: ");
        String tweet=scanner1.next();
        Request request=new Request("like","to like",tweet);
        return jsonTool.toJSON(request);
    }

    /**
     * request for remove
     * @return request
     */
    public static String remove()
    {
        System.out.println("Enter tweet you intend to remove: ");
        String tweet=scanner1.next();
        Request request=new Request("remove","removing",tweet);
        return jsonTool.toJSON(request);
    }

    /**
     * request for TWEET
     * @return request
     */
    public static String tweet()
    {
        System.out.println("Enter tweet: ");
        String tweet=scanner1.next();
        Request request=new Request("tweet","tweeting",tweet);
        return jsonTool.toJSON(request);
    }

    /**
     * request for logOut
     * @return request
     */
    public static String logOut(){
        Request request=new Request("logOut","loggingOut","");
        return jsonTool.toJSON(request);
    }

    /**
     * request for logIn
     * @return request
     * @throws JsonProcessingException exception
     */
    public static String logIn() throws JsonProcessingException {
        System.out.println("ID: ");
        String id=scanner.next();
        System.out.println("Password: ");
        String Password=scanner.next();
        LogIn login=new LogIn(id,Password);
        Request request=new Request("logIn","logging In",jsonTool.toJSON(login));
        return jsonTool.toJSON(request);
    }

    /**
     *
     * @return request for sign up
     * @throws NoSuchAlgorithmException e
     */
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
