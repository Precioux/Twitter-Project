package Services;

/**
 * This class defines TimeLine Service
 * @author Samin Mahdipour
 * @version 1.0
 * @since 12.7.2021
 * */
public interface TimeLineService  {
    public class noFollowerException  extends Exception {}
    /**
     *
     * @return next choice
     */
    public int begin();
    /**
     * @return timeline
     * prints timeline
     */
    public String printTimeLine();
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
     * get all tweets
     * @param follower data
     */
   public void getTweets(String follower);

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

    /**
     * find followings
     */
    public void findFollowings();
}
