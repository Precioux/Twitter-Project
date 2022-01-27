package graphics;

import Services.impl.ObserverServiceImp;
import Services.impl.TweetingServiceImp;
import Tools.JSONtool;
import com.google.gson.Gson;
import entity.Account;
import entity.Error;
import entity.React;
import graphics.Controllers.TweetController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import requestsFormats.ForServices;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Scanner;
/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines Tweet dark controller
 */
public class TweetDarkControllerImp extends ListCell<TWEET> implements TweetController {
    Account account=new Account();
    JSONtool jsoNtool=new JSONtool();
    ObserverServiceImp observerServiceImp=new ObserverServiceImp();
    TweetingServiceImp tweetingServiceImp=new TweetingServiceImp();
    Gson gson=new Gson();

    @FXML
    private Button remove;
    @FXML
    private Pane tweetArea;
    @FXML
    private Button comment;

    @FXML
    private Button like;

    @FXML
    private Label likes;

    @FXML
    private Button retweet;

    @FXML
    private Label status;

    @FXML
    private Label time;

    @FXML
    private Label tweetText;

    @FXML
    private Hyperlink userID;
    String ls="";
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
     * go to profile
     * @param actionEvent e
     */
    @FXML
    public void goProfile(ActionEvent actionEvent)
    {
        setAccount();

        File viewer=new File("./files/View.txt");
        System.out.println(viewer.exists());
        FileWriter fileWriter=null;
        try {
            fileWriter = new FileWriter(viewer);
            System.out.println(userID.getText());
            fileWriter.write(userID.getText());
            fileWriter.close();
            Parent signUpRoot = FXMLLoader.load(getClass().getResource("ProfileDark.fxml"));
            Scene p = new Scene(signUpRoot);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(p);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
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
     * to comment
     * @param event e
     */
    @FXML
    public void commentIt(ActionEvent event)  {
        setAccount();
        File file=new File("./files/CommentSource.txt");
        FileWriter fileWriter=null;
        try {
            fileWriter=new FileWriter(file);
            System.out.println(account.ID+"\n"+userID.getText()+"\n"+tweetText.getText());
            fileWriter.write(account.ID+"\n"+userID.getText()+"\n"+tweetText.getText());
            fileWriter.close();
            Parent signUpRoot = FXMLLoader.load(getClass().getResource("CommentDark.fxml"));
            Scene p = new Scene(signUpRoot);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(p);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * to like
     * @param event e
     * @throws IOException e
     */
    @FXML
    public void likeIt(ActionEvent event) throws IOException {
        System.out.println("like ");
        actions(2);
    }


    /**
     * to like and ret
     * @param acti a
     * @throws IOException e
     */
    public void actions(int acti) throws IOException {
        setAccount();
        observerServiceImp.addAccount(account);
        System.out.println("this is actions for "+acti);
        Gson gson = new Gson();
        React react=new React(account.ID,userID.getText(),acti,tweetText.getText(),"");
        ForServices forServices = new ForServices(5, jsoNtool.toJSON(react));
        System.out.println(jsoNtool.toJSON(forServices));
        int rslt = observerServiceImp.begin(jsoNtool.toJSON(forServices));
        if (rslt == 0) {

            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now();
            submitLog(localDate, localTime, Integer.toString(acti), "SuccessFul", 0);
        } else {
            if (rslt == -1) {

                Error error = new Error();
                error.errorSearch(1000);
                alaart(error.getErrorType());
                LocalDate localDate = LocalDate.now();
                LocalTime localTime = LocalTime.now();
                submitLog(localDate, localTime, Integer.toString(acti), "Failed", 1000);
            } else {
                if (rslt == 9 || rslt == 999) {
                    Error error = new Error();
                    error.errorSearch(rslt);
                    alaart(error.getErrorType());
                    LocalDate localDate = LocalDate.now();
                    LocalTime localTime = LocalTime.now();
                    submitLog(localDate, localTime, Integer.toString(acti), "Failed", rslt);
                }
            }
        }
    }

    /**
     * to remove
     * @param actionEvent e
     * @throws IOException e
     */
    @FXML
    public void removeIt(ActionEvent actionEvent) throws IOException {
        System.out.println("this is remove it");
        setAccount();
        if(userID.getText().equals(account.ID))
        {
            File file=new File("./files/RemoveSource.txt");
            FileWriter fileWriter=null;
            try {
                fileWriter=new FileWriter(file);
                fileWriter.write(account.ID+"\n"+tweetText.getText());
                fileWriter.close();
                System.out.println("written to remove source");
                Parent signUpRoot = FXMLLoader.load(getClass().getResource("WaitDark.fxml"));
                Scene p = new Scene(signUpRoot);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(p);
                window.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            alaart("You don't own the tweet!");
        }
    }

    /**
     * to ret
     * @param event e
     */
    @FXML
    public void toRetweet(ActionEvent event) throws IOException {
        System.out.println("this is retweeter");
        actions(3);
    }
    private FXMLLoader mLLoader;

    /**
     * update
     * @param item i
     * @param empty e
     */
    protected void updateItem(TWEET item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null);
        }
        else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("tweetDark.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            status.setText(item.status);
            userID.setText(item.owner);
            tweetText.setText(item.text);
            time.setText(item.time);
            likes.setText(String.valueOf(item.likes));
            setGraphic(tweetArea);
        }
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
                log+= localDate+" "+localTime+ "  @" + account.ID + "  : " + request + " => " + result+"\n";
            }
            else
            {
                Error error=new Error();
                error.errorSearch(ErrorCode);
                log+=localDate +" "+localTime+ "  @" + account.ID + "  : " + request + " => " + result+" > "+error.getErrorType()+"\n";
            }
            fileWriter.write(log);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
