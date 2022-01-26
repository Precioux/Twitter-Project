package graphics;
/**
 * AP-Project-phase4
 * @author Samin Mahdipour
 * @since 1.26.2022
 * @version 4.0
 * this class is defines TWEET
 */
public class TWEET {
   public String status="";
    public String owner="";
    public String text="";
    public String time="";
    public int likes=0;

    /**
     * getter
     * @param text t
     */
    public void getText(String text)
    {
        this.text+=text;
    }

    /**
     * getter
     * @param time t
     */
    public void getTime(String time)
    {
        this.time+=time;
    }

    /**
     * getter
     * @param likes t
     */
    void getLikes(int likes)
    {
     this.likes=likes;
    }

    /**
     * getter
     * @param owner o
     */
    public void getOwner(String owner)
    {
        this.owner+=owner;
    }

    /**
     * status
     * @param status s
     */
    public void getStatus(String status)
    {
        this.status+=status;
    }
}
