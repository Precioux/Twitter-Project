package requestsFormats;

public class Comment {
    public String tweet;
    public String comment;

    public Comment(String tweet, String comment) {
        this.tweet = tweet;
        this.comment = comment;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
