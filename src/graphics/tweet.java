package graphics;

public class tweet {
    String status="";
    String owner="";
    String text="";
    String time="";
    int likes=0;
    void getText(String text)
    {
        this.text+=text;
    }
    void getTime(String time)
    {
        this.time+=time;
    }
    void getLikes(int likes)
    {
     this.likes=likes;
    }
    void getOwner(String owner)
    {
        this.owner+=owner;
    }
    void getStatus(String status)
    {
        this.status=status;
    }
}
