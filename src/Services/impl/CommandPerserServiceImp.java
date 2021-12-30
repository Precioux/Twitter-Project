package Services.impl;

import Services.CommandPerserService;
import Tools.JSONtool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import entity.Account;
import entity.Request;
import requestsFormats.LogIn;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Scanner;

public class CommandPerserServiceImp implements CommandPerserService {
    static Scanner scanner=new Scanner(System.in);
    static JSONtool jsonTool=new JSONtool();
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
