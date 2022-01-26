package Services.impl;
import Services.TimeLineService;
import entity.Account;

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
    public String timeLine="";
    public void addAccount(Account account) {
        this.account=account;
    }

    /**
     *
     * @return result of work
     */
    public int begin()
    {
        AFollowings.clear();
        tweetlist.clear();
        timeline.clear();
        addr.clear();
        check.clear();
        int type=-1;
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
                timeLine="";
                timeLine+=printTimeLine();
                System.out.println("TLS : "+timeLine);
                type=0;
            }
        } catch (noFollowerException e) {
            type=11;
        }
        return type;
    }

    /**
     * @return timeLine
     * prints timeline
     */
    public String printTimeLine()
    {
        String tL="";
        for (String t:timeline) {
            tL+="------------------------------------------------------\n";
            tL+=t;
            tL+="\n------------------------------------------------------\n";
        }
        return tL;
    }

    /**
     *
     * @param key data
     * @return TWEET
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
                filereader.close();
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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