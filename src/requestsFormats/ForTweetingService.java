package requestsFormats;

public class ForTweetingService {
    public int choice;
    public String data;

    public ForTweetingService(int choice, String data) {
        this.choice = choice;
        this.data = data;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
