package graphics;

import Tools.*;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class TimeLineController {
    public class noFollowerException  extends Exception {}
    private ArrayList<String> AFollowings=new ArrayList<>();
    private ArrayList<HashMap<Long, String>> tweetlist = new ArrayList<HashMap<Long, String>>();
    private ArrayList<String> timeline=new ArrayList<>();
    private ArrayList<Long> addr=new ArrayList<>();
    private ArrayList<Boolean> check=new ArrayList<>();

    JSONtool jsoNtool=new JSONtool();
    Account account=new Account();
//    public void setAccount(Account account) {
//        this.account=account;
//    }
    @FXML
    private MenuBar menuBar;

    @FXML
    private Button refresh;

    @FXML
    private ListView<tweet> MainTimeLine;
    private final ObservableList<tweet> tweets = FXCollections.observableArrayList();
    @FXML
    void refreshIt(ActionEvent event) {
      getTimeLine();
    }

    /**
     *
     * Timeline
     */
    public void getTimeLine()
    {
        AFollowings.clear();
        tweetlist.clear();
        timeline.clear();
        addr.clear();
        check.clear();
            findFollowings();
                for (String follower : AFollowings) {
                    getTweets(follower);
                    getLikes(follower);
                    getRetweets(follower);
                    getComments(follower);
                }
                sortTimeline();



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
                tweet T=new tweet();
                 T.getTime(scanner.next());
                T.getOwner(scanner.next());
                T.getText(scanner.next());
                T.getStatus("Tweeted");
                String twet=jsoNtool.toJSON(T);
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
                File twt = new File("./Data/comments/" + follower + "/" + t + "/ddg");
                FileReader filereader = new FileReader(twt);
                Scanner scanner=new Scanner(filereader);
                tweet T=new tweet();
                T.getTime(scanner.next());
                T.getOwner(scanner.next());
                T.getStatus(scanner.next());
                T.getText(scanner.next());
                String twet=jsoNtool.toJSON(T);
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
                File twt = new File("./Data/retweets/" + follower + "/" + t + "/ddg");
                FileReader filereader = new FileReader(twt);
                Scanner scanner=new Scanner(filereader);
                tweet T=new tweet();
                T.getTime(scanner.next());
                T.getOwner(scanner.next());
                T.getStatus(scanner.next());
                T.getText(scanner.next());
                String twet=jsoNtool.toJSON(T);
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
                File twt = new File("./Data/likes/" + follower + "/" + t + "/ddg");
                FileReader filereader = new FileReader(twt);
                Scanner scanner=new Scanner(filereader);
                tweet T=new tweet();
                T.getTime(scanner.next());
                T.getOwner(scanner.next());
                T.getStatus(scanner.next());
                T.getText(scanner.next());
                String twet=jsoNtool.toJSON(T);
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
}
