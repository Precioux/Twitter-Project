package graphics.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines profile controller interface
 */
public interface ProfileController {

    /**
     * sets account
     * @param user e
     */
    public void setUser(String user);

    /**
     * sets account view
     *
     */
    public void setAccount();

    /**
     * get likes
     * @param user u
     * @param txt t
     * @return result
     */
    int getlike(String user,String txt);

    /**
     * to change profile
     * @param event e
     * @throws IOException e
     */
    @FXML
    void toChange(ActionEvent event) throws IOException;

    /**
     * alart
     * @param err error
     * @throws IOException e
     */
    void alaart(String err) throws IOException;

    /**
     * follow
     * @param event e
     * @throws IOException e
     */
    @FXML
    void toFollow(ActionEvent event) throws IOException;
    /**
     * timeLine
     * @param event e
     * @throws IOException e
     */
    @FXML
    void toTimeLine(ActionEvent event) throws IOException;
    /**
     * unfollow
     * @param event e
     * @throws IOException e
     */
    @FXML
    void toUnfollow(ActionEvent event) throws IOException;

    /**
     * get all tweets
     * @param follower data
     */
    public void getTweets(String follower);

    /**
     *
     * @param key data
     * @return TWEET
     */
    public String search(long key);

    /**
     * Sort timeline
     */
    public void sortTimeline();

    /**
     * add
     */
    void addTweets();

    /**
     * from json to TWEET
     */
    void toTweetType();

    /**
     * initialize
     */
    public void initialize();

    /**
     * submit log
     * @param localTime data
     * @param localDate data
     * @param request data
     * @param result data
     * @param ErrorCode data
     */
    public void submitLog(LocalDate localDate, LocalTime localTime,String request, String result, int ErrorCode);

}
