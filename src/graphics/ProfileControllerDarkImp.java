package graphics;

import Services.impl.ObserverServiceImp;
import Tools.JSONtool;
import com.google.gson.Gson;
import entity.Account;
import entity.Data;
import entity.Error;
import graphics.Controllers.ProfileController;
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
/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines profile controller dark
 */
public class ProfileControllerDarkImp implements ProfileController {
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
                fileReader.close();
                scanner.close();

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
                fileReader.close();
                scanner.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return number;
    }
    /**
     * to change profile
     * @param event e
     * @throws IOException e
     */
    @FXML
    public void toChange(ActionEvent event) throws IOException {
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
    public void alaart(String err) throws IOException {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(err);
        Optional<ButtonType> res=alert.showAndWait();
    }

    /**
     * follow
     * @param event e
     * @throws IOException e
     */
    @FXML
    public void toFollow(ActionEvent event) throws IOException {
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
    /**
     * to time Line
     * @param event e
     * @throws IOException e
     */
    @FXML
    public void toTimeLine(ActionEvent event) throws IOException {
        Parent signUpRoot= FXMLLoader.load(getClass().getResource("TimeLineDark.fxml"));
        Scene signUpview=new Scene(signUpRoot);
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signUpview);
        window.show();
    }
    /**
     * unfollow
     * @param event e
     * @throws IOException e
     */
    @FXML
    public void toUnfollow(ActionEvent event) throws IOException {
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
                T.getLikes(getlike(T.owner,T.text));
                T.getStatus("Tweeted");
                String twet=jsoNtool.toJSON(T);
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
     * add
     */
    public void addTweets()
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
    public void toTweetType()
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

                            }
                        }
                );
        // set custom ListView cell factory
        tweets.setCellFactory(
                new Callback<ListView<TWEET>, ListCell<TWEET>>() {
                    @Override
                    public ListCell<TWEET> call(ListView<TWEET> listView) {
                        return new TweetDarkControllerImp();

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
