package Tools;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
/**
 * This class defines follow editor
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.7.2021
 * */
public class FollowEditor {
    protected String account;
    private ArrayList<String> Afollowers=new ArrayList<>();
    private ArrayList<String> Afollowings=new ArrayList<>();
    private ArrayList<String> ufollowers=new ArrayList<>();
    private ArrayList<String> ufollowings=new ArrayList<>();

    /**
     *
     * @param account data
     */
    protected void addAccount(String account)
    {
        this.account=account;
    }

    /**
     *
     * @param user data
     */
    private void unfollowed(String user)
    {
        try {
         File tounfollow = new File("./Data/Users/" + user + "/followers");
        if(!tounfollow.exists())
            throw new FileNotFoundException();
        else
        {
            collectData2(user);
            Iterator<String> iter = ufollowers.iterator();
            while (iter.hasNext())
            {
                if(iter.next().equals(account)) {
                    System.out.println("Found and removed");
                    iter.remove();
                }
            }
            FileWriter fileWriter=new FileWriter(tounfollow);
            for (String s:ufollowers) {
                fileWriter.write(s+"\n");
            }
            fileWriter.close();

        }
    } catch (FileNotFoundException e) {
        System.out.println("File not found");
    } catch (IOException e) {
        e.printStackTrace();
    }

    }

    /**
     *
     * @param user data
     * @return true if unfollowed successfully
     */
    protected boolean unfollower(String user)
    {
        boolean check=false;
      try{
                    File you = new File("./Data/Users/" + account+ "/following");
                    if(!you.exists())
                        throw new FileNotFoundException();
                    else
                    {
                        collectData();
                        Iterator<String> iter = Afollowings.iterator();
                        while (iter.hasNext())
                        {

                            if(iter.next().equals(user)) {
                                iter.remove();
                            }
                        }
                        FileWriter fileWriter=new FileWriter(you);
                        for (String s:Afollowings) {
                            fileWriter.write(s+"\n");
                        }
                        unfollowed(user);
                        check=true;
                        System.out.println(user+" unfollowed successfully!");
                        fileWriter.close();

                    }
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                e.printStackTrace();
            }
      return check;
        }

    /**
     * collect data of account following and followers
     */
    private void collectData()
        {
            File dataFollowing=new File("./Data/Users/"+account+"/following");
            File dataFollowers=new File("./Data/Users/"+account+"/followers");
            try {
                FileReader followers = new FileReader(dataFollowers);
                FileReader followings = new FileReader(dataFollowing);
                Scanner sfollowers = new Scanner(followers);
                Scanner sfollowings = new Scanner(followings);
                while (sfollowers.hasNextLine())
                {
                    String str=sfollowers.nextLine();
                    Afollowers.add(str);
                }
                while (sfollowings.hasNextLine())
                {
                    Afollowings.add(sfollowings.nextLine());
                }
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    /**
     *
     * @param user  collect data of account following and followers
     */
    private void collectData2(String user)
    {
        File dataFollowing=new File("./Data/Users/"+user+"/following");
        File dataFollowers=new File("./Data/Users/"+user+"/followers");
        try {
            FileReader followers = new FileReader(dataFollowers);
            FileReader followings = new FileReader(dataFollowing);
            Scanner sfollowers = new Scanner(followers);
            Scanner sfollowings = new Scanner(followings);
            while (sfollowers.hasNextLine())
            {
                String str=sfollowers.nextLine();
                Afollowers.add(str);
            }
            while (sfollowings.hasNextLine())
            {
                Afollowings.add(sfollowings.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
