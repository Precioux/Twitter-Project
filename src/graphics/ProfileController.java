package graphics;

import Services.impl.ObserverServiceImp;
import Tools.JSONtool;
import com.google.gson.Gson;
import entity.Account;
import entity.Error;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import requestsFormats.ForServices;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ProfileController {
    JSONtool jsoNtool=new JSONtool();
    Account account=new Account();
    public class noFollowerException  extends Exception {}
    private ArrayList<String> AFollowings=new ArrayList<>();
    private ArrayList<HashMap<Long, String>> tweetlist = new ArrayList<HashMap<Long, String>>();
    private ArrayList<String> timeline=new ArrayList<>();
    private ArrayList<Long> addr=new ArrayList<>();
    private ArrayList<Boolean> check=new ArrayList<>();
    ObserverServiceImp observerServiceImp=new ObserverServiceImp();
    ObservableList<TWEET> Otweets= FXCollections.observableArrayList();
    Account accountUser=new Account();
    Gson gson=new Gson();
    @FXML
    private GridPane mainArea;
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
    String view="";
    String user="";
    /**
     * sets account
     *
     */
    public void setUser(String user) {
        System.out.println("this is set user");
        try {
            accountUser.ID=user;
            File file1=new File("./Data/Users/"+user+"/accountData");
            Scanner scanner1=new Scanner(file1).useDelimiter("\n");
            accountUser.password=scanner1.next();
            accountUser.fname=scanner1.next();
            accountUser.lname=scanner1.next();
            accountUser.bio=scanner1.next();
            accountUser.birthString=scanner1.next();
            accountUser.joinString=scanner1.next();
            accountUser.photoPath=scanner1.next();
            System.out.println(accountUser.toStringForOverWrite());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * sets account view
     *
     */
    public void setAccount() {
        System.out.println("this is set account");
        File fileV=new File("./files/View.txt");
        File fileU=new File("./files/Exchange.txt");
        FileReader fV=null;
        FileReader fU=null;
        try {
            fV=new FileReader(fileV);
            fU=new FileReader(fileU);
            Scanner scannerV=new Scanner(fileV);
            Scanner scannerU=new Scanner(fileU);
            view=scannerV.next();
            user=scannerU.next();
            System.out.println(view);
            account.ID=view;
            File file1=new File("./Data/Users/"+view+"/accountData");
            id.setText(view);
            Scanner scanner1=new Scanner(file1).useDelimiter("\n");
            account.password=scanner1.next();
            account.fname=scanner1.next();
            account.lname=scanner1.next();
            account.bio=scanner1.next();
            bio.setText(account.bio);
            account.birthString=scanner1.next();
            account.joinString=scanner1.next();
            joined.setText(account.joinString);
            account.photoPath=scanner1.next();
            System.out.println(account.photoPath);
            File p=new File(account.photoPath);
            Image image=new Image(p.toURI().toString());
            photo.setImage(image);
            System.out.println(account.toStringForOverWrite());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * to change profile
     * @param event
     * @throws IOException
     */
    @FXML
    void toChange(ActionEvent event) throws IOException {
        if( view.equals(user))
         {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Profile Photo");
            Stage stage = (Stage) mainArea.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                File accountData = new File("./Data/Users/" + account.ID + "/accountData");
                FileWriter fw = null;
                try {
                    account.photoPath = "";
                    account.addPhotoPath(file.getAbsolutePath());
                    fw = new FileWriter(accountData);
                    fw.write(account.toStringForOverWrite());
                    System.out.println("profile changed");
                    File p = new File(account.photoPath);
                    Image image = new Image(p.toURI().toString());
                    photo.setImage(image);
                    fw.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        else {
            alaart("You cannot change another user's profile!!");
        }
    }
    /**
     * alart
     * @param err error
     */
    void alaart(String err) throws IOException {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(err);
        Optional<ButtonType> res=alert.showAndWait();
    }
    @FXML
    void toFollow(ActionEvent event) throws IOException {
        setUser(user);
        observerServiceImp.addAccount(accountUser);
        ForServices forServices=new ForServices(1,view);
        int rslt=observerServiceImp.begin(jsoNtool.toJSON(forServices));
        if(rslt==0)
        {
            LocalDate localDate=LocalDate.now();
            LocalTime localTime=LocalTime.now();
            submitLog(localDate,localTime,"follow","SuccessFul",0);
        }
        else
        {
            if(rslt==-1)
            {
                Error error=new Error();
                error.errorSearch(1000);
                alaart(error.getErrorType());
                LocalDate localDate=LocalDate.now();
                LocalTime localTime=LocalTime.now();
                submitLog(localDate,localTime,"follow","Failed",1000);
            }
            else
            {
                if(rslt==9 || rslt==999 || rslt==12)
                {
                    Error error=new Error();
                    error.errorSearch(rslt);
                    alaart(error.getErrorType());
                    LocalDate localDate=LocalDate.now();
                    LocalTime localTime=LocalTime.now();
                    submitLog(localDate,localTime,"follow","Failed",rslt);
                }
            }
        }
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
    void toUnfollow(ActionEvent event) throws IOException {
        setUser(user);
        observerServiceImp.addAccount(accountUser);
        ForServices forServices=new ForServices(2,view);
        int rslt=observerServiceImp.begin(jsoNtool.toJSON(forServices));
        if(rslt==0)
        {
            LocalDate localDate=LocalDate.now();
            LocalTime localTime=LocalTime.now();
            submitLog(localDate,localTime,"unfollow","SuccessFul",0);
        }
        else
        {
            if(rslt==-1)
            {
                Error error=new Error();
                error.errorSearch(1000);
               alaart(error.getErrorType());
                LocalDate localDate=LocalDate.now();
                LocalTime localTime=LocalTime.now();
                submitLog(localDate,localTime,"unfollow","Failed",1000);
            }
            else
            {
                if(rslt==9 || rslt==999 || rslt==10)
                {

                    Error error=new Error();
                    error.errorSearch(rslt);
                   alaart(error.getErrorType());
                    LocalDate localDate=LocalDate.now();
                    LocalTime localTime=LocalTime.now();
                    submitLog(localDate,localTime,"unfollow","Failed",rslt);
                }
            }
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

    /**
     * submit log
     * @param localTime data
     * @param localDate data
     * @param request data
     * @param result data
     * @param ErrorCode data
     */
    public void submitLog(LocalDate localDate, LocalTime localTime,String request, String result, int ErrorCode)
    {
        File file=new File("./files/log.txt");
        String log="";
        FileWriter fileWriter=null;
        try {
            fileWriter = new FileWriter(file,true);
            if (ErrorCode==0) {
                log+= localDate+" "+localTime+ "  @" + accountUser.ID + "  : " + request + " => " + result+"\n";
            }
            else
            {
                Error error=new Error();
                error.errorSearch(ErrorCode);
                log+=localDate +" "+localTime+ "  @" + accountUser.ID + "  : " + request + " => " + result+" > "+error.getErrorType()+"\n";
            }
            fileWriter.write(log);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
