package graphics;

public class TWEET {
   public String status="";
    public String owner="";
    public String text="";
    public String time="";
    public int likes=0;
    public void getText(String text)
    {
        this.text+=text;
    }
    public void getTime(String time)
    {
        this.time+=time;
    }
    void getLikes(int likes)
    {
     this.likes=likes;
    }
    public void getOwner(String owner)
    {
        this.owner+=owner;
    }
    public void getStatus(String status)
    {
        this.status+=status;
    }
}
