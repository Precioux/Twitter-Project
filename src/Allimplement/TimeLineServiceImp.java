package Allimplement;
import Services.TimeLineService;
import Services.*;
import materials.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
/**
 * This class defines TimeLine Service Imp
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.28.2021
 * */
public class TimeLineServiceImp implements TimeLineService {
    public class noFollowerException  extends Exception {}
    private ArrayList<String> AFollowings=new ArrayList<>();
    private ArrayList<HashMap<Long, String>> tweetlist = new ArrayList<HashMap<Long, String>>();
    private ArrayList<String> timeline=new ArrayList<>();
    private ArrayList<Long> addr=new ArrayList<>();
    private ArrayList<Boolean> check=new ArrayList<>();
    Account account=new Account();

    public void addAccount(Account account) {
        this.account=account;
    }

    /**
     *
     * @return next choice
     */
    public int begin()
    {
        int next = 0;
        try {

            findFollowings();
            if(AFollowings.size()==0)
                throw new noFollowerException();
            else {
                for (String follower : AFollowings) {
                    getTweets(follower);
                    getLikes(follower);
                    getRetweets(follower);
                    getComments(follower);
                }
                sortTimeline();
                printTimeLine();
            }
        } catch (noFollowerException e) {
            System.out.println("You follow no one!");
        }
        System.out.println("Enter -1 to return and 0 to stay");
        Scanner scanner=new Scanner(System.in);
        int change=scanner.nextInt();
        next=change;
        return next;
    }

    /**
     * prints timeline
     */
    public void printTimeLine()
    {
        for (String t:timeline) {
            System.out.println("------------------------------------------------------");
            System.out.println(t);
            System.out.println("------------------------------------------------------");
        }
    }

    /**
     *
     * @param key data
     * @return tweet
     */
    public String search(long key)
    {
        int i=0;
        String str="";
        for (HashMap<Long,String> h:tweetlist) {
            if(h.containsKey(key))
            {
                if(!check.get(i))
                {
                    str+=h.get(key);
                    check.set(i,true);
                    break;
                }
            }
            i++;
        }
        return str;
    }

    /**
     * Sort timeline
     */
    public void sortTimeline()
    {
        final long[] arr = new long[addr.size()];
        int index = 0;
        for (final Long value : addr) {
            arr[index++] = value;
        }
        Arrays.sort(arr);
        for (long i:arr)
        {
            timeline.add(search(i));
        }

    }

    /**
     * get all tweets
     * @param follower data
     */
    public void getTweets(String follower)
    {
        File folder=new File("./Data/Tweets/"+follower+"/");
        try {
            String[] twts = folder.list();
            for (String t : twts) {
                File twt = new File("./Data/Tweets/" + follower + "/" + t + "/ddu");
                FileReader filereader = new FileReader(twt);
                Scanner scanner=new Scanner(filereader).useDelimiter("\n");
                String twet =scanner.next();
                twet+="  @";
                twet+=scanner.next();
                twet+="  ";
                twet+=scanner.next();
                HashMap<Long,String> ht=new HashMap<>();
                long num=Long.parseLong(t);
                ht.put(num,twet);
                addr.add(num);
                check.add(false);
                tweetlist.add(ht);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * get all comments
     * @param follower data
     */
    public void getComments(String follower)
    {
        File folder=new File("./Data/comments/"+follower+"/");
        try {
            String[] twts = folder.list();
            for (String t : twts) {
                File twt = new File("./Data/comments/" + follower + "/" + t + "/ddu");
                FileReader filereader = new FileReader(twt);
                Scanner scanner=new Scanner(filereader).useDelimiter("\n");
                String twet =scanner.next();
                HashMap<Long,String> ht=new HashMap<>();
                long num=Long.parseLong(t);
                ht.put(num,twet);
                addr.add(num);
                check.add(false);
                tweetlist.add(ht);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * get all retweets
     * @param follower data
     */
    public void getRetweets(String follower)
    {
        File folder=new File("./Data/retweets/"+follower+"/");
        try {
            String[] twts = folder.list();
            for (String t : twts) {
                File twt = new File("./Data/retweets/" + follower + "/" + t + "/ddu");
                FileReader filereader = new FileReader(twt);
                Scanner scanner=new Scanner(filereader).useDelimiter("\n");
                String twet =scanner.next();
                HashMap<Long,String> ht=new HashMap<>();
                long num=Long.parseLong(t);
                ht.put(num,twet);
                addr.add(num);
                check.add(false);
                tweetlist.add(ht);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * get all likes
     * @param follower data
     */
    public void getLikes(String follower)
    {
        File folder=new File("./Data/likes/"+follower+"/");
        try {
            String[] twts = folder.list();
            for (String t : twts) {
                File twt = new File("./Data/likes/" + follower + "/" + t + "/ddu");
                FileReader filereader = new FileReader(twt);
                Scanner scanner=new Scanner(filereader).useDelimiter("\n");
                String twet =scanner.next();
                HashMap<Long,String> ht=new HashMap<>();
                long num=Long.parseLong(t);
                ht.put(num,twet);
                addr.add(num);
                check.add(false);
                tweetlist.add(ht);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * find followings
     */
    public void findFollowings()
    {
        File dataFollowings=new File("./Data/Users/"+account.ID+"/following");
        try {
            FileReader followings = new FileReader(dataFollowings);
            Scanner sfollowings = new Scanner(followings);
            while (sfollowings.hasNextLine())
            {
                String str=sfollowings.nextLine();
                AFollowings.add(str);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}