package graphics;

import Tools.JSONtool;
import com.dustinredmond.fxtrayicon.FXTrayIcon;
import com.google.gson.Gson;
import entity.Account;
import entity.Data;
import graphics.Controllers.TimeLineController;
import javafx.application.Platform;
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
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.util.*;

/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines TimeLine controller
 */
public class TimeLineControllerImp implements TimeLineController {
    public class noFollowerException  extends Exception {}
    private ArrayList<String> AFollowings=new ArrayList<>();
    private ArrayList<HashMap<Long, String>> tweetlist = new ArrayList<HashMap<Long, String>>();
    private ArrayList<String> timeline=new ArrayList<>();
    private ArrayList<Long> addr=new ArrayList<>();
    private ArrayList<Boolean> check=new ArrayList<>();
    JSONtool jsoNtool=new JSONtool();
    Account account=new Account();
    Gson gson=new Gson();
    int Emode=-1;
    int theme=-1;
    @FXML
    private BorderPane area;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button refresh;

    @FXML
    private ListView<TWEET> MainTimeLine;
    private final ObservableList<TWEET> tweets = FXCollections.observableArrayList();

    /**
     * exit mode
     * @param actionEvent
     */
    public void toExitMode(ActionEvent actionEvent) throws IOException {
        Parent r= FXMLLoader.load(getClass().getResource("Mode.fxml"));
        Scene s=new Scene(r);
        Stage window=(Stage) area.getScene().getWindow();
        window.setScene(s);
        window.show();
    }

    /**
     * to full screen
     * @param actionEvent e
     */
    @FXML
    public void toFullScreen(ActionEvent actionEvent) {
        Stage stage=(Stage) area.getScene().getWindow();
        if(!stage.isFullScreen())
            stage.setFullScreen(true);
        else stage.setFullScreen(false);
    }

    /**
     * change theme
     * @param actionEvent e
     */
    @FXML
    public void toChangeTheme(ActionEvent actionEvent) throws IOException {
        Parent r= FXMLLoader.load(getClass().getResource("Theme.fxml"));
        Scene s=new Scene(r);
        Stage window=(Stage) area.getScene().getWindow();
        window.setScene(s);
        window.show();
    }
    /**
     * help
     * @param actionEvent e
     */
    @FXML
    public void toHelp(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        File file=new File("./files/Help.txt");
        FileReader fileReader=null;
        String data="";
        try {
            fileReader=new FileReader(file);
            Scanner scanner=new Scanner(fileReader).useDelimiter("\n");
            while (scanner.hasNext())
            {
                data+=scanner.next();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        alert.setContentText(data);
        Optional<ButtonType> res=alert.showAndWait();
    }

    /**
     * about
     * @param actionEvent e
     */
    @FXML
    public void toAbout(ActionEvent actionEvent)
    {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setContentText("Twitter\nAP Project-Fall 2021\nBy Samin Mahdipour\n9839039\nContact me : Uni.mahdipour@gmail.com");
        Optional<ButtonType> res=alert.showAndWait();
    }
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
    /**
     * log out
     * @param actionEvent e
     */
    @FXML
    public void toLogOut(ActionEvent actionEvent)
    {
        File ExitMode=new File("./files/Setting/ExitMode.txt");
        File Theme=new File("./files/Setting/Theme.txt");
        ExitMode.delete();
        Theme.delete();
        try {
            Stage window = (Stage) area.getScene().getWindow();
            Parent Root = FXMLLoader.load(getClass().getResource("Authentication.fxml"));
            Scene Aview = new Scene(Root);
            window.setScene(Aview);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * exit
     * @param actionEvent e
     */
    @FXML
    public void toExit(ActionEvent actionEvent) {
        getSetting();
        Stage window = (Stage) area.getScene().getWindow();
        if (Emode == 0) {

            Platform.exit();
            System.exit(0);
        }
        else {

        FXTrayIcon trayIcon = new FXTrayIcon(window, getClass().getResource("recources/twitterlogo.png"));
        trayIcon.show();
        window.close();
    }

    }
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
    public void refreshIt(ActionEvent event) {
        initialize();
    }

    /**
     * from json to TWEET
     */
    public void toTweetType()
    {
        for (String t:timeline) {
            tweets.add(gson.fromJson(t, TWEET.class));
        }
    }

    /**
     * get previous setting
     */
    public void getSetting()
    {
        File ExitMode=new File("./files/Setting/ExitMode.txt");
        FileReader onExitMode=null;
        try {
            onExitMode=new FileReader(ExitMode);
            Scanner scannerOnExitMode=new Scanner(onExitMode);
            if (scannerOnExitMode.hasNextInt())
            {
                Emode=scannerOnExitMode.nextInt();

            }
            onExitMode.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * set list view
     */
    public void initialize()
    {
        getSetting();
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
                        return new TweetControllerImp();
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
    public void toProfile(ActionEvent event) {
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
    public void toTweet(ActionEvent event) throws IOException {

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
    public int getlike(String user, String txt)
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
        System.out.println("this is txt:"+txt);
        File tweet=new File("./Data/Tweets/"+user+"/"+tweetsToAddress[index]+"/likes");
        FileReader fileReader=null;
        try {
            fileReader=new FileReader(tweet);
            Scanner scanner=new Scanner(fileReader);
            if(scanner.hasNextInt()) {
                System.out.println("got n");
                number = scanner.nextInt();
            }

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

        int l=arr.length;
        System.out.println("length:"+l);
        for (int i=l-1;i>=0;i--)
        {
            timeline.add(search(arr[i]));
        }
//        for (long i:arr)
//        {
//            timeline.add(search(i));
//        }

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
                T.getLikes(getlike(T.owner,T.text));
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
                T.getLikes(getlike(T.owner,T.text));
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
                Scanner scanner=new Scanner(filereader).useDelimiter("\n");
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
                Scanner scanner=new Scanner(filereader).useDelimiter("\n");
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
        File folder=new File("./Data/Likes/"+follower+"/");
        try {
            String[] twts = folder.list();
            for (String t : twts) {
                File twt = new File("./Data/Likes/" + follower + "/" + t + "/ddg");
                FileReader filereader = new FileReader(twt);
                Scanner scanner=new Scanner(filereader).useDelimiter("\n");
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