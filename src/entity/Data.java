package entity;

public class Data {
    public String time;
    public String user;
    public String string;


    public void addTime(String time) {
        this.time = time;
    }


    public void addUser(String user) {
        this.user=user;
    }

    public void addString(String string) {
        this.string = string;
    }

    public String toString()
    {
        return "@"+user+" : "+string +"  "+time;
    }
}
