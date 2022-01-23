package graphics;

import entity.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class TweetController extends ListCell<TWEET> {
    Account account=new Account();

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
    void commentIt(ActionEvent event) {

    }

    @FXML
    void likeIt(ActionEvent event) {

    }

    @FXML
    void removeIt(ActionEvent actionEvent) throws IOException {
        if(userID.getText().equals(account.ID))
        {

        }
        else
        {
            alaart("You don't own the tweet!");
        }
    }
    @FXML
    void toRetweet(ActionEvent event) {

    }
    private FXMLLoader mLLoader;

    protected void updateItem(TWEET item, boolean empty) {
        // required to ensure that cell displays properly
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null); // don't display anything
        }
        else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("tweet.fxml"));
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
}