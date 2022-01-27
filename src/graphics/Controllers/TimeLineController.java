package graphics.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
/**
 * AP-Project-Phase4
 * @author Samin Mahdipour
 * @version 4.0
 * @since 1.22.2022
 * this class defines TimeLine controller interface
 */
public interface TimeLineController {

    /**
     * exit mode
     * @param actionEvent e
     * @throws IOException o
     */
    public void toExitMode(ActionEvent actionEvent) throws IOException;

    /**
     * to full screen
     * @param actionEvent e
     */
    @FXML
    public void toFullScreen(ActionEvent actionEvent);

    /**
     * change theme
     * @param actionEvent e
     * @throws IOException e
     */
    @FXML
    public void toChangeTheme(ActionEvent actionEvent) throws IOException;

    /**
     * help
     * @param actionEvent e
     */
    @FXML
    public void toHelp(ActionEvent actionEvent);

    /**
     * about
     * @param actionEvent e
     */
    @FXML
    public void toAbout(ActionEvent actionEvent);

    /**
     * sets account
     *
     */
    public void setAccount();

    /**
     * log out
     * @param actionEvent e
     */
    @FXML
    public void toLogOut(ActionEvent actionEvent);

    /**
     * exit
     * @param actionEvent e
     */
    @FXML
    public void toExit(ActionEvent actionEvent);

    /**
     * search
     * @param actionEvent e
     * @throws IOException o
     */
    @FXML
    public void searchforUser(ActionEvent actionEvent) throws IOException;

    /**
     * refreshes the timeLine
     * @param event ecent
     */
    @FXML
    void refreshIt(ActionEvent event);

    /**
     * from json to TWEET
     */
    void toTweetType();

    /**
     * get previous setting
     */
    public void getSetting();

    /**
     * set list view
     */
    public void initialize();

    /**
     * to user's profile
     * @param event e
     */
    @FXML
    void toProfile(ActionEvent event);

    /**
     * to tweet
     * @param event e
     * @throws IOException e
     */
    @FXML
    void toTweet(ActionEvent event) throws IOException;

    /**
     *
     * Timeline
     */
    public void getTimeLine();

    /**
     * @param txt t
     * @param user u
     * get likes
     * @return result
     */
    int getlike(String user,String txt);

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
     * find followings
     */
    public void findFollowings();

    /**
     * get all tweets
     * @param follower data
     */
    public void getTweets(String follower);

    /**
     * get all tweets
     */
    public void getMytweets();

    /**
     * get all comments
     * @param follower data
     */
    public void getComments(String follower);

    /**
     * get all retweets
     * @param follower data
     */
    public void getRetweets(String follower);

    /**
     * get all likes
     * @param follower data
     */
    public void getLikes(String follower);
}
