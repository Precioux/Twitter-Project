package requestsFormats;
/**
 * AP-Project-Phase 3
 * @author Samin Mahdipour
 * @since 12.22.2021
 * @version 3.0
 * This class defines for others
 */
public class ForOther {
    public  int index;
    public  String owner;
    public int action;
    public String d;

    /**
     * constructor
     * @param index data
     * @param owner data
     * @param action data
     * @param d data
     */
    public ForOther(int index, String owner, int action, String d) {
        this.index = index;
        this.owner = owner;
        this.action = action;
        this.d = d;
    }

    /**
     *  getter
     * @return data
     */
    public int getIndex() {
        return index;
    }

    /**
     * setter
     * @param index data
     */
    public void setIndex(int index) {
        this.index = index;
    }
    /**
     *  getter
     * @return data
     */
    public String getOwner() {
        return owner;
    }
    /**
     * setter
     * @param owner data
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
    /**
     *  getter
     * @return data
     */
    public int getAction() {
        return action;
    }
    /**
     * setter
     * @param action data
     */
    public void setAction(int action) {
        this.action = action;
    }
    /**
     *  getter
     * @return data
     */
    public String getD() {
        return d;
    }

    /**
     * setter
     * @param d data
     */
    public void setD(String d) {
        this.d = d;
    }
}
