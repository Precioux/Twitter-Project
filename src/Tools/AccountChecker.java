package Tools;
import materials.Account;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class defines AccountChecker
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public class AccountChecker {
    private Account account=new Account();
    public class IdException extends Exception {}
    public class BioException extends Exception {}
    public class WrongPasswordException extends Exception {}
    private File folder=new File("./Users/");
    private String[] users=folder.list();

    /**
     *
     * @param account data
     * @param id data
     */
    public void addData(Account account,String id)
    {
        try {
            File f = new File("./Users/" + id+"/accountData");
            FileReader fr = new FileReader(f);
            account.addID(id);
            Scanner scanner = new Scanner(fr).useDelimiter("\n");
           account.addPassword(scanner.next());
           account.addFirstName(scanner.next());
           account.addLastName(scanner.next());
           account.addBio(scanner.next());
            String dd = scanner.next();
            String dd2 = scanner.next();
            Scanner scan = new Scanner(dd).useDelimiter("-");
            int y, d, m;
            y = Integer.parseInt(scan.next());
            m = Integer.parseInt(scan.next());
            d = Integer.parseInt(scan.next());
           LocalDate bd=LocalDate.of(y,m,d);
           account.addBdate(bd);
            Scanner s = new Scanner(dd2).useDelimiter("-");
            y = Integer.parseInt(s.next());
            m = Integer.parseInt(s.next());
            d = Integer.parseInt(s.next());
           LocalDate jd=LocalDate.of(y,m,d);
           account.addjDate(jd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @return account
     */
    public Account connectAAC()
    {
       return account;
    }
    /**
     * new account
     * @throws IdException check ID
     * @throws BioException check bio
     * @throws NoSuchAlgorithmException for checking algorithm
     */
    public Account getInfo() throws NoSuchAlgorithmException, IdException, BioException, IOException {
        Scanner input=new Scanner(System.in);
        System.out.println("First Name: ");
        String fname = input.next();
        account.addFirstName(fname);
        System.out.println("Last Name: ");
        String lname = input.next();
        account.addLastName(lname);
        System.out.println("ID: ");
        String ID="";
        int flag=0;
        while(flag==0) {
            try {
                ID="";
                ID += input.next();
                if (checkID(ID))
                    flag = 1;
                else throw new IdException();
            } catch (IdException e) {
                System.err.println("This ID is taken, Choose another one!");
            }
        }
        account.addID(ID);
        System.out.println("Birthdate:  (yyyy-mm-dd)" );
        int y,d,m;
        y=input.nextInt();
        m=input.nextInt();
        d=input.nextInt();
        LocalDate bd= LocalDate.of(y,m,d);
        account.addBdate(bd);
        System.out.println("Password: ");
        String password= input.next();
        account.addPassword(toHash(password));
        LocalDate l=LocalDate.now();
        account.addjDate(l);
        System.out.println("Bio: (256 characters only!)");
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        String bio="";
        int flagb=0;
        while (flagb==0)
        {
            try {
                bio="";
               bio += scanner.next();
                if (checkBio(bio))
                    flagb = 1;
                else throw new BioException();
            }
            catch (BioException e){
                System.err.println("More than 256 characters!");
            }
        }
        account.addBio(bio);
        account.createDirectory();
        if(toFile(account))
              System.out.println("Your account created successfully!\nWelcome to the Twitter "+account.fname+"!");
        else System.out.println("There is something wrong!");

        return connectAAC();

    }

    /**
     *
     * @param id finder
     * @return true if id is found
     */
    private boolean idFinder(String id)
    {
        boolean ans=false;
        for (String n:users) {
            if(id.equals(n))
                ans= true;
        }
       return ans;
    }

    /**
     *
     * @param list of char
     * @return string
     */
    private String getStringRepresentation(ArrayList<Character> list)
    {
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list)
        {
            builder.append(ch);
        }
        return builder.toString();
    }

    /**
     *
     * @param id user
     * @param passwod given by user
     * @return true if pass is ok
     * @throws IOException check
     */
    private boolean checkPass(String id,String passwod) throws IOException {
        File chp=null;
        FileReader fileReader=null;
        boolean ans=false;
        try {

            chp=new File("./Users/"+id+"/accountData");
            fileReader=new FileReader(chp);
            if(!chp.exists())
                throw new FileNotFoundException();
            else{
                int ch;
                ArrayList<Character> p=new ArrayList<>();
                while ((ch=fileReader.read())!=10)
                    p.add((char) ch);
                String pass1=getStringRepresentation(p);
                String pass2=toHash(passwod);
                if(pass1.equals(pass2))
                    ans=true;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not Found!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
         fileReader.close();
         return ans;
        }
    }
    /**
     * check account data
     * @return
     */
    public Account checkInfo()
    {
        Scanner scanner=new Scanner(System.in);
        String id="";
        String p="";
        boolean check=false;
        while (!check) {
            try {
               id="";
               p="";
               System.out.println("Enter ID: ");
                id += scanner.next();
                System.out.println("Enter Password: ");
                p += scanner.next();
                if (!idFinder(id)) {
                    throw new FileNotFoundException();
                }
                else {
                    if (!checkPass(id,p))
                    throw new WrongPasswordException();
                    else {
                        check=true;
                        System.out.println("Welcome back!");
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("No user with given ID,try again");
            } catch (WrongPasswordException e) {
                System.out.println("Username and Password don't match!,try again");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        addData(account,id);
        return connectAAC();
    }

    /**
     * @return true if file exists
     * @param account data
     * @throws IOException check
     */
    private boolean toFile(Account account) throws IOException {
        File user = new File("./Users/" + account.ID+"/accountData");
        FileWriter fw=null;
        try {
            fw = new FileWriter(user);
            fw.write(account.toString());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            fw.close();
            if(user.exists())
                return true;
            else return false;
        }
    }
    /**
     *
     * @param ID data
     * @return true if ID is accepted
     */
    private boolean checkID(String ID)
    {
        int find=0;
        for (String a:users) {
            if(ID.equals(a))
                find=1;
        }
        if(find==1)
            return false;
        else return true;
    }

    /**
     *
     * @param bio data
     * @return true if bio is accepted
     */
    private boolean checkBio(String bio)
    {

        if(bio.length()<=256)
            return true;
        else return false;
    }

    /**
     *
     * @param password data
     * @return Hash of password
     * @throws NoSuchAlgorithmException for checking algorithm
     */
    public String toHash(String password) throws NoSuchAlgorithmException {
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
