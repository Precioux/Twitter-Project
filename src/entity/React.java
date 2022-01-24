package entity;

public class React {
    String from;
    String to;
    int action=0;//1 comment 2 like 3 ret
    String tweet;
    String comment;

    public React(String from, String to, int action, String tweet, String comment) {
        this.from = from;
        this.to = to;
        this.action = action;
        this.tweet = tweet;
        this.comment = comment;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
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
