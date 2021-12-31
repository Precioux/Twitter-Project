package requestsFormats;
/**
 * AP-Project-Phase 3
 * @author Samin Mahdipour
 * @since 12.22.2021
 * @version 3.0
 * This class defines client
 */
public class Comment {
    public String tweet;
    public String comment;

    /**
     * constructor
     * @param tweet data
     * @param comment data
     */
    public Comment(String tweet, String comment) {
        this.tweet = tweet;
        this.comment = comment;
    }

    /**
     * getter
     * @return data
     */
    public String getTweet() {
        return tweet;
    }

    /**
     * setter
     * @param tweet data
     */
    public void setTweet(String tweet) {
        this.tweet = tweet;
    }
    /**
     * getter
     * @return data
     */
    public String getComment() {
        return comment;
    }

    /**
     * setter
     * @param comment data
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
