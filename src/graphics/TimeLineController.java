package graphics;

import Tools.JSONtool;
import com.google.gson.Gson;
import entity.Account;
import entity.Data;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
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
    Gson gson=new Gson();

    /**
     * sets account
     *
     */
    public void setAccount() {
       File file=new File("./files/Exchange.txt");
       FileReader fr=null;
       try {
           fr=new FileReader(file);
           Scanner scanner=new Scanner(fr);
           String d=scanner.next();
           account.ID=d;
           System.out.println(account.ID);
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
    }
    @FXML
    private MenuBar menuBar;

    @FXML
    private Button refresh;

    @FXML
    private ListView<TWEET> MainTimeLine;
    private final ObservableList<TWEET> tweets = FXCollections.observableArrayList();

    /**
     * search
     * @param actionEvent e
     */
    @FXML
    public void searchforUser(ActionEvent actionEvent) throws IOException {
        Parent signUpRoot = FXMLLoader.load(getClass().getResource("Search.fxml"));
        Scene p = new Scene(signUpRoot);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(p);
        window.show();
    }
    /**
     * refreshes the timeLine
     * @param event ecent
     */
    @FXML
    void refreshIt(ActionEvent event) {
        initialize();
    }

    /**
     * from json to TWEET
     */
    void toTweetType()
    {
        for (String t:timeline) {
            tweets.add(gson.fromJson(t, TWEET.class));
        }
    }
    /**
     * set list view
     */
   public void initialize()
    {
         getTimeLine();
        toTweetType();
        MainTimeLine.setItems(tweets);
        MainTimeLine.getSelectionModel().selectedItemProperty().
                addListener(
                        new ChangeListener<TWEET>() {
                            @Override
                            public void changed(ObservableValue<? extends TWEET> ov,
                                                TWEET oldValue, TWEET newValue) {
                                //????????????????????????????
                            }
                        }
                );
        // set custom ListView cell factory
        MainTimeLine.setCellFactory(
                new Callback<ListView<TWEET>, ListCell<TWEET>>() {
                    @Override
                    public ListCell<TWEET> call(ListView<TWEET> listView) {
                        return new TweetController();
                        //return new ImageTextCell();
                    }
                }
        );
    }

    /**
     * to user's profile
     * @param event e
     * @throws IOException e
     */
    @FXML
    void toProfile(ActionEvent event) {
        File viewer=new File("./files/View.txt");
        System.out.println(viewer.exists());
        FileWriter fileWriter=null;
        try {
            fileWriter = new FileWriter(viewer);
            System.out.println(account.ID);
            fileWriter.write(account.ID);
            fileWriter.close();
            Parent signUpRoot = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            Scene p = new Scene(signUpRoot);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(p);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * to tweet
     * @param event e
     * @throws IOException e
     */
    @FXML
    void toTweet(ActionEvent event) throws IOException {

        Parent signUpRoot= FXMLLoader.load(getClass().getResource("AddTweet.fxml"));
        Scene signUpview=new Scene(signUpRoot);
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signUpview);
        window.showAndWait();
    }
    /**
     *
     * Timeline
     */
    public void getTimeLine()
    {
        setAccount();
        AFollowings.clear();
        tweetlist.clear();
        timeline.clear();
        addr.clear();
        check.clear();
        tweets.clear();
        MainTimeLine.getItems().clear();
            findFollowings();
                for (String follower : AFollowings) {
                    getTweets(follower);
                    getLikes(follower);
                    getRetweets(follower);
                    getComments(follower);

                }
               getMytweets();
                sortTimeline();



    }

    /**
     * get likes
     */
    int getlikes(String user,String txt)
    {
        int number=0;
        File toTweetFolder=new File("./Data/Tweets/"+user+"/");
        String[] tweetsToAddress=toTweetFolder.list();
        String[] tweetsToString=new String[tweetsToAddress.length];
        int i=0;
        for (String address:tweetsToAddress) {
            File tweet=new File("./Data/Tweets/"+user+"/"+address+"/DDU");
            FileReader fileReader=null;
            try{
                Data data=new Data();
                fileReader=new FileReader(tweet);
                Scanner scanner=new Scanner(fileReader).useDelimiter("\n");
                data.addTime(scanner.next());
                data.addUser(scanner.next());
                data.addString(scanner.next());
                tweetsToString[i]=jsoNtool.toJSON(data);
                scanner.close();
                fileReader.close();
                i++;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int index=-1;
        int j=0;
        for (String twt:tweetsToString) {
            Data d=gson.fromJson(twt,Data.class);
            if (d.string.equals(txt)){
                index=j;}
            j++;
        }
        File tweet=new File("./Data/Tweets/"+user+"/"+tweetsToAddress[index]+"/likes");
        FileReader fileReader=null;
        try {
            fileReader=new FileReader(tweet);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            number=bufferedReader.read();
            System.out.println(number);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return number;
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
                TWEET T=new TWEET();
                 T.getTime(scanner.next());
                T.getOwner(scanner.next());
                T.getText(scanner.next());
                T.getStatus("Tweeted");
                T.getLikes(getlikes(T.owner,t));
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
     * get all tweets
     */
    public void getMytweets()
    {
        File folder=new File("./Data/Tweets/"+account.ID+"/");
        try {
            String[] twts = folder.list();
            for (String t : twts) {
                File twt = new File("./Data/Tweets/" + account.ID + "/" + t + "/ddu");
                FileReader filereader = new FileReader(twt);
                Scanner scanner=new Scanner(filereader).useDelimiter("\n");
                TWEET T=new TWEET();
                T.getTime(scanner.next());
                T.getOwner(scanner.next());
                T.getText(scanner.next());
                T.getLikes(getlikes(T.owner,T.text));
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
                TWEET T=new TWEET();
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
                TWEET T=new TWEET();
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
                TWEET T=new TWEET();
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
