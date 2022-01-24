package graphics;

import Tools.JSONtool;
import com.google.gson.Gson;
import entity.Account;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ProfileController {
    JSONtool jsoNtool=new JSONtool();
    Account account=new Account();
    public class noFollowerException  extends Exception {}
    private ArrayList<String> AFollowings=new ArrayList<>();
    private ArrayList<HashMap<Long, String>> tweetlist = new ArrayList<HashMap<Long, String>>();
    private ArrayList<String> timeline=new ArrayList<>();
    private ArrayList<Long> addr=new ArrayList<>();
    private ArrayList<Boolean> check=new ArrayList<>();
    ObservableList<TWEET> Otweets= FXCollections.observableArrayList();
    Gson gson=new Gson();
    @FXML
    private Button back;

    @FXML
    private Label bio;

    @FXML
    private Button change;

    @FXML
    private Button follow;

    @FXML
    private Label id;

    @FXML
    private Label joined;

    @FXML
    private ImageView photo;

    @FXML
    private ListView<TWEET> tweets=new ListView<>();

    @FXML
    private Button unfollow;
    /**
     * sets account
     *
     */
    public void setAccount() {
        System.out.println("this is set account");
        File file=new File("./files/Exchange.txt");
        FileReader fr=null;
        try {
            fr=new FileReader(file);
            Scanner scanner=new Scanner(fr);
            String d=scanner.next();
            System.out.println(d);
            account.ID=d;
            File file1=new File("./Data/Users/"+d+"/accountData");
            id.setText(d);
            Scanner scanner1=new Scanner(file1).useDelimiter("\n");
            account.password=scanner1.next();
            account.fname=scanner1.next();
            account.lname=scanner1.next();
            account.bio=scanner1.next();
            bio.setText(account.bio);
            String r=scanner1.next();
            joined.setText(scanner1.next());
            account.photoPath=scanner1.next();
            System.out.println(account.photoPath);
            File p=new File(account.photoPath);
            Image image=new Image(p.toURI().toString());
            photo.setImage(image);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void toChange(ActionEvent event) {

    }

    @FXML
    void toFollow(ActionEvent event) {

    }

    @FXML
    void toTimeLine(ActionEvent event) throws IOException {
        Parent signUpRoot= FXMLLoader.load(getClass().getResource("TimeLine.fxml"));
        Scene signUpview=new Scene(signUpRoot);
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signUpview);
        window.show();
    }

    @FXML
    void toUnfollow(ActionEvent event) {

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
    void addTweets()
    {
        AFollowings.clear();
        tweetlist.clear();
        timeline.clear();
        addr.clear();
        check.clear();
        Otweets.clear();
        getTweets(account.ID);
        sortTimeline();
    }

    /**
     * from json to TWEET
     */
    void toTweetType()
    {
        for (String t:timeline) {
            Otweets.add(gson.fromJson(t, TWEET.class));
        }
    }
    /**
     * initialize
     */
    public void initialize()
    {
        System.out.println("initialize");
        setAccount();
        addTweets();
        toTweetType();
        tweets.setItems(Otweets);
        tweets.getSelectionModel().selectedItemProperty().
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
        tweets.setCellFactory(
                new Callback<ListView<TWEET>, ListCell<TWEET>>() {
                    @Override
                    public ListCell<TWEET> call(ListView<TWEET> listView) {
                        return new TweetController();
                        //return new ImageTextCell();
                    }
                }
        );
    }


}
