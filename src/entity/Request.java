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

    public Request(String method, String description, String ParameterValue) {
        this.method = method;
        this.description = description;
        this.ParameterValue = ParameterValue;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParameterValue() {
        return ParameterValue;
    }

    public void setParameterValue(String ParameterValue) {
        this.ParameterValue = ParameterValue;
    }
}
