package Services.impl;
import Services.ObserverService;
import Tools.ObserverTool;
import com.google.gson.Gson;
import entity.Account;
import requestsFormats.ForServices;
import resultFormats.Result;
/**
 * This class defines Observer Service Imp
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.28.2021
 * */
public class ObserverServiceImp implements ObserverService {
    Account account=new Account();
    int next=0;
    String profileShortCut="";

    /**
     * add account
     * @param account data
     */
    public void addAccount(Account account) {
        this.account=account;
    }
    /**
     *
     * @return reflex
     */
    public int begin(String jdata)
    {
        Gson gson=new Gson();
        ForServices forServices=gson.fromJson(jdata,ForServices.class);
        ObserverTool observerTool = new ObserverTool();
        observerTool.addAccount(account);
        int choice = forServices.choice;
        switch (choice)
        {
            case 1:
            {
                int rslt=observerTool.follow(forServices.data);
                changeNext(rslt);
                break;
            }
            case 2:
            {
                int rslt=observerTool.unfollow(forServices.data);
                changeNext(rslt);
                break;
            }
            case 3:
            {
                Result result =gson.fromJson(observerTool.profile(forServices.data),Result.class);
                    if(result.type==0)
                    {
                        String p=result.data;
                        System.out.println("p");
                        recieveProfile(p);
                    }
                changeNext(result.type);
                break;
            }
            case 4:
            {
             int rslt=observerTool.profileForAction(forServices.data);
             changeNext(rslt);
                break;
            }
            case 5:
            {
               int rslt= observerTool.profileForAction(observerTool.reactions(forServices.data));
               changeNext(rslt);
               break;
            }
        }
        return next;
    }

    /**
     * set profile
     * @param p data
     */
    public void recieveProfile(String p)
    {
        this.profileShortCut="";
        this.profileShortCut+=p;
    }

    /**
     * send profile
     * @return data
     */
    public String sendProfile()
    {
        return profileShortCut;
    }

    /**
     * change next
     * @param next next
     */
    @Override
    public void changeNext(int next) {
        this.next=next;
    }


}
