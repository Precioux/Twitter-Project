package resultFormats;

/**
 * this class defines result
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.30.2021
 */
public class Result {
    public int type;
    public String data;

    /**
     * constructor
     * @param type data
     * @param data data
     */
    public Result(int type, String data) {
        this.type = type;
        this.data = data;
    }

    /**
     * getter
     * @return data
     */
    public int getType() {
        return type;
    }

    /**
     * setter
     * @param type  data
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * getter
     * @return data
     */
    public String getData() {
        return data;
    }

    /**
     * setter
     * @param data data
     */
    public void setData(String data) {
        this.data = data;
    }
}
