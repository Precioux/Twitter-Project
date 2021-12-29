package entity;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Response {
    public boolean validity=false;
    public String error="No Error Found";
    public int Count;
    public ArrayList<String> Results=new ArrayList<>();
    private void setCount(){
        Count=Results.size();
    }
    public void addResult(String result)
    {
        Results.add(result);
        setCount();
    }
    public void addError(Error error)
    {
        this.error="";
        Gson gson=new Gson();
        this.error+=gson.toJson(error);
    }
    public void setTik()
    {
        validity=true;
    }

}
