package entity;

import com.google.gson.Gson;

import java.util.ArrayList;
/**
 * This class defines response
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.7.2021
 * */
public class Response {
    public boolean validity=false;
    public String error="No Error Found";
    public int Count;
    public ArrayList<String> Results=new ArrayList<>();

    /**
     * set count
     */
    private void setCount(){
        Count=Results.size();
    }

    /**
     * add result
     * @param result data
     */
    public void addResult(String result)
    {
        Results.add(result);
        setCount();
    }
    /**
     * add result
     * @param error data
     */
    public void addError(Error error)
    {
        this.error="";
        Gson gson=new Gson();
        this.error+=gson.toJson(error);
    }

    /**
     * set tik
     */
    public void setTik()
    {
        validity=true;
    }

}
