package requestsFormats;

public class ForOther {
    public  int index;
    public  String owner;
    public int action;
    public String d;

    public ForOther(int index, String owner, int action, String d) {
        this.index = index;
        this.owner = owner;
        this.action = action;
        this.d = d;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
}
