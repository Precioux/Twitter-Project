package entity;

/**
 * This class defines Request
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.28.2021
 * */
public class Request {
    public String method;
    public String description;
    public String ParameterValue;

    /**
     * constructor
     * @param method data
     * @param description data
     * @param ParameterValue data
     */
    public Request(String method, String description, String ParameterValue) {
        this.method = method;
        this.description = description;
        this.ParameterValue = ParameterValue;
    }

    /**
     * getter
     * @return data
     */
    public String getMethod() {
        return method;
    }
    /**
     * setter
     * @param method data
     */
    public void setMethod(String method) {
        this.method = method;
    }
    /**
     * getter
     * @return data
     */
    public String getDescription() {
        return description;
    }
    /**
     * setter
     * @param description data
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * getter
     * @return data
     */
    public String getParameterValue() {
        return ParameterValue;
    }

    /**
     * setter
     * @param ParameterValue data
     */
    public void setParameterValue(String ParameterValue) {
        this.ParameterValue = ParameterValue;
    }
}
