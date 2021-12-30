package requestsFormats;
/**
 * AP-Project-Phase 3
 * @author Samin Mahdipour
 * @since 12.22.2021
 * @version 3.0
 * This class defines for services
 */
public class ForServices {
    public int choice;
    public String data;

    /**
     * constructor
     * @param choice data
     * @param data data
     */
    public ForServices(int choice, String data) {
        this.choice = choice;
        this.data = data;
    }

    /**
     * getter
     * @return data
     */
    public int getChoice() {
        return choice;
    }

    /**
     * setter
     * @param choice data
     */
    public void setChoice(int choice) {
        this.choice = choice;
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
